package no.bekk.fagdag.meieri;

import java.util.concurrent.CountDownLatch;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

/**
 * Steg2:
 *
 * Denne klassen skal ha ansvar for å bestille en tankbil, og si fra når den har gjort det.
 *
 */
public class MeieriRunner {
    final Meieri meieri = new Meieri();

    public MeieriRunner() throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addComponent("activemq", activeMQComponent("tcp://0.0.0.0:61616"));
        context.addRoutes(new RouteBuilder() {
            @Override public void configure() throws Exception {

                //Camelkode skrives her.

            }
        });
        context.start();
        CountDownLatch l = new CountDownLatch(1);
        l.await();
    }

    public static void main(String[] args) throws Exception {
        new MeieriRunner();
    }
}
