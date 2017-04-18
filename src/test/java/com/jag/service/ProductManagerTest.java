package com.jag.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jag.config.JagexConfiguration;
import com.jag.service.ProductManager;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JagexConfiguration.class})
public class ProductManagerTest {
	@Autowired
	private ProductManager productManager;
	
	@Test
	public void find(){
		productManager.find("VqKb4tyj9V6i");
	}
	
	@Test
	public void doesProductExist(){
		assertTrue(this.productManager.doesProductExist("VqKb4tyj9V6i"));
	}
	
	@Test
	public void productDoesntExist(){
		assertFalse(this.productManager.doesProductExist("789"));
	}
}
