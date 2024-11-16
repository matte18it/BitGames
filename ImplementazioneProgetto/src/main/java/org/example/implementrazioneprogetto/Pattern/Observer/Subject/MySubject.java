package org.example.implementrazioneprogetto.Pattern.Observer.Subject;

import org.example.implementrazioneprogetto.Pattern.Observer.Observer.ChartObserver;

public interface MySubject {
    void attach(ChartObserver o);
    void detach(ChartObserver d);
    void notifyObservers();
}
