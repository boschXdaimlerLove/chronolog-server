package com.bxdlove.worktimeserver.api;

import com.bxdlove.worktimeserver.api.json.PasswordChangeObject;
import com.bxdlove.worktimeserver.api.security.CredentialUtils;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.model.Filters;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.bson.Document;

/**
 * This class contains the settings API endpoint.
 *
 * @author Gregor Gottschewski
 */
@Path("/settings")
@RequestScoped
public class UserSettings {
    @PATCH
    @Path("/password")
    public Response updateUserPassword(@Context SecurityContext securityContext, PasswordChangeObject passwordChangeObject) {
        if (securityContext.getUserPrincipal() == null) {
            return ResponseMessages.UNAUTHORIZED.getResponseBuilder().build();
        }

        if (!CredentialUtils.validate(securityContext.getUserPrincipal().getName(), passwordChangeObject.getOldPassword())) {
            return ResponseMessages.UNAUTHORIZED.getResponseBuilder().build();
        }

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            mongoClient.getDatabase("worktime_server").getCollection("employee")
                    .updateOne(
                            Filters.eq("email", securityContext.getUserPrincipal().getName()),
                            new Document("$set", new Document("password", passwordChangeObject.getNewPassword()))
                    );
        } catch (Exception e) {
            return ResponseMessages.DATABASE_ERROR.getResponseBuilder().build();
        }

        return Response.ok().build();
    }
}
