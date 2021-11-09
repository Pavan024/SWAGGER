package com.testoper.bankservice.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testoper.bankservice.entity.Account;
import com.testoper.bankservice.request.TransactionRequest;
import com.testoper.bankservice.response.TransactionResponse;
import com.testoper.bankservice.service.TransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@RestController
@RequestMapping(value = "/bankService/v1/transactions")
@Api(value = "Transaction Management", tags = { "Transaction Management" })
@Validated
public class TransactionController {
	@Autowired
	TransactionService transactionService;

	@ApiOperation(value = "Withdraws Money from Bank Account")
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, response = TransactionResponse.class, message = "Money withdrawn Successfully"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, response = String.class, message = "Invalid parameters"),
			@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, response = String.class, message = "Invalid Token / Without Token"),
			@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, response = String.class, message = "UnAuthorized Access") })
	@PostMapping(value = "/withdraw", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionResponse> withdraw(@Valid @RequestBody TransactionRequest transactionRequest) {
		Account account = transactionService.withdraw(transactionRequest);
		ModelMapper modelMapper = new ModelMapper();
		TransactionResponse transactionResponse = modelMapper.map(account, TransactionResponse.class);
		transactionResponse.setBankName(account.getBank().getName());
		return ResponseEntity.status(HttpStatus.OK).body(transactionResponse);
	}

	@ApiOperation(value = "Deposits Money to Bank Account")
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, response = TransactionResponse.class, message = "Money deposited Successfully"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, response = String.class, message = "Invalid parameters"),
			@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, response = String.class, message = "Invalid Token / Without Token"),
			@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, response = String.class, message = "UnAuthorized Access") })
	@PostMapping(value = "/deposit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionResponse> deposit(@Valid @RequestBody TransactionRequest transactionRequest) {
		Account account = transactionService.deposit(transactionRequest);
		ModelMapper modelMapper = new ModelMapper();
		TransactionResponse transactionResponse = modelMapper.map(account, TransactionResponse.class);
		transactionResponse.setBankName(account.getBank().getName());
		return ResponseEntity.status(HttpStatus.OK).body(transactionResponse);
	}
}
