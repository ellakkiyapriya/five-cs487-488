/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package autotrade.core;

import autotrade.core.technicalanalysismethod.TechnicalAnalysisMethod;

/**
 *
 * @author Dinh
 */
public class User {
    private int userID;
    private String userName;
    private int typeID;
    private String typeName;
    private TechnicalAnalysisMethod technicalAnalysisMethod;
    private double cash_remain;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getCash_remain() {
        return cash_remain;
    }

    public void setCash_remain(double cash_remain) {
        this.cash_remain = cash_remain;
    }

    public TechnicalAnalysisMethod getTechnicalAnalysisMethod() {
        return technicalAnalysisMethod;
    }

    public void setTechnicalAnalysisMethod(TechnicalAnalysisMethod technicalAnalysisMethod) {
        this.technicalAnalysisMethod = technicalAnalysisMethod;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
