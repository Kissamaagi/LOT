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

public class Vitsi extends Dokumentti{
    //Attribuutti
    private String laji; //Ei null tai ""
    
    //Rakentaja
    public Vitsi(int uusiTunniste, String uusiLaji, String uusiTeksti) {
        super(uusiTunniste, uusiTeksti);
        laji(uusiLaji);
    }

    //Aksessorit laji-attribuutille
    public void laji(String uusiLaji) throws IllegalArgumentException {
        if (uusiLaji != null && !uusiLaji.equals("")) {
            laji = uusiLaji;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    public String laji() {
        return laji;
    }
}
