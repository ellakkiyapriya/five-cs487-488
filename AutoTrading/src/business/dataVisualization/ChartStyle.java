/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package business.dataVisualization;

/**
 *
 * @author Dinh
 */
public class ChartStyle {

    private Class chartClass;

    public ChartStyle(Class chartClass) {
        this.chartClass = chartClass;
    }

    public Class getChartClass() {
        return chartClass;
    }

    @Override
    public String toString() {
        return chartClass.getSimpleName();
    }
    
}
