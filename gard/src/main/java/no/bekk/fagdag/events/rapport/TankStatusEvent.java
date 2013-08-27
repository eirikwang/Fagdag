package no.bekk.fagdag.events.rapport;

import no.bekk.fagdag.events.Event;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class TankStatusEvent extends Event implements RapportEvent {
    private final long literMelk;

    public TankStatusEvent(long literMelk) {
        this.literMelk = literMelk;
    }

    @Override public String toString() {
        return String.format("TankStatusEvent{literMelk=%d}", literMelk);
    }
}
