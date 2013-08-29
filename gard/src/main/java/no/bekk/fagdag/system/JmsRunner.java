package no.bekk.fagdag.system;

import java.util.concurrent.CountDownLatch;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class JmsRunner {
    public JmsRunner() throws Exception {
        LocalRunner r = new LocalRunner();
        JmsConnectRouteBuilder route = new JmsConnectRouteBuilder(r);
        r.register(route);

        CamelContext context = new DefaultCamelContext();
        context.addComponent("activemq", activeMQComponent("tcp://0.0.0.0:61616"));
        context.addRoutes(route);
        context.start();
        CountDownLatch l = new CountDownLatch(1);
        l.await();
    }

    public static void main(String[] args) throws Exception {
        new JmsRunner();
    }
}
