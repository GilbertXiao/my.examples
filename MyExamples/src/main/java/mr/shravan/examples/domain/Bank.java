package mr.shravan.examples.domain;

import java.util.ArrayList;
import java.util.List;

import mr.shravan.examples.drools.DroolsExample.Message;

public class Bank {
	public static final int INACTIVE = 0;
	public static final int ACTIVE = 1;

	private String bankID;
	private String bankName;
	private String message;
	private int status;
	
	private List<Customer> customers = new ArrayList<Customer>();
	

	public String getBankID() {
		return bankID;
	}

	public void setBankID(String bankID) {
		this.bankID = bankID;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(final int status) {
		this.status = status;
	}

	public static Message doSomething(Message message) {
		return message;
	}

	public boolean isSomething(String msg, List<Object> list) {
		list.add(this);
		return this.message.equals(msg);
	}
	
	public boolean retTrue() {
		return true;
	}
	
	public boolean addCustomer(Customer c, Account a) {
		System.out.println("Adding customer...");
		customers.add(c);
		return true;
	}
}
