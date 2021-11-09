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

import com.testoper.bankservice.entity.Bank;
import com.testoper.bankservice.request.CreateBankRequest;
import com.testoper.bankservice.response.CreateBankResponse;
import com.testoper.bankservice.service.BankService;

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
@Api(value = "Bank Management", tags = { "Bank Management" })
@Validated
public class BankController {

	@Autowired
	BankService bankService;

	@ApiOperation(value = "Creates a Bank")
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, response = CreateBankResponse.class, message = "Bank created Successfully"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, response = String.class, message = "Invalid parameters"),
			@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, response = String.class, message = "Invalid Token / Without Token"),
			@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, response = String.class, message = "UnAuthorized Access") })
	@PostMapping(value = "/banks", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateBankResponse> createBank(@Valid @RequestBody CreateBankRequest createBankRequest) {
		Bank createdBank = bankService.createBank(createBankRequest);
		ModelMapper modelMapper = new ModelMapper();
		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(createdBank, CreateBankResponse.class));
	}

	@ApiOperation(value = "Gets a Bank")
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, response = Bank.class, message = "Bank Details fetched Successfully"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, response = String.class, message = "Invalid parameters"),
			@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, response = String.class, message = "Invalid Token / Without Token"),
			@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, response = String.class, message = "UnAuthorized Access") })
	@GetMapping(value = "/banks/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bank> getBank(@Valid @Positive(message = "Invalid Bank Id") @PathVariable("id") Long id) {
		Bank bank = bankService.getBank(id);
		return ResponseEntity.status(HttpStatus.OK).body(bank);
	}
}
