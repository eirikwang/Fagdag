package no.bekk.fagdag.meieri;

import java.util.concurrent.CountDownLatch;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import static no.bekk.fagdag.system.JmsConnectRouteBuilder.getxStreamDataFormat;
import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class MeieriRunner {
    public MeieriRunner() throws Exception {
        final Meieri meieri = new Meieri();
        CamelContext context = new DefaultCamelContext();
        context.addComponent("activemq", activeMQComponent("tcp://0.0.0.0:61616"));
        context.addRoutes(new RouteBuilder() {
            @Override public void configure() throws Exception {
                from("activemq:topic:messageBroker")
                        .filter().xpath("//Event[type='TankSnartFullEvent']")
                        .bean(meieri, "bestill")
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                exchange.getOut().setBody(new TankbilBestilltEvent(exchange.getIn().getBody(Integer.class)));
                            }
                        }).marshal(getxStreamDataFormat()).to("activemq:topic:messageBroker");

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
