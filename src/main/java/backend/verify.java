package backend;

import java.io.IOException;
import java.util.Objects;

public class verify {

    private final hash hashing = new hash();

    public boolean compare_hashes(String h1, String h2) {
        return Objects.equals(h1, h2);
    }

    public boolean verify_file(String path, String hash) throws IOException {
        String new_hash = hashing.file(path);
        return compare_hashes(hash, new_hash);
    }

    public boolean verify_string(String string, String hash) {
        String new_hash = hashing.string(string);
        return compare_hashes(hash, new_hash);
    }

}
