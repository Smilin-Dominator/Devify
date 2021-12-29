package backend;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Objects;

public class hash {

    private final HashFunction sha256_hasher = Hashing.sha256();

    public String sha256(String a) {
        return sha256_hasher.hashString(a, StandardCharsets.UTF_8).toString();
    }

    public boolean verify(String path, String hash) throws FileNotFoundException {

        // Creating A File With The Path
        File fil = new File(path);

        // Exit if the Path does not exist
        if (!fil.exists()) {
            System.out.println("Path Does Not Exist!");
            System.exit(1);
        }

        // Preparing to read the file
        Scanner reader = new Scanner(fil);
        StringBuilder build = new StringBuilder();

        // Reading the file line by line
        while (reader.hasNextLine()) {
            build.append(reader.nextLine());
        }

        // Hashing the string
        String new_hash = sha256(build.toString());

        // Return the result of the two
        return Objects.equals(new_hash, hash);

    }

}
