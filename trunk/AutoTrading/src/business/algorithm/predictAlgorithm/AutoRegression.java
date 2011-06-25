package business.algorithm.predictAlgorithm;




import Jama.Matrix;
import Utility.ParamList;
import Utility.Utility;
import JSci.maths.statistics.*;
import java.lang.Double;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.TreeMap;

public class AutoRegression extends AbstractPredictAlgorithm {

    @Override
    public ParamList runAlgorithm(ParamList parameters) {
        // TODO Auto-generated method stub
        ArrayList<Double> priceList = ((ParamForAutoRegression) parameters).getPriceList();
        int future_interval = ((ParamForAutoRegression) parameters).getFuture_interval();
        double confidence_level = ((ParamForAutoRegression) parameters).getConfidence_level();
        int movingAveragePeriod = (Integer) ((ParamForAutoRegression) parameters).getMA_period();
        int autoRegerssivePeriod = (Integer) ((ParamForAutoRegression) parameters).getAR_period();

        /*
         * Moving Average Step
         */
        int nSample = priceList.size();
        ArrayList<Double> movingAverage = new ArrayList<Double>();
        double temp;
        for (int i = 0; i <= nSample - movingAveragePeriod; ++i) {
            temp = 0.0;
            for (int j = 0; j < movingAveragePeriod; ++j) {
                temp += priceList.get(i + j);
            }
            movingAverage.add(temp / movingAveragePeriod);
        }
        /*
         * Auto Regression step
         */
        int nMovingAverage = movingAverage.size();
        double[][] x = new double[nMovingAverage - autoRegerssivePeriod][autoRegerssivePeriod + 1];
        double[] y = new double[nMovingAverage - autoRegerssivePeriod];
        for (int i = 0; i < (nMovingAverage - autoRegerssivePeriod); ++i) {
            for (int j = 0; j <= autoRegerssivePeriod; ++j) {
                if (j != autoRegerssivePeriod) {
                    x[i][j] = movingAverage.get(i + j);
                } else {
                    x[i][j] = 1;
                }
            }
            y[i] = movingAverage.get(i + autoRegerssivePeriod);
        }
        Matrix matrixX = new Matrix(x);
        Matrix matrixY = new Matrix(y, nMovingAverage - autoRegerssivePeriod);
        Matrix matrixB = matrixX.transpose();
        matrixB = matrixB.times(matrixX);
        matrixB = matrixB.inverse();
        matrixB = matrixB.times(matrixX.transpose());
        matrixB = matrixB.times(matrixY);

        ArrayList<Double> predictionPriceList = new ArrayList<Double>();
        for (int i = autoRegerssivePeriod; i > 0; --i) {
            predictionPriceList.add(movingAverage.get(movingAverage.size() - i));
        }
        for (int i = 0; i < future_interval; ++i) {
            temp = 0.0;
            for (int j = autoRegerssivePeriod; j >= 0; --j) {
                if (j != 0) {
                    temp += predictionPriceList.get(predictionPriceList.size() - j) * matrixB.get(autoRegerssivePeriod - j, 0);
                } else {
                    temp += matrixB.get(autoRegerssivePeriod, 0);
                }
            }
            predictionPriceList.add(temp);
        }
        for (int i = autoRegerssivePeriod; i > 0; --i) {
            predictionPriceList.remove(0);
        }

        Matrix matrixC = matrixX.transpose().times(matrixX).inverse();
        double variance = Utility.calculateVariance(movingAverage);
        double s_b0 = Math.sqrt(variance * matrixC.get(0, 0));
        double t = 1; // need to revise here
        TDistribution tDistribution = new TDistribution(autoRegerssivePeriod - 1);
        t = tDistribution.cumulative(1 - confidence_level);

        double lambda = t * s_b0;

        return new OutputOfAutoRegression(predictionPriceList, lambda);
    }

    @Override
    public TreeMap<String, Class> getParametersList() {
        TreeMap<String, Class> map = new TreeMap<String, Class>();
        //map.put("Price list", ArrayList.class);
        map.put("Future interval", Integer.class);
        map.put("Confidence level", Double.class);
        map.put("MA period", Integer.class);
        map.put("AR period", Integer.class);

        return map;
    }

    @Override
    public ParamList setParametersValue(TreeMap<String, Object> map)
    {
        ArrayList<Double> priceList = (ArrayList)map.get("Price List");
        Integer future_interval = (Integer)map.get("Future Interval");
        Double confidence_level = (Double)map.get("Confidence level");
        Integer MA_period = (Integer)map.get("MA period");
        Integer AR_period = (Integer)map.get("AR period");

        return new ParamForAutoRegression(priceList, future_interval, confidence_level, MA_period, AR_period);
    }
    public static void main(String args[]) {
        ArrayList<Double> priceList = new ArrayList<Double>();
        priceList.add(23.0);
        priceList.add(23.1);
        priceList.add(24.0);
        priceList.add(25.2);
        priceList.add(24.8);
        priceList.add(24.9);
        priceList.add(24.7);
        priceList.add(24.4);
        priceList.add(24.4);
        priceList.add(24.4);
        int future_interval = 10;
        double confidence_level = 0.9;
        int MA_period = 3;
        int AR_period = 3;
        double training_ratio = 0.7;
        ParamForAutoRegression input = new ParamForAutoRegression(priceList,
                future_interval, confidence_level, MA_period, AR_period);
        AutoRegression ar = new AutoRegression();
        ParamList output = ar.runAlgorithm(input);
        for (int i = 0; i < future_interval; ++i) {
            OutputOfAutoRegression temp = (OutputOfAutoRegression) output;
            System.out.println(temp.getPredictionPrice().get(i));
        }
    }
}
