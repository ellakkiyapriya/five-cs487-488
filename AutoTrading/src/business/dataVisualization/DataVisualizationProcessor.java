/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.dataVisualization;

import Utility.Utility;
import business.algorithm.decisionAlgorithm.AbstractDecisionAlgorithm;
import business.algorithm.decisionAlgorithm.Order;
import business.algorithm.predictAlgorithm.AbstractPredictAlgorithm;
import business.algorithm.predictAlgorithm.OutputOfAutoRegression;
import business.algorithm.predictAlgorithm.PredictionAlgorithmEntity;
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
    private ArrayList<AbstractPredictAlgorithm> preAlgList = new ArrayList<AbstractPredictAlgorithm>();
    private ArrayList<AbstractDecisionAlgorithm> decAlgList = new ArrayList<AbstractDecisionAlgorithm>();
    private VisulizationChart visualizationChart;
    private ArrayList<PriceEntity> prices;

    public DataVisualizationProcessor(AssetEntity asset, Date fromDate, Date toDate, ChartStyle chartStyle) {
        try {
            this.asset = asset;
            this.fromDate = fromDate;
            this.toDate = toDate;

            this.prices = priceManager.getPriceInInterval(asset.getAssetID(), new java.sql.Date(fromDate.getTime()), new java.sql.Date(toDate.getTime()));

            visualizationChart = (VisulizationChart) chartStyle.getChartClass().newInstance();
            visualizationChart.initalChart();
            visualizationChart.setPrices(prices);
            visualizationChart.updateChart();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    public void updateChartData() {
        this.prices = priceManager.getPriceInInterval(asset.getAssetID(), new java.sql.Date(fromDate.getTime()), new java.sql.Date(toDate.getTime()));
        visualizationChart.setPrices(prices);

        //update prediction algorithms
        {
            visualizationChart.removeAllPredictionPrice();

            //add new results of Algorithms
            for (AbstractPredictAlgorithm preAlgo : preAlgList) {
                visualizationChart.addPredictionPrices(preAlgo, runPreAlg(preAlgo));
            }

            for (AbstractDecisionAlgorithm decAlgo : decAlgList) {
                visualizationChart.addOrders(decAlgo, runDecAlg(decAlgo));
            }
        }

        //update decision algorithms
        {
            visualizationChart.removeAllOrders();
        }

        visualizationChart.updateChart();
    }

    private PredictionAlgorithmEntity runPreAlg(AbstractPredictAlgorithm preAlgo) {
        ArrayList<Double> list = new ArrayList<Double>();
        for (PriceEntity priceEntity : prices) {
            if (priceEntity.getDate().before(startPreDate)) {
                list.add(priceEntity.getClose());
            }
        }

        preAlgo.setPriceList(list);
        OutputOfAutoRegression outputOfAutoRegression = (OutputOfAutoRegression) preAlgo.runAlgorithm();

        return outputOfAutoRegression.convertThis(startPreDate);
    }

    private ArrayList<Order> runDecAlg(AbstractDecisionAlgorithm decAlgo) {
        ArrayList<Double> list = new ArrayList<Double>();
        for (PriceEntity priceEntity : prices) {
            list.add(priceEntity.getClose());
        }

        decAlgo.setPriceList(list);
        ArrayList<Order> result = decAlgo.runAlgorithm();

        for (Order order : result) {
            order.setDate(prices.get(order.getNth_day_in_future()).getDate());
        }

        return result;
    }

    public void changeChartType(ChartStyle chartStyle) {
        try {
            visualizationChart = (VisulizationChart) chartStyle.getChartClass().newInstance();
            visualizationChart.initalChart();
            this.updateChartData();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    public void addDecAlg(AbstractDecisionAlgorithm abstractDecisionAlgorithm) {
        decAlgList.add(abstractDecisionAlgorithm);
        visualizationChart.addOrders(abstractDecisionAlgorithm, runDecAlg(abstractDecisionAlgorithm));
        visualizationChart.updateChart();
    }

    public void removeDecAlg(AbstractDecisionAlgorithm abstractDecisionAlgorithm) {
        decAlgList.remove(abstractDecisionAlgorithm);
        visualizationChart.removeOrder(abstractDecisionAlgorithm);
        visualizationChart.updateChart();
    }

    public void removeAllDecAlg() {
        decAlgList.clear();
        visualizationChart.removeAllOrders();
        visualizationChart.updateChart();
    }

    public void addPreAlg(AbstractPredictAlgorithm abstractPredictAlgorithm) {
        preAlgList.add(abstractPredictAlgorithm);
        visualizationChart.addPredictionPrices(abstractPredictAlgorithm, runPreAlg(abstractPredictAlgorithm));
        visualizationChart.updateChart();
    }

    public void removePreAlg(AbstractPredictAlgorithm abstractPredictAlgorithm) {
        preAlgList.remove(abstractPredictAlgorithm);
        visualizationChart.removePredictionPrice(abstractPredictAlgorithm);
        visualizationChart.updateChart();
    }

    public void removeAllPreAlg() {
        preAlgList.clear();
        visualizationChart.removeAllPredictionPrice();
        visualizationChart.updateChart();
    }

    public ArrayList<AbstractDecisionAlgorithm> getDecAlgList() {
        return decAlgList;
    }

    public void setDecAlgList(ArrayList<AbstractDecisionAlgorithm> decAlg) {
        this.decAlgList = decAlg;
    }

    public ArrayList<AbstractPredictAlgorithm> getPreAlgList() {
        return preAlgList;
    }

    public void setPreAlgList(ArrayList<AbstractPredictAlgorithm> preAlg) {
        this.preAlgList = preAlg;
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
        return visualizationChart.getChart();
    }
}
