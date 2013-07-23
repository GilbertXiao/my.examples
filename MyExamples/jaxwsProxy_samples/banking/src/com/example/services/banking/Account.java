package com.example.services.banking;

import java.util.Random;
import java.lang.Exception;

public class Account {
	String owner;
	double balance;
	long accountNo;
	
	static Random randomGen = new Random(); 
	
	Account(String inOwner, double initialBalance) {
		owner=inOwner;
		balance=initialBalance;
		accountNo=randomGen.nextLong();
		while (accountNo < 0) {
			accountNo=randomGen.nextLong();
		}
	}
	
	public String getOwner() { return owner; }
	public double getBalance() {return balance; }
	public long getAccountNo() {return accountNo; }
	
	public double withdraw (double amount) throws Exception {
		double tmpBalance = balance - amount;
		if (tmpBalance < 0) {
			throw new Exception("Insufficient Funds");
		}
		balance = tmpBalance;
		return balance;		
	}

}
