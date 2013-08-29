package no.bekk.fagdag.aktorer;

import no.bekk.fagdag.system.LocalRunner;

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


    private transient LocalRunner eventHandler;

    public void setEventHandler(LocalRunner eventHandler) {
        this.eventHandler = eventHandler;
    }
}
