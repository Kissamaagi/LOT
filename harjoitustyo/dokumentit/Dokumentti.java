package harjoitustyo.dokumentit;

import java.util.LinkedList;
import java.util.TreeMap;
import java.util.Arrays;
import harjoitustyo.apulaiset.Tietoinen;

/**
 * Abstrakti Dokumentti-luokka, jossa on dokumenteille yhteiset piirteet.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2020 
 * <p>
 * @author Miia Pynnönen (miia.pynnonen@tuni.fi)
 */

public abstract class Dokumentti implements Comparable<Dokumentti>, Tietoinen<Dokumentti> {
    /**Dokumentin tunniste. */
    private int tunniste;
    /**Dokumentin teksti. */
    private String teksti;
    
    /**
     * Dokumentin parametrillinen rakentaja.
     * 
     * @param uusiTunniste uusi tunniste oliolle 
     * @param uusiTeksti uusi teksti oliolle
     */
    public Dokumentti(int uusiTunniste, String uusiTeksti){
        tunniste(uusiTunniste);
        teksti(uusiTeksti);
    }

    /**
     * Asettava aksessori tunniste-attribuutille. 
     * Uusi tunniste tarkistetaan että se on yli 0. 
     * 
     * @param uusiTunniste uusi tunniste oliolle. 
     * @throws IllegalArgumentException Jos tunniste on alle 0.
     */
    public void tunniste(int uusiTunniste) throws IllegalArgumentException{
        if (uusiTunniste > 0) {
            tunniste = uusiTunniste;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Palauttava aksessori tunniste-attribuutille.
     * 
     * @return palauttaa tunniste-attribuutin.
     */
    public int tunniste() {
        return tunniste;
    }

    /**
     * Asettava aksessori teksti-attribuutille. 
     * Teksti tarkastetaan ettei se ole null tai tyhjä.
     * 
     * @param uusiTeksti uusi teksti joka tarkistetaan.
     * @throws IllegalArgumentException jos parametri on virheellinen.
     */
    public void teksti(String uusiTeksti) throws IllegalArgumentException{
        if (uusiTeksti != null && !uusiTeksti.equals("")){
            teksti = uusiTeksti;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Palauttava aksessori teksti-attribuutille.
     * 
     * @return palauttaa teksti-attribuutin.
     */
    public String teksti() {
        return teksti;
    }

    /**
     * Korvattu Object-luokan toString-metodi.
     * 
     * @return Dokumentin tunniste ja teksti erotettuna merkeillä ///
     */
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

    /**
     * Korvattu Comparable-luokan compareTo-metodi.
     * Dokumentteja vertaillaan tunnisteen mukaan. 
     * 
     * @param vertailtava Dokumentti johon Dokumenttia vertaillaan
     * @return -1 jos vertailtava on pienempi, 0 jos yhtäsuuri ja 1 jos suurempi
     */
    @Override
    public int compareTo(Dokumentti vertailtava) {
        if (this.tunniste < vertailtava.tunniste()) {
            return -1;
        }
        else if (this.tunniste == vertailtava.tunniste()) {
            return 0;
        }
        else {
            return 1;
        }
    }

    /**
     * @see Tietoinen#sanatTäsmäävät(LinkedList)
     */
    public boolean sanatTäsmäävät(LinkedList<String> hakusanat)
    throws IllegalArgumentException {
        if (hakusanat != null && hakusanat.size() > 0){
            String[] sanat = teksti.split(" ");

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

    /**
     * @see Tietoinen#siivoa(LinkedList, String)
     */
    public void siivoa(LinkedList<String> sulkusanat, String välimerkit)
    throws IllegalArgumentException {
        if ((sulkusanat != null && sulkusanat.size() > 0)
        && (välimerkit != null && välimerkit.length() > 0)) {
            //Käydään välimerkit läpi ja poistetaan ne
            String[] merkit = välimerkit.split("");
            for (String merkki : merkit) {
                if (teksti.contains(merkki)) {
                    teksti = teksti.replace(merkki, "");
                }
            }
            //muutetaan teksti LinkedListiksi ja käydään läpi 
            LinkedList<String> sanaLista = new LinkedList<String>(Arrays.asList(teksti.toLowerCase().split(" ")));
            sanaLista.removeAll(sulkusanat);

            StringBuilder tekstiRakentaja = new StringBuilder();

            for (int i = 0; i < sanaLista.size(); i++) {
                if (i == 0){
                    tekstiRakentaja.append(sanaLista.get(i).trim());
                }
                else {
                    tekstiRakentaja.append(" " + sanaLista.get(i).trim());
                }
            }
            teksti = tekstiRakentaja.toString().trim().replace("  ", " ");
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Metodi jolla lasketaan dokumentin tekstin sanamäärä.
     * 
     * @return sanojen määrä dokumentissa
     */
    public int laskeSanat() {
        String[] sanaLista = teksti.split(" ");
        return sanaLista.length;
    }

    /**
     * @see Tietoinen#laskeFrekvenssit()
     */
    public TreeMap<String, Integer> laskeFrekvenssit() {
        TreeMap<String, Integer> frekvenssit = new TreeMap<String, Integer>();

        String[] sanaLista = teksti.split(" ");
        for (String sana : sanaLista) {
            if (!frekvenssit.containsKey(sana)) {
                frekvenssit.put(sana, 1);
            }
            else {
                Integer val = frekvenssit.get(sana);
                frekvenssit.replace(sana, val+1);
            }
        }
        return frekvenssit;
    }

}
