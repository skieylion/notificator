package com.example.notification.router;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/health")
public class HealthResource {

    // health check endpoint
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String health() {
        return "OK";
    }
}
