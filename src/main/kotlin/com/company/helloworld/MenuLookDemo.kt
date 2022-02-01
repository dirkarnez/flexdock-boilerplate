/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
import javax.swing.JPanel
import java.awt.BorderLayout
import javax.swing.Icon
import java.awt.Image
import java.awt.Color
import java.awt.Font
import javax.swing.JButton
import javax.swing.JTabbedPane
import javax.swing.border.LineBorder
import java.awt.Dimension
import java.awt.Rectangle

/* MenuLookDemo.java requires images/middle.gif. */ /*
 * This class exists solely to show you what menus look like.
 * It has no menu-related event handling.
 */
class MenuLookDemo {
    var output: JTextArea? = null
    var scrollPane: JScrollPane? = null
    fun createMenuBar(): JMenuBar {
        val menuBar: JMenuBar
        var menu: JMenu
        val submenu: JMenu
        var menuItem: JMenuItem
        var rbMenuItem: JRadioButtonMenuItem
        var cbMenuItem: JCheckBoxMenuItem

        //Create the menu bar.
        menuBar = JMenuBar()

        //Build the first menu.
        menu = JMenu("A Menu")
        menu.mnemonic = KeyEvent.VK_A
        menu.accessibleContext.accessibleDescription = "The only menu in this program that has menu items"
        menuBar.add(menu)

        //a group of JMenuItems
        menuItem = JMenuItem("A text-only menu item",
                KeyEvent.VK_T)
        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItem.accelerator = KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK)
        menuItem.accessibleContext.accessibleDescription = "This doesn't really do anything"
        menu.add(menuItem)
        val icon = ResourceManager.createIcon("images/middle.gif")
        //ImageIcon icon = createImageIcon("middle.gif");
        menuItem = JMenuItem("Both text and icon", icon)
        menuItem.mnemonic = KeyEvent.VK_B
        menu.add(menuItem)
        menuItem = JMenuItem(icon)
        menuItem.mnemonic = KeyEvent.VK_D
        menu.add(menuItem)

        //a group of radio button menu items
        menu.addSeparator()
        val group = ButtonGroup()
        rbMenuItem = JRadioButtonMenuItem("A radio button menu item")
        rbMenuItem.isSelected = true
        rbMenuItem.mnemonic = KeyEvent.VK_R
        group.add(rbMenuItem)
        menu.add(rbMenuItem)
        rbMenuItem = JRadioButtonMenuItem("Another one")
        rbMenuItem.mnemonic = KeyEvent.VK_O
        group.add(rbMenuItem)
        menu.add(rbMenuItem)

        //a group of check box menu items
        menu.addSeparator()
        cbMenuItem = JCheckBoxMenuItem("A check box menu item")
        cbMenuItem.mnemonic = KeyEvent.VK_C
        menu.add(cbMenuItem)
        cbMenuItem = JCheckBoxMenuItem("Another one")
        cbMenuItem.mnemonic = KeyEvent.VK_H
        menu.add(cbMenuItem)

        //a submenu
        menu.addSeparator()
        submenu = JMenu("A submenu")
        submenu.mnemonic = KeyEvent.VK_S
        menuItem = JMenuItem("An item in the submenu")
        menuItem.accelerator = KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK)
        submenu.add(menuItem)
        menuItem = JMenuItem("Another item")
        submenu.add(menuItem)
        menu.add(submenu)

        //Build second menu in the menu bar.
        menu = JMenu("Another Menu")
        menu.mnemonic = KeyEvent.VK_N
        menu.accessibleContext.accessibleDescription = "This menu does nothing"
        menuBar.add(menu)
        return menuBar
    }

    companion object {
        /** Returns an ImageIcon, or null if the path was invalid.  */
        protected fun createImageIcon(path: String): ImageIcon? {
            val imgURL = MenuLookDemo::class.java.getResource(path)
            return if (imgURL != null) {
                ImageIcon(imgURL)
            } else {
                System.err.println("Couldn't find file: $path")
                null
            }
        }
    }
}