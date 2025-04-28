// src/main/kotlin/com/samkortekaas/norminette/settings/NorminetteSettingsPanel.kt
package com.samkortekaas.norminette.settings

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener
import java.awt.FlowLayout

/**
 * The “Preferences → Norminette” panel.
 * All UI and service lookups happen inside createComponent/reset/apply.
 */
object NorminetteSettingsPanel : Configurable {
    private var modified = false

    private var panel: JPanel? = null
    private var textField: TextFieldWithBrowseButton? = null
    private var button: JButton? = null

    override fun getDisplayName(): String = "Norminette"

    override fun createComponent(): JComponent {
        val service = NorminetteSettingsService.getInstance()
        val initial = service.path

        panel = JPanel(FlowLayout(FlowLayout.LEFT, 5, 5))

        textField = TextFieldWithBrowseButton(JTextField(30)).apply {
            // safe to call FileChooserFactory here
            addBrowseFolderListener(
                "Norminette Executable",
                "Select the Norminette CLI executable",
                null,
                FileChooserDescriptorFactory.createSingleFileDescriptor()
            )
            text = initial
            // listen for edits
            this.textField.document.addDocumentListener(object : DocumentListener {
                override fun insertUpdate(e: DocumentEvent) { modified = true }
                override fun removeUpdate(e: DocumentEvent) { modified = true }
                override fun changedUpdate(e: DocumentEvent) { modified = true }
            })
        }

        button = JButton("Detect installation").apply {
            addActionListener {
                val found = service.detect()
                textField!!.text = if (found.isEmpty()) "No installation found" else found
                modified = true
            }
        }

        panel!!.add(JLabel("Norminette:"))
        panel!!.add(textField)
        panel!!.add(button)

        return panel!!
    }

    override fun isModified(): Boolean = modified

    override fun apply() {
        val service = NorminetteSettingsService.getInstance()
        service.path = textField!!.text
        modified = false
    }

    override fun reset() {
        val service = NorminetteSettingsService.getInstance()
        val current = service.path
        if (current.isEmpty()) {
            // auto-detect on blank
            val found = service.detect()
            textField!!.text = if (found.isEmpty()) "" else found
        } else {
            textField!!.text = current
        }
        modified = false
    }

    override fun disposeUIResources() {
        // allow GC
        panel = null
        textField = null
        button = null
    }
}
