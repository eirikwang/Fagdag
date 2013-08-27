package no.bekk.fagdag.aktorer;

import no.bekk.fagdag.events.bonde.MelketAlleKuer;
import no.bekk.fagdag.events.system.NyDagEvent;
import no.bekk.fagdag.system.EventHandler;

import com.google.common.eventbus.Subscribe;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class Bonde {
    public EventHandler eventBus;

    public Bonde(EventHandler eventBus) {
        this.eventBus = eventBus;
    }

    @Subscribe public void nyDag(NyDagEvent nyDag) {
        eventBus.postEvent(new MelketAlleKuer(nyDag.dag));
    }
}
