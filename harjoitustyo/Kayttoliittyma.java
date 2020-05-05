package harjoitustyo;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import harjoitustyo.dokumentit.Dokumentti;
import harjoitustyo.dokumentit.Uutinen;
import harjoitustyo.dokumentit.Vitsi;
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
    /** Boolean jolla tarkastetaan onko kokoelma uutisia */
    private boolean onUutinen = false;

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
        argsOk = (args != null && args.length == 2);
        if (argsOk) {
            try {
                dokTiedosto = new File(args[0]);
                sulkuTiedosto = new File(args[1]);

                onUutinen = args[0].contains("news");
                filesOk = (dokTiedosto.exists() && sulkuTiedosto.exists());
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

    /**
     * Metodissa lisätään tiedostossa saadut dokumentit kokoelmaan. 
     * Aiemmin tiedostoa lukiessa on tarkastettu onko tiedoston nimessä "news",
     * jonka mukaan dokumentit olisivat uutisia. Tässä metodissa sen arvon mukaan
     * tehdään dokumenteista joko uutis- tai vitsiolioita, ja lisätään kokoelmaan.
     * 
     * @param dokTiedosto dokumentit sisältävä tiedosto
     */
    public void lisääKokoelmaan(File dokTiedosto) {
        Scanner dokLukija = null;
        try {
            dokLukija = new Scanner(dokTiedosto);
            while (dokLukija.hasNextLine()) {
                String[] tempDok = dokLukija.nextLine().split("///");
                if (onUutinen) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
                    Uutinen uusi = new Uutinen(Integer.parseInt(tempDok[0]), 
                                               LocalDate.parse(tempDok[1], formatter), 
                                               tempDok[2]);
                    kokoelma.lisää(uusi);
                }
                else {
                    Vitsi uusi = new Vitsi(Integer.parseInt(tempDok[0]), 
                                           tempDok[1], 
                                           tempDok[2]);
                    kokoelma.lisää(uusi);
                }
            }
        }
        catch (Exception e) {
            dokLukija.close();
        }
    }

    /**
     * Metodissa lisätään tiedostosta sulkusanat sulkusanalistaan
     * 
     * @param sulkuTiedosto sulkusanat sisältävä tiedosto
     */
    public void luoSulkusanaLista(File sulkuTiedosto) {
        sulkusanat = new LinkedList<String>();
        Scanner sulkuLukija = null;
        try {
            sulkuLukija = new Scanner(sulkuTiedosto);

            while (sulkuLukija.hasNextLine()) {
                sulkusanat.add(sulkuLukija.nextLine());
            }
            sulkuLukija.close();
        }
        catch (Exception e) {
            sulkuLukija.close();
        }
    }
}
