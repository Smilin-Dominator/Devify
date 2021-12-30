package backend;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class hash {


    public String string(String a) {
        return DigestUtils.sha256Hex(a);
    }

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

    public void checksum(String hash, String path) {

        // Getting the path of the checksum hash
        Path directory = Paths.get(path).getParent();
        Path path_to_cs = Paths.get(directory.toString(), "sha256.txt");

        // Constructing the string
        String text = hash + "  " + path;

        // Writing to the file
        try {
            assert path_to_cs.toFile().createNewFile();
            Files.writeString(path_to_cs, text);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
