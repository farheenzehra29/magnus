package com.demo.magnus.service.application;

import com.demo.magnus.converter.CustomerConverter;
import com.demo.magnus.dto.CustomerDto;
import com.demo.magnus.model.Customer;
import com.demo.magnus.service.data.CustomerDataService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
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
        return customerDto;
    }

    public List<CustomerDto> search(String firstName, String lastName, String gender, String email) {
        List<Customer> customerList = customerDataService.search(firstName, lastName, gender, email);
        List<CustomerDto> customerDtoList = customerConverter.mapToDtoList(customerList);
        return customerDtoList;
    }
}
