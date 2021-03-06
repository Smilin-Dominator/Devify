// hash.java -> This contains all the functions related to hashing
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

import com.sun.source.tree.BreakTree;
import org.apache.commons.codec.digest.DigestUtils;

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
     * @param algo The hashing algorithm
     * @return The SHA256 Hash as a string
     */
    public String string(String a, String algo) {
        switch (algo) {
            case "SHA256" -> {
                return DigestUtils.sha256Hex(a);
            }
            case "SHA512" -> {
                return DigestUtils.sha512Hex(a);
            }
            case "MD5" -> {
                return DigestUtils.md5Hex(a);
            }
            default -> {
                return "";
            }
        }
    }

    /**
     * Loads a file as a FileInputStream and returns it's hash, similar to the 'sha256sum' and 'Get-FileHash' commands
     * in *NIX and DOS respectively
     * @param path The path to the file
     * @param algo The hashing algorithm to use
     * @return The SHA256 Hash of the File if the file exists, otherwise it returns a ""
     */
    public String file(String path, String algo) {

        // Creating A File With The Path
        File fil = new File(path);

        // Exit if the Path does not exist
        if (!fil.exists()) {
            System.out.println("Path '" + fil.getAbsolutePath() + "' Does Not Exist!");
            return "";
        }

        // Hashing the file itself
        String hash = "";
        try {
            switch (algo) {
                case "SHA256" -> hash = DigestUtils.sha256Hex(new FileInputStream(fil));
                case "MD5" -> hash = DigestUtils.md5Hex(new FileInputStream(fil));
                case "SHA512" -> hash = DigestUtils.sha512Hex(new FileInputStream(fil));
            }
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
     *
     * @return 1 if it's Successful, 2 if it's an IOException, 3 if it's an AssertionError
     */
    public int checksum(String hash, String path, String checksum_file) {

        // String filename (without directories)
        Path fn = Paths.get(path).getFileName();

        // Getting the path of the checksum hash
        Path directory = Paths.get(path).getParent();
        Path path_to_cs = Paths.get(directory.toString(), checksum_file);

        // Constructing the string
        String text = hash + "  " + fn.toString();

        // Writing to the file
        try {
            assert path_to_cs.toFile().createNewFile();
            Files.writeString(path_to_cs, text);
            return 1;
        } catch (IOException e) { // When you can't open, etc..
            return 2;
        } catch (AssertionError e) { // When a Checksum File Already Exists
            return 3;
        }

    }

}
