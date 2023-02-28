package com.quarkus.bootcamp.nttdata.domain.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@MongoEntity(collection = "buysbootcoin")
public class Buys {
    private ObjectId id;
    private double amount;
    private ObjectId paymentType;
    private String state = "In progress";
    private double balance;
    private String idTransaction = null;
    private String idSalesMan;//Vendedor
    private String idBuyerMan;//Comprador
}
