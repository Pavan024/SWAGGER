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
import com.testoper.bankservice.service.AccountService;
import com.testoper.bankservice.util.DataBuilder;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Mock
	AccountService accountService;

	@InjectMocks
	AccountController accountController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(accountController)
				.setControllerAdvice(CustomizedExceptionHandler.class).build();
	}

	@Test
	public void testCreateAccount() throws Exception {
		String request = new Gson().toJson(DataBuilder.getMockCreateAccountRequest());
		when(accountService.createAccount(Mockito.any(), Mockito.anyLong())).thenReturn(DataBuilder.getMockAccount());
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
				.post("/accountService/v1/banks/98/accounts").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(request);
		ResultActions perform = mockMvc.perform(mockHttpServletRequestBuilder);
		MvcResult mvcResult = perform.andReturn();
		Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
	}

	@Test
	public void testGetAccount() throws Exception {
		when(accountService.getAccount(Mockito.anyLong(), Mockito.anyLong())).thenReturn(DataBuilder.getMockAccount());
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
				.get("/accountService/v1/banks/98/accounts/49").contentType(MediaType.APPLICATION_JSON_VALUE);
		ResultActions perform = mockMvc.perform(mockHttpServletRequestBuilder);
		MvcResult mvcResult = perform.andReturn();
		Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
	}
}
