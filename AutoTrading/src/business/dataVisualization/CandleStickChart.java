/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.dataVisualization;

import dataAccess.databaseManagement.entity.OrderEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;

/**
 *
 * @author Dinh
 */
public class CandleStickChart implements VisulizationChart {

    private JFreeChart candleStickChart;
    private OHLCDataset candleDataset;
    private XYDataset volumeDataset,
            predictionDataset,
            markPointsDataset;

    @Override
    public JFreeChart getChart() {
        return candleStickChart;
    }

    @Override
    public void setPrices(ArrayList<PriceEntity> prices) {
        OHLCDataItem[] oHLCDataItems = new OHLCDataItem[prices.size()];
        TimeSeries volumeSeries = new TimeSeries("Volume Series");

        for (int i = 0; i < prices.size(); ++i) {
            double open = prices.get(i).getOpen();
            if (open == 0) {
                open = prices.get(i).getClose();
            }
            oHLCDataItems[i] = new OHLCDataItem(prices.get(i).getDate(), open, prices.get(i).getHigh(), prices.get(i).getLow(), prices.get(i).getClose(), prices.get(i).getVolume());
            volumeSeries.add(new Day(prices.get(i).getDate()), prices.get(i).getVolume());
        }

        candleDataset = new DefaultOHLCDataset("Price Series", oHLCDataItems);

        TimeSeriesCollection dataset = new TimeSeriesCollection(volumeSeries);
        volumeDataset = new XYBarDataset(dataset, 100);

    }

    @Override
    public void updateChart() {
        XYPlot plot = candleStickChart.getXYPlot();
        plot.setDataset(0,candleDataset);
        plot.setDataset(1, volumeDataset);
        plot.setDataset(2,predictionDataset);
        plot.setDataset(3, markPointsDataset);
    }

    @Override
    public void initalChart() {
        candleStickChart = ChartFactory.createCandlestickChart(
                "",
                "Date",
                "Price",
                null,
                true);
        candleStickChart.setBackgroundPaint(Color.white);
        CandlestickRenderer candlestickRenderer = (CandlestickRenderer) candleStickChart.getXYPlot().getRenderer();
        candlestickRenderer.setDrawVolume(false);
        candlestickRenderer.setAutoWidthMethod(CandlestickRenderer.WIDTHMETHOD_SMALLEST);
        

        XYPlot plot = candleStickChart.getXYPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinePaint(Color.blue);
        plot.setDomainGridlinePaint(Color.blue);

        DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
        dateAxis.setPositiveArrowVisible(true);

        NumberAxis priceRangeAxis = (NumberAxis) plot.getRangeAxis();
        priceRangeAxis.setLowerMargin(0.5); // to leave room for volume bars
        priceRangeAxis.setAutoRangeIncludesZero(false);

        NumberAxis volumeRangeAxis = new NumberAxis("Volume");
        volumeRangeAxis.setUpperMargin(0.5); // to leave room for price line
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

        //add prediction price series
        plot.mapDatasetToRangeAxis(2, 0);
        DeviationRenderer deviationRenderer = new DeviationRenderer(true, false);
        deviationRenderer.setSeriesStroke(0, new BasicStroke(3F, 1, 1));
        deviationRenderer.setSeriesPaint(0, Color.BLUE);
        deviationRenderer.setSeriesFillPaint(0, new Color(255, 200, 200));
        plot.setRenderer(2, deviationRenderer);

        //add mark points
        plot.mapDatasetToRangeAxis(3, 0);
        XYLineAndShapeRenderer xyLineAndShapeRenderer = new XYLineAndShapeRenderer();
        xyLineAndShapeRenderer.setSeriesLinesVisible(0, false);
        xyLineAndShapeRenderer.setSeriesLinesVisible(1, false);
        plot.setRenderer(3, xyLineAndShapeRenderer);
    }

    @Override
    public void setOrders(Object object, ArrayList<OrderEntity> orders) {
        TimeSeries buySeries = new TimeSeries("Buy Signals");
        TimeSeries sellSeries = new TimeSeries("Sell Signals");

        for (OrderEntity order : orders) {
            if (order.getOrderType())
                buySeries.add(new Day(order.getDate()), order.getPrice());
            else
                sellSeries.add(new Day(order.getDate()), order.getPrice());
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(buySeries);
        dataset.addSeries(sellSeries);
        markPointsDataset = dataset;
    }

    @Override
    public void setPredictionPrices(Object object, ArrayList<PriceEntity> prices) {
        YIntervalSeries yintervalseries = new YIntervalSeries("Predict");
        for (PriceEntity price : prices) {
            yintervalseries.add(price.getDate().getTime(), price.getClose()+1, price.getClose() + 0.5, price.getClose() + 1.5);
        }

        YIntervalSeriesCollection yintervalseriescollection = new YIntervalSeriesCollection();
        yintervalseriescollection.addSeries(yintervalseries);
        predictionDataset = yintervalseriescollection;
    }

    @Override
    public void addPredictionPrices(Object object, ArrayList<PriceEntity> prices) {
        if (predictionDataset == null) {
            predictionDataset = new YIntervalSeriesCollection();
        }

        YIntervalSeries yintervalseries = new YIntervalSeries("Predict - " + object.toString());
        for (PriceEntity price : prices) {
            yintervalseries.add(price.getDate().getTime(), price.getOpen()+1, price.getOpen() + 0.5, price.getOpen() + 1.5);
        }

        ((YIntervalSeriesCollection) predictionDataset).addSeries(yintervalseries);
    }
}
