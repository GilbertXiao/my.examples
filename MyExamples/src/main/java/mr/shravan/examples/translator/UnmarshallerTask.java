package mr.shravan.examples.translator;

import java.io.StringReader;

import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.manh.inegration.iom.calendar.Calendar;
import com.manh.inegration.iom.calendar.Header;
import com.manh.inegration.iom.calendar.TXML;

public class UnmarshallerTask implements Processor {

	@Override
	public void process(Exchange xChange) {
		try {
			Unmarshaller unmarshaller = (Unmarshaller) xChange
					.getProperty("UNMARSHALLER");
			String message = xChange.getIn().getBody(String.class);
			//System.out.println("Message received :" + message);
			StringReader reader = new StringReader(message);
			TXML tXML = (TXML) xChange.getProperty("TXML");
			//System.out.println("tXML object :" + tXML);
			//System.out.println("unmarshaller :" + unmarshaller);
			XMLInputFactory factory = XMLInputFactory.newInstance();

			if (message.startsWith("<Header>")) {
				Header header = (Header) unmarshaller.unmarshal(
						factory.createXMLStreamReader(reader), Header.class)
						.getValue();
				if (tXML != null) {
					tXML.setHeader(header);
				}
			} else if (message.startsWith("<CALENDAR>")) {
				Calendar calendar = (Calendar) unmarshaller.unmarshal(
						factory.createXMLStreamReader(reader), Calendar.class)
						.getValue();
				if (tXML != null) {
					tXML.getMessage().getCALENDAR().add(calendar);
				}
			}
			xChange.setProperty("TXML", tXML);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
