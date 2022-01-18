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
import java.util.HashMap;
import java.util.Objects;

import com.smilin_dominator.devify.backend.verify;
import com.smilin_dominator.devify.backend.hash;

public class Verify extends JFrame implements ActionListener {

    private final verify VerifyClass = new verify();
    private final hash HashClass = new hash();

    private final JDialog fileSelectionDialog = new JDialog();
    private final JFileChooser fileChooser = new JFileChooser();
    private final JTextField pathToChecksum = new JTextField("Paste Or Browse");
    private final JTextField status = new JTextField();

    private final JPanel verifyWithChecksumFile = new JPanel();

    private void CheckFiles(String path, String checksumFileName) {

        verifyWithChecksumFile.removeAll();
        verifyWithChecksumFile.revalidate();

        HashMap<String, String> FileHashMap = VerifyClass.getFiles(path, checksumFileName, status);
        for (String file : FileHashMap.keySet()) {

            JPanel row = new JPanel();
            row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

            String newHash = HashClass.file(file);
            String lastHash = FileHashMap.get(file);

            JLabel currentFile = new JLabel(file);
            JLabel previousHash = new JLabel(lastHash);
            JLabel currentHash = new JLabel(newHash);
            JLabel sameFile = new JLabel(String.valueOf(Objects.equals(newHash, lastHash)));

            row.add(currentFile);
            row.add(previousHash);
            row.add(currentHash);
            row.add(sameFile);

            verifyWithChecksumFile.add(row);

        }

        verifyWithChecksumFile.repaint();
        verifyWithChecksumFile.setVisible(true);

    }

    public Verify() {

        // Main Options
        setSize(485,800);
        setTitle("Devify - Verifying!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        verifyWithChecksumFile.setVisible(false);

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
        main.add(verifyWithChecksumFile);
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
                CheckFiles(path, pathToChecksum.getText());
            }

            case "CancelSelection" -> {
                fileSelectionDialog.setVisible(false);
            }

        }
    }

}
