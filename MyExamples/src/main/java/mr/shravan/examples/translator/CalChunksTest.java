package mr.shravan.examples.translator;

import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.manh.inegration.iom.calendar.Calendar;
import com.manh.inegration.iom.calendar.Header;
import com.manh.inegration.iom.calendar.Message;
import com.manh.inegration.iom.calendar.TXML;

public class CalChunksTest {
	public static void main(String[] args) {
		String filePath = args[0];
		try {
			final int MegaBytes = 1024 * 1024;
			long totalMemory = Runtime.getRuntime().totalMemory() / MegaBytes;
			long maxMemory = Runtime.getRuntime().maxMemory() / MegaBytes;
			long freeMemory = Runtime.getRuntime().freeMemory() / MegaBytes;

			System.out.println("**** Heap utilization Analysis [MB] ****");
			System.out
					.println("JVM totalMemory also equals to initial heap size of JVM :"
							+ totalMemory);
			System.out
					.println("JVM maxMemory also equals to maximum heap size of JVM: "
							+ maxMemory);
			System.out.println("JVM freeMemory: " + freeMemory);

			chunkXML(filePath);

			totalMemory = Runtime.getRuntime().totalMemory() / MegaBytes;
			maxMemory = Runtime.getRuntime().maxMemory() / MegaBytes;
			freeMemory = Runtime.getRuntime().freeMemory() / MegaBytes;

			System.out.println("Used Memory in JVM: "
					+ (maxMemory - freeMemory));
			System.out
					.println("totalMemory in JVM shows current size of java heap:"
							+ totalMemory);
			System.out.println("maxMemory in JVM: " + maxMemory);
			System.out.println("freeMemory in JVM: " + freeMemory);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void chunkXML(String filePath) throws Exception {

		final String headerElement = "Header";
		final String calendarElement = "CALENDAR";
		Long startTime = System.currentTimeMillis();
		// create xml event reader for input stream
		XMLInputFactory xif = XMLInputFactory.newInstance();
		XMLEventReader reader = xif.createXMLEventReader(new FileReader(
				filePath));

		// initialize jaxb
		JAXBContext jaxbCtx = JAXBContext.newInstance(TXML.class, TXML.class);
		Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();

		// unmarshall the tXML element without parsing the child elements
		// TXML tXML = unmarshaller.unmarshal(new PartialXmlEventReader(reader,
		// headerElement), TXML.class).getValue();
		TXML tXML = new TXML();
		Message message = new Message();
		XMLEvent e = null;
		tXML.setMessage(message);
		// loop though the xml stream
		String elementName = null;
		while ((e = reader.peek()) != null) {

			// check the event is a Document start element
			if (e.isStartElement()) {
				elementName = ((StartElement) e).getName().getLocalPart();
				switch (elementName) {
				case headerElement: {

					// unmarshall the document
					Header header = unmarshaller
							.unmarshal(reader, Header.class).getValue();
					// System.out.println(header);
					tXML.setHeader(header);

					break;
				}
				case calendarElement: {

					Calendar calendar = unmarshaller.unmarshal(reader,
							Calendar.class).getValue();
					// System.out.println(calendar);
					message.getCALENDAR().add(calendar);

					break;
				}
				default: {
					reader.next();
					break;
				}
				}
			} else {
				reader.next();
			}
		}
		Long endTime = System.currentTimeMillis();
		System.out.println("Total time :" + (endTime - startTime));
		System.out.println("Total calendars :"
				+ tXML.getMessage().getCALENDAR().size());
	}
}
