package no.bekk.fagdag.routes;

import org.apache.camel.builder.RouteBuilder;

public class TankAlertRouter extends RouteBuilder {
    @Override public void configure() throws Exception {
        from("activemq:topic:messageBroker")
                .log("${body}")
                .filter().xpath("//Event[type='TankStatusEvent']")
                .filter().xpath("//Event/payload[literMelk>5000]")
                .to("direct:tankAlert");

        from("direct:tankAlert")
                .transform(simple("<Event><type>TankSnartFullEvent</type><payload class=\"no.bekk.fagdag.events.system.TankSnartFullEvent\"></payload></Event>"))
                .to("activemq:topic:messageBroker");
    }

}
