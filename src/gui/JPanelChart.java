/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
n    */
package gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author -
 */
public class JPanelChart extends JPanel {

     JFreeChart chart;
    private XYSeries average;
    private XYSeries maximum;
    private XYSeries minimum;
    private String title;
    private String xTitle;
    private String yTitle;
    ChartPanel chartPanel;

    public JPanelChart(String title,String yTitle , String xTitle) {

        super(new BorderLayout());

        average = new XYSeries("average");
        maximum = new XYSeries("maximum");
        minimum = new XYSeries("minimum");
        XYSeriesCollection dataset = new XYSeriesCollection();
        average.add(0, 0);
        minimum.add(0, 0);
        maximum.add(0, 0);
        dataset.addSeries(minimum);
        dataset.addSeries(average);
        dataset.addSeries(maximum);
        chart = ChartFactory.createXYLineChart(title, yTitle, xTitle, dataset, PlotOrientation.VERTICAL, true, true, true);
        chartPanel = new ChartPanel(chart);
        add(chartPanel);

    }
//Nowa seria danych
    public void setNewSeries() {
        average.clear();
        minimum.clear();
        maximum.clear();

        average.add(0, 0);
        minimum.add(0, 0);
        maximum.add(0, 0);


    }
    public void setMin(double min, int population) {
        minimum.add(population, min);
    }

    public void setMax(double max, int population) {
        maximum.add(population, max);
    }

    public void setAv(double av, int population) {
        average.add(population, av);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getxTitle() {
        return xTitle;
    }

    public void setxTitle(String xTitle) {
        this.xTitle = xTitle;
    }

    public String getyTitle() {
        return yTitle;
    }

    public void setyTitle(String yTitle) {
        this.yTitle = yTitle;
    }

    public JFreeChart getChart() {
        return chart;
    }
}
