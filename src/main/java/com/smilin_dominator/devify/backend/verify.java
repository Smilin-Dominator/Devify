// verify.java -> This contains all the functions related to verifying
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
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
     * @param algo The algorithm to hash the file in
     * @return True if they're the same, False if they're different
     * @throws IOException If there's an error while reading the file
     */
    public boolean verify_file(String path, String hash, String algo) {
        String new_hash = hashing.file(path, algo);
        return compare_hashes(hash, new_hash);
    }

    /**
     * Verifies a string and it's hash
     * @param string The String
     * @param hash The Hash of The String
     * @param algo The algorithm to hash the file in
     * @return True if they're the same, False if they're different.
     */
    public boolean verify_string(String string, String hash, String algo) {
        String new_hash = hashing.string(string, algo);
        return compare_hashes(hash, new_hash);
    }

    public HashMap<String,String> getFiles(String checksumFileName) {

        // Make 'Path' objects
        Path checksumFile = Paths.get(checksumFileName);

        // Get all the lines of the file
        HashMap<String, String> output = new HashMap<>();
        try {
            List<String> contents = Files.readAllLines(checksumFile);
            for (String line: contents) {
                String[] split = line.split("  ");
                output.put(split[1], split[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;

    }

}
