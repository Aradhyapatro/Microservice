package com.Aradhya.CurrencyConversion.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.Aradhya.CurrencyConversion.Data.currencyConversion;
import com.Aradhya.CurrencyConversion.feign.CurrencyExchangeProxy;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CurrencyConversionController {
	@Autowired
	private CurrencyExchangeProxy proxy;
	
//	public CurrencyConversionController(CurrencyExchangeProxy proxy) {
//		super();
//		this.proxy = proxy;
//	}

	@GetMapping(path="/currency-conversion/from/{from}/and/to/{to}/quantity/{quantity}")
	public currencyConversion convert
	(@PathVariable("from") String from,@PathVariable("to") 
	String to,@PathVariable("quantity") BigDecimal quantity) {
		
		HashMap<String, String> uriVariable=new HashMap<String,String>();
		uriVariable.put("from", from);
		uriVariable.put("to", to);
		
		ResponseEntity<currencyConversion> data= new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",currencyConversion.class,uriVariable);
		currencyConversion d=data.getBody();
		log.info(""+data);
		return new currencyConversion(d.getId(),from,to,quantity,d.getConvertionMultiple(),quantity.multiply(d.getConvertionMultiple()),d.getEnvironment());
	}
	
	@GetMapping(path="/currency-conversion-feign/from/{from}/and/to/{to}/quantity/{quantity}")
	public currencyConversion convert2
	(@PathVariable("from") String from,@PathVariable("to") 
	String to,@PathVariable("quantity") BigDecimal quantity) {
		currencyConversion d = proxy.getData(from, to);
		log.info("feign data = "+d);
		return new currencyConversion(d.getId(),from,to,quantity,d.getConvertionMultiple(),quantity.multiply(d.getConvertionMultiple()),d.getEnvironment());
	}
}
