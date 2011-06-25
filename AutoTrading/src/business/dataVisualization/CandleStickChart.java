/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.dataVisualization;

import dataAccess.databaseManagement.entity.OrderEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.TreeMap;
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

    private TreeMap<Object, TimeSeries> mappingOrderSeries = new TreeMap<Object, TimeSeries>();
    private TreeMap<Object, YIntervalSeries> mappingPredictionPriceSeries = new TreeMap<Object, YIntervalSeries>();

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
            double high = prices.get(i).getHigh();
            double low = prices.get(i).getLow();
            if (open == 0) {
                open = prices.get(i).getClose();
            }
            if (high == 0) {
                high = open;
            }
            if (low == 0) {
                low = open;
            }
            oHLCDataItems[i] = new OHLCDataItem(prices.get(i).getDate(), open, high, low, prices.get(i).getClose(), prices.get(i).getVolume());
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
//        deviationRenderer.setSeriesStroke(0, new BasicStroke(3F, 1, 1));
//        deviationRenderer.setSeriesPaint(0, Color.BLUE);
//        deviationRenderer.setSeriesFillPaint(0, new Color(255, 200, 200));
        plot.setRenderer(2, deviationRenderer);

        //add mark points
        plot.mapDatasetToRangeAxis(3, 0);
        XYLineAndShapeRenderer xyLineAndShapeRenderer = new XYLineAndShapeRenderer();
        plot.setRenderer(3, xyLineAndShapeRenderer);
    }

    @Override
    public void addOrders(Object object, ArrayList<OrderEntity> orders) {
        TimeSeries buySeries = new TimeSeries("Buy Signals - " + object.toString());
        TimeSeries sellSeries = new TimeSeries("Sell Signals - " + object.toString());

        for (OrderEntity order : orders) {
            if (order.getOrderType())
                buySeries.add(new Day(order.getDate()), order.getPrice());
            else
                sellSeries.add(new Day(order.getDate()), order.getPrice());
        }

        if (markPointsDataset == null) {
            markPointsDataset = new TimeSeriesCollection();
        }

        ((TimeSeriesCollection)markPointsDataset).addSeries(buySeries);
        ((TimeSeriesCollection)markPointsDataset).addSeries(sellSeries);

        mappingOrderSeries.put(object, buySeries);

        XYPlot plot = candleStickChart.getXYPlot();
        XYLineAndShapeRenderer xYLineAndShapeRenderer = (XYLineAndShapeRenderer) plot.getRenderer(3);
        xYLineAndShapeRenderer.setSeriesLinesVisible(markPointsDataset.getSeriesCount() - 1, false);
        xYLineAndShapeRenderer.setSeriesLinesVisible(markPointsDataset.getSeriesCount() - 2, false);
    }

    @Override
    public void addPredictionPrices(Object object, ArrayList<PriceEntity> prices) {
        if (predictionDataset == null) {
            predictionDataset = new YIntervalSeriesCollection();
        }

        YIntervalSeries yintervalseries = new YIntervalSeries("Predict - " + object.toString());
        for (PriceEntity price : prices) {
            yintervalseries.add(price.getDate().getTime(), price.getClose()+1, price.getClose() + 0.5, price.getClose() + 1.5);
        }

        mappingPredictionPriceSeries.put(object, yintervalseries);
        ((YIntervalSeriesCollection) predictionDataset).addSeries(yintervalseries);

        Random random = new Random(Calendar.getInstance().getTimeInMillis());
        XYPlot plot = candleStickChart.getXYPlot();
        DeviationRenderer deviationRenderer = (DeviationRenderer) plot.getRenderer(2);
        deviationRenderer.setSeriesStroke(predictionDataset.getSeriesCount() - 1, new BasicStroke(3F, 1, 1));
        deviationRenderer.setSeriesPaint(predictionDataset.getSeriesCount() - 1, new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
        deviationRenderer.setSeriesFillPaint(predictionDataset.getSeriesCount() - 1, new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
    }

    @Override
    public void removeOrder(Object object) {
        ((TimeSeriesCollection)markPointsDataset).removeSeries(mappingOrderSeries.get(object));
    }

    @Override
    public void removeAllOrders() {
        ((TimeSeriesCollection)markPointsDataset).removeAllSeries();
    }

    @Override
    public void removePredictionPrice(Object object) {
        ((YIntervalSeriesCollection) predictionDataset).removeSeries(mappingPredictionPriceSeries.get(object));
    }

    @Override
    public void removeAllPredictionPrice() {
        ((YIntervalSeriesCollection) predictionDataset).removeAllSeries();
    }
}
