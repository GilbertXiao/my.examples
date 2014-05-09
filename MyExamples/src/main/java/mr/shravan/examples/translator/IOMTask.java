package mr.shravan.examples.translator;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.manh.inegration.iom.calendar.TXML;

public class IOMTask implements Processor {

	@Override
	public void process(Exchange xChange) throws Exception {
		System.out.println("end time :" + System.currentTimeMillis());
		TXML tXML = (TXML) xChange.getProperty("TXML");
		if (tXML != null) {
			System.out.println("Calendar object(s) size :"
					+ tXML.getMessage().getCALENDAR().size());
		} else {
			System.out.println("tXML is null");
		}

	}
}
