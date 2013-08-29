package no.bekk.fagdag.aktorer;

import no.bekk.fagdag.events.bonde.MelketAlleKuer;
import no.bekk.fagdag.events.ku.KuGaMelk;
import no.bekk.fagdag.system.LocalRunner;

import com.google.common.eventbus.Subscribe;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class Ku {

    public long id;
    public String navn;
    public double kraftforFaktor;
    public double forventetMelkLaktasjon;

    public Ku(long id, String navn, double kraftforFaktor, double forventetMelkLaktasjon) {
        this.id = id;
        this.navn = navn;
        this.kraftforFaktor = kraftforFaktor;
        this.forventetMelkLaktasjon = forventetMelkLaktasjon;
    }


    @Subscribe public void bleMelket(MelketAlleKuer melketEvent) {
        eventHandler.postEvent(new KuGaMelk(melketEvent.dag, id, 50));
    }

    private transient LocalRunner eventHandler;

    public void setEventHandler(LocalRunner eventHandler) {
        this.eventHandler = eventHandler;
    }
}
