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
            this.add(uusi);
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    
}
