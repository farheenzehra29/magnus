package com.demo.magnus.service.data;

import com.demo.magnus.model.Customer;
import com.demo.magnus.repository.CustomerRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerDataService {

    private CustomerRepository customerRepository;

    private MongoTemplate mongoTemplate;

    public CustomerDataService(CustomerRepository customerRepository,
                               MongoTemplate mongoTemplate) {
        this.customerRepository = customerRepository;
        this.mongoTemplate = mongoTemplate;
    }


    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findByUuid(String uuid) {
        return customerRepository.findByUuid(uuid);
    }

    public List<Customer> search(String firstName, String lastName, String gender, String email) {

        Criteria criteria = new Criteria();

        if (Objects.nonNull(firstName)) {
            criteria = criteria.and("firstName").is(firstName);
        }

        if (Objects.nonNull(lastName)) {
            criteria = criteria.and("lastName").is(lastName);
        }

        if (Objects.nonNull(gender)) {
            criteria = criteria.and("gender").is(gender);
        }

        if (Objects.nonNull(email)) {
            criteria = criteria.and("email").is(email);
        }

        Query query = new Query();
        query.addCriteria(criteria);
        List<Customer> customerList = mongoTemplate.find(query, Customer.class);
        return customerList;

    }


    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(String uuid, Customer updatedCustomer) {
        Customer customerOld = customerRepository.findByUuid(uuid);
        updatedCustomer.setId(customerOld.getId());
        return customerRepository.save(updatedCustomer);
    }

    public void delete(String uuid) {
        Customer toDeleteCustomer = customerRepository.findByUuid(uuid);
        customerRepository.delete(toDeleteCustomer);
    }
}
