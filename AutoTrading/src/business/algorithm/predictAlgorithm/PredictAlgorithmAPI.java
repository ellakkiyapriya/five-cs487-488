package business.algorithm.predictAlgorithm;

public class PredictAlgorithmAPI {
	private static final String AUTOREGRESSION_MA = "Auto Regression MA";
	private static final String AUTOREGRESSION_FE = "Auto Regression FE";
	private static final String PARTICLE_FILTER = "Particle filter";
	private static final String ARFE_PF = "AutoRegressionFE_ParticleFilter";

	public static final String[] PREDICTION_ALGORITHM_LIST = {
			AUTOREGRESSION_MA, AUTOREGRESSION_FE, PARTICLE_FILTER, ARFE_PF };

	public static AbstractPredictAlgorithm getPredictionAlgorithm(String str) {
		if (str.equals(AUTOREGRESSION_MA)) {
			return (new AutoRegressionMA());
		} else if (str.equals(AUTOREGRESSION_FE)) {
			return (new AutoRegressionFE());
		} else if (str.equals(PARTICLE_FILTER)) {
			return (new ParticleFilter());
		}  else if (str.equals(ARFE_PF)) {
			return (new ARFE_ParticleFilter());
		}
		return null;
	}

}
