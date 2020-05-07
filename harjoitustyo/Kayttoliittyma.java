package harjoitustyo;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import harjoitustyo.dokumentit.Dokumentti;
import harjoitustyo.dokumentit.Uutinen;
import harjoitustyo.dokumentit.Vitsi;
import harjoitustyo.kokoelma.Kokoelma;

import java.util.Arrays;
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
            lisääKokoelmaan(dokTiedosto);
            luoSulkusanaLista(sulkuTiedosto);
            Scanner inputReader = new Scanner(System.in);
            boolean echo = false;
            while (jatkuu) {
                System.out.println("Please, enter a command:");
                String komento = inputReader.nextLine();
                String[] komennot = komento.split(" ");

                switch (komennot[0]) {
                    case "quit": if (echo) {echo(komento);}
                                 jatkuu = false;
                                 break;
                    case "echo": echo = !echo; 
                                 if (echo) {echo(komento);}
                                 break;
                    case "print": if (echo) {echo(komento);}
                                  tulosta(komennot);
                                  break;
                    case "reset": if (echo) {echo(komento);}
                                  reset(komennot);
                                  break;
                    case "add": if (echo) {echo(komento);}
                                lisääKokoelmaan(komennot);
                                break;
                    case "remove": if (echo) {echo(komento);}
                                   poistaKokoelmasta(komennot);
                                   break;
                    case "find": if (echo) {echo(komento);}
                                 hae(komennot);
                                 break;
                    case "polish": if (echo) {echo(komento);}
                                   esikasittely(komennot);
                                   break;
                    default: if (echo) {echo(komento);}
                             oletTehnytVirheitaPoika();
                             break;
                }
            }
            inputReader.close();
        }
        
        System.out.println("Program terminated.");
    }

    /**
     * Metodi kaiuttaa käyttäjän antaman komennon.
     * 
     * @param komento käyttäjän antama komento.
     */
    public void echo(String komento) {
        System.out.println(komento);
    }

    /**
     * Metodissa lisätään tiedostossa saadut dokumentit kokoelmaan. 
     * Aiemmin tiedostoa lukiessa on tarkastettu onko tiedoston nimessä "news",
     * jonka mukaan dokumentit olisivat uutisia. Metodista kutsutaan uutis- tai vitsi-
     * olioita luovia metodeja sen mukaan, mitä kokoelmalle asetetaan. 
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
                    luoUutinen(tempDok);
                }
                else {
                    luoVitsi(tempDok);
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
            String[] uusiDokumentti = kokoaDokumentti(komennot).split("///");
            if (onUutinen) {
                luoUutinen(uusiDokumentti);
            }
            else {
                luoVitsi(uusiDokumentti);
            }
        }
        else {
            oletTehnytVirheitaPoika();
        }

            
    }

    /**
     * Metodi luo uuden uutisolion ja lisää sen kokoelmaan.
     * 
     * @param uusiUutinen uutisen tiedot oikeassa järjestyksessä, String[]-tyyppi
     */
    public void luoUutinen(String[] uusiUutinen) {
        try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.uuuu");
        LocalDate pvm = LocalDate.parse(uusiUutinen[1], formatter);
        Uutinen uusi = new Uutinen(Integer.parseInt(uusiUutinen[0]), 
                                    pvm, 
                                    uusiUutinen[2]);
        kokoelma.lisää(uusi);
        }
        catch (Exception e) {
            oletTehnytVirheitaPoika();
        }  
    }

    /**
     * Metodi luo uuden Vitsiolion ja lisää sen kokoelmaan
     * 
     * @param uusiVitsi vitsin tiedot String[]-tyyppisenä, oikeassa järjestyksessä
     */
    public void luoVitsi(String[] uusiVitsi) {
        try {
            if (!uusiVitsi[1].contains(".")) {
                Vitsi uusi = new Vitsi(Integer.parseInt(uusiVitsi[0]), 
                                                        uusiVitsi[1], 
                                                        uusiVitsi[2]);
                kokoelma.lisää(uusi);
            }
            else {
                oletTehnytVirheitaPoika();
            }
        }
        catch (IllegalArgumentException e){
            oletTehnytVirheitaPoika();
        }
        
    }

    /**
     * Metodi kokoaa add-komentona annetusta dokumentista
     * String-tyyppisen dokumentin
     * 
     * @param komennot komento arrayna, pilkottu " "-merkistä
     * @return Dokumentti String-muodossa ilman "add"-sanaa
     */
    public String kokoaDokumentti(String[] komennot) {
        LinkedList<String> uusiDokumentti = new LinkedList<String>(Arrays.asList(komennot));
        uusiDokumentti.removeFirst();

        StringBuilder dok = new StringBuilder();

        for (int i = 0; i < uusiDokumentti.size(); i++) {
            if (i == 0) {
                dok.append(uusiDokumentti.get(i));
            }
            else {
                dok.append(" " + uusiDokumentti.get(i));
            }
        }
        return dok.toString();
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
     * Metodi jolla poistetaan tietty dokumentti tunnisteen perusteella kokoelmasta.
     * 
     * @param komennot käyttäjän antamat komennot String[]-muodossa
     */
    public void poistaKokoelmasta(String[] komennot) {
        if (komennot.length == 2) {
            try {
                int poistettavaTunnus = onkoNumero(komennot[1]);
                kokoelma.poista(poistettavaTunnus);
            }
            catch (IllegalArgumentException e) {
                oletTehnytVirheitaPoika();
            }   
        }
        else {
            oletTehnytVirheitaPoika();
        }
    }

    /**
     * Metodi, joka hakee hakusanoilla dokumentteja, ja tulostaa niiden tunnisteet
     * 
     * @param komennot käyttäjän antamat komennot String[] muodossa
     */
    public void hae(String[] komennot) {
        if (komennot.length >= 2) {
            LinkedList<String> hakusanat = new LinkedList<String>(Arrays.asList(komennot));
            hakusanat.removeFirst();

            for (Dokumentti haettava : kokoelma.dokumentit()) {
                if (haettava.sanatTäsmäävät(hakusanat)) {
                    System.out.println(haettava.tunniste());
                }
            }
        }
        else {
            oletTehnytVirheitaPoika();
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
                    oletTehnytVirheitaPoika();
                }
            }
            else {
                oletTehnytVirheitaPoika();
            }
        }
        else {
            oletTehnytVirheitaPoika();
        }

    }

    /**
     * Metodi esikäsittelee kokoelman poistamalla käyttäjän antamat välimerkit,
     * laittamalla kaiken pieniksi kirjaimiksi ja poistamalla sulkusanalistan sanat.
     * 
     * @param komennot käyttäjän antamat komennot
     */
    public void esikasittely(String[] komennot) {
        if (komennot.length == 2) {
            String välimerkit = komennot[1];
            System.out.println(välimerkit);

            for (Dokumentti siivottava : kokoelma.dokumentit()) {
                siivottava.siivoa(sulkusanat, välimerkit);
            }
        }
        else {
            oletTehnytVirheitaPoika();
        }
    } 

    /**
     * Metodi poistaa aiemmat muutokset ja lataa dokumentit uudelleen kokoelmaan.
     * Virheilmoitus tulee, jos käyttäjä on antanut reset-komennon lisäksi 
     * ylimääräisiä parametrejä.
     * 
     * @param komennot käyttäjän antamat komennot.
     */
    public void reset(String[] komennot) {
        if (komennot.length == 1) {
            kokoelma.dokumentit().clear();
            lisääKokoelmaan(dokTiedosto);
        }
        else {
            oletTehnytVirheitaPoika();
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

    public void oletTehnytVirheitaPoika() {
        System.out.println("Error!");
    }
}
