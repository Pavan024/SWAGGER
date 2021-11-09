package com.testoper.paymentservice.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

/**
 * 
 * 
 * @author muralikrishnak
 *
 */
@RestController
@Api(value = "Application Monitoring Management", tags = { "Application Monitoring Management" })
public class ApplicationMonitorController {
	@GetMapping(value = "/actuator", produces = MediaType.APPLICATION_JSON_VALUE)
	public void actuator() {

	}
	
	@GetMapping(value = "/actuator/info", produces = MediaType.APPLICATION_JSON_VALUE)
	public void info() {

	}
	
	@GetMapping(value = "/actuator/health", produces = MediaType.APPLICATION_JSON_VALUE)
	public void health() {

	}
	
	@GetMapping(value = "/actuator/env", produces = MediaType.APPLICATION_JSON_VALUE)
	public void env() {

	}
}
