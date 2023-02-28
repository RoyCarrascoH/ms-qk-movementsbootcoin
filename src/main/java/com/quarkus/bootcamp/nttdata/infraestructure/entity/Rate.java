package com.quarkus.bootcamp.nttdata.infraestructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rate {
    private ObjectId id;
    private double rateBuys;
    private double rateSale;
    private boolean state;
}
