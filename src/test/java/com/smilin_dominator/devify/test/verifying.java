// verifying.java -> Unit tests for verifying
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
import com.smilin_dominator.devify.backend.verify;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class verifying {

    private final verify Verify = new verify();

    @Test
    @Order(1)
    void two_hashes() {

        String hash1 = "bf3f1a53a871ca130b3a238b3de8c757c6d9ba9614e8fc73ddb23daf003a8cd9";
        String hash2 = "bf3f1a53a871ca130b3a238b3de8c757c6d9ba9614e8fc73ddb23daf003a8cd9";
        String hash3 = "bf3f1a53a871ca130b3a238b3de8c757614e8fc73ddb23daf003a8cd9";

        boolean True = Verify.compare_hashes(hash1, hash2);
        boolean False = Verify.compare_hashes(hash1, hash3);

        assertTrue(True);
        assertFalse(False);

    }

    @Test
    @Order(1)
    void string_hash() {

        String string = "You Are A Donk!";
        String hash = "f6fa9bb646aed5a65fe22c770935bce17b501ad8f6d63bd6f30c001878f0f3e5";

        boolean True = Verify.verify_string(string, hash);

        assertTrue(True);

    }

    @Test
    @Order(2)
    void verifying_file() throws IOException {

        File ver = new File("verify_test.txt");
        assert ver.createNewFile();

        List<String> spl = Arrays.asList("Unicorn", "Lasagna", "lol!");
        Files.write(ver.toPath(), spl, StandardCharsets.UTF_8);

        String hash = "d2725eec6af3fc3ea92c0ee285bb6780d6414f28a3d06b18f69cca57025f9360";
        boolean True = Verify.verify_file(ver.toString(), hash);

        assertTrue(True);

    }

    @Test
    @Order(3)
    void verifying_the_checksum() {

        File tbh = new File("verify_test.txt");
        hash Hash = new hash();
        Hash.checksum("d2725eec6af3fc3ea92c0ee285bb6780d6414f28a3d06b18f69cca57025f9360", tbh.getAbsolutePath(), "test_sha256.txt");

        HashMap<String, String> hashes = Verify.getFiles("test_sha256.txt");
        String newHash = Hash.file(tbh.getAbsolutePath());
        assert Objects.equals(hashes.get(tbh.getAbsolutePath()), newHash);

    }

    @AfterAll
    static void clean() {
        new File("verify_test.txt").delete();
        new File("test_sha256.txt").delete();
    }

}
