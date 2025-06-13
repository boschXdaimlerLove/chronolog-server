package com.bxdlove.worktimeserver.startup;

import com.bxdlove.worktimeserver.api.Status;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import com.bxdlove.worktimeserver.startup.setup.InitStep;
import com.bxdlove.worktimeserver.startup.setup.SetupException;
import org.bson.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Properties;

import static com.bxdlove.worktimeserver.io.ApplicationDirectories.ADMIN_CONFIG_FILE;
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

                @Override
                public boolean isSatisfied() {
                    return Files.exists(APPLICATION_HOME.getPath());
                }
            },
            new InitStep() {
                @Override
                public String name() {
                    return "Admin configuration file creation";
                }

                @Override
                public String description() {
                    return "Creates the admin config file";
                }

                @Override
                public void execute() {
                    Properties prop = new Properties();
                    prop.setProperty("admin-password", "123465");
                    try (FileWriter fileWriter = new FileWriter(ADMIN_CONFIG_FILE.getPath().toFile())) {
                        prop.store(fileWriter, "application setup");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public boolean isSatisfied() {
                    return Files.exists(ADMIN_CONFIG_FILE.getPath());
                }
            },
            new InitStep() {
                @Inject
                private Status status;

                @Override
                public String name() {
                    return "MongoDB database creation";
                }

                @Override
                public String description() {
                    return "Creates the application database";
                }

                @Override
                public void execute() {}

                @Override
                public boolean isSatisfied() {
                    try (MongoClient mongoClient = MongoClients.create("mongodb://worktime-mongodb:27017")) {
                        mongoClient.getDatabase("worktime_server");
                        return true;
                    } catch (Exception e) {
                        return false;
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
                    status.addException(new SetupException(this, "database connection failed"));
                }

                @Override
                public boolean isSatisfied() {
                    try (MongoClient mongoClient = MongoClients.create("mongodb://worktime-mongodb:27017")) {
                        MongoDatabase database = mongoClient.getDatabase("admin");
                        database.runCommand(new Document("ping", 1));
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
            }
    );

    public void init() {
        for (InitStep initStep : initSteps) {
            if (!initStep.isSatisfied()) {
                initStep.execute();
            }
        }

        for (InitStep preFlightCheck : preFlightChecks) {
            if (!preFlightCheck.isSatisfied()) {
                preFlightCheck.execute();
            }
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
