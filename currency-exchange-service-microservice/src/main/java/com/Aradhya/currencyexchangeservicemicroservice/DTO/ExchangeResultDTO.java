package com.Aradhya.currencyexchangeservicemicroservice.DTO;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "CurrencyExchange")
public class ExchangeResultDTO {
	@Id
	private Long id;
	@Column(name = "Currency_from")
	private String from;
	@Column(name = "Currency_to")
	private String to;
	private BigDecimal convertionMultiple;
	private String environment;

	public ExchangeResultDTO(Long id, String from, String to, BigDecimal convertionMultiple) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.convertionMultiple = convertionMultiple;
	}
}
