package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer,Account, Loans Information"
)
public class CustomersDetailsDto {

    @Schema(
            description = "Name of the Customer",example = "Shreyas"
    )
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @Schema(
            description = "Email Address of the Customer",example = "shreyask02@gmail.com"
    )
    @NotEmpty(message = "Name cannot be null or empty")
    @Email(message = "Email address should be valid value")
    private String email;

    @Schema(
            description = "Mobile Number of the Customer",example = "4384763920"
    )
    @Pattern(regexp = "($|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account Details of the customer"
    )
    private AccountsDto accountsDto;

    @Schema(
            description = "Loans Details of the customer"
    )
    private LoansDto loansDto;

    @Schema(
            description = "Cards Details of the customer"
    )
    private CardsDto cardsDto;
}

