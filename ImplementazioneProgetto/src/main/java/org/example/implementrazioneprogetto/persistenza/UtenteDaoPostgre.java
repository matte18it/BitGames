package org.example.implementrazioneprogetto.persistenza;

import org.example.implementrazioneprogetto.model.Utente;
import org.example.implementrazioneprogetto.persistenza.dao.UtenteProgrammatoreDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDaoPostgre implements UtenteProgrammatoreDAO {
    //Attributi
    Connection conn;

    //Costruttore
    public UtenteDaoPostgre(Connection conn) {
        this.conn = conn;
    }

    //Metodi
    //con questa cerco nel database per username e password
    @Override
    public Utente findByPrimaryKey(String username, String password) {
        Utente user = null;
        String query = "select * from utenteprogrammatore where username = ? and password = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                user = new Utente();
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setNome(rs.getString("nome"));
                user.setCognome(rs.getString("cognome"));
                user.setDataNascita(rs.getString("datanascita"));
                user.setCitta(rs.getString("citta"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    //con questa cerco nel database per username
    @Override
    public Utente findByUsername(String username) {
        Utente user = null;
        String query = "select * from utenteprogrammatore where username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                user = new Utente();
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setNome(rs.getString("nome"));
                user.setCognome(rs.getString("cognome"));
                user.setDataNascita(rs.getString("datanascita"));
                user.setCitta(rs.getString("citta"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    //con questa verifico se esiste già un utente con lo stesso username
    @Override
    public boolean checkUsername(String username) {
        boolean check = true;
        String query = "select * from utenteprogrammatore where username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            if (rs.next())
                check = false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    //con questa salvo nel database
    @Override
    public void registrazione(String username, String password, String email, String nome, String cognome, String dataNascita, String citta) {
        String query = "insert into utenteprogrammatore values (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, email);
            st.setString(4, nome);
            st.setString(5, cognome);
            st.setString(6, dataNascita);
            st.setString(7, citta);

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
