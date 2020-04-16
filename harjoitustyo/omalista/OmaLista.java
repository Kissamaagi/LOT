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
            } 
            else {
                for(int i = 0; i < this.size(); i++) {
                    E temp = this.get(i);
                    Comparable compTemp = (Comparable)temp;
                    Comparable compUusi = (Comparable)uusi;

                    //NÄÄ EI TOIMI, EI LISÄÄ OIKEIN, LOGIIKKAONKLEMA VISSII
                    if (compUusi.compareTo(compTemp) == -1) {
                        this.add(i, uusi);
                    }
                    else if (compUusi.compareTo(compTemp) == 0) {
                        this.add(i+1, uusi);
                    }
                }
            }
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    
}
