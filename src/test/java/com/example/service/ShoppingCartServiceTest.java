package com.example.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.example.exception.ProductNotFoundException;
import com.example.model.Product;

@RunWith(JUnit4.class)
public class ShoppingCartServiceTest {

	ShoppingCartService shoppingCartService = null;

	@Before
	public void init() {
		shoppingCartService = new ShoppingCartServiceImpl();
	}

	@After
	public void clear() {
		shoppingCartService = null;
	}

	@Test(expected = ProductNotFoundException.class)
	public void testRemoveNonExistentItem() throws ProductNotFoundException {
		Product product1 = new Product(100, "Rolax Watch", 8000.00, 1);
		Product product2 = new Product(101, "Laptop", 1000.00, 1);

		shoppingCartService.addItem(product1);
		shoppingCartService.addItem(product2);

		shoppingCartService.removeItem(104);
	}

	
	@Test
	public void testClearCart() {

		Product product1 = new Product(100, "Rolax Watch", 8000.00, 1);
		Product product2 = new Product(101, "Laptop", 1000.00, 1);

		shoppingCartService.addItem(product1);
		shoppingCartService.addItem(product2);

		assertEquals(2, shoppingCartService.countItems());
		shoppingCartService.clearCart();
		assertEquals(0, shoppingCartService.countItems());
		assertEquals(0, shoppingCartService.showDetail().length);
			
	}


	@Test
	public void testShowDetail() {

		Product product1 = new Product(100, "Rolax Watch", 8000.00, 1);
		Product product2 = new Product(101, "Laptop", 1000.00, 1);

		shoppingCartService.addItem(product1);
		shoppingCartService.addItem(product2);

		
		assertNotNull(shoppingCartService.showDetail());
		assertEquals(2, shoppingCartService.showDetail().length);

		Product p1 = (Product)shoppingCartService.showDetail()[0];
		Product p2 = (Product)shoppingCartService.showDetail()[1];

		assertTrue(p1.getTitle().equals("Rolax Watch"));
		assertTrue(p2.getTitle().equals("Laptop"));
	}

	@Test
	public void testRemoveDuplicateItem() {
		Product product1 = new Product(100, "Rolax Watch", 8000.00, 1);
		Product product2 = new Product(101, "Laptop", 1000.00, 1);

		shoppingCartService.addItem(product1);
		shoppingCartService.addItem(product1);
		shoppingCartService.addItem(product1);
		shoppingCartService.addItem(product1);
		shoppingCartService.addItem(product2);

		assertEquals(5, shoppingCartService.countItems());

		try {
			shoppingCartService.removeItem(100);
			assertEquals(4, shoppingCartService.countItems());
			assertEquals(2, shoppingCartService.showDetail().length);

		} catch (ProductNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testRemoveItem() {
		Product product1 = new Product(100, "Rolax Watch", 8000.00, 1);
		Product product2 = new Product(101, "Laptop", 1000.00, 1);
		Product product3 = new Product(102, "Radio", 1000.00, 1);

		shoppingCartService.addItem(product1);
		shoppingCartService.addItem(product2);
		shoppingCartService.addItem(product3);

		assertEquals(3, shoppingCartService.countItems());

		try {
			shoppingCartService.removeItem(100);
			assertEquals(2, shoppingCartService.countItems());
			assertEquals(2, shoppingCartService.showDetail().length);

		} catch (ProductNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testTotalPrice() {
		Product product1 = new Product(100, "Rolax Watch", 8000.00, 1);
		Product product2 = new Product(101, "Laptop", 1000.00, 1);
		Product product3 = new Product(102, "Radio", 1000.00, 1);

		shoppingCartService.addItem(product1);
		shoppingCartService.addItem(product1);
		shoppingCartService.addItem(product1);
		shoppingCartService.addItem(product2);
		shoppingCartService.addItem(product2);
		shoppingCartService.addItem(product3);
		shoppingCartService.addItem(product3);

		assertEquals(28000.00, shoppingCartService.totalPrice(), 0.00);
	}

	@Test
	public void testFiveItem() {
		Product product1 = new Product(100, "Rolax Watch", 780000.00, 1);
		Product product2 = new Product(101, "Laptop", 10000.00, 1);
		Product product3 = new Product(102, "Radio", 9000.00, 1);
		Product product4 = new Product(103, "Speaker", 2000.00, 1);

		shoppingCartService.addItem(product1);
		shoppingCartService.addItem(product2);
		shoppingCartService.addItem(product3);
		shoppingCartService.addItem(product4);
		shoppingCartService.addItem(product4);

		assertEquals(5, shoppingCartService.countItems());
	}

	@Test
	public void testAddItem() {
		Product product = new Product(100, "Rolax Watch", 780000.00, 1);
		shoppingCartService.addItem(product);
		assertEquals(1, shoppingCartService.countItems());
	}

	@Test
	public void testDuplicateAddItem() {
		Product product = new Product(100, "Rolax Watch", 780000.00, 1);
		shoppingCartService.addItem(product);
		shoppingCartService.addItem(product);
		assertEquals(2, shoppingCartService.countItems());
	}

	
	@Test
	public void testPlaceOrder() {

		Product product1 = new Product(100, "Rolax Watch", 780000.00, 1);
		Product product2 = new Product(101, "Laptop", 10000.00, 1);

		shoppingCartService.addItem(product1);
		shoppingCartService.addItem(product2);
		shoppingCartService.addItem(product2);

		assertNotNull(shoppingCartService.placeOrder());
		assertTrue("Order ID should start with 'O'",shoppingCartService.placeOrder().startsWith("O"));
		assertTrue("Order ID length should be 11",shoppingCartService.placeOrder().length() == 11);
	}

}
