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

import com.smilin_dominator.devify.backend.hash;
import com.smilin_dominator.devify.backend.resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hash extends JFrame implements ActionListener {

    private final JTextField filePath;
    private final JFileChooser fileChooser;
    private final JDialog fileChoosingDialog;
    private final JTextField statusBar;
    private final JTextField checksumFileName;
    private final JPanel buttonContainer;

    private final hash HashClass = new hash();

    public Hash() {

        // Main Options
        setSize(800,1000);
        setTitle("Devify - Hashing!");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main Container
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        // The File Selection Container
        JPanel FileContainer = new JPanel();
        FileContainer.setLayout(new BorderLayout());
        filePath = new JTextField("Paste The Path Here Or Click The Button On The Right!");

        JButton fileChoosingButton = new JButton("Select A File");
        fileChoosingButton.addActionListener(this);

        JButton usePath = new JButton("Submit");
        usePath.addActionListener(this);

        // The Main Functions
        JPanel mainFunctions = new JPanel();
        mainFunctions.setLayout(new BorderLayout());
        mainFunctions.setVisible(true);

        statusBar = new JTextField("I'm The Status Bar!");
        checksumFileName = new JTextField("sha256.txt");

        buttonContainer = new JPanel();
        buttonContainer.setLayout(new GridLayout(1, 2));

        JButton showHash = new JButton("Calculate The Hash");
        showHash.addActionListener(this);

        JButton generateChecksums = new JButton("Write The Checksum");
        generateChecksums.addActionListener(this);

        // The Hidden File Selection Dialog
        fileChoosingDialog = new JDialog();
        fileChoosingDialog.setSize(400, 600);
        fileChoosingDialog.setLayout(new GridLayout(1, 1));
        fileChoosingDialog.setVisible(false);

        fileChooser = new JFileChooser();
        fileChooser.addActionListener(this);

        // Final Stage
        fileChoosingDialog.add(fileChooser);

        buttonContainer.add(showHash);
        buttonContainer.add(generateChecksums);

        mainFunctions.add(statusBar, BorderLayout.NORTH);
        mainFunctions.add(buttonContainer, BorderLayout.CENTER);
        mainFunctions.add(checksumFileName, BorderLayout.PAGE_END);

        FileContainer.add(filePath, BorderLayout.CENTER);
        FileContainer.add(usePath, BorderLayout.LINE_END);
        FileContainer.add(fileChoosingButton, BorderLayout.AFTER_LAST_LINE);

        main.add(FileContainer, BorderLayout.PAGE_START);
        resources resourceClass = new resources();
        main.add(new JLabel(resourceClass.image("logos", "DevifyLogo1.png")));
        main.add(mainFunctions, BorderLayout.PAGE_END);

        this.add(main);

    }

    public void run() {
        mainFunctionsVisible(false);
        setVisible(true);
    }

    private void mainFunctionsVisible(boolean condition) {
        statusBar.setVisible(condition);
        buttonContainer.setVisible(condition);
        checksumFileName.setVisible(condition);
    }

    public void actionPerformed(ActionEvent e) {
        String com = e.getActionCommand();
        String path = filePath.getText();
        String checksumFile = checksumFileName.getText();
        switch (com) {

            // Sent by fileChoosingButton
            case "Select A File" -> {
                fileChoosingDialog.setVisible(true);
                mainFunctionsVisible(false);
            }

            // Sent by usePath
            case "Submit" -> mainFunctionsVisible(true);


            // Sent by fileChooser
            case "ApproveSelection" -> {
                filePath.setText(fileChooser.getSelectedFile().getAbsolutePath());
                fileChoosingDialog.setVisible(false);
                mainFunctionsVisible(true);
            }

            case "CancelSelection" -> {
                fileChoosingDialog.setVisible(false);
                mainFunctionsVisible(false);
            }

            // Sent by mainFunctions
            case "Calculate The Hash" -> {
                String hash = HashClass.file(path);
                statusBar.setText(hash);
            }

            case "Write The Checksum" -> {
                String hash = HashClass.file(path);
                HashClass.checksum(hash, path, checksumFile, statusBar);
                fileChooser.rescanCurrentDirectory();
            }

        }
    }

}
