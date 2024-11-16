package org.example.implementrazioneprogetto.model;

public class Gioco {
    //Attributi
    private String nome;
    private String descrizione;
    private String genere;
    private String lingua;
    private String icona;
    private String versione;
    private String immagine;
    private String file;
    private boolean mobile;

    //Costruttori
    public Gioco(String nome, String descrizione, String genere, String lingua, String icona, String versione, String immagine, String file, boolean mobile) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.genere = genere;
        this.lingua = lingua;
        this.icona = icona;
        this.versione = versione;
        this.immagine = immagine;
        this.file = file;
        this.mobile = mobile;
    }
    public Gioco(){}

    //Metodi
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public String getGenere() {
        return genere;
    }
    public void setGenere(String genere) {
        this.genere = genere;
    }
    public String getLingua() {
        return lingua;
    }
    public void setLingua(String lingua) {
        this.lingua = lingua;
    }
    public String getIcona() {
        return icona;
    }
    public void setIcona(String icona) {
        this.icona = icona;
    }
    public String getVersione() {
        return versione;
    }
    public void setVersione(String versione) {
        this.versione = versione;
    }
    public String getImmagine() {
        return immagine;
    }
    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }
    public String getFile() {
        return file;
    }
    public void setFile(String file) {
        this.file = file;
    }
    public boolean getMobile() {
        return mobile;
    }
    public void setMobile(boolean mobile) {
        this.mobile = mobile;
    }
}
