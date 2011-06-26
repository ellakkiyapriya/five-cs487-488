/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package business.dataVisualization;

import business.algorithm.decisionAlgorithm.AbstractDecisionAlgorithm;
import business.algorithm.decisionAlgorithm.Order;
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

    public void removeOrder(AbstractDecisionAlgorithm abstractDecisionAlgorithm);
    public void removeAllOrders();
    public void addOrders(AbstractDecisionAlgorithm abstractDecisionAlgorithm, ArrayList<Order> orders);

    public void removePredictionPrice(AbstractPredictAlgorithm abstractPredictAlgorithm);
    public void removeAllPredictionPrice();
    public void addPredictionPrices(AbstractPredictAlgorithm abstractPredictAlgorithm, PredictionAlgorithmEntity result);

    public void initalChart();
    public void updateChart();
    public JFreeChart getChart();
}
