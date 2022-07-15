package com.efimchick.ifmo.io.filetree;

public class StructTreeDirectory {
    private final String treeDirectory;
    private final long sizeDirectory;

    public StructTreeDirectory(String tree, long size) {
        this.treeDirectory = tree;
        this.sizeDirectory = size;
    }

    public String getTree() {
        return treeDirectory;
    }

    public long getSize() {
        return sizeDirectory;
    }
}
