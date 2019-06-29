package com.demo.magnus.repository;

import com.demo.magnus.model.Customer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, ObjectId> {

    Customer findByUuid(String uuid);
}
