/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package business.dataVisualization;

import business.algorithm.decisionAlgorithm.AbstractDecisionAlgorithm;
import business.algorithm.predictAlgorithm.AbstractPredictAlgorithm;
import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.PriceManager;
import java.sql.Date;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author Dinh
 */
public class DataVisualizationChart {
    private static PriceManager priceManager = new PriceManager();
    
    private JFreeChart chart;
    private AssetEntity asset;
    private Date fromDate;
    private Date toDate;
    private Date startPreDate;
    private AbstractPredictAlgorithm preAlg;
    private AbstractDecisionAlgorithm decAlg;
    private XYDataset priceDataset, volumeDataset;

    public DataVisualizationChart() {
//        JFreeChart chart = ChartFactory.createTimeSeriesChart(
//                "",
//                "Date",
//                "Price",
//                priceDataset,
//                true,
//                true,
//                false);
//        //chart.getXYPlot().getRangeAxis().setLowerBound(0);
//        chart.setBackgroundPaint(Color.white);
//        XYPlot plot = chart.getXYPlot();
//
//        DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
//        dateAxis.setPositiveArrowVisible(true);
//
//        NumberAxis priceRangeAxis = (NumberAxis) plot.getRangeAxis();
//        priceRangeAxis.setLowerMargin(0.40); // to leave room for volume bars
//        DecimalFormat format = new DecimalFormat("00.00");
//        priceRangeAxis.setNumberFormatOverride(format);
//
//        XYItemRenderer priceRenderer = plot.getRenderer();
//
//        priceRenderer.setBaseToolTipGenerator(
//                new StandardXYToolTipGenerator(
//                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
//                new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));
//
//
//
//        NumberAxis volumeRangeAxis = new NumberAxis("Volume");
//        volumeRangeAxis.setUpperMargin(1.00); // to leave room for price line
//        plot.setRangeAxis(1, volumeRangeAxis);
//        plot.setDataset(1, new XYBarDataset(volumeDataset, 100000000));
//        plot.mapDatasetToRangeAxis(1, 1);
//        XYBarRenderer volumeRenderer = new XYBarRenderer(0.20);
//        volumeRenderer.setBarPainter(new StandardXYBarPainter());
//        volumeRenderer.setShadowVisible(false);
//        volumeRenderer.setBaseToolTipGenerator(
//                new StandardXYToolTipGenerator(
//                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
//                new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0,000.00")));
//        volumeRenderer.setSeriesPaint(0, Color.GREEN);
//        plot.setRenderer(1, volumeRenderer);
    }

    private void createPriceAndVolumeDataset() {
        if (asset == null || fromDate == null || toDate == null) {
            priceDataset = null;
            volumeDataset = null;
            return;
        }

        TimeSeries priceSeries = new TimeSeries("Price Series");
        TimeSeries volumeSeries = new TimeSeries("Volume Series");
        
        for (PriceEntity price : priceManager.getPriceInInterval(asset.getAssetID(), fromDate, toDate)) {
            priceSeries.add(new Day(price.getDate()), price.getClose());
            volumeSeries.add(new Day(price.getDate()), price.getVolume());
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(priceSeries);
        priceDataset = dataset;

        dataset = new TimeSeriesCollection(volumeSeries);
        volumeDataset = dataset;
    }

    public AbstractDecisionAlgorithm getDecAlg() {
        return decAlg;
    }

    public void setDecAlg(AbstractDecisionAlgorithm decAlg) {
        this.decAlg = decAlg;
    }

    public AbstractPredictAlgorithm getPreAlg() {
        return preAlg;
    }

    public void setPreAlg(AbstractPredictAlgorithm preAlg) {
        this.preAlg = preAlg;
    }

    public AssetEntity getAsset() {
        return asset;
    }

    public void setAsset(AssetEntity asset) {
        this.asset = asset;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getStartPreDate() {
        return startPreDate;
    }

    public void setStartPreDate(Date startPreDate) {
        this.startPreDate = startPreDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public JFreeChart getChart() {
        return chart;
    }

    public void setChart(JFreeChart chart) {
        this.chart = chart;
    }
    
}
