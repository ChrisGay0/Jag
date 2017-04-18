package com.jag.service;

import java.util.Collection;

import com.jag.domain.ProductPackage;
import com.jag.enums.CurrencyCode;

/**
 * @author Chris Gay
 * 
 * Interface to manage the {@link ProductPackage} class
 */
public interface ProductPackageManager {
	/**
	 * Creates a {@link ProductPackage} and returns the generated id for the object.
	 * 
	 * @param {@link ProductPackage}
	 * @return the id of the newly created {@link ProductPackage}
	 */
	long create(ProductPackage productPackage);
	/**
	 * Finds a specific {@link ProductPackage} by its unique identifier
	 * 
	 * @param id
	 * @return the {@link ProductPackage} requested
	 */
	ProductPackage find(long id);
	/**
	 * Finds a specific {@link ProductPackage} by its unique identifier and converts the cost to the 
	 * specified currency
	 * 
	 * @param id
	 * @param currencyCode
	 * @return  the {@link ProductPackage} requested
	 */
	ProductPackage find(long id, CurrencyCode currencyCode);
	/**
	 * Persists any changes to the {@link ProductPackage}
	 * 
	 * @param {@link ProductPackage}
	 */
	void update(ProductPackage productPackage);
	/**
	 * deletes the {@link ProductPackage} relating to the id
	 * 
	 * @param id
	 */
	void delete(long id);
	/**
	 * Finds all {@link ProductPackage}
	 * 
	 * @return a collection of all {@link ProductPackage}
	 */
	Collection<ProductPackage> findAll();
	/**
	 * Clears all data from the system
	 */
	void reset();
}
