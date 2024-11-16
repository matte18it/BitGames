package org.example.implementrazioneprogetto.Pattern.Observer.ConcreteObserver;

import org.example.implementrazioneprogetto.Pattern.Observer.ConcreteSubject.ConcreteSubject;
import org.example.implementrazioneprogetto.Pattern.Observer.Observer.ChartObserver;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ImageChartObserver implements ChartObserver {
    //Attributi
    private ConcreteSubject concreteSubject;

    //Costruttore
    public ImageChartObserver(ConcreteSubject concreteSubject) {
        this.concreteSubject = concreteSubject;
    }

    //Metodi
    @Override
    public void update() {
        //ongi volta che il subject cambia, viene chiamato questo metodo che crea la nuova immagine con i nuovi dati

        // Ottieni la data attuale
        LocalDate dataAttuale = LocalDate.now();

        // Creare il dataset con i dati degli ultimi 30 giorni
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = Math.min(30, concreteSubject.getData().size()) - 1; i >= 0; i--) {
            // Sottrai i giorni dalla data attuale in ordine crescente
            LocalDate dataGiorno = dataAttuale.minusDays(Math.min(i, concreteSubject.getData().size() - 1));

            // Aggiungi il valore al dataset con la data formattata
            dataset.addValue(concreteSubject.getData().get(i), "Dati del grafico", dataGiorno.format(DateTimeFormatter.ofPattern("dd/MM")));
        }

        // Crea il grafico a linea
        JFreeChart chart = ChartFactory.createLineChart(
                "",
                "Giorni",
                "Partite Giocate",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        // Personalizza lo sfondo del grafico
        chart.setBackgroundPaint(Color.decode("#333333"));

        // Ottieni il riferimento alla trama del grafico per ulteriori personalizzazioni
        CategoryPlot plot = chart.getCategoryPlot();

        // Personalizza lo sfondo della trama del grafico
        plot.setBackgroundPaint(Color.decode("#333333"));

        // Personalizza il colore della linea
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.WHITE);

        // Personalizza il colore delle etichette asse x
        plot.getDomainAxis().setLabelPaint(Color.WHITE);
        plot.getDomainAxis().setTickLabelPaint(Color.WHITE);
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        // Personalizza il colore delle etichette asse y
        plot.getRangeAxis().setLabelPaint(Color.WHITE);

        // Personalizza il colore dei valori sull'asse y (i tick)
        plot.getRangeAxis().setTickLabelPaint(Color.WHITE);

        // Salva il grafico come immagine
        try {
            ChartUtils.saveChartAsPNG(new File("src/main/resources/static/img/lineChart.png"), chart, 1200, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
