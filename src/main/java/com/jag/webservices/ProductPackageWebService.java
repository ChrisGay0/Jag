package com.jag.webservices;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jag.domain.ProductPackage;
import com.jag.enums.CurrencyCode;
import com.jag.service.ProductManager;
import com.jag.service.ProductPackageManager;

@Controller
@RequestMapping("/productPackage")
public class ProductPackageWebService {
	@Autowired
	private ProductPackageManager productPackageManager;
	@Autowired
	private ProductManager productManager;
	
	/**
	 * Endpoint to find all {@link ProductPackage}
	 * 
	 * @return the collection of all {@link ProductPackage} in the system
	 */
	@RequestMapping(path = "/findAll", method = RequestMethod.GET)
	public @ResponseBody Collection<ProductPackage> findAll() {
		 return this.productPackageManager.findAll();
	}

	/**
	 * Finds a {@link ProductPackage} by its unique id
	 * 
	 * @param id
	 * @return the {@link ProductPackage} relating to the id. Will be null if it doesn't exist
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public @ResponseBody ProductPackage find(@PathVariable (name="id", required=true) long id) {
		 return this.productPackageManager.find(id);
	}
	
	/**
	 * Finds a {@link ProductPackage} by its unique id and converts the cost to the given currency
	 * 
	 * @param id
	 * @param currency
	 * @return the {@link ProductPackage} relating to the id. Will be null if it doesn't exist
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/{currency}")
	public @ResponseBody ProductPackage findInCurrency(@PathVariable (name="id", required=true) long id, @PathVariable (name="currency", required=true) String currency) {
		 return this.productPackageManager.find(id, CurrencyCode.valueOf(currency));
	}
	
	/**
	 * 
	 * Creates a new {@link ProductPackage} with the given information
	 * 
	 * @param name
	 * @param description
	 * @param products
	 * @return a String containing the id of the newly generated {@link ProductPackage}
	 */
	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestParam(name="name", required=true) String name, @RequestParam(name="description", required=true) String description, @RequestParam(name="products", required=true) String[] products) {
		if(!validateProducts(products)){
			return "Invalid product ids supplied";
		}
		ProductPackage productPackage = new ProductPackage(name, description);
		populateProducts(productPackage, products);
		long id = this.productPackageManager.create(productPackage);
		
		return "new product created with id: " + id;
	}
	
	/**
	 * Updates an existing product package
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param products
	 * @return a String containing a success or failure message
	 */
	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestParam(name="id", required=true) long id, @RequestParam(name="name", required=true) String name, @RequestParam(name="description", required=true) String description, @RequestParam(name="products", required=true) String[] products) {
		if(!validateProducts(products)){
			return "Invalid product ids supplied";
		}
		ProductPackage productPackage = this.productPackageManager.find(id);
		productPackage.setName(name);
		productPackage.setDescription(description);
		populateProducts(productPackage, products);
		
		this.productPackageManager.update(productPackage);
		
		return "Product Updated";
	}
	
	/**
	 * Deletes the {@link ProductPackage} relating to the id
	 * 
	 * @param id
	 */
	@RequestMapping(path = "/delete", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@RequestParam(name="id", required=true) long id) {
		this.productPackageManager.delete(id);
	}
	
	//Method to populate a ProductPackage with the product objects that relate to the products parameter
	private void populateProducts(ProductPackage productPackage, String[] products){
		if(products != null && products.length > 0){
			for(String productId: products){
				productPackage.getProducts().add(this.productManager.find(productId));
			}
		}
	}
	
	//Validates that the product ids exist
	private boolean validateProducts(String[] products){
		if(products != null && products.length > 0){
			for(String productId: products){
				if(!this.productManager.doesProductExist(productId)){
					return false;
				}
			}
		}
		
		return true;
	}
}
