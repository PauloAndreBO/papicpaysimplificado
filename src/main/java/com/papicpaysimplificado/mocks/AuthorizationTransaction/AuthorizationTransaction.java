package com.papicpaysimplificado.mocks.AuthorizationTransaction;


import com.papicpaysimplificado.dtos.responses.ResponseAuthorizationTransaction;
import jakarta.ws.rs.Consumes;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v2/authorize")
@RegisterRestClient(configKey = "mock-api")
public interface AuthorizationTransaction {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResponseAuthorizationTransaction authorize();
}
