package com.samkortekaas.norminette.settings

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import java.io.File

/**
 * Holds and persists the path to the Norminette executable,
 * and can auto-detect it on the user’s $PATH.
 */
@Service(Service.Level.APP)
class NorminetteSettingsService {
    private val props = PropertiesComponent.getInstance()
    private val PATH_KEY = "skrtks.norminette.settings.path"

    /** The stored path, or "" if unset. */
    var path: String
        get() = props.getValue(PATH_KEY, "")
        set(value) = props.setValue(PATH_KEY, value)

    /**
     * Scan $PATH (plus /usr/local/bin) for “norminette” and persist it.
     * Returns the discovered path (or "" if not found).
     */
    fun detect(): String {
        val searchPath = System.getenv("PATH") + ":/usr/local/bin"
        for (dir in searchPath.split(File.pathSeparator)) {
            val candidate = File(dir, "norminette")
            if (candidate.exists() && candidate.canExecute()) {
                path = candidate.absolutePath
                return path
            }
        }
        path = ""
        return ""
    }

    companion object {
        /** Retrieve the singleton service. */
        @JvmStatic
        fun getInstance(): NorminetteSettingsService = service()
    }
}
