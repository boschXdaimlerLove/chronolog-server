package com.bxdlove.worktimeserver.api.security;

import com.bxdlove.worktimeserver.io.ApplicationDirectories;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class for credential management of employees
 *
 * @author Gregor Gottschewski
 */
public class CredentialUtils {

    /**
     * Validates user credentials.
     *
     * @param username username of the user
     * @param password password of the user
     * @return {@code true} if credentials are valid, {@code false} otherwise
     */
    public static boolean validate(String username, String password) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://worktime-mongodb:27017")) {
            return mongoClient.getDatabase("worktime_server")
                    .getCollection("employee")
                    .find(new Document("email", username).append("password", password))
                    .first() != null;
        }
    }

    /**
     * Validates if specified user is an admin.
     *
     * @param username username of the admin user
     * @param password password of the admin user
     * @return {@code true} if the specified user is admin, {@code false} otherwise
     */
    public static boolean validateAdmin(String username, String password) throws IOException {
        if (username.equals("admin")) {
            Properties properties = new Properties();
            properties.load(new FileInputStream(ApplicationDirectories.ADMIN_CONFIG_FILE.getPath().toFile()));
            return properties.getProperty("admin-password").equals(password);
        }

        try (MongoClient mongoClient = MongoClients.create("mongodb://worktime-mongodb:27017")) {
            return mongoClient.getDatabase("worktime_server")
                    .getCollection("employee")
                    .find(new Document("email", username).append("password", password).append("admin", true))
                    .first() != null;
        }
    }
}
