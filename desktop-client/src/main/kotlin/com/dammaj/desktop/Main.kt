package com.dammaj.desktop

import com.dammaj.core.util.Logger
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.WindowConstants
import java.awt.BorderLayout
import java.awt.Dimension

fun main() {
    Logger.log("DesktopClient", "Starting Dammaj Desktop Client")
    SwingUtilities.invokeLater {
        createAndShowGUI()
    }
}

private fun createAndShowGUI() {
    val frame = JFrame("Dammaj Agent - Desktop Client")
    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    frame.minimumSize = Dimension(600, 400)

    val panel = JPanel(BorderLayout())

    val label = JLabel("Welcome to Dammaj Agent Desktop Client", JLabel.CENTER)
    panel.add(label, BorderLayout.CENTER)

    val button = JButton("Start Agent")
    button.addActionListener {
        Logger.log("DesktopClient", "Agent started")
        label.text = "Agent is running..."
    }
    panel.add(button, BorderLayout.SOUTH)

    frame.contentPane = panel
    frame.pack()
    frame.setLocationRelativeTo(null)
    frame.isVisible = true
}
