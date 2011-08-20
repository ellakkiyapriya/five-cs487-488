/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * rowHeaderJPanel.java
 *
 * Created on Jun 24, 2011, 5:10:05 PM
 */

package presentation.guiForDecisionAlgorithmEvaluation;

import business.algorithm.decisionAlgorithm.AbstractDecisionAlgorithm;
import business.virtualTrading.PortfolioEntry;
import dataAccess.databaseManagement.entity.OrderEntity;
import java.util.ArrayList;
import javax.swing.JDialog;
import presentation.guiForVirtualTrading.OrderTableModel;
import presentation.guiForVirtualTrading.PortfolioTableModel;

/**
 *
 * @author Dinh
 */
public class RowHeaderJPanel extends javax.swing.JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object object;
    private OrderTableModel orderTableModel;
    private PortfolioTableModel portfolioTableModel;

    /** Creates new form rowHeaderJPanel */
    public RowHeaderJPanel(AbstractDecisionAlgorithm decAlg, ArrayList<OrderEntity> orders, ArrayList<PortfolioEntry> portfolio) {
        this.object = decAlg;
        this.orderTableModel = new OrderTableModel();

        this.portfolioTableModel = new PortfolioTableModel();

        for (PortfolioEntry portfolioEntry : portfolio) {
            portfolioTableModel.addRow(portfolioEntry);
        }

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

        algorithmJLabel = new javax.swing.JLabel();
        viewOrdersJButton = new javax.swing.JButton();
        viewPortfolioJButton = new javax.swing.JButton();

        algorithmJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        algorithmJLabel.setText("jLabel1");

        viewOrdersJButton.setText("View Orders");
        viewOrdersJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewOrdersJButtonActionPerformed(evt);
            }
        });

        viewPortfolioJButton.setText("View Portfolio");
        viewPortfolioJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPortfolioJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(algorithmJLabel)
                    .addComponent(viewOrdersJButton)
                    .addComponent(viewPortfolioJButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {algorithmJLabel, viewOrdersJButton, viewPortfolioJButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(algorithmJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewOrdersJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewPortfolioJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {algorithmJLabel, viewOrdersJButton, viewPortfolioJButton});

    }// </editor-fold>//GEN-END:initComponents

    private void viewOrdersJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewOrdersJButtonActionPerformed
        viewOrdersJDialog.setVisible(true);
    }//GEN-LAST:event_viewOrdersJButtonActionPerformed

    private void viewPortfolioJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPortfolioJButtonActionPerformed
        viewPortfolioJDialog.setVisible(true);
    }//GEN-LAST:event_viewPortfolioJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel algorithmJLabel;
    private javax.swing.JButton viewOrdersJButton;
    private javax.swing.JButton viewPortfolioJButton;
    // End of variables declaration//GEN-END:variables

    private JDialog viewOrdersJDialog;
    private JDialog viewPortfolioJDialog;

    private void initOtherComponents() {
        algorithmJLabel.setText(object.toString());
        viewOrdersJDialog = newViewOrdersJDialog();
        viewPortfolioJDialog = newViewPortfolioJDialog();
    }

    private JDialog newViewOrdersJDialog() {
        JDialog jDialog = new JDialog();
        jDialog.setTitle(object.toString() + "-Orders");
        ViewOrderJPanel panel = new ViewOrderJPanel(orderTableModel);
        jDialog.add(panel);
        jDialog.pack();
        return jDialog;
    }

    private JDialog newViewPortfolioJDialog() {
        JDialog jDialog = new JDialog();
        jDialog.setTitle(object.toString() + "-Portfolio");
        ViewPortfolioJPanel panel = new ViewPortfolioJPanel(portfolioTableModel);
        jDialog.add(panel);
        jDialog.pack();
        return jDialog;
    }

}
