package com.Aradhya.CurrencyConversion.Data;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class currencyConversion {
	Long id;
	String from;
	String to;
	BigDecimal Quantity;
	BigDecimal convertionMultiple;
	BigDecimal total;
	String environment;
	
	public currencyConversion(Long id, String currency_from, String currency_to, BigDecimal quantity,
			BigDecimal conversionMultiple, BigDecimal total) {
		super();
		this.id = id;
		this.from = currency_from;
		this.to = currency_to;
		Quantity = quantity;
		this.convertionMultiple = conversionMultiple;
		this.total = total;
	}
}
