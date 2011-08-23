/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VirtualTradingJPanel.java
 *
 * Created on Jun 8, 2011, 11:12:31 AM
 */

package presentation.guiForVirtualTrading;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JSpinner;

import presentation.mainJFrame;
import business.virtualTrading.Order;
import business.virtualTrading.User;
import business.virtualTrading.UserList;
import dataAccess.databaseManagement.manager.PriceManager;

/**
 *
 * @author Dinh
 */
public class VirtualTradingJPanel extends javax.swing.JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Creates new form VirtualTradingJPanel */
    public VirtualTradingJPanel() {
        initComponents();
        initOtherComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        portfolioDateJSpinner = new javax.swing.JSpinner();
        portfolioDatejLabel = new javax.swing.JLabel();
        portfolioJLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        portfolioJTable = new javax.swing.JTable();
        orderLogJLabel = new javax.swing.JLabel();
        orderDatejLabel = new javax.swing.JLabel();
        orderLogDateJSpinner = new javax.swing.JSpinner();
        jScrollPane3 = new javax.swing.JScrollPane();
        orderLogJTable = new javax.swing.JTable(new OrderTableModel());
        jPanel2 = new javax.swing.JPanel();
        userJComboBox = new javax.swing.JComboBox();
        cashRemainJLabel = new javax.swing.JLabel();
        cashRemainJTextField = new javax.swing.JTextField();
        ganiLossJLabel = new javax.swing.JLabel();
        gainLossJTextField = new javax.swing.JTextField();
        addUserJButton = new javax.swing.JButton();
        removeUserJButton = new javax.swing.JButton();
        addCashJButton = new javax.swing.JButton();
        addCashJSpinner = new javax.swing.JSpinner();
        vndJLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        todayOrderJTable = new javax.swing.JTable(new OrderTableModel());
        addOrderJButton = new javax.swing.JButton();
        removeOrderJButton = new javax.swing.JButton();
        executeJButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        currentDateJLabel = new javax.swing.JLabel();
        nextCurrentDateJButton = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "USER INFORMATION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        portfolioDateJSpinner.setModel(new javax.swing.SpinnerDateModel());
        portfolioDateJSpinner.setEditor(new JSpinner.DateEditor(portfolioDateJSpinner, "MM/dd/yyyy"));
        portfolioDateJSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                portfolioDateJSpinnerStateChanged(evt);
            }
        });

        portfolioDatejLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        portfolioDatejLabel.setText("Date:");

        portfolioJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        portfolioJLabel.setText("User Portfolio:");

        portfolioJTable.setModel(new PortfolioTableModel());
        jScrollPane2.setViewportView(portfolioJTable);

        orderLogJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        orderLogJLabel.setText("Order Log:");

        orderDatejLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        orderDatejLabel.setText("Date:");

        orderLogDateJSpinner.setModel(new javax.swing.SpinnerDateModel());
        orderLogDateJSpinner.setEditor(new JSpinner.DateEditor(orderLogDateJSpinner, "MM/dd/yyyy"));
        orderLogDateJSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                orderLogDateJSpinnerStateChanged(evt);
            }
        });

        jScrollPane3.setViewportView(orderLogJTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(portfolioJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 344, Short.MAX_VALUE)
                        .addComponent(portfolioDatejLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(portfolioDateJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(orderLogJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 366, Short.MAX_VALUE)
                        .addComponent(orderDatejLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(orderLogDateJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {orderLogDateJSpinner, portfolioDateJSpinner});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portfolioJLabel)
                    .addComponent(portfolioDateJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portfolioDatejLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderLogJLabel)
                    .addComponent(orderLogDateJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderDatejLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "USER", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        userJComboBox.setModel(new DefaultComboBoxModel((new UserList()).getUserList().toArray()));
        userJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userJComboBoxActionPerformed(evt);
            }
        });

        cashRemainJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        cashRemainJLabel.setText("Cash Remain:");

        cashRemainJTextField.setEditable(false);

        ganiLossJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        ganiLossJLabel.setText("Gain/Loss (%):");

        gainLossJTextField.setEditable(false);

        addUserJButton.setText("Add");
        addUserJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserJButtonActionPerformed(evt);
            }
        });

        removeUserJButton.setText("Remove");
        removeUserJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeUserJButtonActionPerformed(evt);
            }
        });

        addCashJButton.setText("Add Cash");
        addCashJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCashJButtonActionPerformed(evt);
            }
        });

        addCashJSpinner.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(100.0d)));

        vndJLabel.setText("x1000 VND");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addCashJButton)
                            .addComponent(cashRemainJLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cashRemainJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(addCashJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vndJLabel)))
                        .addGap(18, 18, 18)
                        .addComponent(ganiLossJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gainLossJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(userJComboBox, 0, 318, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addUserJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeUserJButton)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addUserJButton, removeUserJButton});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addUserJButton)
                    .addComponent(removeUserJButton))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cashRemainJLabel)
                    .addComponent(ganiLossJLabel)
                    .addComponent(gainLossJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cashRemainJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCashJButton)
                    .addComponent(vndJLabel)
                    .addComponent(addCashJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addUserJButton, removeUserJButton});

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PLACE ORDER", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jScrollPane1.setViewportView(todayOrderJTable);

        addOrderJButton.setText("Add");
        addOrderJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOrderJButtonActionPerformed(evt);
            }
        });

        removeOrderJButton.setText("Remove");
        removeOrderJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeOrderJButtonActionPerformed(evt);
            }
        });

        executeJButton.setText("Execute");
        executeJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removeOrderJButton)
                    .addComponent(addOrderJButton)
                    .addComponent(executeJButton))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addOrderJButton, executeJButton, removeOrderJButton});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(addOrderJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeOrderJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
                        .addComponent(executeJButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addOrderJButton, executeJButton, removeOrderJButton});

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CURRENT DATE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        currentDateJLabel.setText("Current Date");

        nextCurrentDateJButton.setText("Next");
        nextCurrentDateJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextCurrentDateJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currentDateJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nextCurrentDateJButton)
                .addContainerGap(358, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentDateJLabel)
                    .addComponent(nextCurrentDateJButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addOrderJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOrderJButtonActionPerformed
        if (selectedUser == null) {
            return;
        }

        addNewOrderJPanel.getParentDialog().setVisible(true);

        if (!addNewOrderJPanel.isOk()) {
            return;
        }

        OrderTableModel orderTableModel = (OrderTableModel) this.todayOrderJTable.getModel();
        orderTableModel.addRow(addNewOrderJPanel.getNewOrder());
        todayOrderJTable.updateUI();

        selectedUser.addOrder(addNewOrderJPanel.getNewOrder());
    }//GEN-LAST:event_addOrderJButtonActionPerformed

    private void removeOrderJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeOrderJButtonActionPerformed
        if (selectedUser == null) {
            return;
        }

        int[] selectedRows = todayOrderJTable.getSelectedRows();

        int j = 0;
        for (int i : todayOrderJTable.getSelectedRows()) {
            selectedUser.removeOrder(selectedUser.getCurOrderList().get(i-j));
            j++;
        }

        OrderTableModel orderTableModel = (OrderTableModel) this.todayOrderJTable.getModel();
        orderTableModel.deleteRows(selectedRows);
        todayOrderJTable.updateUI();
        
    }//GEN-LAST:event_removeOrderJButtonActionPerformed

    private void addUserJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserJButtonActionPerformed
        addNewUserJPanel.getParentDialog().setVisible(true);

        if (!addNewUserJPanel.isAdd()) {
            return;
        }

        DefaultComboBoxModel model = (DefaultComboBoxModel) userJComboBox.getModel();
        model.addElement(addNewUserJPanel.getNewUser());
        userJComboBox.updateUI();
}//GEN-LAST:event_addUserJButtonActionPerformed

    private void removeUserJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeUserJButtonActionPerformed
        if (selectedUser == null) {
            return;
        }

        selectedUser.removeFromDatabase();

        //remove
        DefaultComboBoxModel model = (DefaultComboBoxModel) userJComboBox.getModel();
        model.removeElement(selectedUser);
        userJComboBox.updateUI();
}//GEN-LAST:event_removeUserJButtonActionPerformed

    private void addCashJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCashJButtonActionPerformed
        if (selectedUser == null) {
            return;
        }

        ((User)userJComboBox.getSelectedItem()).addCash((Double)addCashJSpinner.getValue());

        cashRemainJTextField.setText((new DecimalFormat("###,###")).format(selectedUser.getCash()*1000) + " VND");
        gainLossJTextField.setText((new DecimalFormat("##.##")).format(selectedUser.profit()) + " %");

}//GEN-LAST:event_addCashJButtonActionPerformed

    private void userJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userJComboBoxActionPerformed
        updateUserInformation();
}//GEN-LAST:event_userJComboBoxActionPerformed

    private void portfolioDateJSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_portfolioDateJSpinnerStateChanged
        if (selectedUser == null) {
            return;
        }

        PortfolioTableModel portfolioTableModel = (PortfolioTableModel) portfolioJTable.getModel();
        portfolioTableModel.setData(selectedUser.getPortfolioByDate(new java.sql.Date(((Date) portfolioDateJSpinner.getValue()).getTime())));
        portfolioJTable.updateUI();
}//GEN-LAST:event_portfolioDateJSpinnerStateChanged

    private void orderLogDateJSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_orderLogDateJSpinnerStateChanged
        if (selectedUser == null) {
            return;
        }

        OrderTableModel orderTableModel = (OrderTableModel) orderLogJTable.getModel();
        orderTableModel.setData(selectedUser.getOrderByDate(new java.sql.Date(((Date) orderLogDateJSpinner.getValue()).getTime())));
        orderLogJTable.updateUI();
}//GEN-LAST:event_orderLogDateJSpinnerStateChanged

    private void executeJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeJButtonActionPerformed
        selectedUser.addOrderToDatabase(new java.sql.Date(currentDate.getTime()));
        selectedUser.getCurOrderList().clear();
        updateUserInformation();
    }//GEN-LAST:event_executeJButtonActionPerformed

    private void nextCurrentDateJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextCurrentDateJButtonActionPerformed
        if (selectedUser == null) {
            return;
        }

        OrderTableModel orderTableModel = (OrderTableModel) todayOrderJTable.getModel();
        selectedUser.setCurOrderList(new ArrayList<Order>());
        orderTableModel.setData(selectedUser.getCurOrderList());
        todayOrderJTable.updateUI();

        currentDate = new Date(priceManager.getNextDate(new java.sql.Date(currentDate.getTime())).getTime());
        portfolioDateJSpinner.getModel().setValue(currentDate);
        orderLogDateJSpinner.getModel().setValue(currentDate);
        currentDateJLabel.setText(currentDate.toString());
        selectedUser.updatePortfolioCurrentPrice(new java.sql.Date(currentDate.getTime()));
        gainLossJTextField.setText((new DecimalFormat("##.##")).format(selectedUser.profit()) + " %");
    }//GEN-LAST:event_nextCurrentDateJButtonActionPerformed

//    public JDialog getAddNewUserJDialog() {
//        return addNewUserJDialog;
//    }

//    public JDialog getAddNewOrderJDialog() {
//        return addNewOrderJDialog;
//    }

    public JDialog newAddNewUserJDialog() {
        JDialog jDialog = new JDialog(mainJFrame.mainFrame, true);
        addNewUserJPanel = new AddNewUserJPanel(jDialog);
        jDialog.add(addNewUserJPanel);
        jDialog.pack();
        return jDialog;
    }

    public JDialog newAddNewOrderJDialog() {
        JDialog jDialog = new JDialog(mainJFrame.mainFrame, true);
        addNewOrderJPanel = new AddNewOrderJPanel(jDialog);
        jDialog.add(addNewOrderJPanel);
        jDialog.pack();
        return jDialog;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCashJButton;
    private javax.swing.JSpinner addCashJSpinner;
    private javax.swing.JButton addOrderJButton;
    private javax.swing.JButton addUserJButton;
    private javax.swing.JLabel cashRemainJLabel;
    private javax.swing.JTextField cashRemainJTextField;
    private javax.swing.JLabel currentDateJLabel;
    private javax.swing.JButton executeJButton;
    private javax.swing.JTextField gainLossJTextField;
    private javax.swing.JLabel ganiLossJLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton nextCurrentDateJButton;
    private javax.swing.JLabel orderDatejLabel;
    private javax.swing.JSpinner orderLogDateJSpinner;
    private javax.swing.JLabel orderLogJLabel;
    private javax.swing.JTable orderLogJTable;
    private javax.swing.JSpinner portfolioDateJSpinner;
    private javax.swing.JLabel portfolioDatejLabel;
    private javax.swing.JLabel portfolioJLabel;
    private javax.swing.JTable portfolioJTable;
    private javax.swing.JButton removeOrderJButton;
    private javax.swing.JButton removeUserJButton;
    private javax.swing.JTable todayOrderJTable;
    private javax.swing.JComboBox userJComboBox;
    private javax.swing.JLabel vndJLabel;
    // End of variables declaration//GEN-END:variables

    private AddNewUserJPanel addNewUserJPanel;
    private AddNewOrderJPanel addNewOrderJPanel;
    private User selectedUser;
    private Date currentDate;
    private static PriceManager priceManager = new PriceManager();

    private void initOtherComponents() {
        newAddNewUserJDialog();
        newAddNewOrderJDialog();
        updateUserInformation();
    }

    private void updateUserInformation() {
        selectedUser = (User) userJComboBox.getSelectedItem();
        if (selectedUser == null) {
            return;
        }
        cashRemainJTextField.setText((new DecimalFormat("###,###")).format(selectedUser.getCash()*1000) + " VND");
        gainLossJTextField.setText((new DecimalFormat("##.##")).format(selectedUser.profit()) + " %");

        PortfolioTableModel portfolioTableModel = (PortfolioTableModel) portfolioJTable.getModel();
        portfolioTableModel.setData(selectedUser.getPortfolioByDate(new java.sql.Date(((Date) portfolioDateJSpinner.getValue()).getTime())));
        portfolioJTable.updateUI();

        OrderTableModel orderTableModel = (OrderTableModel) orderLogJTable.getModel();
        orderTableModel.setData(selectedUser.getOrderByDate(new java.sql.Date(((Date) orderLogDateJSpinner.getValue()).getTime())));
        orderLogJTable.updateUI();

        orderTableModel = (OrderTableModel) todayOrderJTable.getModel();
        orderTableModel.setData(selectedUser.getCurOrderList());
        todayOrderJTable.updateUI();

        currentDate = selectedUser.getPortfolioLatestDate();
        if (currentDate == null) {
            currentDate = new Date();
        } else {
            currentDate = new Date(currentDate.getTime());
        }
        portfolioDateJSpinner.getModel().setValue(currentDate);
        orderLogDateJSpinner.getModel().setValue(currentDate);
        currentDateJLabel.setText(currentDate.toString());
    }
}
