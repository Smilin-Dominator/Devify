// ThreadWrapper.java -> Wraps the functions in the backend with Threading
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
package com.smilin_dominator.devify.frontend;

import javax.swing.JLabel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.smilin_dominator.devify.backend.hash;
import com.smilin_dominator.devify.backend.verify;

public class ThreadWrapper {

    private static JLabel status = null;
    private static final verify VerifyClass = new verify();

    public ThreadWrapper(JLabel statusBar) {
        status = statusBar;
    }

    public static class Hash extends ThreadWrapper {

        private final ExecutorService exec = Executors.newSingleThreadExecutor();
        private final hash HashClass = new hash();

        public Hash(JLabel statusBar) {
            super(statusBar);
        }

        public void display_hash(String fn) {
            exec.submit(() -> {
                status.setText("Calculating Hash!");
                status.setText(HashClass.file(fn));
            });
        }

        public void write_checksum(String path, String checksumPath) {
            exec.submit(() -> {
                status.setText("Writing Checksum!");
                String hash = HashClass.file(path);
                int cs_status = HashClass.checksum(hash, path, checksumPath);
                switch (cs_status) {
                    case 1 -> status.setText("Success!");
                    case 2 -> status.setText("Error While Writing To File!");
                    case 3 -> status.setText("There's a checksum file in this directory!");
                }
            });
        }

    }

}
