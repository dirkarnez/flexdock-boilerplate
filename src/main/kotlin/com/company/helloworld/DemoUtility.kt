package myview

import javax.swing.JFrame
import java.io.PrintWriter
import javax.swing.JTextArea
import javax.swing.JScrollPane
import javax.swing.JOptionPane
import javax.swing.JMenuBar
import javax.swing.JMenu
import javax.swing.JMenuItem
import javax.swing.JRadioButtonMenuItem
import javax.swing.JCheckBoxMenuItem
import java.awt.event.KeyEvent
import javax.swing.KeyStroke
import java.awt.event.ActionEvent
import javax.swing.ImageIcon
import javax.swing.ButtonGroup
import myview.MenuLookDemo
import java.awt.*
import java.io.StringWriter
import java.lang.Boolean
import javax.swing.JPanel
import javax.swing.Icon
import javax.swing.JButton
import javax.swing.JTabbedPane
import javax.swing.border.LineBorder

object DemoUtility {
    fun setDemoDisableExitOnClose() {
        System.setProperty("disable.system.exit", "true")
    }

    fun setCloseOperation(f: JFrame) {
        if (!Boolean.getBoolean("disable.system.exit")) f.defaultCloseOperation = JFrame.EXIT_ON_CLOSE else f.defaultCloseOperation = JFrame.HIDE_ON_CLOSE
    }

    /**
     * Opens a JOptionPane with the error message and formatted stack trace of the throwable in a scrollable text area.
     *
     * @param c
     * optional argument for parent component to open modal error
     * dialog relative to
     * @param error_message
     * short string description of failure, must be non-null
     * @param t
     * the throwable that's being reported, must be non-null
     */
    fun showErrorDialog(c: Component?, error_message: String?,
                        t: Throwable) {
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        pw.println(error_message)
        pw.print("Exception is: ")
        t.printStackTrace(pw)
        pw.flush()
        val ta = JTextArea(sw.toString(), 15, 60)
        val sp = JScrollPane(ta)
        JOptionPane.showMessageDialog(c, sp, error_message,
                JOptionPane.ERROR_MESSAGE)
    }
}