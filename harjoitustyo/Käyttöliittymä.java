package harjoitustyo;

import java.io.File;
import harjoitustyo.kokoelma.Kokoelma;

/**
 *
 * @author Miia Pynnönen (miia.pynnonen@tuni.fi)
 */
public class Käyttöliittymä {
    private File dokumenttilista;
    private File sulkusanalista;
    private Kokoelma kokoelma;

    public Käyttöliittymä(String dokumentit, String sulkusanat) {
        dokumenttilista = new File(dokumentit);
        sulkusanalista = new File(sulkusanat);
        kokoelma = new Kokoelma();
    }

    public void lisääKokoelmaan(File dokumenttilista) {
        
    }
}
