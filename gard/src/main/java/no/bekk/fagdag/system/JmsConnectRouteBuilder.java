package no.bekk.fagdag.system;

import no.bekk.fagdag.events.external.Envelope;
import no.bekk.fagdag.events.system.NyDagEvent;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.XStreamDataFormat;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class JmsConnectRouteBuilder extends RouteBuilder {
    private final EventHandler eventHandler;

    public JmsConnectRouteBuilder(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override public void configure() throws Exception {
        XStreamDataFormat xstreamDefinition = new XStreamDataFormat();
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("Event", Envelope.class.getName());
        aliases.put("Dag", NyDagEvent.class.getName());
        xstreamDefinition.setAliases(aliases);
        onException(Throwable.class).log(LoggingLevel.ERROR, "Feilet: ${body}");

        from("activemq:topic:messageBroker").log("${body}").unmarshal(xstreamDefinition).log("${body}")
                .transform(simple("${body.payload}"))
                .log("${body}").bean(eventHandler);
    }
}
