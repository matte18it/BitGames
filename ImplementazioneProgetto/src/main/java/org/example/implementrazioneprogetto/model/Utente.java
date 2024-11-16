package org.example.implementrazioneprogetto.model;

public class Utente {
    //Attributi
    private String username;
    private String email;
    private String nome;
    private String cognome;
    private String dataNascita;
    private String citta;

    //Costruttore
    public Utente(String username, String email, String nome, String cognome, String dataNascita, String citta) {
        this.username = username;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.citta = citta;
    }
    public Utente(){}


    //Metodi
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getDataNascita() {
        return dataNascita;
    }
    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }
    public String getCitta() {
        return citta;
    }
    public void setCitta(String citta) {
        this.citta = citta;
    }
}
