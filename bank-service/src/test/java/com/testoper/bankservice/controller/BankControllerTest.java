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
import com.testoper.bankservice.service.BankService;
import com.testoper.bankservice.util.DataBuilder;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@ExtendWith(MockitoExtension.class)
public class BankControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Mock
	BankService bankService;

	@InjectMocks
	BankController bankController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(bankController).setControllerAdvice(CustomizedExceptionHandler.class)
				.build();
	}

	@Test
	public void testCreateBank() throws Exception {
		String request = new Gson().toJson(DataBuilder.getMockCreateBankRequest());
		when(bankService.createBank(Mockito.any())).thenReturn(DataBuilder.getMockBank());
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
				.post("/bankService/v1/banks").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(request);
		ResultActions perform = mockMvc.perform(mockHttpServletRequestBuilder);
		MvcResult mvcResult = perform.andReturn();
		Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@Test
	public void testGetBank() throws Exception {
		when(bankService.getBank(Mockito.anyLong())).thenReturn(DataBuilder.getMockBank());
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
				.get("/bankService/v1/banks/25").contentType(MediaType.APPLICATION_JSON_VALUE);
		ResultActions perform = mockMvc.perform(mockHttpServletRequestBuilder);
		MvcResult mvcResult = perform.andReturn();
		Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
	}
}
