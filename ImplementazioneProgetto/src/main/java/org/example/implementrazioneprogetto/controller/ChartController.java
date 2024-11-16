package org.example.implementrazioneprogetto.controller;

import org.example.implementrazioneprogetto.Pattern.Singleton.DBManager;
import org.example.implementrazioneprogetto.Pattern.Observer.ConcreteObserver.ImageChartObserver;
import org.example.implementrazioneprogetto.Pattern.Observer.ConcreteSubject.ConcreteSubject;
import org.example.implementrazioneprogetto.Pattern.Singleton.UserData;
import org.example.implementrazioneprogetto.persistenza.StatisticheDaoPostgre;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

@Controller
public class ChartController {
    //Attributi
    StatisticheDaoPostgre statisticheDaoPostgre;
    ConcreteSubject subject;
    ImageChartObserver o1;

    @GetMapping("/statistiche")
    public String statistiche() {
        //inizializzo tutte le varibili
        statisticheDaoPostgre = new StatisticheDaoPostgre(DBManager.getInstance().getConnection());

        subject = new ConcreteSubject();
        o1 = new ImageChartObserver(subject);

        subject.attach(o1);

        return "statistiche";
    }

    @GetMapping("/updateData") @ResponseBody
    public List<Integer> updateData() {
        //prendo i dati dal db
        List<Integer> dati = statisticheDaoPostgre.getStatistiche(UserData.getInstance().getUsername());

        Collections.reverse(dati);
        //aggiorno i dati del subject
        subject.setData(dati);
        //aggiorno gli observer
        subject.notifyObservers();

        Collections.reverse(dati);
        //restituisco i dati
        return dati;
    }
}
