package mr.shravan.examples.translator;

import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;

import com.manh.inegration.iom.calendar.Calendar;
import com.manh.inegration.iom.calendar.Message;

public class WorkerThread implements Runnable {

	Message message = null;
	XMLEventReader reader = null;
	Unmarshaller unmarshaller = null;

	public WorkerThread(Message message, XMLEventReader reader,
			Unmarshaller unmarshaller) {
		this.message = message;
		this.reader = reader;
		this.unmarshaller = unmarshaller;
	}

	@Override
	public void run() {
		try {
			Calendar calendar = unmarshaller.unmarshal(reader, Calendar.class)
					.getValue();
			// System.out.println(calendar);
			message.getCALENDAR().add(calendar);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
