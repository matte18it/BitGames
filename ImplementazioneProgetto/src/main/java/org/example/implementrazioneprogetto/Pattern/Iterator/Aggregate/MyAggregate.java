package org.example.implementrazioneprogetto.Pattern.Iterator.Aggregate;

import org.example.implementrazioneprogetto.Pattern.Iterator.Iterator.MyIterator;

public interface MyAggregate<T> {
    MyIterator<T> getIterator();
    void addItem(T item);
    int size();
}
