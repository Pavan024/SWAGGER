package com.testoper.bankservice.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testoper.bankservice.entity.Account;
import com.testoper.bankservice.request.CreateAccountRequest;
import com.testoper.bankservice.response.AccountResponse;
import com.testoper.bankservice.response.CreateAccountResponse;
import com.testoper.bankservice.service.AccountService;

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
@RequestMapping(value = "/bankService/v1")
@Api(value = "Account Management", tags = { "Account Management" })
@Validated
public class AccountController {
	@Autowired
	AccountService accountService;

	@ApiOperation(value = "Creates an Account")
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, response = CreateAccountResponse.class, message = "Account created Successfully"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, response = String.class, message = "Invalid parameters"),
			@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, response = String.class, message = "Invalid Token / Without Token"),
			@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, response = String.class, message = "UnAuthorized Access") })
	@PostMapping(value = "/banks/{bankId}/accounts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateAccountResponse> createAccount(
			@Valid @Positive(message = "Invalid Bank Id") @PathVariable("bankId") Long bankId,
			@Valid @RequestBody CreateAccountRequest createAccountRequest) {
		Account createdAccount = accountService.createAccount(createAccountRequest, bankId);
		ModelMapper modelMapper = new ModelMapper();
		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(createdAccount, CreateAccountResponse.class));
	}

	@ApiOperation(value = "Gets an Account")
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, response = AccountResponse.class, message = "Account Details fetched Successfully"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, response = String.class, message = "Invalid parameters"),
			@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, response = String.class, message = "Invalid Token / Without Token"),
			@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, response = String.class, message = "UnAuthorized Access") })
	@GetMapping(value = "/banks/{bankId}/accounts/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountResponse> getAccount(
			@Valid @Positive(message = "Invalid Bank Id") @PathVariable("bankId") Long bankId,
			@Valid @Positive(message = "Invalid Account Id") @PathVariable("accountId") Long accountId) {
		Account account = accountService.getAccount(bankId, accountId);
		ModelMapper modelMapper = new ModelMapper();
		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(account, AccountResponse.class));
	}
}
