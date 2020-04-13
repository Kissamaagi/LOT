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
    private int tunniste; 
    private String teksti; 
    
    //Rakentajat
    //NÄITÄ EI VISSIIN PITÄISI OLLA????
    public void tunniste(int uusiTunniste) throws IllegalArgumentException {
        if (uusiTunniste > 0) {
            tunniste = uusiTunniste;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    public void teksti(String uusiTeksti) throws IllegalArgumentException {
        if (uusiTeksti != null && !uusiTeksti.equals("")){
            teksti = uusiTeksti;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    
    //Aksessorit
    public int tunniste() {
        return tunniste;
    }
    public String teksti() {
        return teksti;
    }
}
