package mr.shravan.examples.camel.aws;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.management.JmxSystemPropertyKeys;

public class Main extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		 //from("file:///C:/Test/inbound").to("file:///C:/Test/outbound");  
		 from("aws-sqs://MIF_CLOUD_IN?accessKey=AKIAIQXQQ36NZ2PKCQHA&secretKey=r0WaTsLHjJwIwnfdL4%2Bb8x6km3KR3NMm8MblG276").to("file:///C:/Test/outbound");  
	}
	public static void main(String[] args) throws Exception {
		CamelContext context = new DefaultCamelContext();
		System.setProperty(JmxSystemPropertyKeys.CREATE_CONNECTOR, ""+true);
		//System.setProperty(JmxSystemPropertyKeys.CONNECTOR_PORT, "1088");
		System.out.println(System.getProperty(JmxSystemPropertyKeys.CREATE_CONNECTOR));
		System.out.println(System.getProperty(JmxSystemPropertyKeys.DISABLED));
		System.out.println(System.getProperty(JmxSystemPropertyKeys.CONNECTOR_PORT));
		context.addRoutes(new Main());
		context.start();
		Thread.sleep(999999);
		//context.stop();
	}
}
