package com.Aradhya.currencyexchangeservicemicroservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Aradhya.currencyexchangeservicemicroservice.DTO.ExchangeResultDTO;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<ExchangeResultDTO, Long>{
	ExchangeResultDTO findByFromAndTo(String from,String to);
}
