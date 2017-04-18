package com.jag.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Object to store information about products
 * 
 * @author Chris Gay
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
	private String id;
	private String name;
	private double usdPrice;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getUsdPrice() {
		return usdPrice;
	}
	public void setUsdPrice(double usdPrice) {
		this.usdPrice = usdPrice;
	}
}
