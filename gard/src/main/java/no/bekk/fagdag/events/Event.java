package no.bekk.fagdag.events;

import java.util.UUID;

/**
 * Abstrakt Event alle eventer skal implementere
 *
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public abstract class Event {
    public final UUID id;
    public final long created;

    protected Event(UUID id, long timestamp) {
        this.id = id;
        this.created = timestamp;
    }

    protected Event() {
        this(UUID.randomUUID(), System.currentTimeMillis());
    }
}
