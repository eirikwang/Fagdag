package no.bekk.fagdag.aktorer;

import no.bekk.fagdag.events.system.NyDagEvent;
import no.bekk.fagdag.events.system.StartetEvent;
import no.bekk.fagdag.system.EventHandler;

import java.util.Timer;
import java.util.TimerTask;

import com.google.common.eventbus.Subscribe;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class DagTicker {
    private final EventHandler eventHandler;
    private long dag;

    public DagTicker(EventHandler runner) {
        this.eventHandler = runner;
    }

    @Subscribe
    public void start(StartetEvent event) {
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override public void run() {
                eventHandler.postEvent(new NyDagEvent(dag++));
            }
        }, 2000, 2000);

    }
}
