package com.example.service;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.example.exception.DivideByZeroException;
import com.example.service.MathService;

@RunWith(JUnit4.class)
public class MathServiceTest {
	
	MathService mathService =null;
	
	@Before
	public void init(){
		System.out.println("Inside init()");
		mathService = new MathService();
	}
	
	@After
	public void clear(){
		System.out.println("Inside clear()");
		mathService = null;
	}

	@Test(timeout=5000)
	public void testSumSLA(){
		mathService.sum(50, 5000);
	}

	
	@Test(expected=DivideByZeroException.class)
	public void testDivideForException() throws DivideByZeroException{
			mathService.divide(50, 0);
	}

	
	@Test
	public void testDivide(){
		try {
			assertEquals(5, mathService.divide(50, 10));
		} catch (DivideByZeroException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDiff(){
		assertEquals("Diff failed!!!!",10, mathService.diff(50, 40));
	}

	
	@Test
	public void testSum(){
		int rs = mathService.sum(50, 40);
		assertEquals(90, rs);
	}

	@Test
	public void testZeroSum(){
		int rs = mathService.sum(50, 0);
		assertEquals(50, rs);
	}


	@Test
	public void testNegativeSum(){
		int rs = mathService.sum(-10, -20);
		assertEquals(-30, rs);
	}

}
