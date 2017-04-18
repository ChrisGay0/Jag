package com.jag.service.utilities;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jag.enums.CurrencyCode;

/**
 * Helper class to find current currency conversion rates
 * 
 * @author Chris Gay
 *
 */
@Service
public class CurrencyConvertor {
	private RestTemplate restTemplate;
	
	public CurrencyConvertor(){
		restTemplate = new RestTemplate();
	}
	
	public float convertUsdToAnotherCurrency(CurrencyCode currencyCode){
		if(currencyCode == null){
			throw new IllegalStateException("Currency code cannot be null");
		}
		//Find the current currency rates
		CurrencyRate currencyRate = restTemplate.getForObject("http://api.fixer.io/latest?base=USD", CurrencyRate.class);
		//Get the requested rate
		Float rateForCurrency = currencyRate.getRates().get(currencyCode.toString());
		
		if(rateForCurrency != null){
			return rateForCurrency;
		}
		else{
			throw new RuntimeException("Currency code: " + currencyCode + " is not supported by this method");
		}
	}
}
