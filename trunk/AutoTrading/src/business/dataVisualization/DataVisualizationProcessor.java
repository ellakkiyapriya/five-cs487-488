/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.dataVisualization;

import business.algorithm.decisionAlgorithm.AbstractDecisionAlgorithm;
import business.algorithm.predictAlgorithm.AbstractPredictAlgorithm;
import business.algorithm.predictAlgorithm.OutputOfAutoRegression;
import business.algorithm.predictAlgorithm.PredictionAlgorithmEntity;
import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import dataAccess.databaseManagement.manager.PriceManager;
import java.util.Date;
import java.util.ArrayList;

import javax.swing.DebugGraphics;

import org.jfree.chart.JFreeChart;
import Utility.Utility;

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
//            visulizationChart.addPredictionPrices("Alg2", prices);
//            visulizationChart.addPredictionPrices("Alg1", prices);
//            ArrayList<OrderEntity> orders = new ArrayList<OrderEntity>();
//            for (PriceEntity price : prices) {
//                orders.add(new OrderEntity(false, 123, price.getDate(), 323, price.getClose(), 0, false));
//            }
//            visulizationChart.addOrders("Alg1", orders);
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
        visulizationChart.removeAllOrders();
        visulizationChart.removeAllPredictionPrice();
        
        //add new results of Algorithms
        for (AbstractPredictAlgorithm algo : preAlgList) {
        	ArrayList<Double> list = new ArrayList<Double>();
        	for (PriceEntity priceEntity : prices) {
				if (priceEntity.getDate().before(startPreDate))
					list.add(priceEntity.getClose());
			}
        	algo.setPriceList(list);
        	OutputOfAutoRegression outputOfAutoRegression = (OutputOfAutoRegression) algo.runAlgorithm();
        	Utility.debug(outputOfAutoRegression.predictionPrice);
        	PredictionAlgorithmEntity result = outputOfAutoRegression.convertThis(startPreDate);
        	
        	Utility.debug(result.list.size());
        	
        	visulizationChart.addPredictionPrices(algo, result);
		}
        
//        visulizationChart.addPredictionPrices("dsafafwe", prices);
//        visulizationChart.addPredictionPrices("asfhhjy", prices);
//        ArrayList<OrderEntity> orders = new ArrayList<OrderEntity>();
//        for (PriceEntity price : prices) {
//            orders.add(new OrderEntity(false, 123, price.getDate(), 323, price.getClose(), 0, false));
//        }
//        visulizationChart.addOrders("dsafafwe", orders);
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

    public void addDecAlg(AbstractDecisionAlgorithm abstractDecisionAlgorithm) {
        decAlgList.add(abstractDecisionAlgorithm);
        //....
    }

    public void removeDecAlg(AbstractDecisionAlgorithm abstractDecisionAlgorithm) {
       decAlgList.remove(abstractDecisionAlgorithm);
       //....
    }

    public void addPreAlg(AbstractPredictAlgorithm abstractPredictAlgorithm) {
        preAlgList.add(abstractPredictAlgorithm);
        //...
    }

    public void removePreAlg(AbstractPredictAlgorithm abstractPredictAlgorithm) {
        preAlgList.remove(abstractPredictAlgorithm);
        //....
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
        return visulizationChart.getChart();
    }
}
