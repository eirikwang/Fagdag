package no.bekk.fagdag.events.external;

import no.bekk.fagdag.events.Event;
import no.bekk.fagdag.events.rapport.DagsRapportEvent;
import no.bekk.fagdag.events.system.NyDagEvent;

import java.io.IOException;
import javax.xml.bind.JAXBException;

import com.thoughtworks.xstream.XStream;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class Envelope {
    public String type;
    public String id;
    public Long timestamp;
    public Object payload;

    public Envelope() {
    }

    public Envelope(Event payload) {
        this.payload = payload;
        this.type = payload.getClass().getSimpleName();
        this.id = payload.id.toString();
        this.timestamp = payload.created;
    }

    public Object getPayload() {
        return payload;
    }

    public static void main(String[] args) throws IOException, JAXBException {
        Envelope env = new Envelope();
        DagsRapportEvent dagsRapportEvent = new DagsRapportEvent(1, 1);
        env.id = "id";
        env.type = dagsRapportEvent.getClass().getSimpleName();
        env.timestamp = System.currentTimeMillis();
        XStream xStream = new XStream();
        env.payload = dagsRapportEvent;
        XStream str = new XStream();
        str.alias("Event", Envelope.class);
        str.alias("Dag", NyDagEvent.class);
        System.out.println(str.toXML(env));
        System.out.println(str.fromXML("<Event><type>NyDagEvent</type><payload class=\"no.bekk.fagdag.events.rapport.DagsRapportEvent\"> <dag>30</dag></payload></Event>"));
    }

    @Override public String toString() {
        return "Envelope{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", payload='" + payload + '\'' +
                '}';
    }
}
