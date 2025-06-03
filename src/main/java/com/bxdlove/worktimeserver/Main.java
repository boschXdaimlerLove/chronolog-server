package com.bxdlove.worktimeserver;

import com.bxdlove.worktimeserver.api.Status;
import com.bxdlove.worktimeserver.api.Time;
import com.bxdlove.worktimeserver.api.UserSettings;
import com.bxdlove.worktimeserver.api.security.ApplicationIdentityStore;
import com.bxdlove.worktimeserver.beans.EmployeeDataBean;
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
        classes.add(Time.class);
        classes.add(ApplicationIdentityStore.class);
        classes.add(UserSettings.class);
        //classes.add(ApplicationStartup.class);
        classes.add(EmployeeDataBean.class);
        return classes;
    }
}
