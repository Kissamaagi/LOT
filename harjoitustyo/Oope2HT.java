/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo;
import harjoitustyo.dokumentit.*;
import harjoitustyo.omalista.OmaLista;

import java.util.LinkedList;

/**
 *
 * @author Miia
 */
public class Oope2HT {
    public static void main(String[] args) {
        System.out.println("Welcome to L.O.T.");

        OmaLista<Object> omaLista = new OmaLista();

        omaLista.lisää("aaa");
        omaLista.lisää("bbb");
        omaLista.lisää("aaa");
        omaLista.lisää("bbb");
        omaLista.lisää("bbb");
        omaLista.lisää("aaa");

        for (Object alkio : omaLista) {
            System.out.println(alkio);
        }
    }
}
