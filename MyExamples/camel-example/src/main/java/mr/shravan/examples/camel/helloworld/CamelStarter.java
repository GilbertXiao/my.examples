package mr.shravan.examples.camel.helloworld;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelStarter {
	public static void main(String[] args) throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.start();
		Thread.sleep(999999);
	}
}
