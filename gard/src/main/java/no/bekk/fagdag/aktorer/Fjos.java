package no.bekk.fagdag.aktorer;

import no.bekk.fagdag.events.ku.KuGaMelk;
import no.bekk.fagdag.events.rapport.TankStatusEvent;
import no.bekk.fagdag.system.EventHandler;

import com.google.common.eventbus.Subscribe;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class Fjos {
    private long tankMengde;

    public Fjos(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Subscribe public void oppdaterTank(KuGaMelk melkeEvent) {
        tankMengde = tankMengde + melkeEvent.literMelk;
        eventHandler.postEvent(new TankStatusEvent(tankMengde));
    }

    private transient EventHandler eventHandler;

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }
}
