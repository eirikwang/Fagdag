package no.bekk.fagdag.system;

import no.bekk.fagdag.aktorer.Bonde;
import no.bekk.fagdag.aktorer.DagTicker;
import no.bekk.fagdag.aktorer.Fjos;
import no.bekk.fagdag.aktorer.Inseminor;
import no.bekk.fagdag.aktorer.Ku;
import no.bekk.fagdag.aktorer.LogConsumer;
import no.bekk.fagdag.aktorer.RapportConsumer;
import no.bekk.fagdag.events.system.StartetEvent;

import java.util.concurrent.CountDownLatch;

import com.google.common.eventbus.EventBus;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class Runner implements EventHandler {
    private final EventBus eventBus;

    public Runner() {
        eventBus = new EventBus("Kusystem");
        registrerHandterere();
        lagKyr();
        eventBus.post(new StartetEvent());

    }

    private void registrerHandterere() {
        eventBus.register(new LogConsumer());
        eventBus.register(new RapportConsumer(this));
        eventBus.register(new Fjos(this));
        eventBus.register(new Bonde(this));
        eventBus.register(new Inseminor());
    }

    private void addTicker() {
        eventBus.register(new DagTicker(this));
    }

    private void lagKyr() {
        for (int i = 0; i < 10; i++) {
            Ku ku = new Ku(i, "navn " + i, i / 10, 5000 + (i * 1000 / 5));
            ku.setEventHandler(this);
            eventBus.register(ku);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runner r = new Runner();
        r.addTicker();
        CountDownLatch l = new CountDownLatch(1);
        l.await();
    }

    public void postEvent(Object event) {
        eventBus.post(event);
    }
}
