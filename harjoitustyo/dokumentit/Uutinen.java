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
    public void päivämäärä(LocalDate uusiPvm) throws IllegalArgumentException{
        if (uusiPvm != null) {
            päivämäärä = uusiPvm;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    
    //Aksessori
    public LocalDate päivämäärä(){
        return päivämäärä;
    }
}
