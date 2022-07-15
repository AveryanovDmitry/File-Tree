package com.efimchick.ifmo.io.filetree;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class WalkThoughDirectory {
    private static final String VERTICAL_SYMBOL = "│";
    private static final String UP_AND_RIGHT_SYMBOL = "└";
    private static final String HORIZONTAL_SYMBOL = "─ ";
    private static final String VERTICAL_AND_RIGHT_SYMBOL = "├";
    private static final String SEPARATOR_SPACE = " ";
    private static final String SEPARATOR_DOUBLE_SPACE = "  ";
    private static final String BYTES = "bytes";

    public StructTreeDirectory getTreeDFS(File directory, String pseudoGraphicSymbolsWithString) {
        long size = 0;
        StringBuilder treeBuilder = new StringBuilder();
        List<File> filesInDirectory = Arrays.asList(Objects.requireNonNull(directory.listFiles()));

        filesInDirectory.sort((File a, File b) -> {
            int result = Boolean.compare(a.isFile(), b.isFile());

            if (result == 0) {
                result = a.getName().compareToIgnoreCase(b.getName());
            }
            return result;
        });

        for (File file : filesInDirectory) {
            treeBuilder.append("\n").append(pseudoGraphicSymbolsWithString);
            StringBuilder levelIndentFolder = new StringBuilder(pseudoGraphicSymbolsWithString);

            boolean isLast = file.equals(filesInDirectory.get(filesInDirectory.size() - 1));

            levelIndentFolder.append(isLast ? SEPARATOR_SPACE : VERTICAL_SYMBOL);

            levelIndentFolder.append(SEPARATOR_DOUBLE_SPACE);

            treeBuilder.append(isLast ? UP_AND_RIGHT_SYMBOL : VERTICAL_AND_RIGHT_SYMBOL);

            treeBuilder.append(HORIZONTAL_SYMBOL);

            if (file.isFile()) {
                size += file.length();
                treeBuilder.append(file.getName()).append(SEPARATOR_SPACE)
                        .append(file.length()).append(SEPARATOR_SPACE).append(BYTES);
            } else {
                StructTreeDirectory treeResult = getTreeDFS(file, levelIndentFolder.toString());
                size += treeResult.getSize();
                treeBuilder.append(treeResult.getTree());
            }
        }

        String name = directory.getName();

        return new StructTreeDirectory(name + SEPARATOR_SPACE + size
                + SEPARATOR_SPACE + BYTES + treeBuilder, size);
    }
}
