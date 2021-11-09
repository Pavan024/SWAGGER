package com.testoper.paymentservice.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.testoper.paymentservice.client.BankServiceClient;
import com.testoper.paymentservice.request.JwtRequest;
import com.testoper.paymentservice.response.JWTTokenResponse;

@Service
public class AuthService {

	@Autowired
	BankServiceClient bankServiceClient;

	@Value("${bankService.auth.username}")
	private String username;

	@Value("${bankService.auth.password}")
	private String password;

	/**
	 * 
	 * Gets Auth Token to call Bank Service
	 * 
	 * @return
	 */
	public String getAuthToken() {
		JWTTokenResponse tokenResponse = bankServiceClient
				.getAuthToken(new JwtRequest(username, decodePassword(password)));
		return "Bearer " + tokenResponse.getAccessToken();
	}

	/**
	 * 
	 * Decodes the Base64 Encoded Password
	 * 
	 * @param encodedPassword
	 * @return
	 */
	private String decodePassword(String encodedPassword) {
		Base64.Decoder decoder = Base64.getMimeDecoder();
		String to = new String(decoder.decode(encodedPassword));
		return to;
	}

}
