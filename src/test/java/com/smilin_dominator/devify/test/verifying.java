/*
    Devify
    Copyright (C) 2021 Devisha Padmaperuma

    Devify is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Devify is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Devify.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.smilin_dominator.devify.test;

import com.smilin_dominator.devify.backend.hash;
import com.smilin_dominator.devify.backend.verify;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class verifying {

    private final verify Verify = new verify();

    @Test
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
    void string_hash() {

        String string = "You Are A Donk!";
        String hash = "f6fa9bb646aed5a65fe22c770935bce17b501ad8f6d63bd6f30c001878f0f3e5";

        boolean True = Verify.verify_string(string, hash);

        assertTrue(True);

    }

    @Test
    void verifying_file() throws IOException {

        File ver = new File("verify_test.txt");
        assert ver.createNewFile();

        List<String> spl = Arrays.asList("Unicorn", "Lasagna", "lol!");
        Files.write(ver.toPath(), spl, StandardCharsets.UTF_8);

        String hash = "27f6b7d42d75634cd4f41d771f96b47a60df33029cfd517e3a62f51d42f47976";
        boolean True = Verify.verify_file(ver.toString(), hash);

        assertTrue(True);

    }

    @Test
    void verifying_the_checksum() {

        File ver = new File("verify_test.txt");
        hash Hash = new hash();
        JTextField test = new JTextField();
        Hash.checksum("27f6b7d42d75634cd4f41d771f96b47a60df33029cfd517e3a62f51d42f47976", ver.getAbsolutePath(), "verify_hash.txt", test);
        assert Objects.equals(test.getText(), "Success!");

        Verify.verify_with_checksum(ver.getAbsolutePath(), "verify_hash.txt", test);
        System.out.println(test.getText());
        assert Objects.equals(test.getText(), "File Is The Same!");

    }

    @AfterAll
    static void clean() {
        new File("verify_test.txt").delete();
        new File("verify_hash.txt").delete();

    }

}
