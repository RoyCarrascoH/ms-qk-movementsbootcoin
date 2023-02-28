package com.quarkus.bootcamp.nttdata.domain.services;

import com.quarkus.bootcamp.nttdata.domain.entity.Buys;
import com.quarkus.bootcamp.nttdata.domain.respository.BuysRepository;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.BootCoin;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.Rate;
import com.quarkus.bootcamp.nttdata.infraestructure.resource.IBootCointApi;
import com.quarkus.bootcamp.nttdata.infraestructure.resource.IRateApi;
import com.quarkus.bootcamp.nttdata.infraestructure.util.Utils;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class BuysService {

    @RestClient
    IBootCointApi bootCointApi;

    @RestClient
    IRateApi rateApi;

    @Inject
    BuysRepository repository;

    public Uni<Buys> add(Buys buys) {
        return repository.persist(buys);
    }

    public Uni<List<Buys>> getAll() {
        return repository.listAll();
    }

    public Uni<Buys> getById(String id) {
        return repository.findById(new ObjectId(id));
    }

    public Uni<List<Buys>> checkPending(String idUser) {
        String state = "In progress";
        return repository.list("idSalesMan = ?1 where status = ?2", idUser, state);
    }

    public Uni<Buys> acceptPurchase(String idMovements) {
        Uni<Buys> buys = repository.findById(new ObjectId(idMovements));
        return buys
                .onItem()
                .transform(au -> {
                    au.setState("Accept");
                    au.setIdTransaction(Utils.triggerIdTransaction());
                    return au;
                }).call(au -> repository.persistOrUpdate(au));
    }

    public Uni<BootCoin> process(Buys buys) {

        //Balance (5) * Tasa venta --> obtener de BootCoin
        // Validar si el monto ingresado es igual al monto calculado
        // Validar metodo de pago si esta registrado  -----ORIGEN-----
                //Origen: Si es numero cuenta validar que cuenta exista y que tenga saldo suficiente
                            //Si es movil Wallet que numero de telefono este registrado en la Wallet y tenga saldo suficiente
        // Validar metodo de pago si esta registrado  -----DESTINO-----
        //Destino: Si es numero cuenta validar que cuenta exista y que tenga saldo suficiente
        //Si es movil Wallet que numero de telefono este registrado en la Wallet y tenga saldo suficiente

        // Procesar actualizacion tanto de monto como de dinero --

        Uni<BootCoin> bootCoin = bootCointApi.getById(buys.getIdSalesMan());
        return bootCoin;
        //Uni<Rate> rate = rateApi.getById
        //double calculatedAmount = 0;
        //return  null;
    }

    /* //MONTO   ||| MODO DE PAGO  ||  NUMERO DE CUENTA O CELULAR //
    "id": "63fe66212ccb1b59c19aaffe",
    "amount": 17.5,
    "paymentType": "63fe62502ccb1b59c19aaffc", //DIGITAL WALLET
    "state": "Accept",
    "balance": 5.0,
    "idTransaction": "584177d3-8930-4101-9e5f-86c6f6022949",
    "idSalesMan": "63fe619e17e2543b8c33c6fa", //VENDEDOR
    "idBuyerMan": "63fe654917e2543b8c33c6fb"  //COMPRADOR
     */

}