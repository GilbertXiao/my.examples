/*
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mr.shravan.examples.drools.migration;

import java.util.Arrays;

import mr.shravan.examples.domain.Account;
import mr.shravan.examples.domain.Bank;
import mr.shravan.examples.domain.Customer;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

/**
 * This is a sample file to launch a rule package from a rule source file.
 *  -Ddrools.sequential=true
 */
public class MigrationTest {

    public static final void main(final String[] args) {
        stateful();
        //stateless(); 
    }

	private static void stateless() {
		KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        System.out.println(kc.verify().getMessages().toString());
        StatelessKieSession ksession = kc.newStatelessKieSession( "StatelessBankKS");

        Bank bank = new Bank();
        bank.setStatus(Bank.INACTIVE);
        bank.setMessage("Account is inactive");
        
        Customer cust = new Customer();
        cust.setCustNo("1");
        
        Account acc = new Account();
        acc.setAccNo("1");

        ksession.execute( Arrays.asList( new Object[]{bank, cust,acc} ) );

        System.out.println(bank);
	}

	private static void stateful() {
		// KieServices is the factory for all KIE services 
        KieServices ks = KieServices.Factory.get();
        
        // From the kie services, a container is created from the classpath
        KieContainer kc = ks.getKieClasspathContainer();
        
        // From the container, a session is created based on  
        // its definition and configuration in the META-INF/kmodule.xml file 
        KieSession ksession = kc.newKieSession("BankKS");
        
        
        // Once the session is created, the application can interact with it
        // In this case it is setting a global as defined in the 
        // org/drools/examples/helloworld/HelloWorld.drl file
//        ksession.setGlobal( "list",
//                            new ArrayList<Object>() );

        // The application can also setup listeners
//        ksession.addEventListener( new DebugAgendaEventListener() );
//        ksession.addEventListener( new DebugRuleRuntimeEventListener() );

//        // To setup a file based audit logger, uncomment the next line 
//         KieRuntimeLogger logger = ks.getLoggers().newFileLogger( ksession, "./helloworld" );
//        
//        // To setup a ThreadedFileLogger, so that the audit view reflects events whilst debugging,
//        // uncomment the next line
//         KieRuntimeLogger logge1 = ks.getLoggers().newThreadedFileLogger( ksession, "./helloworld", 1000 );
        
//        Agenda agenda = ksession.getAgenda();
//        agenda.getAgendaGroup( "test-1" ).setFocus();
        // The application can insert facts into the session
        final Bank bank = new Bank();
        bank.setStatus(Bank.INACTIVE);
        bank.setMessage("Account is inactive");
        
        Customer cust = new Customer();
        cust.setCustNo("1");
        
        Account acc = new Account();
        acc.setAccNo("1");
        
        ksession.insert(bank);
        ksession.insert(cust);
        ksession.insert(acc);

        // and fire the rules
        ksession.fireAllRules();
        
        // Remove comment if using logging
        // logger.close();

        // and then dispose the session
        ksession.dispose();
        System.out.println(bank);
	}

}
