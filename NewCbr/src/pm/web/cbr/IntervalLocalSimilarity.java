package pm.web.cbr;

import jcolibri.datatypes.Instance;
import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

/**
 * Локальная интервальная мера сходства.
 * @author Mikhail Navrotskiy
 * @version 1.0
 */
public class IntervalLocalSimilarity implements LocalSimilarityFunction {

    /**
     * Метод сравнения прецедентов.
     * @param caseObject Прецедент из бд.
     * @param queryObject Запрос.
     * @return Результат сравнения.
     * @throws NoApplicableSimilarityFunctionException
     */
    @Override
    public double compute(Object caseObject, Object queryObject) throws NoApplicableSimilarityFunctionException {

        if ((caseObject == null) || (queryObject == null))
            return 0;
        if (!(caseObject instanceof Instance))
            throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), caseObject.getClass());
        if (!(queryObject instanceof DescriptionQuery))
            throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), queryObject.getClass());

        Instance c = (Instance) caseObject;
        DescriptionQuery query = (DescriptionQuery) queryObject;

        return 0;
    }

    @Override
    public boolean isApplicable(Object o, Object o2) {
        return false;
    }
}
