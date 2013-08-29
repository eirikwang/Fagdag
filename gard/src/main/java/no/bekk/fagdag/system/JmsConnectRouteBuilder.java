package no.bekk.fagdag.system;

import no.bekk.fagdag.events.Event;
import no.bekk.fagdag.events.external.Envelope;
import no.bekk.fagdag.events.system.NyDagEvent;

import java.util.HashMap;
import java.util.Map;

import com.google.common.eventbus.Subscribe;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultProducerTemplate;
import org.apache.camel.model.dataformat.XStreamDataFormat;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class JmsConnectRouteBuilder extends RouteBuilder {
    private final EventHandler eventHandler;
    private ProducerTemplate template;


    public JmsConnectRouteBuilder(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override public void configure() throws Exception {
        XStreamDataFormat xstreamDefinition = getxStreamDataFormat();
        onException(Throwable.class).log(LoggingLevel.ERROR, "Feilet: ${body}").handled(true);

        from("activemq:topic:messageBroker")
                .log("${body}")
                .filter().xpath("//Event[type='NyDagEvent']")
                .unmarshal(xstreamDefinition)
                .transform(simple("${body.payload}"))
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().getBody(Event.class).incomming=true;
                    }
                })
                .bean(eventHandler, "postEvent");
        from("direct:send").id("sender").marshal(getxStreamDataFormat()).to("activemq:topic:messageBroker");
        template = new DefaultProducerTemplate(getContext(), getContext().getEndpoint("direct:send"));
        template.start();
    }

    @Subscribe public void subscribe(Event object) {
        if (!object.incomming) {
            template.sendBody(new Envelope(object));
        }
    }

    public static XStreamDataFormat getxStreamDataFormat() {
        XStreamDataFormat xstreamDefinition = new XStreamDataFormat();
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("Event", Envelope.class.getName());
        aliases.put("Dag", NyDagEvent.class.getName());
        xstreamDefinition.setAliases(aliases);
        return xstreamDefinition;
    }
}
