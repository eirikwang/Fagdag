package no.bekk.fagdag.routes;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class DagRouteBuilder extends RouteBuilder {

    @Override public void configure() throws Exception {
        from("timer:day?period=3s")
                .setHeader("dag", method(this, "newDay"))
                .transform(simple("<Event><type>NyDagEvent</type><payload class=\"no.bekk.fagdag.events.system.NyDagEvent\"> <dag>${headers.dag}</dag></payload></Event>"))
                .setHeader("event", simple("NyDagEvent")).log("data ${body}")
                .to("activemq:topic:messageBroker");
    }
    private int day = 0;

    public long newDay() {
        return day++;
    }

}
