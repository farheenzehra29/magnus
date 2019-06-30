package com.demo.magnus.service.application;

import com.demo.magnus.converter.CustomerConverter;
import com.demo.magnus.dto.CustomerDto;
import com.demo.magnus.model.Customer;
import com.demo.magnus.service.data.CustomerDataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@Slf4j
public class CustomerApplicationServiceTest {

    @Mock
    private CustomerDataService customerDataService;

    private CustomerConverter customerConverter;

    @InjectMocks
    private CustomerApplicationService customerApplicationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customerConverter = mock(CustomerConverter.class);
        customerApplicationService = new CustomerApplicationService(customerConverter, customerDataService);
    }

    @Test
    public void givenUuid_Return_CustomerDto() throws Exception {
        String uuid = "93b2aaa-11b1-47ea-8074-fbad332169f3";

        log.info("Uuid {}", uuid);
        Customer customer = new Customer();
        customer.setFirstName("farheen");
        customer.setLastName("Zehra");
        customer.setEmail("farheen@gmail.com");
        customer.setGender("Female");
        customer.setUuid(uuid);

        //builder pattern in lombok
        CustomerDto customerDto = CustomerDto.builder()
                .firstName("farheen")
                .lastName("Zehra")
                .email("farheen@gmail.com")
                .gender("Female")
                .uuid(uuid)
                .build();
        when(customerDataService.findByUuid(anyString())).thenReturn(customer);
        when(customerConverter.mapToDto(any(Customer.class))).thenReturn(customerDto);
        CustomerDto actualCustomerDto = customerApplicationService.findByUuid(uuid);
        Assert.assertEquals("farheen Zehra", actualCustomerDto.getFullName());
        verify(customerConverter,times(1)).mapToDto(customer);
    }

    @Test(expected = RuntimeException.class)
    public void givenUuid_Throw_DatabaseException() throws Exception{
        String uuid = "93b2aaa-11b1-47ea-8074-fbad332169f3";
        when(customerDataService.findByUuid(anyString())).thenThrow(new RuntimeException());
        CustomerDto actualCustomerDto = customerApplicationService.findByUuid(uuid);
        verify(customerConverter,never()).mapToDto(any());
    }


}
