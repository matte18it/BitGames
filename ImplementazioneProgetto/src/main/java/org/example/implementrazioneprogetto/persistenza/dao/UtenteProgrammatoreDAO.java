package org.example.implementrazioneprogetto.persistenza.dao;

import org.example.implementrazioneprogetto.model.Utente;

public interface UtenteProgrammatoreDAO {
    public Utente findByPrimaryKey(String username, String password);
    public Utente findByUsername(String username);
    public boolean checkUsername(String username);
    public void registrazione(String username, String password, String email, String nome, String cognome, String dataNascita, String citta);
}
