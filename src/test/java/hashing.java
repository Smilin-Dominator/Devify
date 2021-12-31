import com.smilin_dominator.devify.backend.hash;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.swing.*;
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
    @Order(1)
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
    @Order(2)
    void hashFile() throws IOException {

        String real_hash = "abdc941476c8244ab0da7df485644f13ac0eb9cb662813a7a31a13b1b034312a";

        File ftbh = new File("tbh.txt");
        assert ftbh.createNewFile();

        List<String> spl = Arrays.asList("This is another test", "lol!");
        Files.write(ftbh.toPath(), spl, StandardCharsets.UTF_8);

        String file_hash = hashing_test.file(ftbh.getAbsolutePath());
        boolean eq = Objects.equals(real_hash, file_hash);

        System.out.println("------------------------ Hashing Test (File) ---------------------------");
        System.out.println("The File's Contents     : " + spl);
        System.out.println("The Real Hash           : " + real_hash);
        System.out.println("The Program's Hash      : " + file_hash);
        System.out.println("Matched?                : " + eq);

        assertTrue(eq);

    }

    @Test
    @Order(3)
    void checksum() throws IOException {

        File ftbh = new File("tbh.txt");
        String abspath = ftbh.getAbsolutePath();

        String real_outcome = "abdc941476c8244ab0da7df485644f13ac0eb9cb662813a7a31a13b1b034312a  " + abspath;
        hashing_test.checksum("abdc941476c8244ab0da7df485644f13ac0eb9cb662813a7a31a13b1b034312a", abspath, new JTextField());

        File check = new File("sha256.txt");
        List<String> fil = Files.readAllLines(check.toPath());
        boolean eq = Objects.equals(real_outcome, fil.get(0));

        System.out.println("------------------------ Hashing Test (Checksum) ---------------------------");
        System.out.println("The File's Contents     : " + fil.get(0));
        System.out.println("The Real Contents       : " + real_outcome);
        System.out.println("Matched?                : " + eq);

        assertTrue(eq);


    }

    @AfterAll
    static void clean() {
        new File("tbh.txt").delete();
        new File("sha256.txt").delete();
    }

}
