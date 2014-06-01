package pm.web.cbr;

import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

/**
 * Локальная интервальная мера сходства.
 * @author Mikhail Navrotskiy
 * @version 1.0
 */
public class IntervalLocalSimilarity implements LocalSimilarityFunction {
    @Override
    public double compute(Object o, Object o2) throws NoApplicableSimilarityFunctionException {
        return 0;
    }

    @Override
    public boolean isApplicable(Object o, Object o2) {
        return false;
    }
}
