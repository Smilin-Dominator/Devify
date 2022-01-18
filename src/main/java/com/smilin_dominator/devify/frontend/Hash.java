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

public class Hash extends JFrame implements ActionListener {

    private final JTextField filePath;
    private final JFileChooser fileChooser;
    private final JDialog fileChoosingDialog;

    public Hash() {

        // Main Options
        setSize(485,800);
        setTitle("Devify - Hashing!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Container
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        // The File Selection
        JPanel FileContainer = new JPanel();
        FileContainer.setLayout(new GridLayout(1, 2));
        filePath = new JTextField("Paste The Path Here Or Click The Button On The Right!");

        JButton fileChoosingButton = new JButton("Select A File");
        fileChoosingButton.addActionListener(this);

        fileChoosingDialog = new JDialog();
        fileChoosingDialog.setSize(400, 600);
        fileChoosingDialog.setLayout(new GridLayout(1, 1));
        fileChoosingDialog.setVisible(false);

        fileChooser = new JFileChooser();
        fileChooser.addActionListener(this);

        // Final Stage
        fileChoosingDialog.add(fileChooser);
        FileContainer.add(filePath);
        FileContainer.add(fileChoosingButton);
        main.add(FileContainer);
        this.add(main);

    }

    public void run() {
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String com = e.getActionCommand();
        switch (com) {
            case "Select A File":
                fileChoosingDialog.setVisible(true);
                break;
        }
    }

}
