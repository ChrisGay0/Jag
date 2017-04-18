package com.jag.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jag.config.JagexConfiguration;
import com.jag.enums.CurrencyCode;
import com.jag.service.utilities.CurrencyConvertor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JagexConfiguration.class})
public class CurrencyConvertorTest {
	@Autowired
	private CurrencyConvertor currencyConvertor;
	
	@Test
	public void convertGBP(){
		System.out.println(currencyConvertor.convertUsdToAnotherCurrency(CurrencyCode.GBP));
	}
	
	@Test(expected = IllegalStateException.class) 
	public void nullCheck(){
		System.out.println(currencyConvertor.convertUsdToAnotherCurrency(null));
	}
}
