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
