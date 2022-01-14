package com.smilin_dominator.devify.backend;

import javax.swing.ImageIcon;
import java.util.Objects;

public class resources {

    /**
     * This returns an Image as an ImageIcon element, and crashes if no such image is found. For example,
     * if you wanted to get 'resources/logos/foo.png' you have to pass 'logos' as the subdir and 'foo.png' as
     * the name and it'll return the file as a 'javax.swing.ImageIcon'
     * @param subdir The Subdirectory of the resources folder
     * @param name The filename
     * @return An ImageIcon with the specified file
     */
    public ImageIcon image(String subdir, String name) {
        String path = "/%s/%s".formatted(subdir, name);
        return new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
    }

}
