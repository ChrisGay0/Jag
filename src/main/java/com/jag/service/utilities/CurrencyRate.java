package com.jag.service.utilities;

import java.util.Date;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Object to store currency conversion information
 * 
 * @author Chris Gay
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRate {
	private String base;
	private Date date;
	private HashMap<String, Float> rates;
	
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public HashMap<String, Float> getRates() {
		return rates;
	}
	public void setRates(HashMap<String, Float> rates) {
		this.rates = rates;
	}
}