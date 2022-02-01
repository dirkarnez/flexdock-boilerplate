/*
 * Created on Mar 4, 2005
 package org.flexdock.demos.view;
 */
package myview

import javax.swing.JFrame
import org.flexdock.docking.DockingConstants
import javax.swing.JPanel
import java.awt.BorderLayout
import javax.swing.border.EmptyBorder
import org.flexdock.view.Viewport
import javax.swing.border.LineBorder
import java.awt.Color
import javax.swing.JTextField
import java.awt.Dimension
import kotlin.jvm.JvmStatic
import org.flexdock.util.SwingUtility
import java.lang.Runnable
import myview.ViewDemo
import org.flexdock.docking.DockingManager
import org.flexdock.view.View
import java.awt.EventQueue

/**
 * @author Christopher Butler
 */
class ViewDemo : JFrame("Viewport Demo"), DockingConstants {
    init {
        contentPane = createContentPane()
    }

    private fun createContentPane(): JPanel {
        val p = JPanel(BorderLayout(0, 0))
        p.border = EmptyBorder(5, 5, 5, 5)
        val viewport = Viewport()
        p.add(viewport, BorderLayout.CENTER)
        val startPage = createStartPage()
        val view1 = createView("solution.explorer", "Solution Explorer")
        val view2 = createView("task.list", "Task List")
        val view3 = createView("class.view", "Class View")
        val view4 = createView("message.log", "Message Log")
        viewport.dock(startPage)
        startPage.dock(view1, DockingConstants.WEST_REGION, .3f)
        startPage.dock(view2, DockingConstants.SOUTH_REGION, .3f)
        startPage.dock(view4, DockingConstants.EAST_REGION, .3f)
        view1.dock(view3, DockingConstants.SOUTH_REGION, .3f)
        return p
    }

    private fun createView(id: String, text: String): View {
        val view = View(id, text)
        view.addAction(DockingConstants.CLOSE_ACTION)
        view.addAction(DockingConstants.PIN_ACTION)
        val p = JPanel()
        //		p.setBackground(Color.WHITE);
        p.border = LineBorder(Color.GRAY, 1)
        val t = JTextField(text)
        t.preferredSize = Dimension(100, 20)
        p.add(t)
        view.contentPane = p
        return view
    }

    private fun createStartPage(): View {
        val id = "startPage"
        val view = View(id, null, null)
        view.setTerritoryBlocked(DockingConstants.CENTER_REGION, true)
        view.titlebar = null
        view.contentPane = VSNetStartPage()
        return view
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SwingUtility.setPlaf("com.sun.java.swing.plaf.windows.WindowsLookAndFeel")
            //		SwingUtility.setPlaf("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            EventQueue.invokeLater { startup() }
        }

        private fun startup() {
            // turn on floating support
            DockingManager.setFloatingEnabled(true)
            val f: JFrame = ViewDemo()
            val demo = MenuLookDemo()
            f.jMenuBar = demo.createMenuBar()
            f.setSize(800, 600)
            SwingUtility.centerOnScreen(f)
            DemoUtility.setCloseOperation(f)
            f.isVisible = true
        }
    }
}