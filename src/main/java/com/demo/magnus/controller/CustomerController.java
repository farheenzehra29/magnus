package com.demo.magnus.controller;

import com.demo.magnus.dto.CustomerDto;
import com.demo.magnus.service.application.CustomerApplicationService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CustomerDto> findByUuid(@PathVariable String uuid) throws IOException {
        CustomerDto customerDto = customerApplicationService.findByUuid(uuid);
        return ResponseEntity.ok(customerDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerDto>> search(@RequestParam(required = false) String firstName,
                                                    @RequestParam(required = false) String lastName,
                                                    @RequestParam(required = false) String gender,
                                                    @RequestParam(required = false) String email) {
        return ResponseEntity.ok(customerApplicationService.search(firstName, lastName, gender, email));

    }

    @PostMapping("/create")
    public CustomerDto create(@RequestBody CustomerDto customerDto) throws IOException {
        return customerApplicationService.create(customerDto);
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<CustomerDto> update(@PathVariable String uuid, @RequestBody CustomerDto customerDto) throws IOException {
        return ResponseEntity.ok(customerApplicationService.update(uuid, customerDto));
    }


    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity delete(@PathVariable String uuid) {
        customerApplicationService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}

