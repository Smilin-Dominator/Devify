// common.java -> This file has common operations that are used by all classes
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
package com.smilin_dominator.devify.backend;

import java.util.Objects;

/**
 * This class consists of common functions that're needed by all parts.
 */
public class common {

    /**
     * Essentially, checks if the filename is in the last element of the path split by '/'.
     * @param filename The Filename, or the final string
     * @param path The Path, or the main string
     * @return True if it's the last string, False if it doesn't exist
     */
    public boolean filename_in_path(String filename, String path) {
        String[] path_split = path.split("\\\\");
        String[] file_split = filename.split("\\\\");
        return Objects.equals(path_split[path_split.length - 1], file_split[file_split.length - 1]);
    }

}
