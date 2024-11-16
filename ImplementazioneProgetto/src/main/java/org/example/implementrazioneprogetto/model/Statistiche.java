package org.example.implementrazioneprogetto.model;

public class Statistiche {
    //Attributi
    private String nPartite;

    //Costruttore
    public Statistiche(String nPartite) {
        this.nPartite = nPartite;
    }
    public Statistiche(){}

    //Metodi
    public String getNPartite() {
        return nPartite;
    }
    public void setNPartite(String nPartite) {
        this.nPartite = nPartite;
    }
}
