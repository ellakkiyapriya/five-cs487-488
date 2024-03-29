/*
 * New Interface
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * mainJFrame.java
 *
 * Created on Jun 8, 2011, 10:46:54 AM
 */
package presentation;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import dataAccess.databaseManagement.entity.AssetEntity;
import dataAccess.databaseManagement.entity.ExchangeEntity;
import dataAccess.databaseManagement.manager.AssetManager;
import dataAccess.databaseManagement.manager.ExchangeManager;

import presentation.guiForDataUpdate.DataUpdateJPanel;
import presentation.guiForDataVisualization.DataVisualizationJPanel;
import presentation.guiForDecisionAlgorithmEvaluation.DecisionAlgorithmEvaluationJPanel;
import presentation.guiForPredictionAlgorithmEvaluation.PredictionAlgorithmEvaluationJPanel;
import presentation.guiForVirtualTrading.VirtualTradingJPanel;

/**
 *
 * @author Dinh
 */
public class mainJFrame extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JFrame mainFrame;
	public static TreeMap<ExchangeEntity, Object[]> mappingExchangeID_Assets = null;

    /** Creates new form mainJFrame */
    public mainJFrame() {
        initComponents();
        initOtherComponents();
        mainFrame = this;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainJTabbedPane = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainJTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainJTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    	java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
        		try {
        		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        		        if ("Nimbus".equals(info.getName())) {
        		            UIManager.setLookAndFeel(info.getClassName());
        		            break;
        		        }
        		    }
        		} catch (Exception e) {
                	try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
        		}
                new mainJFrame().setVisible(true);
            }
        });
    }

    private void initOtherComponents() {
        if (mappingExchangeID_Assets == null) {
            mappingExchangeID_Assets = new TreeMap<ExchangeEntity, Object[]>();
            ArrayList<ExchangeEntity> listAllExchangeEntitys = (new ExchangeManager()).getAllExchanges();
            for (ExchangeEntity exchangeEntity : listAllExchangeEntitys) {
                ArrayList<AssetEntity> listAssets = (new AssetManager()).getAssetsByExchange(exchangeEntity.getExchangeID());
                mappingExchangeID_Assets.put(exchangeEntity, listAssets.toArray());
            }
        }
    	
    	JScrollPane jScrollPane;

        DataVisualizationJPanel dataVisualizationJPanel = new DataVisualizationJPanel();
        jScrollPane = new JScrollPane(dataVisualizationJPanel);
        mainJTabbedPane.addTab("Data Visualization", jScrollPane);

        DecisionAlgorithmEvaluationJPanel decisionAlgorithmEvaluationJPanel = new DecisionAlgorithmEvaluationJPanel();
        jScrollPane = new JScrollPane(decisionAlgorithmEvaluationJPanel);
        mainJTabbedPane.addTab("Decision Algorithm Evaluation", jScrollPane);

        PredictionAlgorithmEvaluationJPanel predictionAlgorithmEvaluationJPanel = new PredictionAlgorithmEvaluationJPanel();
        jScrollPane = new JScrollPane(predictionAlgorithmEvaluationJPanel);
        mainJTabbedPane.addTab("Prediction Algorithm Evaluation", jScrollPane);

        VirtualTradingJPanel virtualTradingJPanel = new VirtualTradingJPanel();
        jScrollPane = new JScrollPane(virtualTradingJPanel);
        mainJTabbedPane.addTab("Virtual Trading", jScrollPane);
        
        DataUpdateJPanel dataUpdateJPanel = new DataUpdateJPanel();
        jScrollPane = new JScrollPane(dataUpdateJPanel);
        mainJTabbedPane.addTab("Data Update", jScrollPane);

        this.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane mainJTabbedPane;
    // End of variables declaration//GEN-END:variables

	public static void debug(Object... os) {
		JOptionPane.showMessageDialog(null, Arrays.deepToString(os));
	}

}
