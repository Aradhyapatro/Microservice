package com.Aradhya.CurrencyConversion.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Aradhya.CurrencyConversion.Data.currencyConversion;

//@FeignClient(name = "currency-exchange-service-microservice",url = "http://localhost:8000")
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {
	@GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
	public currencyConversion getData
	(@PathVariable("from") String from,@PathVariable("to") String to);
	
}
