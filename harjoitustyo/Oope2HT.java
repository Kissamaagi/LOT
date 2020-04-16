/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo;
import harjoitustyo.dokumentit.*;
import java.util.LinkedList;

/**
 *
 * @author Miia
 */
public class Oope2HT {
    public static void main(String[] args) {
        System.out.println("Welcome to L.O.T.");

        int uusiTunniste = 10;
        String uusiLaji = "epidemia";
        String uusiTeksti = "Mies meni kauppaan. Korona.";
        Vitsi x = new Vitsi(uusiTunniste, uusiLaji, uusiTeksti);

        uusiTunniste = 1;
        uusiLaji = "hassu";
        uusiTeksti = "Nothing sucks like a hoover.";
        Vitsi y = new Vitsi(uusiTunniste, uusiLaji, uusiTeksti);

        int vertaus = x.compareTo(y);

        LinkedList<String> sulkusanat = new LinkedList<>();
        sulkusanat.add("nothing");
        sulkusanat.add("a");
        String välimerkit = ".";

        y.siivoa(sulkusanat, välimerkit);

        System.out.println(y.teksti());
    }
}
