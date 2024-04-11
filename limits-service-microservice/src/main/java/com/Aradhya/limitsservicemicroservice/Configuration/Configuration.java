package com.Aradhya.limitsservicemicroservice.Configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties("limits-service")
@Data
@Component
public class Configuration {
	int minimum;
	int maximum;
}
