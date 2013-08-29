package no.bekk.fagdag.system;

import no.bekk.fagdag.aktorer.DagTicker;
import no.bekk.fagdag.aktorer.Ku;
import no.bekk.fagdag.events.system.StartetEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.google.common.eventbus.EventBus;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class LocalRunner implements EventHandler {
    private final EventBus eventBus;

    public LocalRunner() {
        eventBus = new EventBus("Kusystem");
        registrerHandterere();

    }

    private void registrerHandterere() {
    }

    private void addTicker() {
        eventBus.register(new DagTicker(this));
    }

    public void register(Object o) {
        eventBus.register(o);
    }

    private List<Ku> lagKyr() {
        List<Ku> kyr = new ArrayList<Ku>();
        for (int i = 0; i < 10; i++) {
            Ku ku = new Ku(i, "navn " + i, i / 10, 5000 + (i * 1000 / 5));
            ku.setEventHandler(this);
            kyr.add(ku);
        }
        return kyr;
    }

    public static void main(String[] args) throws InterruptedException {
        LocalRunner r = new LocalRunner();
        r.addTicker();
        r.postEvent(new StartetEvent());
        CountDownLatch l = new CountDownLatch(1);
        l.await();
    }

    public void postEvent(Object event) {
        eventBus.post(event);
    }
}
