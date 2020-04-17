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
        if (uusi != null && uusi instanceof Comparable) {
            //Jos lista on tyhjä, lisätään uusi alkio suoraan
            if (this.size() == 0) {
                this.add(uusi);
            } 
            else {
                Comparable compUusi = (Comparable)uusi;

                //Käydään lista kohta kohdalta läpi
                for(int i = 0; i < this.size(); i++) {
                    Comparable compTemp = (Comparable)this.get(i);
                    
                    //Jos uusi alkio on pienempi kuin kyseisen kohdan alkio, lisätään se sen paikalle
                    if (compTemp.compareTo(compUusi) >= 1) {
                        this.add(i, uusi);
                        break;
                    }
                    //Jos uusi alkio on suurempi kuin muut alkiot listalla, lisätään se listan viimeiseksi
                    else if (i == this.size()-1) {
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
