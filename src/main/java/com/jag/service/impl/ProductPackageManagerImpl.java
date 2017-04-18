package com.jag.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jag.domain.ProductPackage;
import com.jag.enums.CurrencyCode;
import com.jag.service.ProductPackageManager;
import com.jag.service.utilities.CurrencyConvertor;

/**
 * Implementation of the product package manager interface that manages the {@link ProductPackage} Object
 * 
 * @author Chris Gay
 *
 */
@Service
public class ProductPackageManagerImpl implements ProductPackageManager{
	@Autowired
	private CurrencyConvertor currencyConvertor;
	//Stores the ProductPackages in memory
	private Collection<ProductPackage> packagesInMemory = new ArrayList<ProductPackage>();
	//used as a counter for the next id to use for a ProductPackage
	private long nextId = 1;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long create(ProductPackage productPackage) {
		if(doesProductPackageExist(productPackage.getId())){
			throw new IllegalArgumentException("Product package id:" + productPackage.getId() + " already exists");
		}
		productPackage.setId(generateNextId());
		this.packagesInMemory.add(productPackage);
		
		return productPackage.getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProductPackage find(long id) {
		//Filter the list to just the package that matches the id parameter
		ProductPackage productPackage = this.packagesInMemory.stream().filter(existingProductPackage -> existingProductPackage.getId() == id).findFirst().orElse(null);
		//The find by currency method overrides the values for currencyCode and usdConversionRate so it needs to be reset
		if(productPackage != null){
			productPackage.setCurrencyCode(CurrencyCode.USD);
			productPackage.setUsdConversionRate(1);
		}
		
		return productPackage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProductPackage find(long id, CurrencyCode currencyCode) {
		ProductPackage productPackage = find(id);
		
		//Set the currency code and the associated conversion rate before returning the object
		productPackage.setCurrencyCode(currencyCode);
		productPackage.setUsdConversionRate(currencyConvertor.convertUsdToAnotherCurrency(currencyCode));
		
		return productPackage;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(ProductPackage productPackage) {
		ProductPackage existingPackage = find(productPackage.getId());
		//If the package doesn't exist we need to throw an exception
		if(existingPackage == null){
			throw new IllegalArgumentException("Product package id:" + productPackage.getId() + " does not exist");
		}
		//clear the old object from memory before adding in the new one. A bit messy and using a database would make this much cleaner
		delete(existingPackage.getId());
		this.packagesInMemory.add(productPackage);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(long id) {
		this.packagesInMemory.remove(find(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<ProductPackage> findAll() {
		return this.packagesInMemory;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset() {
		this.packagesInMemory.clear();
		this.nextId = 1;
	}
	
	private boolean doesProductPackageExist(long id){
		return find(id) != null;
	}
	
	private long generateNextId(){
		return nextId++;
	}
}
