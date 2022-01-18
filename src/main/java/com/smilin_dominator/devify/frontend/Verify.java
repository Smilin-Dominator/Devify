/*
    Devify
    Copyright (C) 2021 Devisha Padmaperuma

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.smilin_dominator.devify.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Verify extends JFrame implements ActionListener {

    private final JDialog fileSelectionDialog = new JDialog();
    private final JFileChooser fileChooser = new JFileChooser();
    private final JTextField pathToChecksum = new JTextField("Paste Or Browse");

    public Verify() {

        // Main Options
        setSize(485,800);
        setTitle("Devify - Verifying!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Panel
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        // Selection Panel
        JPanel selection = new JPanel();
        selection.setLayout(new GridLayout(1, 2));
        JButton selectButton = new JButton("Select File");
        selectButton.addActionListener(this);

        // File Choosing Dialog
        fileChooser.addActionListener(this);

        fileSelectionDialog.setVisible(false);
        fileSelectionDialog.setSize(400, 600);
        fileSelectionDialog.add(fileChooser);

        // Final
        selection.add(pathToChecksum);
        selection.add(selectButton);
        main.add(selection);
        this.add(main);

    }

    public void run() {
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String com = e.getActionCommand();
        String path = pathToChecksum.getText();
        switch (com) {

            // Sent by selectButton
            case "Select File" -> {
                fileSelectionDialog.setVisible(true);
            }

            // Sent by fileSelectionDialog
            case "ApproveSelection" -> {
                pathToChecksum.setText(fileChooser.getSelectedFile().getAbsolutePath());
                fileSelectionDialog.setVisible(false);
            }

            case "CancelSelection" -> {
                fileSelectionDialog.setVisible(false);
            }

        }
    }

}
