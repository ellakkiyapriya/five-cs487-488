/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package business.dataVisualization;

import dataAccess.databaseManagement.entity.PriceEntity;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author Dinh
 */
public class LineChart implements VisulizationChart{

    private JFreeChart lineChart;
    private XYDataset priceDataset, volumeDataset;

    @Override
    public JFreeChart getChart() {
        return lineChart;
    }

    @Override
    public void setPrices(ArrayList<PriceEntity> prices) {
        TimeSeries priceSeries = new TimeSeries("Price Series");
        TimeSeries volumeSeries = new TimeSeries("Volume Series");

        for (PriceEntity price : prices) {
            priceSeries.add(new Day(price.getDate()), price.getClose());
            volumeSeries.add(new Day(price.getDate()), price.getVolume());
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(priceSeries);
        priceDataset = dataset;

        dataset = new TimeSeriesCollection(volumeSeries);
        volumeDataset = new XYBarDataset(dataset, 100000000);

    }

    @Override
    public void updateChart() {
        XYPlot plot = lineChart.getXYPlot();
        plot.setDataset(0, priceDataset);
        plot.setDataset(1, volumeDataset);
    }

    @Override
    public void initalChart() {
        lineChart = ChartFactory.createTimeSeriesChart(
                "",
                "Date",
                "Price",
                null,
                true,
                true,
                false);
        //chart.getXYPlot().getRangeAxis().setLowerBound(0);
        lineChart.setBackgroundPaint(Color.white);
        XYPlot plot = lineChart.getXYPlot();

        DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
        dateAxis.setPositiveArrowVisible(true);

        NumberAxis priceRangeAxis = (NumberAxis) plot.getRangeAxis();
        priceRangeAxis.setLowerMargin(1.00); // to leave room for volume bars
        DecimalFormat format = new DecimalFormat("00.00");
        priceRangeAxis.setNumberFormatOverride(format);

        XYItemRenderer priceRenderer = plot.getRenderer();

        priceRenderer.setBaseToolTipGenerator(
                new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));

        NumberAxis volumeRangeAxis = new NumberAxis("Volume");
        volumeRangeAxis.setUpperMargin(1.00); // to leave room for price line
        plot.setRangeAxis(1, volumeRangeAxis);
        plot.mapDatasetToRangeAxis(1, 1);
        XYBarRenderer volumeRenderer = new XYBarRenderer(0.20);
        volumeRenderer.setBarPainter(new StandardXYBarPainter());
        volumeRenderer.setShadowVisible(false);
        volumeRenderer.setBaseToolTipGenerator(
                new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0,000.00")));
        volumeRenderer.setSeriesPaint(0, Color.GRAY);
        plot.setRenderer(1, volumeRenderer);
    }

    
}
