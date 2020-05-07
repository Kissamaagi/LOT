package harjoitustyo.kokoelma;

import harjoitustyo.omalista.OmaLista;
import harjoitustyo.dokumentit.*;
import harjoitustyo.apulaiset.Kokoava;
/**
 * Kokoelma-luokka, jonka mukaan vodiaan luoda kokoelma Dokumenteille.
 *
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2020
 * 
 * @author Miia Pynnönen (miia.pynnonen@tuni.fi)
 */
public class Kokoelma extends Object implements Kokoava<Dokumentti> {
    /** OmaLista dokumentit johon Dokumentit laitetaan */
    private OmaLista<Dokumentti> dokumentit;

    /**Parametriton rakentaja jossa alustetaan dokumentit-attribuutti */
    public Kokoelma() {
        dokumentit = new OmaLista<Dokumentti>();
    }

    /**
     * Palauttava aksessori dokumentit-attribuutille
     * 
     * @return dokumentit
     */
    public OmaLista<Dokumentti> dokumentit() {
        return dokumentit;
    }

    /**
     * @see Kokoava#lisää(Object)
     */
    public void lisää(Dokumentti uusi) throws IllegalArgumentException {
        if (uusi != null && !dokumentit.contains(uusi)) {
            dokumentit.lisää(uusi);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @see Kokoava#hae(int)
     */
    public Dokumentti hae(int tunniste) {
        if (dokumentit.size() > 0) {
            for (Dokumentti dokumentti : dokumentit) {
                if (tunniste == dokumentti.tunniste()) {
                    return dokumentti;
                }
            }
        }
        return null;
    }

    /**
     * Metodi, jolla voi poistaa kokoelmasta tietyn dokumentin tunnisteen perusteella
     * 
     * @param tunniste poistettavan dokumentin tunniste
     */
    public void poista(int tunniste) {
        Dokumentti poistettava = this.hae(tunniste);
        if (poistettava != null) {
            dokumentit.remove(poistettava);
        }
        else {
            throw new IllegalArgumentException();
        }  
    }   
}
