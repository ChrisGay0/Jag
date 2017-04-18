package com.jag.service;

import com.jag.domain.Product;

/**
 * Interface to manage the {@link Product} class
 * 
 * @author Chris Gay
 */
public interface ProductManager {
	/**
	 * Method to validate if a {@link Product} exists with the given id
	 * 
	 * @param id
	 * @return true if the {@link Product} exists, false if it doesn't
	 */
	boolean doesProductExist(String id);
	/**
	 * Finds the {@link Product} relating to the given id
	 * 
	 * @param id
	 * @return the {@link Product} with the given id.
	 */
	Product find(String id);
}
