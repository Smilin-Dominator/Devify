// hashing.java -> Unit tests for hashing
/*
    Devify
    Copyright (C) 2021 Devisha Padmaperuma

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.smilin_dominator.devify.test;

import com.smilin_dominator.devify.backend.hash;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

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

        String real_hash = "7a9b7f453ce0659bfc7d0df24054a5f85a534bd381ddb06bfd9eed993969a939";

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
        hashing_test.checksum("abdc941476c8244ab0da7df485644f13ac0eb9cb662813a7a31a13b1b034312a", abspath, "hash_test_hash.txt");

        File check = new File("hash_test_hash.txt");
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
        new File("hash_test_hash.txt").delete();
    }

}
