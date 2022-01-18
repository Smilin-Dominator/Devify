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

import org.apache.commons.codec.digest.DigestUtils;

import javax.swing.JTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * This class is responsible for providing hashes, hashing files and writing checksums!
 */
public class hash {

    /**
     * Returns a hash of a string
     * @param a The string to be hashed
     * @return The SHA256 Hash as a string
     */
    public String string(String a) {
        return DigestUtils.sha256Hex(a);
    }

    /**
     * Loads a file as a FileInputStream and returns it's hash, similar to the 'sha256sum' and 'Get-FileHash' commands
     * in *NIX and DOS respectively
     * @param path The path to the file
     * @return The SHA256 Hash of the File
     */
    public String file(String path) {

        // Creating A File With The Path
        File fil = new File(path);

        // Exit if the Path does not exist
        if (!fil.exists()) {
            System.out.println("Path '" + fil.getAbsolutePath() + "' Does Not Exist!");
        }

        // Hashing the file itself
        String hash = "";
        try {
            hash = DigestUtils.sha256Hex(new FileInputStream(fil));
            System.out.println("Chosen File : " + path);
            System.out.println("Hash        : " + hash);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hash;

    }

    /**
     * Writes a checksum with the filename of your choice in the format "hash  filename"
     * @param hash The SHA256 Hash of the File
     * @param path The path to the file
     * @param checksum_file The filename of the checksum file
     * @param status The JTextField to write the status to
     */
    public void checksum(String hash, String path, String checksum_file, JTextField status) {

        // Getting the path of the checksum hash
        Path directory = Paths.get(path).getParent();
        Path path_to_cs = Paths.get(directory.toString(), checksum_file);

        // Constructing the string
        String text = hash + "  " + path;

        // Writing to the file
        try {
            assert path_to_cs.toFile().createNewFile();
            Files.writeString(path_to_cs, text);
            status.setText("Success!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AssertionError e) {
            status.setText("A checksum exists in this directory!");
        }

    }

}
