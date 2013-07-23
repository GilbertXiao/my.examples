package org.fusesource.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

public class OrderRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        JaxbDataFormat jaxb = new JaxbDataFormat("org.fusesource.camel");
        
        
        from("file:///C:/Test/inbound").to("file:///C:/Test/outbound");  
       
        
		from("file:///C:/Test/inbound")
			.convertBodyTo(String.class).choice()
				.when()
					.method("orderHelper", "isXml")
						.unmarshal(jaxb)
						.to("jms:orderQueue")
				.when()
					.method("orderHelper", "isCsv")
						.unmarshal().csv()
						.to("bean:normalizer")
							.to("jms:orderQueue");
        
        
        
//        from("file:///C:/Test/placeorder").to("jms:incomingOrderQueue");        
//        
//        from("jetty:http://localhost:8888/placeorder")
//          .inOnly().to("jms:incomingOrderQueue")
//          .transform().constant("OK");
//       
        
    }

	protected void test(JaxbDataFormat jaxb) {
		// Do the normalization
		from("jms:incomingOrderQueue")
         .convertBodyTo(String.class)
         .choice()
           .when().method("orderHelper", "isXml")
             .unmarshal(jaxb)
             .to("jms:orderQueue")
           .when().method("orderHelper", "isCsv")
             .unmarshal().csv()         
             .to("bean:normalizer")
             .to("jms:orderQueue");
	}

}
