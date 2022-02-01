/*
 * Created on Apr 15, 2005
 */
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
import org.flexdock.util.ResourceManager
import java.awt.*
import javax.swing.JPanel
import javax.swing.Icon
import javax.swing.JButton
import javax.swing.JTabbedPane
import javax.swing.border.LineBorder

/**
 * @author Christopher Butler
 */
class VSNetStartPage : JPanel(BorderLayout(0, 0)) {
    private var miscIcons: Icon? = null
    private var tabsImg: Image? = null
    private var tabRunBG: Color? = null
    private var contentBG1: Color? = null
    private var contentBG2: Color? = null
    private var tableBG1: Color? = null
    private var labelFont: Font? = null
    var newProjButton: JButton? = null
        private set
    var openProjButton: JButton? = null
        private set
    private var tablePane: JPanel? = null
    private var contentPane: JPanel? = null
    private var tabbedPane: JTabbedPane? = null

    init {
        init()
    }

    private fun init() {
        initAttribs()
        newProjButton = JButton("New Project")
        openProjButton = JButton("Open Project")
        tablePane = createTablePane()
        contentPane = createContentPane()
        contentPane!!.add(tablePane)
        contentPane!!.add(newProjButton)
        contentPane!!.add(openProjButton)
        tabbedPane = createTabbedPane()
        tabbedPane!!.addTab("Start Page", contentPane)
        tabbedPane!!.border = null
        add(tabbedPane, BorderLayout.CENTER)
        border = LineBorder(Color.GRAY, 1)
    }

    private fun initAttribs() {
        miscIcons = ResourceManager.createIcon("images/ms_misc_icons001.png")
        tabsImg = ResourceManager.createImage("images/ms_tabs001.png")
        tabRunBG = Color(247, 243, 233)
        contentBG1 = Color(246, 246, 246)
        contentBG2 = Color(102, 153, 204)
        tableBG1 = Color(154, 154, 143)
        labelFont = Font("Dialog", Font.BOLD, 11)
    }

    private fun createTablePane(): JPanel {
        return object : JPanel() {
            override fun paintComponent(g: Graphics) {
                g.color = tableBG1
                g.fillRect(0, 0, width, 20)
                g.color = this@VSNetStartPage.background
                g.drawRect(0, 0, width - 1, height - 1)
                g.color = Color.BLACK
                g.font = labelFont
                g.drawString("Name", 5, 15)
                g.drawString("Modified", 350, 15)
            }
        }
    }

    private fun createContentPane(): JPanel {
        return object : JPanel(null) {
            override fun doLayout() {
                var tableH = height - 120 - 55
                tableH = Math.max(tableH, 25)
                tablePane!!.setBounds(12, 120, 475, tableH)
                val buttonY = 120 + tableH + 18
                val d = newProjButton!!.preferredSize
                newProjButton!!.setBounds(12, buttonY, d.width, d.height)
                openProjButton!!.setBounds(24 + d.width, buttonY, openProjButton!!.preferredSize.width, d.height)
            }

            override fun paintComponent(g: Graphics) {
                super.paintComponent(g)
                val w = width
                val origC = g.color
                val origF = g.font
                g.color = Color.WHITE
                g.fillRect(0, 0, w, height)
                g.color = contentBG1
                g.fillRect(0, 0, w, 48)
                g.color = contentBG2
                g.fillRect(0, 48, w, 23)
                g.drawImage(tabsImg, 0, 0, null, this)
                g.color = Color.BLACK
                g.font = labelFont
                g.drawString("Open an Existing Project", 12, 100)
                g.font = origF
                g.color = origC
            }
        }
    }

    private fun createTabbedPane(): JTabbedPane {
        return object : JTabbedPane(TOP) {
            override fun paintComponent(g: Graphics) {
                val orig = g.color
                val tabBounds = getBoundsAt(0)
                val tabLowerY = tabBounds.y + tabBounds.height
                g.color = tabRunBG
                g.fillRect(0, 0, width, tabLowerY)
                val iconX = width - miscIcons!!.iconWidth
                val iconY = tabLowerY / 2 - miscIcons!!.iconHeight / 2 + 1
                miscIcons!!.paintIcon(this, g, iconX, iconY)
                g.color = orig
                super.paintComponent(g)
                g.color = Color.WHITE
                g.drawRect(0, 0, width - 1, height - 1)
                g.drawRect(1, tabLowerY - 1, width - 3, height - tabLowerY - 1)
                g.color = orig
            }
        }
    }
}