package com.example.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.exception.ProductNotFoundException;
import com.example.model.Product;

public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	Map<Integer, Product> cart = null;
	
	public ShoppingCartServiceImpl() {
			cart = new HashMap<>();
	}
			
	
	@Override
	public void addItem(Product product){
		int pid = product.getId();
		if(cart.containsKey(pid)){
			Product pr = cart.get(pid);
			pr.setQty(pr.getQty()+1);
		}else{
			cart.put(pid, product);
		}
	}
	
	@Override
	public int countItems(){
		int count = 0;
		Collection<Product> products = cart.values();
		for(Product product : products){
			count = count + product.getQty();
		}
		return count;
	}

	@Override
	public double totalPrice(){
		double total = 0;
		Collection<Product> products = cart.values();
		for(Product product : products){
			double temp = product.getPrice()*product.getQty();
			total = total + temp;
		}
		return total;
	}

	@Override
	public void removeItem(int pid) throws ProductNotFoundException {
		if(cart.containsKey(pid)){
			Product pr = cart.get(pid);
			pr.setQty(pr.getQty()-1);
			
			if(pr.getQty()==0)
				cart.remove(pid);
		}else{
			throw new ProductNotFoundException("Prodct with ID : "+pid+" does not exist in the cart!!!!");
		}
	}
		
	@Override
	public Object[] showDetail(){
		return cart.values().toArray();
	}
	
	@Override
	public void clearCart(){
		cart.clear();
	}
	
	@Override
	public String placeOrder(){
		//DB logic
		System.out.println("Order has been placed!!!");
		String orderId =""+System.nanoTime();
		orderId ="O"+orderId.substring(5,15);
		return orderId;
	}
	
	
	

}
