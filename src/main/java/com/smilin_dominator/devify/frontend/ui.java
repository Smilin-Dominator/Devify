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
import com.smilin_dominator.devify.backend.verify;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;


/** This class is the main User Interface of the Program */
public class ui extends JFrame implements ActionListener {

    /*------------------  Colours ---------------------*/

    /** Used in the background of 'hashText' */
    private final Color sea_green_crayola = Color.decode("#00FFC5");

    /** Used when an item is selected in the FileChooser */
    private final Color celeste = Color.decode("#ADF5FF");

    /** Used for the button 'Generate Checksum' */
    private final Color orange_red_crayola = Color.decode("#FF5E5B");

    /** Used for the button 'Generate Hash' */
    private final Color corn = Color.decode("#FFED66");

    /** Used for the button 'Verify Checksum' */
    private final Color yellow_orange = Color.decode("#F4AC45");

    /*------------------- My Packages  ---------------*/

    /** The resources class (backend.resources) */
    private final resources Resources = new resources();

    /** The hashing class (backend.hash) */
    private final hash Hash = new hash();

    /** The verifying class (backend.verify) */
    private final verify Verify = new verify();

    /*------------------- JElements  ---------------*/

    /** The main file explorer */
    private final JFileChooser fileChooser;

    /** The main status bar (stored in fileActions) */
    private final JTextField hashText;

    /** The field where you specify the name of the checksum file */
    private final JTextField checksumFile;

    /** The file actions panel which is visible only when a file is selected */
    private final JPanel fileActions;

    /**The absolute path of the chosen file */
    private String chosen_file;

    /** This constructs the main UI */
    public ui() {

        // Bare Bones
        setSize(485,800);
        setTitle("Devify - The Verifier!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Panel
        JPanel master = new JPanel();
        master.setLayout(new BoxLayout(master, BoxLayout.Y_AXIS));

        // Hash Text
        hashText = new JTextField();
        hashText.setHorizontalAlignment(JTextField.CENTER);
        hashText.setBackground(sea_green_crayola);
        hashText.setText("Choose An Action!");

        // Checksum File
        checksumFile = new JTextField();
        checksumFile.setHorizontalAlignment(JTextField.CENTER);
        checksumFile.setText("sha256.txt");
        checksumFile.setBackground(celeste);

        // File Chooser
        fileChooser = new JFileChooser();
        fileChooser.addActionListener(this);
        UIManager.put("Table.focusCellBackground", celeste);

        // Actions For The File
        fileActions = new JPanel();
        fileActions.setVisible(false);

        // Adding to Master
        master.add(fileChooser);
        master.add(fileActions);
        add(master);

        // Final
        setVisible(true);

    }

    /**
     * Upon selecting a file, this activates. It completely destroys all elements of the previous
     * instance of it and rebuilds it with; The Status Bar (hashText) at the Top and three buttons (genHash,
     * genChecksum, verifyChecksum) at the center. It then repaints and displays the new menu.
     */
    private void showActions() {

        fileActions.removeAll();
        fileActions.revalidate();

        fileActions.setLayout(new BorderLayout(5, 1));
        hashText.setText("Choose An Action!");

        JButton genHash = new JButton("Generate Hash");
        genHash.setBackground(corn);
        genHash.addActionListener(this);
        genHash.setOpaque(true);

        JButton genChecksum = new JButton("Generate Checksum");
        genChecksum.setBackground(orange_red_crayola);
        genChecksum.addActionListener(this);
        genChecksum.setOpaque(true);

        JButton verifyChecksum = new JButton("Verify Checksum");
        verifyChecksum.setBackground(yellow_orange);
        verifyChecksum.addActionListener(this);
        verifyChecksum.setOpaque(true);

        fileActions.add(hashText, BorderLayout.PAGE_START);
        fileActions.add(genHash, BorderLayout.LINE_START);
        fileActions.add(genChecksum, BorderLayout.CENTER);
        fileActions.add(verifyChecksum, BorderLayout.LINE_END);
        fileActions.add(checksumFile, BorderLayout.PAGE_END);

        fileActions.repaint();
        fileActions.setVisible(true);

    }

    /**
     * Sets the Status to the hash of the chosen file
     * @throws IOException If there's a problem reading the file
     */
    private void hash_file() throws IOException {
        hashText.setText(Hash.file(chosen_file));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String ac_com = e.getActionCommand();
        String checksumfile = checksumFile.getText();

        // The File Choosing Section
        if (Objects.equals(ac_com, "ApproveSelection")) {
            chosen_file = fileChooser.getSelectedFile().getAbsolutePath();
            showActions();
        }
        else if (Objects.equals(ac_com, "CancelSelection")) {
            fileActions.setVisible(false);
        }

        // The File Actions
        else if (Objects.equals(ac_com, "Generate Hash")) {
            try {
                hash_file();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (Objects.equals(ac_com, "Generate Checksum")) {
            try {
                hash_file();
                Hash.checksum(hashText.getText(), chosen_file, checksumfile, hashText);
                fileChooser.rescanCurrentDirectory();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (Objects.equals(ac_com, "Verify Checksum")) {
            Verify.verify_with_checksum(chosen_file, checksumfile, hashText);
        }

    }

}
