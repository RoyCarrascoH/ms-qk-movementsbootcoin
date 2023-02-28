package com.quarkus.bootcamp.nttdata.infraestructure.resource;

import com.quarkus.bootcamp.nttdata.infraestructure.entity.Rate;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/api/rate")
public interface IRateApi {

  @GET
  @Path("/{id}")
  public Uni<Rate> getById(@PathParam("id") String id);

}
