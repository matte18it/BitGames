package org.example.implementrazioneprogetto.persistenza.dao;

import java.util.List;

public interface StatisticheDAO {
    public int ottieniStatistiche(String username);
    public List<Integer> getStatistiche(String username);
}
