package com.example.service;

import com.example.exception.DivideByZeroException;

public class MathService {
	
	public int sum(int a,int b){
		/*try {
			Thread.sleep(1000*6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		return a+b;
	}

	
	public int diff(int a,int b){
		return a-b;
	}

	public int divide(int a,int b) throws DivideByZeroException{
		if(b==0){
			throw new DivideByZeroException("Value can not be divided by zero!!!!");
		}
		return a/b;
	}
}
