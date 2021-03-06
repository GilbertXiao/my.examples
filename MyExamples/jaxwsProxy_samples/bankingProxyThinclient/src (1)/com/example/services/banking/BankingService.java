
package com.example.services.banking;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;


/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI IBM 2.0_03-06/12/2007 07:44 PM(Raja)-fcs
 * Generated source version: 2.0
 * 
 */
@WebServiceClient(name = "BankingService", targetNamespace = "http://www.example.com/services/Banking", wsdlLocation = "WEB-INF/wsdl/BankingService.wsdl")
public class BankingService
    extends Service
{

    private final static URL BANKINGSERVICE_WSDL_LOCATION;

    static {
        URL url = null;
        try {
            url = new URL("file:/WEB-INF/wsdl/BankingService.wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BANKINGSERVICE_WSDL_LOCATION = url;
    }

    public BankingService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BankingService() {
        super(BANKINGSERVICE_WSDL_LOCATION, new QName("http://www.example.com/services/Banking", "BankingService"));
    }

    /**
     * 
     * @return
     *     returns BankingSEI
     */
    @WebEndpoint(name = "AccountsPort")
    public BankingSEI getAccountsPort() {
        return (BankingSEI)super.getPort(new QName("http://www.example.com/services/Banking", "AccountsPort"), BankingSEI.class);
    }

}
