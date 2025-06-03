package com.bxdlove.worktimeserver.api.security;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;

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
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            return mongoClient.getDatabase("worktime_server")
                    .getCollection("employee")
                    .find(new Document("email", username).append("password", password))
                    .first() != null;
        }
    }
}
