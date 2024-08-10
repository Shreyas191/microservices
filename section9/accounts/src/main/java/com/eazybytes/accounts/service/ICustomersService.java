package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomersDetailsDto;

public interface ICustomersService {


    CustomersDetailsDto fetchCustomerDetails(String mobileNumber);
}
