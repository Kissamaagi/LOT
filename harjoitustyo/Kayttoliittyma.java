package harjoitustyo;

import java.io.File;
import harjoitustyo.kokoelma.Kokoelma;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Harjoitustyön Käyttöliittymä-luokka. Luokassa tapahtuu kaikki interaktio
 * käyttäjän kanssa. Luokasta ajetaan myös muiden luokkien metodeja, erityisesti
 * Kokoelma-luokan.
 * 
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2020 
 *
 * @author Miia Pynnönen (miia.pynnonen@tuni.fi)
 */
public class Kayttoliittyma {
    /**
     * File-tyyppinen attribuutti, jossa säilytetään
     * dokumentit sisältävää tekstitiedostoa
     */
    private File dokTiedosto;
    /**
     * File-tyyppinen attribuutti, jolla säilytetään
     * sulkusanat sisältävää tekstitiedostoa
     */
    private File sulkuTiedosto;

    /** Sulkusanat sisältävä String-tyyppinen LinkedList */
    private LinkedList<String> sulkusanat;
    /** Kokoelma joka sisältää dokumentit */
    private Kokoelma kokoelma;

    /** Boolean jolla tarkastetaan että komentiriviparametrit on oikein */
    private boolean argsOk = false;
    /** Boolean jolla tarkastetaan että tiedostot on oikein */
    private boolean filesOk = false;

    /** 
     * Rakentaja, jossa alustetaan kokoelma, dokTiedosto ja sulkuTiedosto.
     * Parametrit ja tiedostot tarkistetaan, että ne eivät ole virheellisiä,
     * ja että teidostot ovat oikeita tiedostoja.
     * 
     * Metodista kustutaan myös kokoelmaan dokumentteja lisäävää metodia sekä sulkusanalistan luovaa metodia.
     * 
     * @param args ajoluoaksta saadut komentoriviparametrit
     */
    public Kayttoliittyma(String[] args) {
        kokoelma = new Kokoelma();
        if (args != null && args.length == 2) {
            argsOk = true;
            try {
                dokTiedosto = new File(args[0]);
                sulkuTiedosto = new File(args[1]);
                if (dokTiedosto.exists() && sulkuTiedosto.exists()) {
                    filesOk = true;
                }
            }
            catch (Exception e) {
                filesOk = false;
            }
        }
    }

    /**
     * Metodi, joka sisältää ohjelman pääsilmukan
     */
    public void start() {
        System.out.println("Welcome to L.O.T.");
        boolean jatkuu = true;
        if (!argsOk) {
            System.out.println("Wrong number of command-line arguments!");
        }
        else if (!filesOk) {
            System.out.println("Missing file!");
        }
        else {
            while (jatkuu) {
                System.out.println("Please, enter a command:");
                jatkuu = false;
            }
        }
        System.out.println("Program terminated.");
    }

    public void lisääKokoelmaan(File dokTiedosto) {
    }

    public void luoSulkusanaLista(File sulkuTiedosto) {
    }
}
