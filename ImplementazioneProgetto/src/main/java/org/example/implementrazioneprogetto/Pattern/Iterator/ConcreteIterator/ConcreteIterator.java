package org.example.implementrazioneprogetto.Pattern.Iterator.ConcreteIterator;

import org.example.implementrazioneprogetto.Pattern.Iterator.Iterator.MyIterator;

import java.util.List;
import java.util.NoSuchElementException;

public class ConcreteIterator<T> implements MyIterator<T> {
    //Attributi
    private List<T> list;
    private int index;

    //Costruttore
    public ConcreteIterator(List<T> list) {
        this.list = list;
        this.index = 0;
    }

    //Metodi
    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    //metodo che restituisce l'elemento corrente e passa al successivo
    @Override
    public T next() {
        if (hasNext()) {
            T item = list.get(index);
            index++;
            return item;
        } else {
            throw new NoSuchElementException("Nessun elemento rimasto!");
        }
    }

    @Override
    public T getCurrent() {
        if (hasNext())
            return list.get(index);
        else
            throw new NoSuchElementException("Nessun elemento rimasto!");
    }
}
