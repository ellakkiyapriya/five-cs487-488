/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.dataVisualization;

import Utility.Utility;
import business.algorithm.decisionAlgorithm.AbstractDecisionAlgorithm;
import business.algorithm.decisionAlgorithm.Order;
import dataAccess.databaseManagement.entity.PriceEntity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.HashMap;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;

import business.algorithm.predictAlgorithm.AbstractPredictAlgorithm;
import business.algorithm.predictAlgorithm.PredictionAlgorithmEntity;

/**
 * 
 * @author Dinh
 */
public class LineChart implements VisulizationChart {

    private JFreeChart lineChart;
    private XYDataset priceDataset,
            volumeDataset,
            predictionDataset = new YIntervalSeriesCollection(),
            markPointsDataset = new TimeSeriesCollection();
    private HashMap<Object, TimeSeries> mappingOrderSeries = new HashMap<Object, TimeSeries>();
    private HashMap<Object, YIntervalSeries> mappingPredictionPriceSeries = new HashMap<Object, YIntervalSeries>();
    private ArrayList<Color> preLineColors = new ArrayList<Color>();

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
        volumeDataset = new XYBarDataset(dataset, 100);

    }

    @Override
    public void updateChart() {
        XYPlot plot = lineChart.getXYPlot();
        plot.setDataset(0, priceDataset);
        plot.setDataset(1, volumeDataset);
        plot.setDataset(2, predictionDataset);
        plot.setDataset(3, markPointsDataset);
    }

    @Override
    public void initalChart() {
        lineChart = ChartFactory.createTimeSeriesChart("", "Date", "Price",
                null, true, true, false);
        // chart.getXYPlot().getRangeAxis().setLowerBound(0);
        lineChart.setBackgroundPaint(Color.white);
        XYPlot plot = lineChart.getXYPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinePaint(Color.blue);
        plot.setDomainGridlinePaint(Color.blue);

        DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
        dateAxis.setPositiveArrowVisible(true);

        NumberAxis priceRangeAxis = (NumberAxis) plot.getRangeAxis();
        priceRangeAxis.setLowerMargin(1.00); // to leave room for volume bars
        DecimalFormat format = new DecimalFormat("00.00");
        priceRangeAxis.setNumberFormatOverride(format);

        XYItemRenderer priceRenderer = plot.getRenderer();

        priceRenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));

        // add volume series
        NumberAxis volumeRangeAxis = new NumberAxis("Volume");
        volumeRangeAxis.setUpperMargin(1.00); // to leave room for price line
        plot.setRangeAxis(1, volumeRangeAxis);
        plot.mapDatasetToRangeAxis(1, 1);
        XYBarRenderer volumeRenderer = new XYBarRenderer(0.20);
        volumeRenderer.setBarPainter(new StandardXYBarPainter());
        volumeRenderer.setShadowVisible(false);
        volumeRenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat(
                "0,000.00")));
        volumeRenderer.setSeriesPaint(0, Color.GRAY);
        plot.setRenderer(1, volumeRenderer);

        // add prediction price series
        plot.mapDatasetToRangeAxis(2, 0);
        DeviationRenderer deviationRenderer = new DeviationRenderer(true, false);
        // deviationRenderer.setSeriesStroke(0, new BasicStroke(3F, 1, 1));
        // deviationRenderer.setSeriesPaint(0, Color.BLUE);
        // deviationRenderer.setSeriesFillPaint(0, new Color(255, 200, 200));
        plot.setRenderer(2, deviationRenderer);

        // add mark points
        plot.mapDatasetToRangeAxis(3, 0);
        XYLineAndShapeRenderer xyLineAndShapeRenderer = new XYLineAndShapeRenderer();
        plot.setRenderer(3, xyLineAndShapeRenderer);

    }

    @Override
    public void addOrders(AbstractDecisionAlgorithm decAlgo, ArrayList<Order> orders) {

        //Utility.debug(orders.size());

        TimeSeries buySeries = new TimeSeries("Buy Signals - "
                + decAlgo.toString());
        TimeSeries sellSeries = new TimeSeries("Sell Signals - "
                + decAlgo.toString());

        for (Order order : orders) {
            if (order.isOrderType() == Order.ORDER_BUY) {
                buySeries.add(new Day(order.getDate()), order.getPrice());
            } else {
                sellSeries.add(new Day(order.getDate()), order.getPrice());
            }
        }

        ((TimeSeriesCollection) markPointsDataset).addSeries(buySeries);
        ((TimeSeriesCollection) markPointsDataset).addSeries(sellSeries);

        mappingOrderSeries.put(decAlgo, buySeries);

        XYPlot plot = lineChart.getXYPlot();
        XYLineAndShapeRenderer xYLineAndShapeRenderer = (XYLineAndShapeRenderer) plot.getRenderer(3);
        xYLineAndShapeRenderer.setSeriesLinesVisible(
                markPointsDataset.getSeriesCount() - 1, false);
        xYLineAndShapeRenderer.setSeriesLinesVisible(
                markPointsDataset.getSeriesCount() - 2, false);
    }

    @Override
    public void addPredictionPrices(AbstractPredictAlgorithm algo,
            PredictionAlgorithmEntity entity) {

        YIntervalSeries yintervalseries = new YIntervalSeries("Predict - " + algo.toString());

        for (PredictionAlgorithmEntity.Entry entry : entity.list) {
            yintervalseries.add(entry.date.getTime(), entry.midValue,
                    entry.lowValue, entry.highValue);
        }

        mappingPredictionPriceSeries.put(algo, yintervalseries);
        ((YIntervalSeriesCollection) predictionDataset).addSeries(yintervalseries);

        XYPlot plot = lineChart.getXYPlot();
        DeviationRenderer deviationRenderer = (DeviationRenderer) plot.getRenderer(2);
        deviationRenderer.setSeriesStroke(predictionDataset.getSeriesCount() - 1, new BasicStroke(3F, 1, 1));

        Random random = new Random(Calendar.getInstance().getTimeInMillis());
        while (preLineColors.size() < predictionDataset.getSeriesCount()) {
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            preLineColors.add(new Color(r, g, b));
        }

        deviationRenderer.setSeriesFillPaint(predictionDataset.getSeriesCount() - 1, preLineColors.get(predictionDataset.getSeriesCount() - 1));
        deviationRenderer.setSeriesPaint(predictionDataset.getSeriesCount() - 1, preLineColors.get(predictionDataset.getSeriesCount() - 1));
    }

    @Override
    public void removeOrder(Object object) {
        if (mappingOrderSeries.get(object) == null) {
            return;
        }

        ((TimeSeriesCollection) markPointsDataset).removeSeries(mappingOrderSeries.get(object));
    }

    @Override
    public void removeAllOrders() {
        ((TimeSeriesCollection) markPointsDataset).removeAllSeries();
    }

    @Override
    public void removePredictionPrice(AbstractPredictAlgorithm abstractPredictAlgorithm) {
        if (mappingPredictionPriceSeries.get(abstractPredictAlgorithm) == null) {
            return;
        }

        ((YIntervalSeriesCollection) predictionDataset).removeSeries(mappingPredictionPriceSeries.get(abstractPredictAlgorithm));
    }

    @Override
    public void removeAllPredictionPrice() {
        ((YIntervalSeriesCollection) predictionDataset).removeAllSeries();
    }
}
