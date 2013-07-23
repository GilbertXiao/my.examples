package com.example.client;

import java.net.URL;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import com.example.services.banking.BankingSEI;
import com.example.services.banking.BankingService;

public class BankingClient {
	public static String testProxy() {
		
		String retVal=null;

		try {
//			 Create a static Service instance			
			URL wsdlLocation =
				new 
				URL("http://localhost:9080/banking/BankingService?wsdl");
			QName serviceName =
				new QName("http://www.example.com/services/Banking",
				"BankingService");

			BankingService service = new BankingService(wsdlLocation, serviceName);
//			 Create a Dynamic Proxy client
			BankingSEI port = service.getAccountsPort();

			BindingProvider bp = (BindingProvider) port;

//			 (Optional) Configure RequestContext with endpoint's URL
			Map<String, Object> rc = bp.getRequestContext();
			rc.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, 
			"http://wsx3:9080/banking/BankingService");
//			 Create an account with $10 in it
			long accountNumber = port.createAccount("Joe Customer", (double)10.00);
//			 Create the Holder parameters
			Holder<String> owner = new Holder<String>();
			Holder<Double> balance = new Holder<Double>();

//			 Get account information
			port.getAccountInfo(accountNumber, balance, owner);

			retVal = "Account number " + accountNumber;
			retVal += ", Account belongs to " + owner.value;
			retVal += ", Account balance is " + balance.value;
			
		} catch (Exception e) {
			retVal = "Caught exception: "+e.toString();
			e.printStackTrace();
		}
		
		return retVal;

	}

}
