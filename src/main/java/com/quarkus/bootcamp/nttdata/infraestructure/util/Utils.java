package com.quarkus.bootcamp.nttdata.infraestructure.util;

import java.util.UUID;

public class Utils {

    public static String triggerIdTransaction(){
        UUID uuid = UUID.randomUUID();
        //int valorDado = (int)Math.floor(Math.random()*10+1);
        return uuid.toString();
    }

}
