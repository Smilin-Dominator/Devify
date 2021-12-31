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
