package mr.shravan.examples.domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Bank {
	public static final int INACTIVE = 0;
	public static final int ACTIVE = 1;
	private String message;
	private int status;
	private boolean condition1 = true;
	private boolean condition2 = true;
	private boolean condition3 = true;
	private boolean condition4 = true;
	
	private final PropertyChangeSupport changes  = new PropertyChangeSupport( this );
	
	private List<Customer> customers = new ArrayList<Customer>();

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

	public void setStatus(final int newState) {
		int oldState = this.status;
		this.status = newState;
		this.changes.firePropertyChange( "status",
                oldState,
                newState );
	}

	public boolean isCondition1() {
		return condition1;
	}

	public void setCondition1(boolean condition1) {
		this.condition1 = condition1;
	}

	public boolean isCondition2() {
		return condition2;
	}

	public void setCondition2(boolean condition2) {
		this.condition2 = condition2;
	}

	public boolean isCondition3() {
		return condition3;
	}

	public void setCondition3(boolean condition3) {
		this.condition3 = condition3;
	}

	public boolean isCondition4() {
		return condition4;
	}

	public void setCondition4(boolean condition4) {
		this.condition4 = condition4;
	}

	public boolean retTrue() {
		return true;
	}

	public boolean addCustomer(Customer c, Account a) {
		System.out.println("Adding customer...");
		customers.add(c);
		return true;
	}
    public void addPropertyChangeListener(final PropertyChangeListener l) {
        this.changes.addPropertyChangeListener( l );
    }

    public void removePropertyChangeListener(final PropertyChangeListener l) {
        this.changes.removePropertyChangeListener( l );
    }

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Bank[").append("Status:" + status)
				.append(",Message:" + message).append("]");
		return sb.toString();
	}
}
