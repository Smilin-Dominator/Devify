// Operations.java -> Wraps the functions in the backend with Threading
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
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.smilin_dominator.devify.backend.hash;
import com.smilin_dominator.devify.backend.verify;

public class Operations {

    public static class Hash extends Operations {

        private final ExecutorService exec = Executors.newSingleThreadExecutor();
        private final hash HashClass = new hash();
        private final JLabel status;

        public Hash(JLabel statusBar) {
            this.status = statusBar;
        }

        public void display_hash(String fn) {
            exec.submit(() -> {
                status.setText("Calculating Hash!");
                String hash = HashClass.file(fn);
                if (Objects.equals(hash, "")) {
                    status.setText("File Not Found!");
                } else {
                    status.setText(HashClass.file(fn));
                }
            });
        }

        public void write_checksum(String path, String checksumPath) {
            exec.submit(() -> {
                status.setText("Writing Checksum!");
                String hash = HashClass.file(path);
                if (Objects.equals(hash, "")) {
                    status.setText("File Not Found!");
                } else {
                    int cs_status = HashClass.checksum(hash, path, checksumPath);
                    switch (cs_status) {
                        case 1 -> status.setText("Success!");
                        case 2 -> status.setText("Error While Writing To File!");
                        case 3 -> status.setText("There's a checksum file in this directory!");
                    }
                }
            });
        }

    }

    public static class Verify extends Operations {

        private final JTree tree;
        private final DefaultMutableTreeNode root;
        private final ExecutorService exec = Executors.newSingleThreadExecutor();
        private final verify VerifyClass = new verify();
        private final hash HashClass = new hash();

        public Verify(JTree treeWidget, DefaultMutableTreeNode treeRoot) {
            this.tree = treeWidget;
            this.root = treeRoot;
        }

        /**
         * This recurses through the elements in the JTree and expands them
         * @param maximum The amount of rows
         * @param count The current iteration
         */
        private void extendElements(int maximum, int count) {
            if (count != maximum) {
                tree.expandRow(count);
                extendElements(maximum, count + 1);
            }
        }

        /**
         * This creates TreeNodes for each file, containing it's status
         * @param checksumFileName Path to the checksum file
         */
        public void CheckFiles(String checksumFileName) {

            exec.submit(() -> {
                tree.setVisible(false);

                root.removeAllChildren();
                HashMap<String, String> FileHashMap = VerifyClass.getFiles(checksumFileName);
                for (String file : FileHashMap.keySet()) {

                    String newHash = HashClass.file(file);
                    if (Objects.equals(newHash, "")) {
                        newHash = "File Doesn't Exist!";
                    }
                    String lastHash = FileHashMap.get(file);

                    DefaultMutableTreeNode name = new DefaultMutableTreeNode(file);
                    DefaultMutableTreeNode equal = new DefaultMutableTreeNode(String.format("Equal\t\t: %s", Objects.equals(newHash, lastHash)));
                    DefaultMutableTreeNode previousHash = new DefaultMutableTreeNode(String.format("Last Hash\t\t: %s", lastHash));
                    DefaultMutableTreeNode currentHash = new DefaultMutableTreeNode(String.format("New Hash\t\t: %s", newHash));

                    name.add(previousHash);
                    name.add(currentHash);
                    name.add(equal);

                    root.add(name);

                }

                extendElements(tree.getRowCount(), 0);

                tree.revalidate();
                tree.setVisible(true);
            });

        }

    }

}
