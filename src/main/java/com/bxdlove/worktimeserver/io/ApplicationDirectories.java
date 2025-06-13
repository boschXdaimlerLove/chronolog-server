package com.bxdlove.worktimeserver.io;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This is a utility class for application files and directories.
 * It stores the paths to these files.
 *
 * @author Gregor Gottschewski
 */
public enum ApplicationDirectories {
    /**
     * application home: {@code userhome/.worktimeserver}
     */
    APPLICATION_HOME(Path.of("/opt/jboss/wildfly/standalone/data/worktime-server")),

    /**
     * admin config file: {@code userhome/.worktimeserver/.admin-config}
     */
    ADMIN_CONFIG_FILE(APPLICATION_HOME.path.resolve(".admin-config"));

    private final Path path;

    ApplicationDirectories(Path path) {
        this.path = Paths.get(System.getProperty("user.home")).resolve(".worktimeserver").resolve(path);
    }

    public Path getPath() {
        return path;
    }

    @Override
    public String toString() {
        return path.toString();
    }
}
