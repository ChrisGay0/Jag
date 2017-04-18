package com.jag.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jag.config.JagexConfiguration;
import com.jag.domain.ProductPackage;
import com.jag.service.ProductPackageManager;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JagexConfiguration.class})
public class ProductPackageManagerTest {
	@Autowired
	private ProductPackageManager productPackageManager;
	
	@Before
	public void setupData(){
		for(int i = 1; i < 4; i++){
			this.productPackageManager.create(generatePackage("Pack " + i));
		}
	}
	
	@After
	public void clearData(){
		this.productPackageManager.reset();
	}
	
	@Test
	public void findAll(){
		assertEquals(productPackageManager.findAll().size(), 3);
	}
	
	@Test
	public void create(){
		ProductPackage productPackage = generatePackage("Test Create");
		this.productPackageManager.create(productPackage);
		assertEquals(productPackageManager.findAll().size(), 4);
		assertEquals(productPackage.getId(), 4);
	}
	
	@Test
	public void update(){
		ProductPackage existingPackage = this.productPackageManager.find(1);
		existingPackage.setName("XYZ");
		this.productPackageManager.update(existingPackage);
		assertEquals(productPackageManager.findAll().size(), 3);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void updateWithError(){
		ProductPackage newPackage = new ProductPackage("Error Test", "Error Test");
		newPackage.setId(77);
		this.productPackageManager.update(newPackage);
	}
	
	@Test
	public void delete(){
		this.productPackageManager.delete(1);
		assertEquals(productPackageManager.findAll().size(), 2);
	}
	
	@Test
	public void deleteNonExisting(){
		this.productPackageManager.delete(100);
		assertEquals(productPackageManager.findAll().size(), 3);
	}
	
	@Test
	public void findExisitng(){
		assertNotNull(this.productPackageManager.find(1));
	}
	
	@Test
	public void findNonExisitng(){
		assertNull(this.productPackageManager.find(11));
	}
	
	private ProductPackage generatePackage(String name){
		return new ProductPackage(name, "Test package");
	}
}
