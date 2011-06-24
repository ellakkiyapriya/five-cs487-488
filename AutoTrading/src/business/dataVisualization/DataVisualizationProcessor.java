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
import java.util.Date;
import java.util.ArrayList;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author Dinh
 */
public class DataVisualizationProcessor {
    public static final ChartStyle[] CHART_STYLES = {new ChartStyle(LineChart.class), new ChartStyle(CandleStickChart.class)};
    private static PriceManager priceManager = new PriceManager();
    
    private AssetEntity asset;
    private Date fromDate;
    private Date toDate;

    private Date startPreDate;
    private AbstractPredictAlgorithm preAlg;
    private AbstractDecisionAlgorithm decAlg;

    private VisulizationChart visulizationChart;
    private ArrayList<PriceEntity> prices;

    public DataVisualizationProcessor(AssetEntity asset, Date fromDate, Date toDate, ChartStyle chartStyle) {
        try {
            this.asset = asset;
            this.fromDate = fromDate;
            this.toDate = toDate;

            this.prices = priceManager.getPriceInInterval(asset.getAssetID(), new java.sql.Date(fromDate.getTime()), new java.sql.Date(toDate.getTime()));
            
            visulizationChart = (VisulizationChart) chartStyle.getChartClass().newInstance();
            visulizationChart.initalChart();
            visulizationChart.setPrices(prices);
            visulizationChart.setPredictionPrices(prices);
            visulizationChart.updateChart();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateChart() {
        this.prices = priceManager.getPriceInInterval(asset.getAssetID(), new java.sql.Date(fromDate.getTime()), new java.sql.Date(toDate.getTime()));
        visulizationChart.setPrices(prices);
        visulizationChart.updateChart();

    }

    public void changeChartType(ChartStyle chartStyle) {
        try {
            visulizationChart = (VisulizationChart) chartStyle.getChartClass().newInstance();
            visulizationChart.initalChart();
            updateChart();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
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
        return visulizationChart.getChart();
    }
    
}
