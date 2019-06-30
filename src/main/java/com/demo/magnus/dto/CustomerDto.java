package com.demo.magnus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDto {

    private String uuid;

    private String firstName;
    private String lastName;

    private String fullName;

    private String email;

    private String gender;

    private String ipAddress;

    private String mobileNumber;

}