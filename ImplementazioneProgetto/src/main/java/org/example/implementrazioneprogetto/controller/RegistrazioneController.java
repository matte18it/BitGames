package org.example.implementrazioneprogetto.controller;

import org.example.implementrazioneprogetto.Pattern.Singleton.DBManager;
import org.example.implementrazioneprogetto.Pattern.Singleton.UserData;
import org.example.implementrazioneprogetto.persistenza.UtenteDaoPostgre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class RegistrazioneController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/checkUsername") @ResponseBody
    public String checkUsername(@RequestParam(name = "username") String username, Model model) {
        UtenteDaoPostgre utenteProgrammatore = new UtenteDaoPostgre(DBManager.getInstance().getConnection());
        if(utenteProgrammatore.checkUsername(username))
            return "true";
        else
            return "false";
    }

    @GetMapping("/saveUser") @ResponseBody
    public String saveUser(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, @RequestParam(name = "email") String email, @RequestParam(name = "nome") String nome, @RequestParam(name = "cognome") String cognome, @RequestParam(name = "dataNascita") String dataNascita, @RequestParam(name = "citta") String citta, Model model) {
        password = crittografia(password);

        //qua vado a salvare l'utente nel database
        UtenteDaoPostgre utenteProgrammatore = new UtenteDaoPostgre(DBManager.getInstance().getConnection());
        utenteProgrammatore.registrazione(username, password, email, nome, cognome, dataNascita, citta);
        UserData.getInstance().setUsername(username);

        return "/homePage";
    }

    //la uso per criptare in md5 la password
    public String crittografia(String password) {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        m.update(password.getBytes());
        byte[] bytes = m.digest();
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < bytes.length; i++)
            s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));

        return s.toString();
    }
}
