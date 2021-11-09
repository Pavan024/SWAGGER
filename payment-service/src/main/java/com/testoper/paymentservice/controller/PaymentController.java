package com.testoper.paymentservice.controller;

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

import com.testoper.paymentservice.entity.Payment;
import com.testoper.paymentservice.request.PaymentRequest;
import com.testoper.paymentservice.response.PaymentResponse;
import com.testoper.paymentservice.service.PaymentService;

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
@RequestMapping(value = "/paymentService/v1")
@Api(value = "Payment Management", tags = { "Payment Management" })
@Validated
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	@ApiOperation(value = "Makes Payment")
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, response = PaymentResponse.class, message = "Payment Successful"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, response = String.class, message = "Invalid parameters"),
			@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, response = String.class, message = "Invalid Token / Without Token"),
			@ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, response = String.class, message = "UnAuthorized Access") })
	@PostMapping(value = "/payments", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentResponse> makePayment(@Valid @RequestBody PaymentRequest paymentRequest) {
		Payment payment = paymentService.makePayment(paymentRequest);
		ModelMapper modelMapper = new ModelMapper();
		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(payment, PaymentResponse.class));
	}

}
