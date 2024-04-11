package com.Aradhya.limitsservicemicroservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Aradhya.limitsservicemicroservice.Configuration.Configuration;
import com.Aradhya.limitsservicemicroservice.DTO.Limits;

@RestController
public class LimitsController {
	@Autowired
	private Configuration config; 

	@GetMapping(path = "/")
	public Limits getReq() {
		return new Limits(config.getMaximum(),config.getMinimum());
	}
}
