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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import com.smilin_dominator.devify.backend.common;

public class commonFuncs {

    private final common common_test = new common();

    @Test
    void pathCheck() {

           String path = "C:\\Users\\Devisha\\Documents\\test.txt";
           String file = "test.txt";
           String file2 = "tes.txt";

           boolean file_in_path = common_test.filename_in_path(file, path);
           boolean fil2_in_path = common_test.filename_in_path(file2, path);

           assertTrue(file_in_path);
           assertFalse(fil2_in_path);

    }

}
