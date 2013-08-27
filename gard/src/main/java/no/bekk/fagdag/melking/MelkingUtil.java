package no.bekk.fagdag.melking;

import no.bekk.fagdag.individ.Ku;

/**
 * Verktøy for å hjelpe til med melking
 *
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class MelkingUtil {

    public static double melkForDagEtterKalving(Ku ku, int dagSidenKalving, int kgKraftforSidenSistMelking) {
        return -4 + 0.003 * ku.forventetMelkLaktasjon - 0.11 * dagSidenKalving + 4 * Math.log(dagSidenKalving);
    }

    public static void main(String[] args) {
        for (int i = 1; i < 350; i++) {
            Ku ku = new Ku();
            ku.forventetMelkLaktasjon = 7500;
            ku.kraftforFaktor = 0.5;
            System.out.println(melkForDagEtterKalving(ku, i, 10));

        }
    }
}
