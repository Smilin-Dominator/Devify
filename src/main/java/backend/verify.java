package backend;

import java.io.IOException;
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

}
