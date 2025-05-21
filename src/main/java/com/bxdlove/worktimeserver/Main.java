package com.bxdlove.worktimeserver;

import com.bxdlove.worktimeserver.api.Status;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class Main extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(Status.class);
        classes.add(ApplicationIdentityStore.class);
        //classes.add(ApplicationStartup.class);
        return classes;
    }
}
