package mr.shravan.examples.camel.large.xml;

import java.io.InputStream;

import org.apache.camel.CamelContext;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.model.RoutesDefinition;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LargeXMLParser {

	public static CamelContext context = null;

	public static void main(String[] args) {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring-camelroute.xml"});
			CamelContext camel = SpringCamelContext.springCamelContext(context); 
            System.out.println(camel.getName()); 
            camel.start(); 
            Thread.sleep(10000); 
            camel.stop(); 
//			context = new DefaultCamelContext();
//			loadRoute();
//			context.start();
//			Thread.sleep(999999);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void loadRoute() throws Exception {
		// load route from XML and add them to the existing camel context
		InputStream is = new LargeXMLParser().getClass().getResourceAsStream("camel-route.xml");
		ModelCamelContext ctx = (ModelCamelContext) context;
		RoutesDefinition routes = ctx.loadRoutesDefinition(is);
		ctx.addRouteDefinitions(routes.getRoutes());
	}

}
