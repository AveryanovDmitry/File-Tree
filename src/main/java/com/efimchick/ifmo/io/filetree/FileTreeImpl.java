package com.efimchick.ifmo.io.filetree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class FileTreeImpl implements FileTree {
    public Optional<String> tree(Path path) {
        long sizeFile = 0;
        if (path == null || Files.notExists(path)) {
            return Optional.empty();
        } else if (Files.isRegularFile(path)) {
            try {
                sizeFile = Files.size(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Optional.of(path.getFileName() + " " + sizeFile + " bytes");
        } else {
            WalkThoughDirectory walkDirectory = new WalkThoughDirectory();
            return Optional.of(walkDirectory.getTreeDFS(path.toFile(), "").getTree());
        }
    }
}
