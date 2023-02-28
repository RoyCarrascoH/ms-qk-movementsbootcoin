package com.quarkus.bootcamp.nttdata.application;

import com.quarkus.bootcamp.nttdata.domain.entity.Buys;
import com.quarkus.bootcamp.nttdata.domain.services.BuysService;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.BootCoin;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.ResponseDto;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/buys")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BuysResource {

    @Inject
    BuysService service;

    @POST
    public Uni<Response> add(Buys buys) {
        return service.add(buys)
                .onItem().transform(uri ->
                        Response.ok(new ResponseDto<>(200, "Se registro correctamente", uri)).status(200).build());
    }

    @GET
    public Uni<List<Buys>> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public Uni<Buys> getById(@PathParam("id") String id) {
        return service.getById(id);
    }

    //Check pending requests by user
    @GET
    @Path("/checkpending/{idUser}")
    public Uni<List<Buys>> checkPendingByUser(@PathParam("idUser") String idUser) {
        return service.checkPending(idUser);
    }

    //Accept purchase request
    @PUT
    @Path("/acceptpurchase/{idMovement}")
    @Transactional
    public Uni<Buys> updateAmount(@PathParam("idMovement") String idMovement) {
        return service.acceptPurchase(idMovement);
    }

    //validate the data of the operation
    @GET
    @Path("/process/operation")
    //@Transactional
    public Uni<BootCoin> process(Buys buys) {
        return service.process(buys);
    }

}
