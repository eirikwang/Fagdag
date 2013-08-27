package no.bekk.fagdag.aktorer;

import no.bekk.fagdag.events.ku.KuGaMelk;
import no.bekk.fagdag.events.rapport.DagsRapportEvent;
import no.bekk.fagdag.events.system.NyDagEvent;
import no.bekk.fagdag.system.EventHandler;

import com.google.common.eventbus.Subscribe;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class RapportConsumer {
    private long melkIDag;
    private long rapportDag;
    private final EventHandler eventHandler;

    public RapportConsumer(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Subscribe
    public void registrerMelk(KuGaMelk kuGaMelk) {
        melkIDag += kuGaMelk.literMelk;
    }

    @Subscribe
    public void nyDag(NyDagEvent nyDag) {
        eventHandler.postEvent(new DagsRapportEvent(rapportDag, melkIDag));
        initNyDag(nyDag.dag);
    }

    private void initNyDag(long dag) {
        this.rapportDag = dag;
        this.melkIDag = 0;
    }

}
