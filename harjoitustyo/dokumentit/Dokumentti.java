/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo.dokumentit;

/**
 *
 * @author Miia
 */

public abstract class Dokumentti {
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

    @Override
    public String toString() {
        return tunniste+"///"+teksti;
    }

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
}
