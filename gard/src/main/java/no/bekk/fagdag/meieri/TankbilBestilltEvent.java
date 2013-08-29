package no.bekk.fagdag.meieri;

import no.bekk.fagdag.events.Event;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class TankbilBestilltEvent extends Event {
    public final int ordreId;

    public TankbilBestilltEvent(int ordreId) {
        this.ordreId = ordreId;
    }
}
