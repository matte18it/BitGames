package org.example.implementrazioneprogetto.Pattern.Iterator.Iterator;

import java.util.Iterator;

// Interfaccia Iterator
public interface MyIterator<T> {
    boolean hasNext();
    T next();
    T getCurrent();
}
