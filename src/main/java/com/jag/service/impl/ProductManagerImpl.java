package com.jag.service.impl;

import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.jag.domain.Product;
import com.jag.service.ProductManager;

/**
 * Implementation of the product manager interface that finds products via a rest web service (https://product-service.herokuapp.com)
 * 
 * @author Chris Gay
 *
 */
@Service
public class ProductManagerImpl implements ProductManager{
	private RestTemplate restTemplate;
	
	/**
	 * {@inheritDoc}
	 */
	public ProductManagerImpl() {
		restTemplate = new RestTemplate();
		
		//Set the username and password for basic auth. Could parameterise these so they aren't hard coded
	    restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("user", "pass"));
	    //force the media type to json
	    restTemplate.getInterceptors().add((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request,body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        });

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean doesProductExist(String id) {
		try{
			find(id);
			
			return true;
		}
		catch(HttpClientErrorException e){
			//if the error code is 404 it means the object id didn't exist
			if(e.getRawStatusCode() == 404){
				return false;
			}
			//Otherwise something else went wrong so we need to throw the exception 
			else{
				throw e;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Product find(String id) {
		return restTemplate.getForObject("https://product-service.herokuapp.com/api/v1/products/" + id, Product.class);
	}

}
