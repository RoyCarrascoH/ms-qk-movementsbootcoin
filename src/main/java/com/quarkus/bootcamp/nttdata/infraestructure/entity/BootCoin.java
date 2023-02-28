package com.quarkus.bootcamp.nttdata.infraestructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BootCoin {

  private ObjectId id;
  private ObjectId rateId;
  private ObjectId salesManId;//Vendedor&Comprador
  private double balance;
}
