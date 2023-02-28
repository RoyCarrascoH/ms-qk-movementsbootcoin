package com.quarkus.bootcamp.nttdata.domain.services;

import com.quarkus.bootcamp.nttdata.domain.entity.PaymentType;
import com.quarkus.bootcamp.nttdata.domain.respository.PaymentTypeRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class PaymentTypeService {

    @Inject
    PaymentTypeRepository rateRepository;

    public Uni<PaymentType> add(PaymentType rate) {
        return rateRepository.persist(rate);
    }

    public Uni<List<PaymentType>> getAll() {
        return rateRepository.listAll();
    }

    public Uni<PaymentType> getById(String id) {
        return rateRepository.findById(new ObjectId(id));
    }

}