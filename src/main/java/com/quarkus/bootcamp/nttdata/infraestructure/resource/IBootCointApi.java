package com.quarkus.bootcamp.nttdata.infraestructure.resource;

import com.quarkus.bootcamp.nttdata.infraestructure.entity.BootCoin;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/api/bootcoin")
public interface IBootCointApi {

  @GET
  @Path("/{id}")
  public Uni<BootCoin> getById(@PathParam("id") String id);

}
