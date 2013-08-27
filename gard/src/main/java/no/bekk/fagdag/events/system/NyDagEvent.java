package no.bekk.fagdag.events.system;

import no.bekk.fagdag.events.KuEvent;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class NyDagEvent extends KuEvent {
    public NyDagEvent(long dag) {
        super(dag);
    }


    @Override public String toString() {
        return String.format("NyDagEvent %d", dag);
    }
}
