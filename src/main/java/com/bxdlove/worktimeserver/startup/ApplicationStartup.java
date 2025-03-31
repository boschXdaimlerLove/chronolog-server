package com.bxdlove.worktimeserver.startup;

import com.bxdlove.worktimeserver.api.Status;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import com.bxdlove.worktimeserver.startup.setup.InitStep;
import com.bxdlove.worktimeserver.startup.setup.SetupException;
import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static com.bxdlove.worktimeserver.io.ApplicationDirectories.APPLICATION_HOME;

@WebListener(value = "Application startup")
public class ApplicationStartup implements ServletContextListener {

    @Inject
    private Status status;

    private static final List<InitStep> initSteps = List.of(
            new InitStep() {
                @Inject
                private Status status;

                @Override
                public String name() {
                    return "Directory creation";
                }

                @Override
                public String description() {
                    return "Creates application directories";
                }

                @Override
                public void execute() {
                    try {
                        Files.createDirectory(APPLICATION_HOME.getPath());
                    } catch (IOException e) {
                        status.addException(new SetupException(this, e.getMessage()));
                    }
                }
            }
    );

    private static final List<InitStep> preFlightChecks = List.of(
            new InitStep() {
                @Inject
                private Status status;

                @Override
                public String name() {
                    return "MongoDB connection check";
                }

                @Override
                public String description() {
                    return "Checks if MongoDB is running on localhost:27017";
                }

                @Override
                public void execute() {
                    try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
                        MongoDatabase database = mongoClient.getDatabase("admin");
                        database.runCommand(new Document("ping", 1));
                    } catch (Exception e) {
                        status.addException(new SetupException(this, e.getMessage()));
                    }
                }
            }
    );

    public void init() {
        if (!Files.exists(APPLICATION_HOME.getPath())) {
            for (InitStep initStep : initSteps) {
                initStep.execute();
            }
        }

        for (InitStep preFlightCheck : preFlightChecks) {
            preFlightCheck.execute();
        }

        if (status.getExceptionCount() == 0) {
            status.setRunning(true);
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup
    }
}
