package backend;

import java.io.IOException;
import java.util.Objects;

public class verify {

    private final hash hashing = new hash();

    public boolean verify_file(String path, String hash) throws IOException {
        String new_hash = hashing.file(path);
        return Objects.equals(hash, new_hash);
    }

}
