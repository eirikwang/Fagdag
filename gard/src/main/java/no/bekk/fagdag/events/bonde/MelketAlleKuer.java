package no.bekk.fagdag.events.bonde;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class MelketAlleKuer {
    public final long dag;

    public MelketAlleKuer(long dag) {
        this.dag = dag;
    }

    @Override public String toString() {
        return String.format("MelketAlleKuer{dag=%d}", dag);
    }
}
