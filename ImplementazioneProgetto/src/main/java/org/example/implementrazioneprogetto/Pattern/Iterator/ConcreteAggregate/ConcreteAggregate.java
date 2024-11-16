package org.example.implementrazioneprogetto.Pattern.Iterator.ConcreteAggregate;

import org.example.implementrazioneprogetto.Pattern.Iterator.Aggregate.MyAggregate;
import org.example.implementrazioneprogetto.Pattern.Iterator.ConcreteIterator.ConcreteIterator;
import org.example.implementrazioneprogetto.Pattern.Iterator.Iterator.MyIterator;

import java.util.ArrayList;
import java.util.List;

public class ConcreteAggregate<T> implements MyAggregate<T> {
    // Attributi
    private List<T> items;

    // Costruttore
    public ConcreteAggregate() {
        this.items = new ArrayList<>();
    }

    // Metodi
    @Override
    public void addItem(T item) {
        items.add(item);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public MyIterator<T> getIterator() {
        return new ConcreteIterator<>(items);
    }

}
