/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddNewOrderJPanel.java
 *
 * Created on Jun 8, 2011, 12:39:52 PM
 */

package presentation.guiForVirtualTrading;

import dataAccess.databaseManagement.manager.AssetManager;
import javax.swing.JDialog;
import javax.swing.JTextField;
import presentation.ComboKeyHandler;

/**
 *
 * @author Dinh
 */
public class AddNewOrderJPanel extends javax.swing.JPanel {

    private JDialog parent;

    /** Creates new form AddNewOrderJPanel */
    public AddNewOrderJPanel(JDialog jDialog) {
        this.parent = jDialog;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buySellJLabel = new javax.swing.JLabel();
        buySellJComboBox = new javax.swing.JComboBox();
        symbolJLabel = new javax.swing.JLabel();
        symbolJComboBox = new javax.swing.JComboBox();
        priceJLabel = new javax.swing.JLabel();
        priceJSpinner = new javax.swing.JSpinner();
        vndJLabel = new javax.swing.JLabel();
        volumeJLabel = new javax.swing.JLabel();
        volumeJSpinner = new javax.swing.JSpinner();
        cancelJButton = new javax.swing.JButton();
        okJButton = new javax.swing.JButton();

        buySellJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        buySellJLabel.setText("Buy/Sell:");

        buySellJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Buy", "Sell" }));

        symbolJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        symbolJLabel.setText("Symbol:");

        symbolJComboBox.setEditable(true);
        symbolJComboBox.setModel(new javax.swing.DefaultComboBoxModel((new AssetManager()).getAllAssets().toArray()));
        JTextField fieldSymbol = (JTextField)symbolJComboBox.getEditor().getEditorComponent();
        fieldSymbol.setText("");
        fieldSymbol.addKeyListener(new ComboKeyHandler(symbolJComboBox));

        priceJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        priceJLabel.setText("Price:");

        priceJSpinner.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(0L), null, null, Long.valueOf(1000L)));

        vndJLabel.setText("VND");

        volumeJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        volumeJLabel.setText("Volume:");

        volumeJSpinner.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(0L), null, null, Long.valueOf(10L)));

        cancelJButton.setText("Cancel");
        cancelJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelJButtonActionPerformed(evt);
            }
        });

        okJButton.setText("OK");
        okJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buySellJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buySellJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(symbolJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(symbolJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(priceJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(priceJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vndJLabel)
                .addGap(18, 18, 18)
                .addComponent(volumeJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(volumeJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(494, Short.MAX_VALUE)
                .addComponent(okJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelJButton)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelJButton, okJButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buySellJLabel)
                    .addComponent(buySellJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(symbolJLabel)
                    .addComponent(symbolJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceJLabel)
                    .addComponent(priceJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vndJLabel)
                    .addComponent(volumeJLabel)
                    .addComponent(volumeJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelJButton)
                    .addComponent(okJButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void okJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okJButtonActionPerformed
        //Add new order

        this.parent.dispose();
    }//GEN-LAST:event_okJButtonActionPerformed

    private void cancelJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelJButtonActionPerformed
        this.parent.dispose();
    }//GEN-LAST:event_cancelJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox buySellJComboBox;
    private javax.swing.JLabel buySellJLabel;
    private javax.swing.JButton cancelJButton;
    private javax.swing.JButton okJButton;
    private javax.swing.JLabel priceJLabel;
    private javax.swing.JSpinner priceJSpinner;
    private javax.swing.JComboBox symbolJComboBox;
    private javax.swing.JLabel symbolJLabel;
    private javax.swing.JLabel vndJLabel;
    private javax.swing.JLabel volumeJLabel;
    private javax.swing.JSpinner volumeJSpinner;
    // End of variables declaration//GEN-END:variables

}
