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

//TODO: polish, add, remove, find
//TODO: add-komento: dokumentin tyypin tarkistus, lisääminen 

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
            lisääKokoelmaan(dokTiedosto);
            luoSulkusanaLista(sulkuTiedosto);
            Scanner inputReader = new Scanner(System.in);
            boolean echo = false;
            while (jatkuu) {
                System.out.println("Please, enter a command:");
                String[] komennot = inputReader.nextLine().split(" ");

                if (echo) {
                    System.out.println(komennot[0]);
                }

                switch (komennot[0]) {
                    case "quit": jatkuu = false;
                                 break;
                    case "echo": echo = !echo; 
                                 break;
                    case "print": tulosta(komennot);
                                  break;
                    case "reset": lisääKokoelmaan(dokTiedosto);
                                  luoSulkusanaLista(sulkuTiedosto);
                                  break;
                    case "add": lisääKokoelmaan(komennot);
                                break;
                    case "remove":
                    case "find":
                    case "polish":
                    default: System.out.println("Error!");
                             break;
                }
            }
            inputReader.close();
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
     * Metodi käyttöliittymän kautta uuden dokumentin kokoelmaan lisäämiseen.
     * 
     * @param komennot käyttäjän antamat komennot, joiden mukaan dokumentti lisätään.
     */
    public void lisääKokoelmaan(String[] komennot) {
        if (komennot.length >= 2) {
            //selvitetään eka onko uutinen vai vitsi

            //sitten onkoUutinen booleanin mukaan lisätään
        }
        else {
            System.out.println("Error!");
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

    /**
     * Metodi tulostaa komennon mukaan joko yhden dokumentin tunnisteen mukaan,
     * tai kaikki kokoelman dokumentit jos tunnistetta ei ole annettu.
     * 
     * @param komennot joista päätellään mitä tulostetaan
     */
    public void tulosta(String[] komennot) {
        if (komennot.length == 1) {
            for (Dokumentti dokumentti : kokoelma.dokumentit()) {
                System.out.println(dokumentti.toString());
            }
        }
        else if (komennot.length == 2) {
            int haettavaTunniste = onkoNumero(komennot[1]);

            if (haettavaTunniste > 0) {
                Dokumentti tulostettava = kokoelma.hae(haettavaTunniste);
                if (tulostettava != null) {
                    System.out.println(tulostettava.toString());
                }
                else {
                    System.out.println("Error!");
                }
            }
            else {
                System.out.println("Error!");
            }
        }

    }

    /**
     * Metodi, jolla selvitetään sisältääkö String-tyyppinen
     * muuttuja numeron vai ei.
     * 
     * @param ehkäNumero String joka tarkistetaan.
     * @return int, joka vastaa Stringissä olevaa numeroa 
     * tai null, jos numeroa ei löytynyt.
     */
    public int onkoNumero(String ehkäNumero) {
        try {
            return Integer.parseInt(ehkäNumero);
        }
        catch (Exception e) {
            return 0;
        }
    }
}
