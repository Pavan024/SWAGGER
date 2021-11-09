package com.testoper.paymentservice.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.testoper.paymentservice.exception.CustomizedExceptionHandler;
import com.testoper.paymentservice.service.PaymentService;
import com.testoper.paymentservice.util.DataBuilder;

/**
 * 
 * 
 * 
 * @author muralikrishnak
 *
 */
@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	PaymentService paymentService;

	@InjectMocks
	PaymentController paymentController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(paymentController)
				.setControllerAdvice(CustomizedExceptionHandler.class).build();
	}

	@Test
	public void testMakePayment() throws Exception {
		String request = new Gson().toJson(DataBuilder.getMockPaymentRequest());
		when(paymentService.makePayment(Mockito.any())).thenReturn(DataBuilder.getMockPayment());
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
				.post("/paymentService/v1/payments").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(request);
		ResultActions perform = mockMvc.perform(mockHttpServletRequestBuilder);
		MvcResult mvcResult = perform.andReturn();
		Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
	}
}