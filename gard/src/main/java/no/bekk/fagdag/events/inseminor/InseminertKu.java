package no.bekk.fagdag.events.inseminor;

import no.bekk.fagdag.events.KuEvent;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class InseminertKu extends KuEvent {
    public Long ku;
    public long dag;

    public InseminertKu(long dag) {
        super(dag);
    }
}
