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
import com.smilin_dominator.devify.backend.verify;
import com.smilin_dominator.devify.backend.resources;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Objects;

public class Verify extends JFrame implements ActionListener {

    private final verify VerifyClass = new verify();
    private final hash HashClass = new hash();
    private final resources ResourceClass = new resources();

    private final JDialog fileSelectionDialog = new JDialog();
    private final JFileChooser fileChooser = new JFileChooser();
    private final JTextField pathToChecksum = new JTextField("Paste Or Browse");

    private final DefaultMutableTreeNode verifyRoot = new DefaultMutableTreeNode("Files");
    private final JTree verifyTree = new JTree(verifyRoot);

    /**
     * This recurses through the elements in the JTree and expands them
     * @param maximum The amount of rows
     * @param count The current iteration
     */
    private void extendElements(int maximum, int count) {
        if (count != maximum) {
            verifyTree.expandRow(count);
            extendElements(maximum, count + 1);
        }
    }

    /**
     * This creates TreeNodes for each file, containing it's status
     * @param checksumFileName Path to the checksum file
     */
    private void CheckFiles(String checksumFileName) {

        verifyTree.setVisible(false);

	    verifyRoot.removeAllChildren();
        HashMap<String, String> FileHashMap = VerifyClass.getFiles(checksumFileName);

        for (String file : FileHashMap.keySet()) {

            // -------------------------------- Data --------------------------- //
            
            String newHash = HashClass.file(file);
            String lastHash = FileHashMap.get(file);
            DefaultMutableTreeNode name = new DefaultMutableTreeNode(file);
            DefaultMutableTreeNode equal = new DefaultMutableTreeNode(String.format("Equal\t\t: %s", Objects.equals(newHash, lastHash)));
            DefaultMutableTreeNode previousHash = new DefaultMutableTreeNode(String.format("Last Hash\t\t: %s", lastHash));
            DefaultMutableTreeNode currentHash = new DefaultMutableTreeNode(String.format("New Hash\t\t: %s", newHash));

            name.add(previousHash);
            name.add(currentHash);
            name.add(equal);

            verifyRoot.add(name);
	    
        }

        extendElements(verifyTree.getRowCount(), 0);

        verifyTree.revalidate();
        verifyTree.setVisible(true);

    }

    public Verify() {

        // Main Options
        setSize(600,800);
        setTitle("Devify - Verifying!");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main Panel
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        // Selection Panel
        JPanel selection = new JPanel(new BorderLayout());

        JButton selectButton = new JButton("Select File");
        selectButton.addActionListener(this);

        JButton confirmVerification = new JButton("Verify");
        confirmVerification.addActionListener(this);

        // File Choosing Dialog
        fileChooser.addActionListener(this);

        fileSelectionDialog.setVisible(false);
        fileSelectionDialog.setSize(400, 600);
        fileSelectionDialog.add(fileChooser);

        // Final
        JPanel verifyPanel = new JPanel();
        verifyTree.setVisible(false);
        verifyPanel.add(verifyTree);

        selection.add(pathToChecksum, BorderLayout.CENTER);
        selection.add(selectButton, BorderLayout.AFTER_LAST_LINE);
        selection.add(confirmVerification, BorderLayout.AFTER_LINE_ENDS);

        main.add(selection, BorderLayout.PAGE_START);
        main.add(new JLabel(ResourceClass.image("logos", "DevifyLogo1.png")), BorderLayout.CENTER);
        main.add(verifyPanel, BorderLayout.PAGE_END);

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

            case "CancelSelection" -> {
                verifyRoot.removeAllChildren();
                verifyTree.revalidate();
                verifyTree.setVisible(false);
                fileSelectionDialog.setVisible(false);
            }

        }
    }

}
