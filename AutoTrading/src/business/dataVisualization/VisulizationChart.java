/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package business.dataVisualization;

import dataAccess.databaseManagement.entity.OrderEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import java.util.ArrayList;
import org.jfree.chart.JFreeChart;

import business.algorithm.predictAlgorithm.AbstractPredictAlgorithm;
import business.algorithm.predictAlgorithm.PredictionAlgorithmEntity;

/**
 *
 * @author Dinh
 */
public interface VisulizationChart {
    public void setPrices(ArrayList<PriceEntity> prices);
    public void removeOrder(Object object);
    public void removeAllOrders();
    public void addOrders(Object object, ArrayList<OrderEntity> orders);
    public void removePredictionPrice(Object object);
    public void removeAllPredictionPrice();
    public void addPredictionPrices(AbstractPredictAlgorithm abstractPredictAlgorithm, PredictionAlgorithmEntity result);
    public void initalChart();
    public void updateChart();
    public JFreeChart getChart();
}
