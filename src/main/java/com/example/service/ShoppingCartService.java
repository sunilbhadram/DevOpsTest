package com.example.service;

import com.example.exception.ProductNotFoundException;
import com.example.model.Product;

public interface ShoppingCartService {

	void addItem(Product product);

	int countItems();

	double totalPrice();

	void removeItem(int pid) throws ProductNotFoundException;

	Object[] showDetail();

	void clearCart();

	String placeOrder();

}
