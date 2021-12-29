package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class verify {

    private final hash hashing = new hash();

    public boolean verify_file(String path, String hash) throws FileNotFoundException {

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
        String new_hash = hashing.string(build.toString());

        // Return the result of the two
        return Objects.equals(new_hash, hash);

    }

}
