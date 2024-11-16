package org.example.implementrazioneprogetto.controller;

import org.example.implementrazioneprogetto.Pattern.Singleton.DBManager;
import org.example.implementrazioneprogetto.Pattern.Iterator.Aggregate.MyAggregate;
import org.example.implementrazioneprogetto.Pattern.Iterator.Iterator.MyIterator;
import org.example.implementrazioneprogetto.Pattern.Singleton.UserData;
import org.example.implementrazioneprogetto.model.Gioco;
import org.example.implementrazioneprogetto.persistenza.GiocoDaoPostgre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewGameController {
    @GetMapping("/visualizzaGiochi")
    public String visualizzaGioco(Model model) {
        //qua vado a cercare tutti i giochi relativi all'utente loggato
        GiocoDaoPostgre giocoDaoPostgre = new GiocoDaoPostgre(DBManager.getInstance().getConnection());

        //creo un concreteAggregate per la lista di giochi
        MyAggregate<Gioco> games = giocoDaoPostgre.findAll(UserData.getInstance().getUsername());

        //creo un iteratore per la mia lista di giochi
        MyIterator<Gioco> gameIterator = games.getIterator();

        model.addAttribute("gameIterator", gameIterator);
        model.addAttribute("size", games.size());

        return "visualizzaGiochi";
    }
}
