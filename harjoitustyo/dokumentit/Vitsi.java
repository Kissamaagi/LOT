package harjoitustyo.dokumentit;

/**
 * Vitsi luokka, joka on Dokumentin alaluokka. Luokassa vitseille ominaiset piirteet.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2020 
 * <p>
 * @author Miia Pynnönen (miia.pynnonen@tuni.fi)
 */

public class Vitsi extends Dokumentti{
    /** Vitsin laji. */
    private String laji; //Ei null tai ""
    
    /**
     * Parametrillinen rakentaja Vitseille. 
     * 
     * @param uusiTunniste uusi tunniste vitsille.
     * @param uusiLaji uusi laji vitsille.
     * @param uusiTeksti uusi teksti vitsille.
     */
    public Vitsi(int uusiTunniste, String uusiLaji, String uusiTeksti) {
        super(uusiTunniste, uusiTeksti);
        laji(uusiLaji);
    }

    /**
     * Asettava aksessori vitsin lajille.
     * laji tarkastetaan ettei se ole null tai tyhjä merkkijono.
     * 
     * @param uusiLaji uusi laji, joka tarkastetaan.
     * @throws IllegalArgumentException jos parametrissa on virheitä.
     */
    public void laji(String uusiLaji) throws IllegalArgumentException {
        if (uusiLaji != null && !uusiLaji.equals("")) {
            laji = uusiLaji;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Palauttava aksessori vitsin lajille. 
     * 
     * @return vitsin laji.
     */
    public String laji() {
        return laji;
    }

    /**
     * Korvattu Object-luokan toString-metodi. 
     * 
     * @return Vitsin tunniste, laji ja teksti erotettuna merkeillä ///
     */
    @Override
    public String toString() {
        String merkit[] = super.toString().split("///");
        return merkit[0]+"///"+laji+"///"+merkit[1];
    }
}
