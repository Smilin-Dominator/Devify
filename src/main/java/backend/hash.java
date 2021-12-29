package backend;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class hash {

    private final HashFunction sha256_hasher = Hashing.sha256();

    public String string(String a) {
        return sha256_hasher.hashString(a, StandardCharsets.UTF_8).toString();
    }

    public String file(String path) throws FileNotFoundException {

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

        // Returning the hashed result
        return string(build.toString());

    }

}
