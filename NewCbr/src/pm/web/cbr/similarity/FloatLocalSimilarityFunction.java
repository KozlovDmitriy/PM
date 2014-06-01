package pm.web.cbr.similarity;

import jcolibri.datatypes.Instance;
import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import pm.web.cbr.DescriptionQuery;

/**
 * Локальная мера сходства для float чисел.
 * @author Mikhail Navrotskiy
 * @version 1.0
 */
public class FloatLocalSimilarityFunction implements LocalSimilarityFunction {

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
        if (!(queryObject instanceof Float))
            throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), queryObject.getClass());

        Instance c = (Instance) caseObject;
        Float query = (Float) queryObject;

        return 0;
    }

    /**
     * Метод проверки корректности запросов.
     * @param caseObject Объект прецедента из БД.
     * @param queryObject Объект запроса.
     * @return Факт корректности параметров.
     */
    @Override
    public boolean isApplicable(Object caseObject, Object queryObject) {

        if ((caseObject == null) && (queryObject == null))
            return true;
        else if (caseObject == null)
            return queryObject instanceof Float;
        else if (queryObject == null)
            return caseObject instanceof Instance;
        else
            return (caseObject instanceof Instance) && (queryObject instanceof Float);
    }
}
