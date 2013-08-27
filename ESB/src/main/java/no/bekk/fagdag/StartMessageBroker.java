package no.bekk.fagdag;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class StartMessageBroker {
    public static void main(String[] args) throws Exception {
        BrokerService broker = BrokerFactory.createBroker("xbean:activemq.xml");
        broker.waitUntilStopped();

    }
}
