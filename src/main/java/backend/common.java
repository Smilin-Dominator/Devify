package backend;

import java.util.Objects;

public class common {

    public boolean filename_in_path(String filename, String path) {
        String[] path_split = path.split("/");
        String[] file_split = filename.split("/");
        return Objects.equals(path_split[path_split.length - 1], file_split[file_split.length - 1]);
    }

}
