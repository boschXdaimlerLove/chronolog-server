package com.bxdlove.worktimeserver.api;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * This class handles employee time entries.
 * It provides the following endpoints:
 * <ol>
 *     <li>POST /time/{employee_mail} - Stamp in for an employee</li>
 *     <li>GET /time/{employee_mail}?range_start={range_start}&range_end={range_end} - Get time entries for an employee between two dates</li>
 *     <li>PATCH /time/{employee_mail} - Stamp out for an employee</li>
 * </ol>
 */
@RequestScoped
@Path("/time")
public class Time {

    @POST
    @Path("/{employee_mail}")
    public Response create(@PathParam("employee_mail") String email) {
        // TODO: Implement the logic to create a time entry for the employee

        return Response.ok().build();
    }

    @GET
    @Path("/{employee_mail}")
    public Response get(
            @PathParam("employee_mail") String email,
            @QueryParam("range_start") String rangeStart,
            @QueryParam("range_end") String rangeEnd)
    {
        // TODO: Implement the logic to retrieve time entries for the employee
        return Response.ok().build();
    }

    @PATCH
    @Path("/{employee_mail}")
    public Response patch(
            @PathParam("employee_mail") String email
    ) {
        // TODO: Stamp out
        return Response.ok().build();
    }

}
