package harjoitustyo.omalista;

import harjoitustyo.apulaiset.Ooperoiva;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * OmaLista-luokka, joka on LinkedListin alaluokka. 
 * Listalle voidaan lisätä minkä tahansa tyyppisiä alkioita,
 * ja alkiot järjetyvät listalle oikeaan järjestykseen.
 *
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2020
 * 
 * @author Miia Pynnönen (miia.pynnonen@tuni.fi)
 */
public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {
    /**
     * @see Ooperoiva#lisää(Object)
     */
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

                for(int i = 0; i < this.size(); i++) {
                    Comparable compTemp = (Comparable)this.get(i);
                    
                    //Jos uusi alkio on pienempi kuin kyseisen kohdan alkio, lisätään se sen paikalle
                    if (compTemp.compareTo(compUusi) >= 1) {
                        this.add(i, uusi);
                        break;
                    }
                    //Jos uusi alkio on suurempi kuin kaikki muut alkiot listalla, lisätään se listan viimeiseksi
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

    /**
     * @see Ooperoiva#lajittele(Comparator)
     */
    public void lajittele(Comparator<? super E> vertailija)
    throws IllegalArgumentException {
      if (vertailija != null) {
        this.sort(vertailija);
      }
      else {
          throw new IllegalArgumentException();
      }
    }
    
}
