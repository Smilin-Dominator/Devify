package backend;

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
     * @param hashText The main status bar
     */
    public void verify_with_checksum(String path_to_file, JTextField hashText) {
        try {
            common Common = new common();
            Path dir = Paths.get(path_to_file).getParent();
            Path shafile = Paths.get(dir.toString(), "sha256.txt");
            List<String> contents = Files.readAllLines(shafile);
            String line = contents.get(0);
            String[] line_split = line.split("  ");
            String hash = line_split[0];
            String filename = line_split[1];
            boolean samefile = Common.filename_in_path(path_to_file, filename);
            if (samefile) {
                if (Objects.equals(hash, hashText.getText())) {
                    hashText.setText("File Is The Same!");
                } else {
                    hashText.setText("File Is Not The Same!");
                }
            } else {
                hashText.setText("This Is Not The File That's Hashed!");
            }
        } catch (IOException ex) {
            hashText.setText("There is no 'sha256.txt' in this DIR!");
        }
    }

}
