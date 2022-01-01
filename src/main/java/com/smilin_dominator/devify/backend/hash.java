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
     * @throws IOException If there's an error while reading the file
     */
    public String file(String path) throws IOException {

        // Creating A File With The Path
        File fil = new File(path);

        // Exit if the Path does not exist
        if (!fil.exists()) {
            System.out.println("Path '" + fil.getAbsolutePath() + "' Does Not Exist!");
            System.exit(1);
        }

        // Hashing the file itself
        String hash = DigestUtils.sha256Hex(new FileInputStream(fil));
        System.out.println("Chosen File : " + path);
        System.out.println("Hash        : " + hash);

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
