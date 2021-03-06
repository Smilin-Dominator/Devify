// Hash.java -> This is the frontend for Hashing
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

import com.smilin_dominator.devify.backend.resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hash extends JFrame implements ActionListener {

    private final JTextField filePath;
    private final JFileChooser fileChooser;
    private final JDialog fileChoosingDialog;
    private final JLabel statusBar;
    private final JTextField checksumFileName;
    private final JPanel buttonContainer;
    private final JComboBox<String> algorithmChooser = new JComboBox<>(new String[]{"SHA256", "SHA512", "MD5"});

    /** Concurrent Wrapper of the Hash Class */
    private final Operations.Hash ops;

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

        statusBar = new JLabel("I'm The Status Bar!", JLabel.CENTER);

        checksumFileName = new JTextField("checksums.txt");
        checksumFileName.setHorizontalAlignment(JTextField.CENTER);

        buttonContainer = new JPanel();
        buttonContainer.setLayout(new GridLayout(1, 2));

        JButton showHash = new JButton("Calculate The Hash");
        showHash.addActionListener(this);

        JButton generateChecksums = new JButton("Write The Checksum");
        generateChecksums.addActionListener(this);

        JPanel userOptions = new JPanel(new GridLayout(2, 1));
        userOptions.add(checksumFileName);
        userOptions.add(algorithmChooser);

        // The Hidden File Selection Dialog
        fileChoosingDialog = new JDialog();
        fileChoosingDialog.setSize(400, 600);
        fileChoosingDialog.setLayout(new GridLayout(1, 1));
        fileChoosingDialog.setVisible(false);

        fileChooser = new JFileChooser();
        fileChooser.addActionListener(this);

        // Final Stage
        ops = new Operations.Hash(statusBar);

        fileChoosingDialog.add(fileChooser);

        buttonContainer.add(showHash);
        buttonContainer.add(generateChecksums);

        mainFunctions.add(statusBar, BorderLayout.PAGE_START);
        mainFunctions.add(buttonContainer, BorderLayout.CENTER);
        mainFunctions.add(userOptions, BorderLayout.PAGE_END);

        FileContainer.add(filePath, BorderLayout.CENTER);
        FileContainer.add(usePath, BorderLayout.AFTER_LAST_LINE);
        FileContainer.add(fileChoosingButton, BorderLayout.LINE_END);

        main.add(FileContainer, BorderLayout.PAGE_START);
        resources resourceClass = new resources();
        main.add(new JLabel(resourceClass.image("logos", "DevifyLogo1.png"), JLabel.CENTER));
        main.add(mainFunctions, BorderLayout.PAGE_END);

        this.add(main);

    }

    /** Makes all the elements in the Main Functions window invisible and makes the JFrame Visible */
    public void run() {
        mainFunctionsVisible(false);
        setVisible(true);
    }

    /** This resets the state */
    public void resetState() {
        fileChoosingDialog.setVisible(false);
        mainFunctionsVisible(false);
        statusBar.setText("I'm The Status Bar!");
        filePath.setText("Paste The Path Here Or Click The Button On The Right!");
    }

    /**
     * Accepts a boolean value and makes the main functions visible or invisible
     * @param condition A Boolean Value
     */
    private void mainFunctionsVisible(boolean condition) {
        algorithmChooser.setVisible(condition);
        statusBar.setVisible(condition);
        buttonContainer.setVisible(condition);
        checksumFileName.setVisible(condition);
    }

    public void actionPerformed(ActionEvent e) {
        String com = e.getActionCommand();
        String algorithm = (String) algorithmChooser.getSelectedItem();
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
            case "Calculate The Hash" -> ops.display_hash(path, algorithm);

            case "Write The Checksum" -> {
                ops.write_checksum(path, checksumFile, algorithm);
                fileChooser.rescanCurrentDirectory();
            }

        }
    }

}
