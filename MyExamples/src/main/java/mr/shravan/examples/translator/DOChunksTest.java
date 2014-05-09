package mr.shravan.examples.translator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import mr.shravan.examples.translators.iom.dord.DistributionOrder;
import mr.shravan.examples.translators.iom.dord.Header;
import mr.shravan.examples.translators.iom.dord.Message;
import mr.shravan.examples.translators.iom.dord.TXML;

public class DOChunksTest {
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
		final QName headerElement = new QName("Header");
		final QName distributionOrderElement = new QName("DistributionOrder");
		Long startTime = System.currentTimeMillis();
		// create xml event reader for input stream
		XMLInputFactory xif = XMLInputFactory.newInstance();
		InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
		XMLEventReader reader = xif.createXMLEventReader(fis);

		// initialize jaxb
		JAXBContext jaxbCtx = JAXBContext.newInstance(TXML.class, TXML.class);
		Unmarshaller um = jaxbCtx.createUnmarshaller();

		// unmarshall the tXML element without parsing the child elements
		TXML tXML = um.unmarshal(
				new PartialXmlEventReader(reader, headerElement), TXML.class)
				.getValue();
		Message message = new Message();
		XMLEvent e = null;

		// loop though the xml stream
		while ((e = reader.peek()) != null) {

			// check the event is a Document start element
			if (e.isStartElement()) {
				if ((((StartElement) e).getName().equals(headerElement))) {
					// unmarshall the document
					Header header = um.unmarshal(reader, Header.class)
							.getValue();
					// System.out.println(header);
					tXML.setHeader(header);

				} else if ((((StartElement) e).getName()
						.equals(distributionOrderElement))) {
					DistributionOrder disOrder = um.unmarshal(reader,
							DistributionOrder.class).getValue();
					// System.out.println(calendar);
					message.getDistributionOrder().add(disOrder);
					tXML.setMessage(message);
				} else {
					reader.next();
				}
			} else {
				reader.next();
			}
		}
		Long endTime = System.currentTimeMillis();
		System.out.println("Total time :" + (endTime - startTime) / 1000);
		// System.out.println("Total DOs :"+
		// tXML.getMessage().getDistributionOrder().size());
		// System.out.println(tXML);

	}
}
