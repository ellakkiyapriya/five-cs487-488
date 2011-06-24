/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package business.dataVisualization;

import dataAccess.databaseManagement.entity.OrderEntity;
import dataAccess.databaseManagement.entity.PriceEntity;
import java.util.ArrayList;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author Dinh
 */
public interface VisulizationChart {
    public void setPrices(ArrayList<PriceEntity> prices);
    public void setOrders(ArrayList<OrderEntity> orders);
    public void setPredictionPrices(ArrayList<PriceEntity> prices);
    public void initalChart();
    public void updateChart();
    public JFreeChart getChart();
}
