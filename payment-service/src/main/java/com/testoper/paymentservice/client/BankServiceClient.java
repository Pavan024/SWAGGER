package com.testoper.paymentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.testoper.paymentservice.request.JwtRequest;
import com.testoper.paymentservice.request.TransactionRequest;
import com.testoper.paymentservice.response.JWTTokenResponse;
import com.testoper.paymentservice.response.TransactionResponse;

/**
 * 
 * 
 * 
 * @author muralikrishnak
 *
 */
@FeignClient(url = "${bankService.domain}", name = "BANK-SERVICE-CLIENT")
public interface BankServiceClient {
	@RequestMapping(value = "/bankService/v1/transactions/withdraw", method = RequestMethod.POST, consumes = "application/json")
	public TransactionResponse withdraw(@RequestHeader(name = "Authorization") String authorization,
			@RequestBody TransactionRequest transactionRequest);

	@RequestMapping(value = "/auth", method = RequestMethod.POST, consumes = "application/json")
	public JWTTokenResponse getAuthToken(@RequestBody JwtRequest jwtRequest);
}
