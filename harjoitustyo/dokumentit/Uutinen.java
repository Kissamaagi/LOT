package harjoitustyo.dokumentit;

import java.time.LocalDate;

/**
 * Uutinen luokka, joka on Dokumentin alaluokka. Luokassa uutisille ominaiset piirteet.
 * 
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2020
 * 
 * @author Miia Pynnönen (miia.pynnonen@tuni.fi)
 */
public class Uutinen extends Dokumentti {
    /**Uutisen päivämäärä */
    private LocalDate päivämäärä; //ei null
    
    /**
     * Uutisen parametrillinen rakentaja.
     * 
     * @param uusiTunniste uusi tunniste uutiselle.
     * @param uusiPvm uusi päivämäärä uutiselle.
     * @param uusiTeksti uusi teksti uutiselle. 
     */
    public Uutinen(int uusiTunniste, LocalDate uusiPvm, String uusiTeksti) {
        super(uusiTunniste, uusiTeksti);
        päivämäärä(uusiPvm);
    }

    /**
     * Asettava aksessori päivämäärälle
     * 
     * @param uusiPvm uusi päivämäärä joka tarkistetaan, ettei ole null.
     * @throws IllegalArgumentException jos päivämäärä on null.
     */
    public void päivämäärä(LocalDate uusiPvm) throws IllegalArgumentException{
        if (uusiPvm != null) {
            päivämäärä = uusiPvm;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Palauttava aksessori päivämäärälle
     * @return päivämäärä
     */
    public LocalDate päivämäärä(){
        return päivämäärä;
    }

    /**
     * Korvattu Object-luokan toString-metodi. 
     * 
     * @return Uutisen tunniste, päivämäärä ja teksti erotettuna merkeillä ///
     */
    @Override
    public String toString() {
        int päivä = päivämäärä.getDayOfMonth();
        int kuukausi = päivämäärä.getMonthValue();
        int vuosi = päivämäärä.getYear();

        String merkit[] = super.toString().split("///");
        return merkit[0]+"///"+päivä+"."+kuukausi+"."+vuosi+"///"+merkit[1];
    }
}
