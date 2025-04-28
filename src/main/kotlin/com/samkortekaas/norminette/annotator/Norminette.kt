package com.samkortekaas.norminette.annotator

import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.samkortekaas.norminette.MyBundle
import com.samkortekaas.norminette.settings.NorminetteSettingsService
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.util.concurrent.TimeUnit

/**
 * Runs the external Norminette CLI on a temp copy of the file
 * and parses its output into warnings.
 */
object Norminette {
    fun doAnnotate(document: Document): Array<NorminetteWarning> {
        val settings = NorminetteSettingsService.getInstance()
        val normPath = settings.path.ifEmpty { settings.detect() }
        if (!isValidNorminettePath(normPath)) return emptyArray()

        val vfile = FileDocumentManager.getInstance().getFile(document) ?: return emptyArray()
        val tmpDir = Files.createTempDirectory("norminette")
        val tmpFile = File(tmpDir.toFile(), vfile.name)
        tmpFile.writeText(document.text)
        tmpFile.deleteOnExit()

        val output = "$normPath ${tmpFile.toPath()}".runCommand()
        tmpFile.delete()

        return parse(output)
    }

    private fun isValidNorminettePath(path: String): Boolean {
        val f = File(path)
        return f.exists() && f.canExecute()
    }

    private fun parse(res: String?): Array<NorminetteWarning> {
        val errors = res?.lines() ?: return emptyArray()
        return errors.mapNotNull {
            if (it.startsWith("Error: ")) parseError(it) else null
        }.toTypedArray()
    }

    private fun parseError(line: String): NorminetteWarning? {
        val parts = line.split("\\s+".toRegex()).filter { it.isNotEmpty() }
        val code = parts.getOrNull(1) ?: return null
        val lineNum = parts.getOrNull(parts.indexOf("(line:") + 1)
            ?.filter { it.isDigit() }
            ?.toIntOrNull() ?: return null
        val colNum = parts.getOrNull(parts.indexOf("col:") + 1)
            ?.filter { it.isDigit() }
            ?.toIntOrNull() ?: return null

        return MyBundle.containsKey(code).takeIf { it }?.let {
            NorminetteWarning(lineNum, colNum, MyBundle.message(code))
        }
    }

    private fun String.runCommand(): String? = try {
        val cmd = this.split("\\s+".toRegex())
        val proc = ProcessBuilder(*cmd.toTypedArray())
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()
        proc.waitFor(60, TimeUnit.MINUTES)
        proc.inputStream.bufferedReader().readText()
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}
