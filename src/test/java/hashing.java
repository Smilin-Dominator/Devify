import backend.hash;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;

public class hashing {

    private final hash hashing_test = new hash();

    @Test
    void hashString() {

        String tbh = "This is a test!";
        String hashed = hashing_test.string(tbh);
        String real_hash = "54ba1fdce5a89e0d3eee6e4c587497833bc38c3586ff02057dd6451fd2d6b640";
        boolean eq = Objects.equals(real_hash, hashed);

        System.out.println("------------------------ Hashing Test (String) ---------------------------");
        System.out.println("The String              : " + tbh);
        System.out.println("The Real Hash           : " + real_hash);
        System.out.println("The Program's Hash      : " + hashed);
        System.out.println("Matched?                : " + eq);

        assertTrue(eq);

    }

    @Test
    void hashFile() throws IOException {

        String tbh = "This is another test\nlol!";

        File ftbh = new File("tbh.txt");
        assert ftbh.createNewFile();

        List<String> spl = Arrays.asList("This is another test", "lol!");
        Files.write(ftbh.toPath(), spl, StandardCharsets.UTF_8);

        System.out.println("File Hash:  " + hashing_test.file(ftbh.getAbsolutePath()));

        /*
        System.out.println("------------------------ Hashing Test (File) ---------------------------");
        System.out.println("The File                : " + tbh);
        System.out.println("The Real Hash           : " + real_hash);
        System.out.println("The Program's Hash      : " + hashed);
        System.out.println("Matched?                : " + eq);
        */

    }

    @AfterAll
    static void clean() {
        File hashed = new File("./tbh.txt");
        assertTrue(hashed.delete());
    }

}
