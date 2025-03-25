package io;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum ApplicationDirectories {
    APPLICATION_HOME(Paths.get(System.getProperty("user.home")).resolve(".worktimeserver"));

    private final Path path;

    ApplicationDirectories(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    @Override
    public String toString() {
        return path.toString();
    }
}
