import harjoitustyo.Kayttoliittyma;

/**
 * Harjoitustyön ajoluokka, jossa käynnistetään käyttöliittymä
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2020 
 * <p>
 * @author Miia Pynnönen (miia.pynnonen@tuni.fi)
 */
public class Oope2HT {
    public static void main(String[] args) {
        Kayttoliittyma kayttis = new Kayttoliittyma(args);
        kayttis.start();
    }
}
