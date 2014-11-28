package mr.shravan.examples.pattern.creational;


import com.journaldev.design.model.Computer;
import com.journaldev.design.model.PC;
import com.journaldev.design.model.Server;
 
public class ComputerFactory {
 
    public static Computer getComputer(String type, String ram, String hdd, String cpu){
        if("Desktop".equalsIgnoreCase(type)) return new PC(ram, hdd, cpu);
        else if("Laptop".equalsIgnoreCase(type)) return new Server(ram, hdd, cpu);
         
        return null;
    }
}