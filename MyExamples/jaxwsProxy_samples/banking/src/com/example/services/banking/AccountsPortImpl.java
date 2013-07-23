package com.example.services.banking;

import javax.xml.ws.Holder;
import java.util.HashMap;


@javax.jws.WebService (endpointInterface="com.example.services.banking.BankingSEI", targetNamespace="http://www.example.com/services/Banking", serviceName="BankingService", portName="AccountsPort", wsdlLocation="WEB-INF/wsdl/BankingService.wsdl")
public class AccountsPortImpl{

    static HashMap accounts = new HashMap(); 

    public long createAccount(String owner, double initialBalance) {
        Account account = new Account(owner, initialBalance);
        accounts.put(account.getAccountNo(), account);
        return account.getAccountNo();
    }

    public void withdraw(long accountNumber, Holder<Double> amount) throws InsufficientFunds {
        Account account = (Account)accounts.get(accountNumber);
        if (account != null) {
            try {
                amount.value = account.withdraw(amount.value);
            } catch (Exception e) {
                com.example.schemas.banking.InsufficientFunds isfFault = new com.example.schemas.banking.InsufficientFunds();
                isfFault.setErrorCode(1);
                isfFault.setErrorMessage("Insufficient Funds");
                throw new InsufficientFunds("Insufficient Funds", isfFault, e);
            }
        } else {
            amount.value = (double)0;
        }
    }

    public void getAccountInfo(long accountNumber, Holder<Double> balance, Holder<String> owner) {
        Account account = (Account)accounts.get(accountNumber);
        if (account != null) {
            owner.value = account.getOwner();
            balance.value = account.getBalance();
        }
    }           


}