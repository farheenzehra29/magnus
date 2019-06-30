package com.demo.magnus.service.application;

import com.demo.magnus.converter.CustomerConverter;
import com.demo.magnus.dto.CustomerDto;
import com.demo.magnus.model.Customer;
import com.demo.magnus.service.data.CustomerDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CustomerApplicationService {

    private CustomerConverter customerConverter;
    private CustomerDataService customerDataService;

    public CustomerApplicationService(CustomerConverter customerConverter, CustomerDataService customerDataService) {
        this.customerConverter = customerConverter;
        this.customerDataService = customerDataService;
    }

    public List<CustomerDto> findAll() {
        List<Customer> customerList = customerDataService.findAll();
        List<CustomerDto> customerDtoList = customerConverter.mapToDtoList(customerList);
        return customerDtoList;

    }

    public CustomerDto findByUuid(String uuid) throws IOException {
        Customer customer = customerDataService.findByUuid(uuid);
        CustomerDto customerDto = customerConverter.mapToDto(customer);
        customerDto.setFullName(customerDto.getFirstName() + " " + customerDto.getLastName());
        return customerDto;
    }

    public List<CustomerDto> search(String firstName, String lastName, String gender, String email) {
        List<Customer> customerList = customerDataService.search(firstName, lastName, gender, email);
        List<CustomerDto> customerDtoList = customerConverter.mapToDtoList(customerList);
        return customerDtoList;
    }

    public CustomerDto create(CustomerDto customerDto) throws IOException {
        customerDto.setUuid(UUID.randomUUID().toString());
        Customer customer = customerConverter.mapToDomain(customerDto);
        Customer createdCustomer = customerDataService.create(customer);
        log.info("Customer created successfully ,{}", createdCustomer.getUuid());
        return customerConverter.mapToDto(createdCustomer);
    }

    public CustomerDto update(String uuid, CustomerDto customerDto) throws IOException {
        Customer customer = customerConverter.mapToDomain(customerDto);
        Customer updatedCustomer = customerDataService.update(uuid,customer);
        log.info("Customer updated successfully ,{}", updatedCustomer.getUuid());
        return customerConverter.mapToDto(updatedCustomer);
    }

    public void delete(String uuid){
        customerDataService.delete(uuid);
        log.info("Customer delete with uuid:{}",uuid);
    }
}
