package no.bekk.fagdag.events.ku;

import no.bekk.fagdag.events.KuEvent;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public final class KuGaMelk extends KuEvent {
    public final long ku;
    public final long literMelk;

    public KuGaMelk(long dag, long ku, long literMelk) {
        super(dag);
        this.ku = ku;
        this.literMelk = literMelk;
    }

    @Override public String toString() {
        return String.format("KuGaMelk{ku=%d, literMelk=%d}", ku, literMelk);
    }
}
