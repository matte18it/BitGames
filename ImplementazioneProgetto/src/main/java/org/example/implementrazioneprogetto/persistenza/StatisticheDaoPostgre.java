package org.example.implementrazioneprogetto.persistenza;

import org.example.implementrazioneprogetto.persistenza.dao.StatisticheDAO;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StatisticheDaoPostgre implements StatisticheDAO {
    //Attributi
    Connection conn;

    //Costruttori
    public StatisticheDaoPostgre(Connection conn) {
        this.conn = conn;
    }

    //Metodi
    @Override
    public int ottieniStatistiche(String username) {
        int nPartite = 0;
        String query = "SELECT count(*) as totale FROM partita AS X, gioco AS Y WHERE X.dataora >= now() - interval '30 days' AND X.dataora <= now() AND X.id = Y.id AND Y.username = ?";

        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            while(rs.next()){
                nPartite = Integer.parseInt(rs.getString("totale"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nPartite;
    }

    //questa funzione serve per ottenere il conteggio delle statistiche giornaliere utili per il grafico
    @Override
    public List<Integer> getStatistiche(String username) {
        String query = "select DATE(X.dataora) AS giorno, COUNT(*) AS tuple from partita AS X join gioco AS Y ON X.id = Y.id where X.dataora >= NOW() - INTERVAL '30 days' AND X.dataora <= NOW() AND Y.username = ? GROUP by DATE(X.dataora)";

        List<Integer> statisticheList = new ArrayList<>(Collections.nCopies(31, 0));

        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                LocalDate data = rs.getDate("giorno").toLocalDate();
                int numeroTuple = rs.getInt("tuple");

                // Calcola l'indice della lista basato sulla differenza di giorni
                int index = (int) ChronoUnit.DAYS.between(data, LocalDate.now());

                // Aggiorna il valore nella lista
                statisticheList.set(index, numeroTuple);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Collections.reverse(statisticheList);
        return statisticheList;
    }
}
