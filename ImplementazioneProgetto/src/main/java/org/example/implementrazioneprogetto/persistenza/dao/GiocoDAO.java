package org.example.implementrazioneprogetto.persistenza.dao;

import org.example.implementrazioneprogetto.Pattern.Iterator.Aggregate.MyAggregate;
import org.example.implementrazioneprogetto.model.Gioco;

import java.util.List;

public interface GiocoDAO {
    public MyAggregate<Gioco> findAll(String username);
    public int findNumber(String username);
    public void pubblicaGioco(String username, String nome, String descrizione, String genere, String lingua, String icona, String versione, List<String> immagine, String file, boolean mobile);
}
