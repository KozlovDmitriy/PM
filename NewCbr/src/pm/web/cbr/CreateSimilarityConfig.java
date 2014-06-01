package pm.web.cbr;

import jcolibri.cbrcore.Attribute;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import pm.web.cbr.similarity.FloatLocalSimilarityFunction;
import pm.web.cbr.similarity.IntegerLocalSimilarityFunction;
import pm.web.cbr.similarity.IntervalLocalSimilarity;

/**
 * Класс создания разных настроек для CBR с разными локальными мерами сходства.
 * @author Mikhail Navrotskiy
 * @version 1.0
 */
public class CreateSimilarityConfig {

    /**
     * Эксперимент 1 - использование своей меры близости.
     * @return Настройка для CBR.
     */
    public static NNConfig experiment_1() {

        NNConfig result = new NNConfig();
        Attribute attribute;
        Double weight = 1.0;

        // avCheck
        attribute = new Attribute("avCheck", Description.class);
        result.addMapping(attribute, new FloatLocalSimilarityFunction());
        result.setWeight(attribute, weight);

        // itemsCount
        attribute = new Attribute("itemsCount", Description.class);
        result.addMapping(attribute, new FloatLocalSimilarityFunction());
        result.setWeight(attribute, weight);

        // checksCount
        attribute = new Attribute("checksCount", Description.class);
        result.addMapping(attribute, new IntegerLocalSimilarityFunction());
        result.setWeight(attribute, weight);

        // impl
        attribute = new Attribute("impl", Description.class);
        result.addMapping(attribute, new FloatLocalSimilarityFunction());
        result.setWeight(attribute, weight);

        // implPlan
        attribute = new Attribute("implPlan", Description.class);
        result.addMapping(attribute, new FloatLocalSimilarityFunction());
        result.setWeight(attribute, weight);

        // sickList
        attribute = new Attribute("sickList", Description.class);
        result.addMapping(attribute, new Equal());
        result.setWeight(attribute, weight);

        // vacation
        attribute = new Attribute("vacation", Description.class);
        result.addMapping(attribute, new Equal());
        result.setWeight(attribute, weight);

        return result;
    }

}
