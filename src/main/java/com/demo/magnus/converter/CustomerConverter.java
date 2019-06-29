package com.demo.magnus.converter;

import com.demo.magnus.dto.CustomerDto;
import com.demo.magnus.model.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CustomerConverter {

    private ObjectMapper objectMapper;

    public CustomerConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public CustomerDto mapToDto(Customer customer) throws IOException {
        CustomerDto customerDto = objectMapper.readValue(objectMapper.writeValueAsString(customer), CustomerDto.class);
        return customerDto;
    }

    public Customer mapToDomain(CustomerDto customerDto) throws IOException {
        Customer customer = objectMapper.readValue(objectMapper.writeValueAsString(customerDto), Customer.class);
        return customer;
    }

    public List<CustomerDto> mapToDtoList(List<Customer> customerList) {
        List<CustomerDto> customerDtoList = null;
        try {
            customerDtoList = objectMapper.readValue(objectMapper.writeValueAsString(customerList),
                    new TypeReference<List<CustomerDto>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customerDtoList;
    }

    public List<Customer> mapToDomainList(List<CustomerDto> customerDtoList) throws IOException {
        List<Customer> customerList = objectMapper.readValue(objectMapper.writeValueAsString(customerDtoList),
                new TypeReference<List<Customer>>() {
                });
        return customerList;
    }
}
