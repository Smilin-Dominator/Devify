// resources.java -> This implements methods to get resources from the resources folder in the JAR at runtime
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

import javax.swing.ImageIcon;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
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

    /**
     * This returns a font as a Font Object, and crashes if no such font is found. For example, if you wanted to
     * get the font 'resources/fonts/Dude.ttf', you set fontName to 'Dude.ttf'
     * @param fontName The filename of the font
     * @return A Font object
     */
    public Font font(String fontName) {
        Font output = Font.getFont(Font.SERIF); // placeholder
        String path = "/fonts/" + fontName;
        InputStream font = Objects.requireNonNull(getClass().getResourceAsStream(path));
        try {
            output = Font.createFont(Font.TRUETYPE_FONT, font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return output;
    }

}
