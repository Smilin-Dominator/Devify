package backend;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class hash {

    private final HashFunction sha256_hasher = Hashing.sha256();

    public String string(String a) {
        return sha256_hasher.hashString(a, StandardCharsets.UTF_8).toString();
    }

    public String file(String path) {
        return "F";
    }

}
