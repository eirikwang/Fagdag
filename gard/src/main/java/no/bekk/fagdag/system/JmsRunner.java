package no.bekk.fagdag.system;

import java.util.concurrent.CountDownLatch;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class JmsRunner {
    public static void main(String[] args) throws Exception {
        Runner r = new Runner();
        CamelContext context = new DefaultCamelContext();
        context.addComponent("activemq", activeMQComponent("tcp://0.0.0.0:61616"));
        context.addRoutes(new JmsConnectRouteBuilder(r));
        context.start();
        CountDownLatch l = new CountDownLatch(1);
        l.await();

    }
}
