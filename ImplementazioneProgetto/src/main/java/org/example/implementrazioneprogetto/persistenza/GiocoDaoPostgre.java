package org.example.implementrazioneprogetto.persistenza;

import org.example.implementrazioneprogetto.Pattern.Iterator.Aggregate.MyAggregate;
import org.example.implementrazioneprogetto.Pattern.Iterator.ConcreteAggregate.ConcreteAggregate;
import org.example.implementrazioneprogetto.model.Gioco;
import org.example.implementrazioneprogetto.persistenza.dao.GiocoDAO;

import java.nio.file.Path;
import java.sql.*;
import java.util.List;
import java.util.UUID;

public class GiocoDaoPostgre implements GiocoDAO {
    //Attributi
    Connection conn;

    //Costruttore
    public GiocoDaoPostgre(Connection conn) {
        this.conn = conn;
    }

    //Metodi
    @Override
    public MyAggregate<Gioco> findAll(String username) {
        MyAggregate<Gioco> games = new ConcreteAggregate<>();
        String query = "select * from gioco where username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Gioco game = new Gioco();
                game.setNome(rs.getString("nome"));
                game.setDescrizione(rs.getString("descrizione"));
                game.setGenere(rs.getString("genere"));
                game.setImmagine(rs.getString("immagini"));
                game.setIcona(rs.getString("icona"));
                game.setVersione(rs.getString("versione"));
                game.setFile(rs.getString("file"));
                game.setLingua(rs.getString("lingua"));
                game.setMobile(rs.getBoolean("mobile"));
                games.addItem(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    @Override
    public int findNumber(String username) {
        int tot = 0;
        String query = "select count(*) as totale from gioco where username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();
            rs.next();
            tot = rs.getInt("totale");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tot;
    }

    @Override
    public void pubblicaGioco(String username, String nome, String descrizione, String genere, String lingua, String icona, String versione, List<String> immagine, String file, boolean mobile) {
        // Unisco i percorsi relativi delle immagini
        StringBuilder immagini = new StringBuilder();
        int lastIndex = immagine.size() - 1;
        for (int i = 0; i < immagine.size(); i++) {
            Path imagePath = Path.of(immagine.get(i));
            String relativePath = imagePath.subpath(imagePath.getNameCount() - 2, imagePath.getNameCount()).toString();
            immagini.append(relativePath);
            if (i < lastIndex)
                immagini.append("*");
        }

        // Per l'icona del gioco
        String relativeIconPath = Path.of(icona).subpath(Path.of(icona).getNameCount() - 2, Path.of(icona).getNameCount()).toString();
        // Per i file
        String relativeIndexPath = Path.of(file).subpath(Path.of(file).getNameCount() - 2, Path.of(file).getNameCount()).toString();

        //Effettuo la query
        String query = "insert into gioco(id, username, nome, genere, lingua, descrizione, icona, versione, immagini, file, mobile) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, UUID.randomUUID().toString());
            st.setString(2, username);
            st.setString(3, nome);
            st.setString(4, genere);
            st.setString(5, lingua);
            st.setString(6, descrizione);
            st.setString(7, relativeIconPath);
            st.setString(8, versione);
            st.setString(9, immagini.toString());
            st.setString(10, relativeIndexPath);
            st.setBoolean(11, mobile);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
