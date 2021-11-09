package com.testoper.bankservice.controller;

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
import com.testoper.bankservice.exception.CustomizedExceptionHandler;
import com.testoper.bankservice.service.TransactionService;
import com.testoper.bankservice.util.DataBuilder;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Mock
	TransactionService transactionService;

	@InjectMocks
	TransactionController transactionController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(transactionController)
				.setControllerAdvice(CustomizedExceptionHandler.class).build();
	}

	@Test
	public void testWithdraw() throws Exception {
		String request = new Gson().toJson(DataBuilder.getMockTransactionRequest());
		when(transactionService.withdraw(Mockito.any())).thenReturn(DataBuilder.getMockAccount());
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
				.post("/transactionService/v1/withdraw").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(request);
		ResultActions perform = mockMvc.perform(mockHttpServletRequestBuilder);
		MvcResult mvcResult = perform.andReturn();
		Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@Test
	public void testDeposit() throws Exception {
		String request = new Gson().toJson(DataBuilder.getMockTransactionRequest());
		when(transactionService.deposit(Mockito.any())).thenReturn(DataBuilder.getMockAccount());
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
				.post("/transactionService/v1/deposit").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(request);
		ResultActions perform = mockMvc.perform(mockHttpServletRequestBuilder);
		MvcResult mvcResult = perform.andReturn();
		Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
	}
}
