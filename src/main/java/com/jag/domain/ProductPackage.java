package com.jag.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.util.CollectionUtils;

import com.jag.enums.CurrencyCode;

/**
 * Object to store information about a collection of {@link Product}
 * 
 * @author Chris Gay
 *
 */
public class ProductPackage {
	private long id;
	private String name;
	private String description;
	private Collection<Product> products = new ArrayList<Product>();
	private CurrencyCode currencyCode = CurrencyCode.USD;
	private float usdConversionRate = 1;
	
	public ProductPackage(String name, String description){
		this.name = name;
		this.description = description;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Collection<Product> getProducts() {
		return products;
	}
	public void setProducts(Collection<Product> products) {
		this.products = products;
	}
	public CurrencyCode getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(CurrencyCode currencyCode) {
		this.currencyCode = currencyCode;
	}
	public float getUsdConversionRate() {
		return usdConversionRate;
	}
	public void setUsdConversionRate(float usdConversionRate) {
		this.usdConversionRate = usdConversionRate;
	}

	public double getTotalPrice(){
		if(!CollectionUtils.isEmpty(this.products)){
			double totalUsdPrice = this.products.stream().mapToDouble(product -> product.getUsdPrice()).sum();
			
			return totalUsdPrice * this.usdConversionRate;
		}
		else {
			return 0;
		}
	}
}