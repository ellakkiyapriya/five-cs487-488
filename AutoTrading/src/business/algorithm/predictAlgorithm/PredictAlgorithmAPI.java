package business.algorithm.predictAlgorithm;

public class PredictAlgorithmAPI {
	private static final String AUTOREGRESSION_MA = "Auto Regression MA";
	private static final String AUTOREGRESSION_FE = "Auto Regression FE";
	private static final String PARTICLE_FILTER = "Particle filter";
	private static final String ARFE_PF = "ARFE_ParticleFilter";
	private static final String LAGRANGE_EXTRAPOLATION = "Lagrange Extrapolation";
	private static final String LAGRANGE_FE = "Lagrange_FE";
	private static final String LAGRANGE_MA = "Lagrange_MA";
	private static final String BARYCENTRIC_EXTRAPOLATION = "Barycentric Extrapolation";
	private static final String BARYCENTRIC_FE = "Barycentric_FE";
	

	public static final String[] PREDICTION_ALGORITHM_LIST = {
			AUTOREGRESSION_MA, AUTOREGRESSION_FE, PARTICLE_FILTER, ARFE_PF, LAGRANGE_EXTRAPOLATION, LAGRANGE_FE, BARYCENTRIC_EXTRAPOLATION, BARYCENTRIC_FE, LAGRANGE_MA };

	public static AbstractPredictAlgorithm getPredictionAlgorithm(String str) {
		if (str.equals(AUTOREGRESSION_MA)) {
			return (new AutoRegressionMA());
		} else if (str.equals(AUTOREGRESSION_FE)) {
			return (new AutoRegressionFE());
		} else if (str.equals(PARTICLE_FILTER)) {
			return (new ParticleFilter());
		}  else if (str.equals(ARFE_PF)) {
			return (new ARFE_ParticleFilter());
		} else if (str.equals(LAGRANGE_EXTRAPOLATION)) {
			return (new LagrangeExtrapolation());
		} else if (str.equals(LAGRANGE_MA)) {
			return (new LagrangeExtrapolation());
		} else if (str.equals(LAGRANGE_FE)) {
			return (new LagrangeFE());
		} else if (str.equals(BARYCENTRIC_EXTRAPOLATION)) {
			return (new BarycentricRationalExtrapolation());
		} else if (str.equals(BARYCENTRIC_FE)) {
			return (new BarycentricRationalFE());
		}
		
		return null;
	}

}
