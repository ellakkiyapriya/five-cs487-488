/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package autotrade.core.technicalanalysismethod;

/**
 *
 * @author Dinh
 */
public class SimpleMovingAverage extends TechnicalAnalysisMethod{
    private int period;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
