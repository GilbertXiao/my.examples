package mr.shravan.examples.pattern.creational;

import com.journaldev.design.abstractfactory.PCFactory;
import com.journaldev.design.abstractfactory.ServerFactory;
import com.journaldev.design.factory.ComputerFactory;
import com.journaldev.design.model.Computer;
 
public class TestFactory {
 
    public static void main(String[] args) {
        Computer pc = ComputerFactory.getComputer("Desktop","2 GB","500 GB","2.4 GHz");
        Computer server = ComputerFactory.getComputer("Laptop","16 GB","1 TB","2.9 GHz");
        System.out.println("Factory PC Config::"+pc);
        System.out.println("Factory Server Config::"+server);
    }
 
}