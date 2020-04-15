/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo.dokumentit;
import java.util.LinkedList;
import java.util.Arrays;
import harjoitustyo.apulaiset.Tietoinen;

/**
 *
 * @author Miia
 */

public abstract class Dokumentti implements Comparable<Dokumentti>, Tietoinen<Dokumentti> {
    //Attribuutit
    private int tunniste; //Pitää olla >0
    private String teksti; //Ei null tai ""
    
    //Rakentaja
    public Dokumentti(int uusiTunniste, String uusiTeksti){
        tunniste(uusiTunniste);
        teksti(uusiTeksti);
    }

    //Aksessorit tunniste-attribuutille
    public void tunniste(int uusiTunniste) throws IllegalArgumentException{
        if (uusiTunniste > 0) {
            tunniste = uusiTunniste;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    public int tunniste() {
        return tunniste;
    }

    //Aksessorit teksti-attribuutille
    public void teksti(String uusiTeksti) throws IllegalArgumentException{
        if (uusiTeksti != null && !uusiTeksti.equals("")){
            teksti = uusiTeksti;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    public String teksti() {
        return teksti;
    }

    //Korvattu toString-metodi, lisää /// tunnisteen ja tekstin väliin. 
    @Override
    public String toString() {
        return tunniste+"///"+teksti;
    }

    //Korvattu equals-metodi, vertailee onko Dokumentit samoja tunnisteen perusteella
    @Override
    public boolean equals(Object toinen) {
        try {
            Dokumentti toinenDokumentti = (Dokumentti)toinen;

            return tunniste == toinenDokumentti.tunniste();
        }
        catch (Exception e) {
            return false;
        }      
    }

    //Korvattu compareTo-metodi joka vertailee Dokumenttien tunnisteita
    @Override
    public int compareTo(Dokumentti vertailtava) {
        if (tunniste < vertailtava.tunniste()) {
            return -1;
        }
        else if (tunniste == vertailtava.tunniste()) {
            return 0;
        }
        else {
            return 1;
        }
    }

    /*
     * Metodi tarkistaa onko parametrina annettuja hakusanoja dokumentin tekstissä
     */
    public boolean sanatTäsmäävät(LinkedList<String> hakusanat)
    throws IllegalArgumentException {
        if (hakusanat != null){
            //Muokataan tekstistä pois välimerkit ja tehdään siitä array tarkistuksen helpottamiseksi
            String muokattuTeksti = teksti.replace(".", "").replace(",", "");
            String[] sanat = muokattuTeksti.split(" ");

            //Käydään hakusanat läpi ja tarkistetaan onko hakusanaa tekstissä,
            //palautetaan heti false, jos yksi hakusana ei täsmää
            for (String hakusana : hakusanat) {
                if (!Arrays.asList(sanat).contains(hakusana)) {
                    return false;
                }  
            }
            return true;         
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void siivoa(LinkedList<String> sulkusanat, String välimerkit)
    throws IllegalArgumentException {
        //Käydään välimerkit läpi ja poistetaan ne
        String[] merkit = välimerkit.split("");
        for (String merkki : merkit) {
            if (teksti.contains(merkki)) {
                teksti = teksti.replace(merkki, "");
            }
        }

        teksti = teksti.toLowerCase();

        for (String sulkusana : sulkusanat) {
            if (teksti.contains(sulkusana)) {
                teksti = teksti.replace(sulkusana, "");
            }
        }
    }
}
