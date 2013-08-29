package no.bekk.fagdag.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
@MockEndpoints("direct")
public class TankAlertRouterTest extends CamelTestSupport {

    @Produce(uri = "activemq:topic:messageBroker")
    protected ProducerTemplate template;

    @Override

    public String isMockEndpoints() {
        return "*";
    }

    @Test
    public void shouldSendMessageThrough() throws InterruptedException {
        String body = "<Event><type>TankStatusEvent</type><payload><literMelk>5001</literMelk></payload></Event>";
        String expected = "<Event><type>TankSnartFullEvent</type><payload class=\"no.bekk.fagdag.events.system.TankSnartFullEvent\"></payload></Event>";
        getMockEndpoint("mock:direct:tankAlert").expectedBodiesReceived(body);
        getMockEndpoint("mock:activemq:topic:messageBroker").expectedBodiesReceived(body, expected);
        template.sendBody(body);
        assertMockEndpointsSatisfied();
    }

    @Override protected CamelContext createCamelContext() throws Exception {

        CamelContext camelContext = super.createCamelContext();
        camelContext.addComponent("activemq", activeMQComponent("vm://localhost?broker.persistent=false"));
        camelContext.addRoutes(new TankAlertRouter());


        return camelContext;
    }
}
