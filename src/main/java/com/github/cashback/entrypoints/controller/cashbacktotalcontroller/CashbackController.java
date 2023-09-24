package com.github.cashback.entrypoints.controller.cashbacktotalcontroller;

import com.github.cashback.entrypoints.controller.cashbacktotalcontroller.converter.CashbackEntityTotalConverter;
import com.github.cashback.entrypoints.controller.errors.ErrorDTO;
import com.github.cashback.usecase.cashbacktotalcustomer.CashbackGetTotalUseCase;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Path("/cashback/api/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CashbackController {

    private final CashbackGetTotalUseCase useCase;

    @GET
    @Path("/{customer}")
    public Uni<Response> find(@PathParam("customer") final String customer) {
        return useCase.execute(CashbackEntityTotalConverter.toEntity(customer))
                .onItem()
                .transform(v -> Response.status(Response.Status.OK)
                        .entity(CashbackEntityTotalConverter.toResponse(v))
                        .build())
                .onFailure()
                .recoverWithItem(v ->
                        Response.status(Response.Status.BAD_REQUEST)
                                .entity(ErrorDTO.builder().message("fail get total to customer " + customer).build())
                                .build());
    }
}
