package no.bekk.fagdag.routes;

import org.apache.camel.builder.RouteBuilder;

public class TankAlertRouter extends RouteBuilder {
    private static final String FULL_TANK_EVENT = "<Event><type>TankSnartFullEvent</type><payload class=\"no.bekk.fagdag.events.system.TankSnartFullEvent\"></payload></Event>";
    private static final int FULL_TANK_TRESHOLD = 5000;

    /**
     * Steg2:
     *
     * Skriv camel kode som tar i mot beskjeder fra topicen, filterer ut de som handler om melkemengde i tanken, og om melkemengden er over 5000 liter, send ut en TankSnartFullEvent.
     *
     */
    @Override public void configure() throws Exception {
        from("????")
                .to("direct:tankAlert");

        from("direct:tankAlert")
                .log("Logikk for å sende ut en TankSnartFull event på topicken. ");
    }

}
