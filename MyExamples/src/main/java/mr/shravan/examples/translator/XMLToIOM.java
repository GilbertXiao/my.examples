package mr.shravan.examples.translator;

import java.io.FileReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.EventFilter;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.manh.inegration.iom.calendar.Header;

import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

public class XMLToIOM {

	static String TXML_TAG = "<tXML>";
	static String HEADER_TAG = "Header";
	static String MESSAGE_TAG = "Message";

	public static void main(String[] args) {

		try {
			parseXML();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void parseXML() throws Exception {
		// Parse the data, filtering out the start elements
		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		FileReader fr = new FileReader("C:\\Trash\\XMLBeans\\Calendar.xml");
		XMLStreamReader xsr = xmlif.createXMLStreamReader(fr);

		StringWriter headerElements = new StringWriter();
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		XMLStreamWriter xmlStreamWriter = outputFactory
				.createXMLStreamWriter(headerElements);

		/*XMLStreamReader xmlfer = xmlif.createFilteredReader(xsr, new MyStreamFilter());

		while (xmlfer.hasNext()) {
			StartElement event = (StartElement) xmlfer.n

			if (event.getName().getLocalPart().equals(HEADER_TAG)) {
				parseHeader(xmlfer);
			} else if (event.getName().getLocalPart().equals(MESSAGE_TAG)) {
				parseMessage(xmlfer);
			}
		}*/
	}

	protected static void parseHeader(XMLEventReader xmlfer) throws Exception {
		System.out.println("Parsing header");
		/*while (xmlfer.hasNext()) {
			StartElement event = (StartElement) xmlfer.nextEvent();
			String tagName = event.getName().getLocalPart();
			System.out.println(tagName);
			if(tagName.equals(HEADER_TAG))
			{
				break;
			}
		}*/
		
		 JAXBContext jc = JAXBContext.newInstance(Header.class);
	        Unmarshaller unmarshaller = jc.createUnmarshaller();
	        JAXBElement<Header> jb = unmarshaller.unmarshal(xmlfer, Header.class);
	        System.out.println("header..:"+jb);
	}

	protected static void parseMessage(XMLEventReader xmlfer) throws Exception {
		System.out.println("\n\n Parsing message");
		while (xmlfer.hasNext()) {
			StartElement event = (StartElement) xmlfer.nextEvent();
			System.out.println(event.getName().getLocalPart());
		}
	}
	
	class MyStreamFilter implements javax.xml.stream.StreamFilter {
		@Override
		public boolean accept(XMLStreamReader reader) {
			return (reader.isStartElement());

		}

	}
}
