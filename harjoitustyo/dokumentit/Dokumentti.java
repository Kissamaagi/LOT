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
public class Dokumentti {
    private int tunniste; //Oltava >0
    private String teksti; //Ei saa olla null tai ""
    
    //Rakentajat
    public void tunniste(int uusiTunniste) {
        if (uusiTunniste > 0) {
            tunniste = uusiTunniste;
        }
    }
    public void teksti(String uusiTeksti) {
        if (uusiTeksti != null && !uusiTeksti.equals("")){
            teksti = uusiTeksti;
        }
    }
    
    public int tunniste() {
        return tunniste;
    }
    public String teksti() {
        return teksti;
    }
}
