/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo.dokumentit;

import java.time.LocalDate;

/**
 *
 * @author Miia
 */
public class Uutinen extends Dokumentti {
    //Attribuutti
    private LocalDate päivämäärä; //ei null
    
    //Rakentaja
    public Uutinen(int uusiTunniste, LocalDate uusiPvm, String uusiTeksti) {
        super(uusiTunniste, uusiTeksti);
        päivämäärä(uusiPvm);
    }

    //Aksessorit päivämäärä-attribuutille
    public void päivämäärä(LocalDate uusiPvm) throws IllegalArgumentException{
        if (uusiPvm != null) {
            päivämäärä = uusiPvm;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    public LocalDate päivämäärä(){
        return päivämäärä;
    }
}
