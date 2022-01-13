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
package com.smilin_dominator.devify.backend;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * This class verifies the hashes provided by the hash class
 */
public class verify {

    // An instance of the Hash class
    private final hash hashing = new hash();

    /**
     * Compares both hashes
     * @param h1 The First Hash
     * @param h2 The Second Hash
     * @return True if they're equal, False if they're not
     */
    public boolean compare_hashes(String h1, String h2) {
        return Objects.equals(h1, h2);
    }

    /**
     * Verifies the hash of a file
     * @param path The path to the file
     * @param hash The hash of the file
     * @return True if they're the same, False if they're different
     * @throws IOException If there's an error while reading the file
     */
    public boolean verify_file(String path, String hash) throws IOException {
        String new_hash = hashing.file(path);
        return compare_hashes(hash, new_hash);
    }

    /**
     * Verifies a string and it's hash
     * @param string The String
     * @param hash The Hash of The String
     * @return True if they're the same, False if they're different.
     */
    public boolean verify_string(String string, String hash) {
        String new_hash = hashing.string(string);
        return compare_hashes(hash, new_hash);
    }

    /**
     * This checks the checksum file and verifies the integrity of the file
     * @param path_to_file The path to the file you want to verify
     * @param checksum The filename of the Checksum File
     * @param hashText The main status bar
     */
    public void verify_with_checksum(String path_to_file, String checksum, JTextField hashText) {
        try {

            // Create a class for the common functions
            common Common = new common();

            // Make 'Path' objects
            Path dir = Paths.get(path_to_file).getParent();
            Path shafile = Paths.get(dir.toString(), checksum);

            // Get the first line of the file
            List<String> contents = Files.readAllLines(shafile);
            String line = contents.get(0);

            // Split the line and obtain information
            String[] line_split = line.split("  ");
            String hash = line_split[0];
            String filename = line_split[1];

            // Verifies the file if the filename is in the absolute path
            boolean samefile = Common.filename_in_path(path_to_file, filename);
            if (samefile) {
                if (verify_file(path_to_file, hash)) {
                    hashText.setText("File Is The Same!");
                } else {
                    hashText.setText("File Is Not The Same!");
                }
            } else {
                hashText.setText("This Is Not The File That's Hashed!");
            }

        } catch (IOException ex) {
            hashText.setText("There is no '" + checksum + "' in this DIR!");
        }
    }

}
