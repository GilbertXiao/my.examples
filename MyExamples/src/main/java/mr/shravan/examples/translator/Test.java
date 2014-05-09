package mr.shravan.examples.translator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import com.manh.inegration.iom.calendar.Header;

public class Test {
	public static void main(String[] args) {
		Reader reader = null;
		try {
			JAXBContext context = JAXBContext
					.newInstance("com.manh.inegration.iom.calendar");
			final Unmarshaller um = context.createUnmarshaller();
			reader = new BufferedReader(new FileReader(
					"C:\\Trash\\XMLBeans\\Calendar.xml"));
			final QName qName = new QName("Header");
			final XMLInputFactory xif = XMLInputFactory.newInstance();
			final XMLEventReader xmlEventReader = xif
					.createXMLEventReader(reader);
			final Object header = (Object) um
					.unmarshal(new PartialXmlEventReader(xmlEventReader, qName));
			System.out.println(header);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
