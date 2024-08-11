package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.CustomersDetailsDto;
import com.eazybytes.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

    public static CustomersDetailsDto mapToCustomersDetailsDto(Customer customer, CustomersDetailsDto customersDetailsDto) {
        customersDetailsDto.setName(customer.getName());
        customersDetailsDto.setEmail(customer.getEmail());
        customersDetailsDto.setMobileNumber(customer.getMobileNumber());
        return customersDetailsDto;
    }
}
