/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo.kokoelma;

import harjoitustyo.omalista.OmaLista;
import harjoitustyo.dokumentit.*;
import harjoitustyo.apulaiset.Kokoava;
/**
 *
 * @author Miia
 */
public class Kokoelma extends Object implements Kokoava<Dokumentti> {
    private OmaLista<Dokumentti> dokumentit;

    public Kokoelma() {
        dokumentit = new OmaLista<Dokumentti>();
    }

    public OmaLista<Dokumentti> dokumentit() {
        return dokumentit;
    }

    public void lisää(Dokumentti uusi) throws IllegalArgumentException {
        if (uusi != null && !dokumentit.contains(uusi)) {
            dokumentit.lisää(uusi);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public Dokumentti hae(int tunniste) {
        if (dokumentit.size() > 0) {
            for (Dokumentti dokumentti : dokumentit) {
                if (tunniste == dokumentti.tunniste()) {
                    return dokumentti;
                }
            }
        }
        return null;
    }
    
}
