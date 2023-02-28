package com.quarkus.bootcamp.nttdata.domain.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@MongoEntity(collection = "paymenttype")
public class PaymentType {
    private ObjectId id;
    private String description;
}
