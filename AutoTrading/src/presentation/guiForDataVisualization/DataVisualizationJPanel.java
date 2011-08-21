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

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import org.jfree.chart.ChartPanel;

import presentation.ComboKeyHandler;
import presentation.ParameterJPanel;
import presentation.mainJFrame;
import business.algorithm.decisionAlgorithm.AbstractDecisionAlgorithm;
import business.algorithm.predictAlgorithm.AbstractPredictAlgorithm;
import business.dataVisualization.chart.ChartStyle;
import business.dataVisualization.chart.VisulizationChart;
import business.dataVisualization.dataProcessing.DataVisualizationProcessor;
import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.ExchangeEntity;

/**
 *
 * @author Dinh
 */
public class DataVisualizationJPanel extends javax.swing.JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        algorithmOptionJPanel = new javax.swing.JPanel();
        decAlgJPanel = new javax.swing.JPanel();
        decAlgJComboBox = new javax.swing.JComboBox();
        runDecAlgJButton = new javax.swing.JButton();
        decAlgParametersContainerJPanel = new javax.swing.JPanel();
        preAlgJPanel = new javax.swing.JPanel();
        preAlgJComboBox = new javax.swing.JComboBox();
        runPreAlgJButton = new javax.swing.JButton();
        preAlgParametersContainerJPanel = new javax.swing.JPanel();
        startPredictionJLabel = new javax.swing.JLabel();
        startPredictionDateJSpinner = new javax.swing.JSpinner();
        visualizationChartJPanel = new javax.swing.JPanel();
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
        refreshJButton = new javax.swing.JButton();
        chartContainerJPanel = new javax.swing.JPanel();

        algorithmOptionJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ALGORITHM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        decAlgJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Decision Algorithm"));

        decAlgJComboBox.setModel(new javax.swing.DefaultComboBoxModel(business.algorithm.decisionAlgorithm.DecisionAlgorithmAPI.DECISION_ALGORITHM_LIST));
        decAlgJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decAlgJComboBoxActionPerformed(evt);
            }
        });

        runDecAlgJButton.setText("Apply");
        runDecAlgJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runDecAlgJButtonActionPerformed(evt);
            }
        });

        decAlgParametersContainerJPanel.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout decAlgJPanelLayout = new javax.swing.GroupLayout(decAlgJPanel);
        decAlgJPanel.setLayout(decAlgJPanelLayout);
        decAlgJPanelLayout.setHorizontalGroup(
            decAlgJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decAlgJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(decAlgJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(decAlgJPanelLayout.createSequentialGroup()
                        .addComponent(decAlgJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                        .addComponent(runDecAlgJButton))
                    .addGroup(decAlgJPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(decAlgParametersContainerJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        decAlgJPanelLayout.setVerticalGroup(
            decAlgJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decAlgJPanelLayout.createSequentialGroup()
                .addGroup(decAlgJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decAlgJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(runDecAlgJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(decAlgParametersContainerJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        preAlgJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Prediction Algorithm"));

        preAlgJComboBox.setModel(new javax.swing.DefaultComboBoxModel(business.algorithm.predictAlgorithm.PredictAlgorithmAPI.PREDICTION_ALGORITHM_LIST));
        preAlgJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preAlgJComboBoxActionPerformed(evt);
            }
        });

        runPreAlgJButton.setText("Apply");
        runPreAlgJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runPreAlgJButtonActionPerformed(evt);
            }
        });

        preAlgParametersContainerJPanel.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout preAlgJPanelLayout = new javax.swing.GroupLayout(preAlgJPanel);
        preAlgJPanel.setLayout(preAlgJPanelLayout);
        preAlgJPanelLayout.setHorizontalGroup(
            preAlgJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(preAlgJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(preAlgJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(preAlgJPanelLayout.createSequentialGroup()
                        .addComponent(preAlgJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                        .addComponent(runPreAlgJButton))
                    .addGroup(preAlgJPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(preAlgParametersContainerJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        preAlgJPanelLayout.setVerticalGroup(
            preAlgJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(preAlgJPanelLayout.createSequentialGroup()
                .addGroup(preAlgJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(preAlgJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(runPreAlgJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(preAlgParametersContainerJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        startPredictionJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        startPredictionJLabel.setText("Start Prediction:");

        startPredictionDateJSpinner.setModel(new javax.swing.SpinnerDateModel());
        startPredictionDateJSpinner.setEditor(new JSpinner.DateEditor(startPredictionDateJSpinner, "MM/dd/yyyy"));
        startPredictionDateJSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                startPredictionDateJSpinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout algorithmOptionJPanelLayout = new javax.swing.GroupLayout(algorithmOptionJPanel);
        algorithmOptionJPanel.setLayout(algorithmOptionJPanelLayout);
        algorithmOptionJPanelLayout.setHorizontalGroup(
            algorithmOptionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, algorithmOptionJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(algorithmOptionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, algorithmOptionJPanelLayout.createSequentialGroup()
                        .addComponent(startPredictionJLabel)
                        .addGap(18, 18, 18)
                        .addComponent(startPredictionDateJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(decAlgJPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(preAlgJPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        algorithmOptionJPanelLayout.setVerticalGroup(
            algorithmOptionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(algorithmOptionJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(decAlgJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(algorithmOptionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startPredictionJLabel)
                    .addComponent(startPredictionDateJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(preAlgJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        visualizationChartJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CHART", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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

        chartStyleJLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        symbolOptionJPanelLayout.setVerticalGroup(
            symbolOptionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(symbolOptionJPanelLayout.createSequentialGroup()
                .addContainerGap()
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        refreshJButton.setText("Refresh");
        refreshJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshJButtonActionPerformed(evt);
            }
        });

        chartContainerJPanel.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout visualizationChartJPanelLayout = new javax.swing.GroupLayout(visualizationChartJPanel);
        visualizationChartJPanel.setLayout(visualizationChartJPanelLayout);
        visualizationChartJPanelLayout.setHorizontalGroup(
            visualizationChartJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizationChartJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(visualizationChartJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chartContainerJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                    .addComponent(refreshJButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(symbolOptionJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        visualizationChartJPanelLayout.setVerticalGroup(
            visualizationChartJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizationChartJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refreshJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chartContainerJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(symbolOptionJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(algorithmOptionJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(visualizationChartJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(visualizationChartJPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(algorithmOptionJPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void exchangeJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exchangeJComboBoxActionPerformed
        if (exchangeJComboBox.getSelectedIndex() == -1) {
            return;
        }
        
        assetJComboBox.setModel(new javax.swing.DefaultComboBoxModel(mainJFrame.mappingExchangeID_Assets.get((ExchangeEntity) exchangeJComboBox.getSelectedItem())));
        assetComboKeyHandler.updateListObjects();
        assetJComboBox.setSelectedIndex(0);
    }//GEN-LAST:event_exchangeJComboBoxActionPerformed

    private void assetJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assetJComboBoxActionPerformed
        if (assetJComboBox.getSelectedIndex() == -1) {
            return;
        }

        dataVisualizationProcessor.setAsset((AssetEntity) assetJComboBox.getSelectedItem());
        dataVisualizationProcessor.updateChartData();
    }//GEN-LAST:event_assetJComboBoxActionPerformed

    private void fromDateJSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fromDateJSpinnerStateChanged
        if (fromDateJSpinner.getValue() == null || dataVisualizationProcessor == null) {
            return;
        }

        dataVisualizationProcessor.setFromDate((Date) fromDateJSpinner.getValue());
        dataVisualizationProcessor.updateChartData();
    }//GEN-LAST:event_fromDateJSpinnerStateChanged

    private void toDateJSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_toDateJSpinnerStateChanged
        if (toDateJSpinner.getValue() == null || dataVisualizationProcessor == null) {
            return;
        }

        dataVisualizationProcessor.setToDate((Date) toDateJSpinner.getValue());
        dataVisualizationProcessor.updateChartData();
    }//GEN-LAST:event_toDateJSpinnerStateChanged

    private void charStyleJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_charStyleJComboBoxActionPerformed
        dataVisualizationProcessor.changeChartType((ChartStyle) charStyleJComboBox.getSelectedItem());
        jFreeChartPanel.setChart(dataVisualizationProcessor.getChart());
    }//GEN-LAST:event_charStyleJComboBoxActionPerformed

    private void preAlgJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preAlgJComboBoxActionPerformed
        preAlgParametersContainerJPanel.removeAll();
        preAlg = business.algorithm.predictAlgorithm.PredictAlgorithmAPI.getPredictionAlgorithm((String) preAlgJComboBox.getSelectedItem());
        preAlgParameterJPanel = new ParameterJPanel(preAlg.getParametersList());
        preAlgParametersContainerJPanel.add(preAlgParameterJPanel);
        preAlgParametersContainerJPanel.updateUI();
    }//GEN-LAST:event_preAlgJComboBoxActionPerformed

    private void decAlgJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decAlgJComboBoxActionPerformed
        decAlgParametersContainerJPanel.removeAll();
        decAlg = business.algorithm.decisionAlgorithm.DecisionAlgorithmAPI.getDecisionAlgorithm((String) decAlgJComboBox.getSelectedItem());
        decAlgParameterJPanel = new ParameterJPanel(decAlg.getParameterList());
        decAlgParametersContainerJPanel.add(decAlgParameterJPanel);
        decAlgParametersContainerJPanel.updateUI();
    }//GEN-LAST:event_decAlgJComboBoxActionPerformed

    private void runPreAlgJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runPreAlgJButtonActionPerformed
        dataVisualizationProcessor.removeAllPreAlg();

        TreeMap<String, Object> valueMap = new TreeMap<String, Object>();
        for (String name : preAlg.getParametersList().keySet()) {
            valueMap.put(name, preAlgParameterJPanel.getValue(name));
        }
        preAlg.setParametersValue(valueMap);

        dataVisualizationProcessor.addPreAlg(preAlg);
    }//GEN-LAST:event_runPreAlgJButtonActionPerformed

    private void runDecAlgJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runDecAlgJButtonActionPerformed
        dataVisualizationProcessor.removeAllDecAlg();

        TreeMap<String, Object> valueMap = new TreeMap<String, Object>();
        for (String name : decAlg.getParameterList().keySet()) {
            valueMap.put(name, decAlgParameterJPanel.getValue(name));
        }
        decAlg.setParameterValue(valueMap);

        dataVisualizationProcessor.addDecAlg(decAlg);
    }//GEN-LAST:event_runDecAlgJButtonActionPerformed

    private void startPredictionDateJSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_startPredictionDateJSpinnerStateChanged
        startPreDate = (Date) startPredictionDateJSpinner.getValue();
        if (dataVisualizationProcessor == null) {
            return;
        }
        dataVisualizationProcessor.setStartPreDate(startPreDate);
        dataVisualizationProcessor.updateChartData();
    }//GEN-LAST:event_startPredictionDateJSpinnerStateChanged

    private void refreshJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshJButtonActionPerformed
        dataVisualizationProcessor.updateChartData();
    }//GEN-LAST:event_refreshJButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel algorithmOptionJPanel;
    private javax.swing.JComboBox assetJComboBox;
    private javax.swing.JLabel assetJLabel;
    private javax.swing.JComboBox charStyleJComboBox;
    private javax.swing.JPanel chartContainerJPanel;
    private javax.swing.JLabel chartStyleJLabel;
    private javax.swing.JComboBox decAlgJComboBox;
    private javax.swing.JPanel decAlgJPanel;
    private javax.swing.JPanel decAlgParametersContainerJPanel;
    private javax.swing.JComboBox exchangeJComboBox;
    private javax.swing.JLabel exchangeJLabel;
    private javax.swing.JSpinner fromDateJSpinner;
    private javax.swing.JLabel fromJLabel;
    private javax.swing.JComboBox preAlgJComboBox;
    private javax.swing.JPanel preAlgJPanel;
    private javax.swing.JPanel preAlgParametersContainerJPanel;
    private javax.swing.JButton refreshJButton;
    private javax.swing.JButton runDecAlgJButton;
    private javax.swing.JButton runPreAlgJButton;
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

    private Date startPreDate;
    private AbstractPredictAlgorithm preAlg;
    private AbstractDecisionAlgorithm decAlg;

    private ChartPanel jFreeChartPanel;
    private ParameterJPanel preAlgParameterJPanel;
    private ParameterJPanel decAlgParameterJPanel;

    private void initOtherComponents() {
        Calendar now = Calendar.getInstance();
        startPredictionDateJSpinner.setValue(now.getTime());
        toDateJSpinner.setValue(now.getTime());
        now.add(Calendar.MONTH, -6);
        fromDateJSpinner.setValue(now.getTime());

        charStyleJComboBox.setModel(new DefaultComboBoxModel(VisulizationChart.CHART_STYLES));

        exchangeJComboBox.setModel(new DefaultComboBoxModel(mainJFrame.mappingExchangeID_Assets.keySet().toArray()));
        exchangeComboKeyHandler = new ComboKeyHandler(exchangeJComboBox);
        JTextField fieldExchange = (JTextField) exchangeJComboBox.getEditor().getEditorComponent();
        fieldExchange.addKeyListener(exchangeComboKeyHandler);

        assetJComboBox.setModel(new DefaultComboBoxModel(mainJFrame.mappingExchangeID_Assets.get((ExchangeEntity) exchangeJComboBox.getSelectedItem())));
        assetComboKeyHandler = new ComboKeyHandler(assetJComboBox);
        JTextField fieldSymbol = (JTextField) assetJComboBox.getEditor().getEditorComponent();
        fieldSymbol.addKeyListener(assetComboKeyHandler);

        dataVisualizationProcessor = new DataVisualizationProcessor((AssetEntity) assetJComboBox.getSelectedItem(), (Date) fromDateJSpinner.getValue(), (Date) toDateJSpinner.getValue(), (ChartStyle) charStyleJComboBox.getSelectedItem());
        startPreDate = (Date) startPredictionDateJSpinner.getValue();
        dataVisualizationProcessor.setStartPreDate(startPreDate);

        jFreeChartPanel = new ChartPanel(dataVisualizationProcessor.getChart());
        chartContainerJPanel.add(jFreeChartPanel);

        preAlg = business.algorithm.predictAlgorithm.PredictAlgorithmAPI.getPredictionAlgorithm((String) preAlgJComboBox.getSelectedItem());
        decAlg = business.algorithm.decisionAlgorithm.DecisionAlgorithmAPI.getDecisionAlgorithm((String) decAlgJComboBox.getSelectedItem());

        preAlgParameterJPanel = new ParameterJPanel(preAlg.getParametersList());
        preAlgParametersContainerJPanel.add(preAlgParameterJPanel);

        decAlgParameterJPanel = new ParameterJPanel(decAlg.getParameterList());
        decAlgParametersContainerJPanel.add(decAlgParameterJPanel);
    }

    public void debug(Object... os) {
        JOptionPane.showMessageDialog(new JFrame(), Arrays.deepToString(os));
    }
}
