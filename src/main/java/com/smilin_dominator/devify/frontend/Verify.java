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

    private final JPanel verifyWithChecksumFile = new JPanel();

    private void CheckFiles(String checksumFileName) {

        verifyWithChecksumFile.removeAll();
        verifyWithChecksumFile.revalidate();
        HashMap<String, String> FileHashMap = VerifyClass.getFiles(checksumFileName);

        int rowCount = FileHashMap.size() + 1;
        GridLayout rowLayout = new GridLayout(rowCount, 4);
        verifyWithChecksumFile.setLayout(rowLayout);

        JLabel currentFileShow = new JLabel("Current File");
        currentFileShow.setOpaque(true);
        currentFileShow.setBackground(Color.LIGHT_GRAY);

        JLabel previousHashShow = new JLabel("Hash In Checksum");
        previousHashShow.setOpaque(true);
        previousHashShow.setBackground(Color.white);

        JLabel currentHashShow = new JLabel("New Hash");
        currentHashShow.setOpaque(true);
        currentHashShow.setBackground(Color.LIGHT_GRAY);

        JLabel sameFileShow = new JLabel("Same?");
        sameFileShow.setOpaque(true);
        sameFileShow.setBackground(Color.white);

        verifyWithChecksumFile.add(currentFileShow);
        verifyWithChecksumFile.add(previousHashShow);
        verifyWithChecksumFile.add(currentHashShow);
        verifyWithChecksumFile.add(sameFileShow);

        for (String file : FileHashMap.keySet()) {

            String newHash = HashClass.file(file);
            String lastHash = FileHashMap.get(file);

            JLabel currentFile = new JLabel(file);
            currentFile.setOpaque(true);
            currentFile.setBackground(Color.LIGHT_GRAY);

            JLabel previousHash = new JLabel(lastHash);
            previousHash.setOpaque(true);
            previousHash.setBackground(Color.white);

            JLabel currentHash = new JLabel(newHash);
            currentHash.setOpaque(true);
            currentHash.setBackground(Color.LIGHT_GRAY);

            JLabel sameFile = new JLabel(String.valueOf(Objects.equals(newHash, lastHash)));
            sameFile.setOpaque(true);
            sameFile.setBackground(Color.white);

            verifyWithChecksumFile.add(currentFile);
            verifyWithChecksumFile.add(previousHash);
            verifyWithChecksumFile.add(currentHash);
            verifyWithChecksumFile.add(sameFile);

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
        JButton confirmVerification = new JButton("Verify");
        confirmVerification.addActionListener(this);
        fileChooser.addActionListener(this);

        fileSelectionDialog.setVisible(false);
        fileSelectionDialog.setSize(400, 600);
        fileSelectionDialog.add(fileChooser);

        // Final
        selection.add(pathToChecksum);
        selection.add(selectButton);
        main.add(selection);
        main.add(confirmVerification);
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

            // sent by confirmVerification
            case "Verify" -> CheckFiles(path);

            // Sent by selectButton
            case "Select File" -> fileSelectionDialog.setVisible(true);

            // Sent by fileSelectionDialog
            case "ApproveSelection" -> {
                pathToChecksum.setText(fileChooser.getSelectedFile().getAbsolutePath());
                fileSelectionDialog.setVisible(false);
                CheckFiles(pathToChecksum.getText());
            }

            case "CancelSelection" -> fileSelectionDialog.setVisible(false);

        }
    }

}
