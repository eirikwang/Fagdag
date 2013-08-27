package no.bekk.fagdag.events.rapport;

import no.bekk.fagdag.events.Event;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class DagsRapportEvent extends Event implements RapportEvent {
    public final long dag;
    public final long literMelk;

    public DagsRapportEvent(long dag, long literMelk) {
        super();
        this.dag = dag;
        this.literMelk = literMelk;
    }

    @Override public String toString() {
        return String.format("Total Melk for dag %d: %dkg", dag, literMelk);
    }
}
