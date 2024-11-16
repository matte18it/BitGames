package org.example.implementrazioneprogetto.controller;

import org.example.implementrazioneprogetto.Pattern.Singleton.UserData;
import org.example.implementrazioneprogetto.persistenza.GiocoDaoPostgre;
import org.example.implementrazioneprogetto.persistenza.StatisticheDaoPostgre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.example.implementrazioneprogetto.Pattern.Singleton.DBManager;
import org.example.implementrazioneprogetto.model.Utente;
import org.example.implementrazioneprogetto.persistenza.UtenteDaoPostgre;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;


@Controller
public class UserController {
    @GetMapping("/homePage")
    public String getUtente(@RequestParam(name = "username", required = false) String username, @RequestParam(name = "password", required = false) String password, @RequestParam(name = "email", required = false) String email, @RequestParam(name = "nome", required = false) String nome, @RequestParam(name = "cognome", required = false) String cognome, @RequestParam(name = "dataNascita", required = false) String dataNascita, @RequestParam(name = "citta", required = false) String citta, Model model) {
        String saluto = "";

        //Con questo codice si ottiene l'ora attuale in millisecondi
        Calendar cal = Calendar.getInstance();
        if(cal.get(Calendar.HOUR_OF_DAY) < 12)
            saluto = "Buongiorno";
        else if(cal.get(Calendar.HOUR_OF_DAY) < 18)
            saluto = "Buon pomeriggio";
        else
            saluto = "Buonasera";

        UtenteDaoPostgre utenteProgrammatore = new UtenteDaoPostgre(DBManager.getInstance().getConnection());
        GiocoDaoPostgre giocoConcrete = new GiocoDaoPostgre(DBManager.getInstance().getConnection());

        Utente utente = utenteProgrammatore.findByUsername(UserData.getInstance().getUsername());
        model.addAttribute("benvenutoMessage", saluto + " " + utente.getUsername() + "!");
        model.addAttribute("username", utente.getUsername());
        model.addAttribute("email", utente.getEmail());
        model.addAttribute("nome", utente.getNome());
        model.addAttribute("cognome", utente.getCognome());
        model.addAttribute("dataNascita", utente.getDataNascita());
        model.addAttribute("citta", utente.getCitta());

        //verifico i giochi pubblicati dall'utente
        GiocoDaoPostgre giocoConcrete1 = new GiocoDaoPostgre(DBManager.getInstance().getConnection());
        model.addAttribute("games", giocoConcrete1.findNumber(UserData.getInstance().getUsername()));

        //verifico ora le statistiche
        StatisticheDaoPostgre statisticheConcrete = new StatisticheDaoPostgre(DBManager.getInstance().getConnection());
        int gamesPlayed = statisticheConcrete.ottieniStatistiche(UserData.getInstance().getUsername());
        model.addAttribute("gamesPlayed", gamesPlayed);

        return "homePage";
    }
}
