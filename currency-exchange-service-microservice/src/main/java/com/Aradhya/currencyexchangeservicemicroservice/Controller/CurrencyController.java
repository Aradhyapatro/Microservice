package com.Aradhya.currencyexchangeservicemicroservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.Aradhya.currencyexchangeservicemicroservice.DTO.ExchangeResultDTO;
import com.Aradhya.currencyexchangeservicemicroservice.Repository.CurrencyExchangeRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CurrencyController {
	@Autowired
	private Environment env;
	@Autowired
	private CurrencyExchangeRepository repo;
	
	@GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
	public ExchangeResultDTO exchange(@PathVariable("from") String from,@PathVariable("to") String to) {
		String port=env.getProperty("local.server.port");
		log.info("The port on which the Currency Echange you called is running on port "+port);
		ExchangeResultDTO exchange=repo.findByFromAndTo(from, to);
		if(exchange==null) {
			throw new RuntimeException("Conversion Not Possible");
		}
		log.info("From = "+from+" to = "+to);
		log.info("Return data = "+exchange);
		exchange.setEnvironment(port);
		return exchange;
	}
	
	@GetMapping(path = "/sample-api")
//	@Retry(name = "sample-api",fallbackMethod = "fallback")
//	@CircuitBreaker(name = "default",fallbackMethod = "fallback")
	@RateLimiter(name = "default",fallbackMethod = "fallback")
	public String sampleApi() {
		log.info("Sample API Was Called just Now");
		ResponseEntity<String> data =new RestTemplate().getForEntity( "http://localhost:8080/getData", String.class);
		return data.getBody();
	}
	
	public String fallback(Exception ex) {
		return "Fallback Data";
	}
}