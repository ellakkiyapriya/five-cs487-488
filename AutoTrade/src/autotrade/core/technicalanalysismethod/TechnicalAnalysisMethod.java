/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package autotrade.core.technicalanalysismethod;

import autotrade.core.Order;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Dinh
 */
public class TechnicalAnalysisMethod {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Order> getOrders(Date date, int user_id) {
        return null;
    };

}
