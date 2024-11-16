package org.example.implementrazioneprogetto.Pattern.Observer.ConcreteSubject;

import org.example.implementrazioneprogetto.Pattern.Observer.Observer.ChartObserver;
import org.example.implementrazioneprogetto.Pattern.Observer.Subject.MySubject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConcreteSubject implements MySubject {
    //Attributi
    private List<ChartObserver> observers;
    private List<Integer> dati;

    //Costruttore
    public ConcreteSubject() {
        observers = new ArrayList<>();
        dati = new ArrayList<>();
    }

    //Metodi
    @Override
    public void attach(ChartObserver o) {
        observers.add(o);
    }
    @Override
    public void detach(ChartObserver d) {
        observers.remove(d);
    }
    @Override
    public void notifyObservers() {
        for (ChartObserver observer : observers) {
            observer.update();
        }
    }

    public void setData(List<Integer> dati) {
        this.dati = dati;

        notifyObservers();
    }
    public List<Integer> getData() {
        return dati;
    }
}
