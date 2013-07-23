Example from "Apache Camel: Integration Nirvana" Article
========================================================

Of course, its expected that you've read the article first!

You will need to compile this example first:
  mvn compile

To load the routes using Maven, type
  mvn camel:run

This will load up the Camel application as described in the article. 

You will probably find it much more useful to run the tests included with
this example though. The tests send messages to the endpoints and add 
mock endpoints for the detection of the messages on the orderQueue.

To run the tests, issue the following Maven command:

mvn test

The tests add the route defined in the file

./src/test/java/org/fusesource/camel/OrderRouterExtension.java

This file basically adds from("jms:orderQueue").to("mock:orders") so that
all messages passing through the normalizer into the orderQueue JMS queue
will be sent to the "orders" mock endpoint. The MockEndpoint in Camel
contains methods helpful for expecting certain message contents (plus
other features that we won't touch on here).

The unit test is in this file

./src/test/java/org/fusesource/camel/PlaceOrderTest.java

To place two file orders we copy the premade file orders to the directory 
where the file endpoint is listening on ("target/placeorder")

        IOHelper.copyFile(new File("src/data/message1.xml"), new File("target/placeorder/message1.xml"));
        IOHelper.copyFile(new File("src/data/message2.csv"), new File("target/placeorder/message2.csv")); 

The mock endpoint basically asserts that the two normalized messages make it to the mock endpoint.

To place an XML order over HTTP we use the camel-http component. Sending to an 
endpoint in Camel programatically is easy when the ProducerTemplate class is 
used. In our case, this is created automatically because we extend the test 
case from ContextTestSupport. We just have to reference the "template" field and 
use the requestBody method. This method will send the message content (a String 
with XML data) to the HTTP endpoint and will wait for the HTTP response. In 
our case, we have defined the response to be a simple "OUT" String.

        Object response = template.requestBody("http://localhost:8888/placeorder", body);

If you hit any problems please let the Camel team know on the forums
  http://camel.apache.org/discussion-forums.html
