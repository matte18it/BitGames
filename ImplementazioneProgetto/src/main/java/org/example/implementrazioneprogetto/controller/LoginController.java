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
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/checkUser") @ResponseBody
    public String checkUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        password = crittografia(password);

        //ora controllo se l'utente esiste nel db
        UtenteDaoPostgre utenteDaoPostgre = new UtenteDaoPostgre(DBManager.getInstance().getConnection());
        if (utenteDaoPostgre.findByPrimaryKey(username, password) != null) {
            UserData.getInstance().setUsername(username);
            return "/homePage";
        } else {
            //se non esiste lo rimando alla pagina di login
            model.addAttribute("error", "Username o password errati");
            return "/login";
        }
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
