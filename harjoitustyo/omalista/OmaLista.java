/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo.omalista;
import harjoitustyo.apulaiset.Ooperoiva;
import java.util.LinkedList;

/**
 *
 * @author Miia
 */
public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {

    @Override
    @SuppressWarnings({"unchecked"})
    public void lisää(E uusi) throws IllegalArgumentException{
        if (uusi != null) {
            if (this.size() == 0) {
                this.add(uusi);
                System.out.println("uusi lisätty: "+0);
            } 
            else {
                Comparable compUusi = (Comparable)uusi;

                for(int i = 0; i < this.size(); i++) {
                    Comparable compTemp = (Comparable)this.get(i);
                    
                    //NYT EHKÄ TOIMII???   TAI EI??? 
                    if (compTemp.compareTo(compUusi) >= 1) {
                        System.out.println("uusi on isompi: "+i);
                        this.add(i, uusi);
                        break;
                    }
                    else if (compTemp.compareTo(compUusi) < 0) {
                        System.out.println("uusi on yhtäsuuri: "+i);
                        this.add(i+1, uusi);
                        break;
                    }
                    /*
                    *else if (compTemp.compareTo(compUusi) == 0) {
                    *    System.out.println("Yhtäsuuri...");
                    *    for(int j = i; j < this.size(); j++) {
                    *        Comparable compYhtäsuuri = (Comparable)this.get(j);
                    *        System.out.println(j);
                    *        if(compYhtäsuuri.compareTo(compUusi) > 0) {
                    *            System.out.println("uusi on samankokoinen: "+j);
                    *            this.add(j, uusi);
                    *            break;
                    *        }  
                    *    }     
                    *}
                    */
                    else if (i == this.size()-1) {
                        System.out.println("uusi on kaikkia pienempi, menee loppuun: "+i+1);
                        this.addLast(uusi);
                        break;
                    }
                }
            }
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    
}
