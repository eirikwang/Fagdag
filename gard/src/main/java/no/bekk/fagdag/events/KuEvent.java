package no.bekk.fagdag.events;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public abstract class KuEvent extends Event {
    public final long dag;

    protected KuEvent(long dag) {
        this.dag = dag;
    }
}
