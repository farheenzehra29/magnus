package com.demo.magnus.controller;

import com.demo.magnus.dto.CustomerDto;
import com.demo.magnus.service.application.CustomerApplicationService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerApplicationService customerApplicationService;

    private CustomerController(CustomerApplicationService customerApplicationService) {

        this.customerApplicationService = customerApplicationService;
    }


    @GetMapping("/all")
    public List<CustomerDto> findAll() {
        List<CustomerDto> customerDtoList = customerApplicationService.findAll();
        return customerDtoList;
    }

    @GetMapping("/{uuid}")
    public CustomerDto findByUuid(@PathVariable String uuid) throws IOException {
        CustomerDto customerDto = customerApplicationService.findByUuid(uuid);
        return customerDto;
    }

    @GetMapping("/search")
    public List<CustomerDto> search(@RequestParam(required = false) String firstName,
                                    @RequestParam(required = false) String lastName,
                                    @RequestParam(required = false) String gender,
                                    @RequestParam(required = false) String email) {
        return customerApplicationService.search(firstName, lastName, gender, email);

    }
}

