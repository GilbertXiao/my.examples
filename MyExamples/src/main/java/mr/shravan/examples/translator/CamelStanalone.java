package mr.shravan.examples.translator;

import static org.apache.camel.component.stax.StAXBuilder.stax;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

import com.manh.inegration.iom.calendar.Calendar;

public class CamelStanalone {

	private Main main;

	public static void main(String[] args) {
		try {
			CamelStanalone example = new CamelStanalone();
			example.boot();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void boot() throws Exception {
		// create a Main instance
		main = new Main();
		// enable hangup support so you can press ctrl + c to terminate the JVM
		main.enableHangupSupport();
		// bind MyBean into the registery
		// main.bind("RouteInitializationTask", new RouteInitializationTask());
		main.bind("UnmarshallerTask", new UnmarshallerTask());
		main.bind("IOMTask", new IOMTask());
		// add routes
		//main.addRouteBuilder(new MyRouteBuilder());
		main.addRouteBuilder(new StAXRouteBuilder());

		// run until you terminate the JVM
		System.out
				.println("Starting Camel. Use ctrl + c to terminate the JVM.\n");
		main.run();
	}

	protected static class MyRouteBuilder extends RouteBuilder {
		@Override
		public void configure() throws Exception {
			from("file://C:/Users/stalupula/Documents/GitHub/my.examples/MyExamples/JAXB/input")
					.process(new RouteInitializationTask())
					.split()
					.tokenizeXML("CALENDAR")
					.streaming()
					.to("UnmarshallerTask")
					.end()
					.to("IOMTask");
		}
	}
	protected static class StAXRouteBuilder extends RouteBuilder {
		@Override
		public void configure() throws Exception {
			from("file://C:/Users/stalupula/Documents/GitHub/my.examples/MyExamples/JAXB/input")
			.to("file://C:/Users/stalupula/Documents/GitHub/my.examples/MyExamples/JAXB/output");
//			.split(stax(Calendar.class,false))
//				.streaming()
//					.to("IOMTask");
		}
	}

	public static class MyBean {
		public void callMe() {
			System.out.println("MyBean.calleMe method has been called");
		}
	}
}
