package harjoitustyo.kokoelma;

import harjoitustyo.omalista.OmaLista;
import harjoitustyo.dokumentit.*;

import java.util.Comparator;
import java.util.TreeMap;

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
     * Metodi joka laskee koko kokoelman sanojen frekvenssit.
     * 
     * @return TreeMap jossa avaimina sanat ja arvoina frekvenssit
     */
    public TreeMap<String, Integer> laskeFrekvenssit() {
        TreeMap<String, Integer> frekvenssit = new TreeMap<String, Integer>();

        for (Dokumentti dok : dokumentit) {
            TreeMap<String, Integer> dokFreqs = dok.laskeFrekvenssit();
            for (String key : dokFreqs.keySet()) {
                if (!frekvenssit.containsKey(key)) {
                    frekvenssit.put(key, dokFreqs.get(key));
                }
                else {
                    Integer addVal = dokFreqs.get(key);
                    Integer val = frekvenssit.get(key);
                    frekvenssit.replace(key, val+addVal);
                }
            }
        }
        return frekvenssit;
    }

    /**
     * Kokoelma lajitellaan annetun tyypin mukaan. Tyypin mukaan luodaan comparator, joka 
     * annetaam parametriksi OmaListan omaan lajittele-metodiin. 
     * 
     * @param lajitteluTyyppi tyyppi jonka mukaan lajitellaan.
     */
    public void lajittele(String lajitteluTyyppi) {
        switch (lajitteluTyyppi) {
            case "id": 
                Comparator<Dokumentti> tunniste = Comparator.comparing(Dokumentti::tunniste);
                dokumentit.lajittele(tunniste);
                break;
            case "type":
                Comparator<Dokumentti> tyyppi = Comparator.comparing(Dokumentti -> ((Vitsi)Dokumentti).laji());
                dokumentit.lajittele(tyyppi);
                break;
            case "date":
                Comparator<Dokumentti> pvm = Comparator.comparing(Dokumentti -> ((Uutinen)Dokumentti).päivämäärä());
                dokumentit.lajittele(pvm);
                break;
        }
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
