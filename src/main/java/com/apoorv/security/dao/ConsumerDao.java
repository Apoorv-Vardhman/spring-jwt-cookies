package com.apoorv.security.dao;

import com.apoorv.security.entities.Consumer;
import com.apoorv.security.model.ConsumerModel;
import com.apoorv.security.repository.ConsumerRepository;
import org.springframework.stereotype.Service;

/**
 * @author Apoorv Vardhman
 * @Github Apoorv-Vardhman
 * @LinkedIN apoorv-vardhman
 */
@Service
public class ConsumerDao {
    private final ConsumerRepository repository;

    public ConsumerDao(ConsumerRepository repository) {
        this.repository = repository;
    }

    public Consumer create(ConsumerModel consumerModel)
    {
        return repository.save(modelToEntity(consumerModel));
    }

    public Consumer findByEmail(String email)
    {
        return repository.findByEmail(email);
    }

    Consumer modelToEntity(ConsumerModel consumerModel)
    {
        Consumer consumer = new Consumer();
        consumer.setId(consumerModel.getId());
        consumer.setName(consumerModel.getName());
        consumer.setEmail(consumerModel.getEmail());
        consumer.setPassword(consumerModel.getPassword());
        return consumer;
    }
}
