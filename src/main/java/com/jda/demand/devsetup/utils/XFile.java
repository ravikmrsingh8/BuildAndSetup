package com.jda.demand.devsetup.utils;

public class XFile {
    private String absolutePath;
    private String path;

    public XFile() {
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return getPath();
    }
}
