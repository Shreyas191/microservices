package com.eazybytes.accounts.service.client;

import com.eazybytes.accounts.dto.CardsDto;
import jakarta.ws.rs.core.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClients {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<CardsDto> fetchCardsDetails(@RequestHeader("eazybank-correlation-id")
        String correlationId,
            @RequestParam String mobileNumber);
}
