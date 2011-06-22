/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DataVisualizationJPanel.java
 *
 * Created on Jun 15, 2011, 11:32:36 AM
 */
package presentation.guiForDataVisualization;

import business.dataVisualization.ChartStyle;
import business.dataVisualization.DataVisualizationProcessor;
import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.ExchangeEntity;
import dataAccess.databaseManagement.manager.AssetManager;
import dataAccess.databaseManagement.manager.ExchangeManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.TimeSeriesCollection;
import presentation.ComboKeyHandler;

/**
 *
 * @author Dinh
 */
public class DataVisualizationJPanel extends javax.swing.JPanel {

    /** Creates new form DataVisualizationJPanel */
    public DataVisualizationJPanel() {
        initComponents();
        initOtherComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        algorithmOptionJPanel = new javax.swing.JPanel();
        algorithmJLabel = new javax.swing.JLabel();
        startPredictionJLabel = new javax.swing.JLabel();
        preAlgJLabel = new javax.swing.JLabel();
        decAlgJLabel = new javax.swing.JLabel();
        startPredictionDateJSpinner = new javax.swing.JSpinner();
        preAlgJComboBox = new javax.swing.JComboBox();
        decAlgJComboBox = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        preAlgParametersJLabel = new javax.swing.JLabel();
        decAlgParametersjLabel = new javax.swing.JLabel();
        visualizationChartJPanel = new ChartPanel(ChartFactory.createTimeSeriesChart("", "", "", new TimeSeriesCollection(), true, true, true));
        symbolOptionJPanel = new javax.swing.JPanel();
        assetJLabel = new javax.swing.JLabel();
        assetJComboBox = new javax.swing.JComboBox();
        fromJLabel = new javax.swing.JLabel();
        fromDateJSpinner = new javax.swing.JSpinner();
        toDateJLabel = new javax.swing.JLabel();
        toDateJSpinner = new javax.swing.JSpinner();
        exchangeJLabel = new javax.swing.JLabel();
        exchangeJComboBox = new javax.swing.JComboBox();
        chartStyleJLabel = new javax.swing.JLabel();
        charStyleJComboBox = new javax.swing.JComboBox();

        algorithmJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        algorithmJLabel.setText("ALGORITHM");

        startPredictionJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        startPredictionJLabel.setText("Start Prediction:");

        preAlgJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        preAlgJLabel.setText("Prediction Algorithm:");

        decAlgJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        decAlgJLabel.setText("Decision Algorithm:");

        startPredictionDateJSpinner.setModel(new javax.swing.SpinnerDateModel());
        startPredictionDateJSpinner.setEditor(new JSpinner.DateEditor(startPredictionDateJSpinner, "MM/dd/yyyy"));

        preAlgJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        decAlgJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 121, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 121, Short.MAX_VALUE)
        );

        preAlgParametersJLabel.setText("Parameter:");

        decAlgParametersjLabel.setText("Parameter:");

        javax.swing.GroupLayout algorithmOptionJPanelLayout = new javax.swing.GroupLayout(algorithmOptionJPanel);
        algorithmOptionJPanel.setLayout(algorithmOptionJPanelLayout);
        algorithmOptionJPanelLayout.setHorizontalGroup(
            algorithmOptionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(algorithmOptionJPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(algorithmOptionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(algorithmJLabel)
                    .addGroup(algorithmOptionJPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(algorithmOptionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(decAlgJLabel)
                            .addGroup(algorithmOptionJPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(algorithmOptionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(decAlgParametersjLabel)
                                    .addGroup(algorithmOptionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(decAlgJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(algorithmOptionJPanelLayout.createSequentialGroup()
                                .addComponent(preAlgJLabel)
                                .addGap(18, 18, 18)
                                .addComponent(preAlgJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(algorithmOptionJPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(algorithmOptionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(preAlgParametersJLabel)))))
                    .addGroup(algorithmOptionJPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(startPredictionJLabel)
                        .addGap(44, 44, 44)
                        .addComponent(startPredictionDateJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        algorithmOptionJPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {decAlgJComboBox, preAlgJComboBox});

        algorithmOptionJPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel1, jPanel2});

        algorithmOptionJPanelLayout.setVerticalGroup(
            algorithmOptionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(algorithmOptionJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(algorithmJLabel)
                .addGap(11, 11, 11)
                .addGroup(algorithmOptionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startPredictionJLabel)
                    .addComponent(startPredictionDateJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(algorithmOptionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(preAlgJLabel)
                    .addComponent(preAlgJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(preAlgParametersJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(algorithmOptionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decAlgJLabel)
                    .addComponent(decAlgJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(decAlgParametersjLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(160, Short.MAX_VALUE))
        );

        algorithmOptionJPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel1, jPanel2});

        javax.swing.GroupLayout visualizationChartJPanelLayout = new javax.swing.GroupLayout(visualizationChartJPanel);
        visualizationChartJPanel.setLayout(visualizationChartJPanelLayout);
        visualizationChartJPanelLayout.setHorizontalGroup(
            visualizationChartJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 825, Short.MAX_VALUE)
        );
        visualizationChartJPanelLayout.setVerticalGroup(
            visualizationChartJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 527, Short.MAX_VALUE)
        );

        assetJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        assetJLabel.setText("Asset:");

        assetJComboBox.setEditable(true);
        assetJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assetJComboBoxActionPerformed(evt);
            }
        });

        fromJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        fromJLabel.setText("From:");

        fromDateJSpinner.setModel(new javax.swing.SpinnerDateModel());
        fromDateJSpinner.setEditor(new JSpinner.DateEditor(fromDateJSpinner, "MM/dd/yyyy"));
        fromDateJSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fromDateJSpinnerStateChanged(evt);
            }
        });

        toDateJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        toDateJLabel.setText("To:");

        toDateJSpinner.setModel(new javax.swing.SpinnerDateModel());
        toDateJSpinner.setEditor(new JSpinner.DateEditor(toDateJSpinner, "MM/dd/yyyy"));
        toDateJSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                toDateJSpinnerStateChanged(evt);
            }
        });

        exchangeJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        exchangeJLabel.setText("Exchange:");

        exchangeJComboBox.setEditable(true);
        exchangeJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exchangeJComboBoxActionPerformed(evt);
            }
        });

        chartStyleJLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chartStyleJLabel.setText("Chart Style:");

        charStyleJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        charStyleJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                charStyleJComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout symbolOptionJPanelLayout = new javax.swing.GroupLayout(symbolOptionJPanel);
        symbolOptionJPanel.setLayout(symbolOptionJPanelLayout);
        symbolOptionJPanelLayout.setHorizontalGroup(
            symbolOptionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(symbolOptionJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exchangeJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exchangeJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(assetJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assetJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(fromJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fromDateJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(toDateJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toDateJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chartStyleJLabel)
                .addGap(18, 18, 18)
                .addComponent(charStyleJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );
        symbolOptionJPanelLayout.setVerticalGroup(
            symbolOptionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, symbolOptionJPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(symbolOptionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exchangeJLabel)
                    .addComponent(exchangeJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assetJLabel)
                    .addComponent(assetJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fromJLabel)
                    .addComponent(fromDateJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toDateJLabel)
                    .addComponent(toDateJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartStyleJLabel)
                    .addComponent(charStyleJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(algorithmOptionJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(visualizationChartJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(symbolOptionJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(visualizationChartJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(symbolOptionJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(algorithmOptionJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void exchangeJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exchangeJComboBoxActionPerformed
        if (exchangeJComboBox.getSelectedIndex() != -1) {
            assetJComboBox.setModel(new javax.swing.DefaultComboBoxModel(mappingExchangeID_Assets.get(((ExchangeEntity) exchangeJComboBox.getSelectedItem()).getExchangeID())));
            assetComboKeyHandler.updateListObjects();
            assetJComboBox.setSelectedIndex(0);
        }
    }//GEN-LAST:event_exchangeJComboBoxActionPerformed

    private void assetJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assetJComboBoxActionPerformed
        if (assetJComboBox.getSelectedIndex() != -1) {
            dataVisualizationProcessor.setAsset((AssetEntity) assetJComboBox.getSelectedItem());
            dataVisualizationProcessor.updateChart();
            ((ChartPanel) visualizationChartJPanel).setChart(dataVisualizationProcessor.getChart());
        }
    }//GEN-LAST:event_assetJComboBoxActionPerformed

    private void fromDateJSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fromDateJSpinnerStateChanged
        if (fromDateJSpinner.getValue() == null || dataVisualizationProcessor == null) {
            return;
        }

        dataVisualizationProcessor.setFromDate((Date) fromDateJSpinner.getValue());
        dataVisualizationProcessor.updateChart();
        ((ChartPanel) visualizationChartJPanel).setChart(dataVisualizationProcessor.getChart());
    }//GEN-LAST:event_fromDateJSpinnerStateChanged

    private void toDateJSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_toDateJSpinnerStateChanged
        if (toDateJSpinner.getValue() == null || dataVisualizationProcessor == null) {
            return;
        }
        
        dataVisualizationProcessor.setToDate((Date) toDateJSpinner.getValue());
        dataVisualizationProcessor.updateChart();
        ((ChartPanel) visualizationChartJPanel).setChart(dataVisualizationProcessor.getChart());
    }//GEN-LAST:event_toDateJSpinnerStateChanged

    private void charStyleJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_charStyleJComboBoxActionPerformed
        dataVisualizationProcessor.changeChartType((ChartStyle) charStyleJComboBox.getSelectedItem());
        ((ChartPanel) visualizationChartJPanel).setChart(dataVisualizationProcessor.getChart());
    }//GEN-LAST:event_charStyleJComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel algorithmJLabel;
    private javax.swing.JPanel algorithmOptionJPanel;
    private javax.swing.JComboBox assetJComboBox;
    private javax.swing.JLabel assetJLabel;
    private javax.swing.JComboBox charStyleJComboBox;
    private javax.swing.JLabel chartStyleJLabel;
    private javax.swing.JComboBox decAlgJComboBox;
    private javax.swing.JLabel decAlgJLabel;
    private javax.swing.JLabel decAlgParametersjLabel;
    private javax.swing.JComboBox exchangeJComboBox;
    private javax.swing.JLabel exchangeJLabel;
    private javax.swing.JSpinner fromDateJSpinner;
    private javax.swing.JLabel fromJLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox preAlgJComboBox;
    private javax.swing.JLabel preAlgJLabel;
    private javax.swing.JLabel preAlgParametersJLabel;
    private javax.swing.JSpinner startPredictionDateJSpinner;
    private javax.swing.JLabel startPredictionJLabel;
    private javax.swing.JPanel symbolOptionJPanel;
    private javax.swing.JLabel toDateJLabel;
    private javax.swing.JSpinner toDateJSpinner;
    private javax.swing.JPanel visualizationChartJPanel;
    // End of variables declaration//GEN-END:variables
    private DataVisualizationProcessor dataVisualizationProcessor;
    private ComboKeyHandler exchangeComboKeyHandler;
    private ComboKeyHandler assetComboKeyHandler;
    private TreeMap<Long, Object[]> mappingExchangeID_Assets;

    private void initOtherComponents() {
        Calendar now = Calendar.getInstance();
        startPredictionDateJSpinner.setValue(now.getTime());
        toDateJSpinner.setValue(now.getTime());
        now.add(Calendar.MONTH, -6);
        fromDateJSpinner.setValue(now.getTime());

        mappingExchangeID_Assets = new TreeMap<Long, Object[]>();
        ArrayList<ExchangeEntity> listAllExchangeEntitys = (new ExchangeManager()).getAllExchanges();
        for (ExchangeEntity exchangeEntity : listAllExchangeEntitys) {
            ArrayList<AssetEntity> listAssets = (new AssetManager()).getAssetsByExchange(exchangeEntity.getExchangeID());
            mappingExchangeID_Assets.put(exchangeEntity.getExchangeID(), listAssets.toArray());
        }

        charStyleJComboBox.setModel(new DefaultComboBoxModel(DataVisualizationProcessor.listAllChartStyles));

        exchangeJComboBox.setModel(new DefaultComboBoxModel(listAllExchangeEntitys.toArray()));
        exchangeComboKeyHandler = new ComboKeyHandler(exchangeJComboBox);
        JTextField fieldExchange = (JTextField) exchangeJComboBox.getEditor().getEditorComponent();
        fieldExchange.addKeyListener(exchangeComboKeyHandler);

        assetJComboBox.setModel(new DefaultComboBoxModel(mappingExchangeID_Assets.get(((ExchangeEntity) exchangeJComboBox.getSelectedItem()).getExchangeID())));
        assetComboKeyHandler = new ComboKeyHandler(assetJComboBox);
        JTextField fieldSymbol = (JTextField) assetJComboBox.getEditor().getEditorComponent();
        fieldSymbol.addKeyListener(assetComboKeyHandler);

        dataVisualizationProcessor = new DataVisualizationProcessor((AssetEntity) assetJComboBox.getSelectedItem(), (Date) fromDateJSpinner.getValue(), (Date) toDateJSpinner.getValue(), (ChartStyle) charStyleJComboBox.getSelectedItem());
        ((ChartPanel) visualizationChartJPanel).setChart(dataVisualizationProcessor.getChart());
    }
}
