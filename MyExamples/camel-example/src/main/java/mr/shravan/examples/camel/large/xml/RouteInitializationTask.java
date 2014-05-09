package mr.shravan.examples.camel.large.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.manh.inegration.iom.calendar.Message;
import com.manh.inegration.iom.calendar.TXML;

public class RouteInitializationTask implements Processor {

	@Override
	public void process(Exchange xChange) {
		try {
			System.out.println("start time :"+System.currentTimeMillis());
			JAXBContext jaxbCtx = JAXBContext.newInstance(TXML.class,
					TXML.class);
			Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
			TXML txml = new TXML();
			Message message = new Message();
			txml.setMessage(message);
			xChange.setProperty("UNMARSHALLER", unmarshaller);
			xChange.setProperty("TXML", txml);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
