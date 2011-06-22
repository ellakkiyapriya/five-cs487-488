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
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author Dinh
 */
public class CandleStickChart implements VisulizationChart {

    private JFreeChart candleStickChart;
    private OHLCDataset candleDataset;
    private XYDataset volumeDataset;

    @Override
    public JFreeChart getChart() {
        return candleStickChart;
    }

    @Override
    public void setPrices(ArrayList<PriceEntity> prices) {
        OHLCDataItem[] oHLCDataItems = new OHLCDataItem[prices.size()];
        TimeSeries volumeSeries = new TimeSeries("Volume Series");

        for (int i = 0; i < prices.size(); ++i) {
            oHLCDataItems[i] = new OHLCDataItem(prices.get(i).getDate(), prices.get(i).getOpen(), prices.get(i).getHigh(), prices.get(i).getLow(), prices.get(i).getClose(), prices.get(i).getVolume());
            volumeSeries.add(new Day(prices.get(i).getDate()), prices.get(i).getVolume());
        }

        candleDataset = new DefaultOHLCDataset("Price Series", oHLCDataItems);

        TimeSeriesCollection dataset = new TimeSeriesCollection(volumeSeries);
        volumeDataset = new XYBarDataset(dataset, 100000000);

    }

    @Override
    public void updateChart() {
        XYPlot plot = candleStickChart.getXYPlot();
        plot.setDataset(0,candleDataset);
        plot.setDataset(1, volumeDataset);
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
        candlestickRenderer.setAutoWidthMethod(CandlestickRenderer.WIDTHMETHOD_AVERAGE);

        XYPlot plot = candleStickChart.getXYPlot();

        DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
        dateAxis.setPositiveArrowVisible(true);

        NumberAxis priceRangeAxis = (NumberAxis) plot.getRangeAxis();
        priceRangeAxis.setLowerMargin(1.00); // to leave room for volume bars
        priceRangeAxis.setAutoRangeIncludesZero(false);

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
