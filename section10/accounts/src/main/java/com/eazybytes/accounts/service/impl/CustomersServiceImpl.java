package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CardsDto;
import com.eazybytes.accounts.dto.CustomersDetailsDto;
import com.eazybytes.accounts.dto.LoansDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.ICustomersService;
import com.eazybytes.accounts.service.client.CardsFeignClients;
import com.eazybytes.accounts.service.client.LoansFeignClients;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClients cardsFeignClients;
    private LoansFeignClients loansFeignClients;

    @Override
    public CustomersDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomersDetailsDto customersDetailsDto = CustomerMapper.mapToCustomersDetailsDto(customer, new CustomersDetailsDto());
        customersDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClients.fetchLoanDetails(correlationId,mobileNumber);
        if(null != loansDtoResponseEntity) {
            customersDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClients.fetchCardsDetails(correlationId,mobileNumber);
        if(null != cardsDtoResponseEntity) {
            customersDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }

        return customersDetailsDto;
    }
}
