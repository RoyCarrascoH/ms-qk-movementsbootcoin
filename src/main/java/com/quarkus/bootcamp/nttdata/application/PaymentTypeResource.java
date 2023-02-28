package com.quarkus.bootcamp.nttdata.application;

import com.quarkus.bootcamp.nttdata.domain.entity.PaymentType;
import com.quarkus.bootcamp.nttdata.domain.services.PaymentTypeService;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.ResponseDto;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/paymenttype")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentTypeResource {
    @Inject
    PaymentTypeService service;

    @POST
    public Uni<Response> add(PaymentType paymentType) {
        return service.add(paymentType)
                .onItem().transform(uri ->
                        Response.ok(new ResponseDto<>(200, "Se registro correctamente", uri)).status(200).build());
    }

    @GET
    public Uni<List<PaymentType>> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public Uni<PaymentType> getById(@PathParam("id") String id) {
        return service.getById(id);
    }

}
