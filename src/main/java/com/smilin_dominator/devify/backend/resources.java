package com.smilin_dominator.devify.backend;

import javax.swing.*;
import java.util.Objects;

public class resources {

    public ImageIcon image(String subdir, String name) {
        String path = "/%s/%s".formatted(subdir, name);
        return new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
    }

}
