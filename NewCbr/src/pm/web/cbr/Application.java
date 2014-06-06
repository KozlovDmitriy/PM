package pm.web.cbr;

import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import jcolibri.casebase.CachedLinealCaseBase;
import jcolibri.casebase.IDIndexedLinealCaseBase;
import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.connector.OntologyConnector;
import jcolibri.datatypes.Instance;
import jcolibri.exception.ExecutionException;
import jcolibri.exception.OntologyAccessException;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.selection.SelectCases;
import jcolibri.util.FileIO;
import jcolibri.util.OntoBridgeSingleton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Класс CBR приложения.
 * @author Mikhail Navrotskiy
 * @version 1.0
 */
public class Application implements StandardCBRApplication {

    /* Поля класса. */
    /** Запрос. */
    private CBRQuery query;
    /** Результат CBR цикла. */
    private Collection<RetrievalResult> eval;
    /** БД прецедентов. */
    private CBRCaseBase caseBase;
    /** Выбранные прецеденты. */
    private Collection<CBRCase> selectedCase;
    /** Коннектор с онтологией. */
    private OntologyConnector connector;
    /** Экземпляр класса. */
    private static Application instance;
    /** Значение CBR (конкретный case). */
    private CBRCase result;

    private double evalValue;

    public double getEvalValue() {
        return evalValue;
    }

    /**
     * Метод получения значения результата.
     * @return Значение результата CBR цикла.
     */
    public CBRCase getResult() {
        return this.result;
    }

    /**
     * Конструктор по умолчанию.
     */
    public Application(){}

    /**
     * Конфигурация.
     * @throws ExecutionException
     */
    @Override
    public void configure() throws ExecutionException {

        this.connector = new OntologyConnector();

        this.connector.initFromXMLfile(FileIO.findFile("configure.xml"));

        this.caseBase = new LinealCaseBase();
    }

    /**
     * Метод действий, выполняемых перед циклом.
     * @return База прецедентов.
     * @throws ExecutionException
     */
    @Override
    public CBRCaseBase preCycle() throws ExecutionException {

        this.caseBase.init(this.connector);

//        for (CBRCase cbrCase : this.caseBase.getCases()) {
//            Iterator<String> caseInstances = ob.listInstances("Case");
//            while (caseInstances.hasNext()) {
//                String instance = caseInstances.next();
//                String baseItem = ((Solution)cbrCase.getSolution()).getMainConcept().toString();
//                if (instance.equals("http://www.owl-ontologies.com/pm-web.owl#" + baseItem)) {
//                    Iterator<String> it = ob.listInstanceProperties(instance);
//                    while (it.hasNext()) {
//                        String prop = it.next();
//                        if (prop.equals("http://www.owl-ontologies.com/pm-web.owl#index_problems")) {
//                            Iterator<String> i = ob.listPropertyValue(instance, prop);
//                            while (i.hasNext()) {
//                                String val = i.next();
//                                try {
//                                    ((Solution)cbrCase.getSolution()).setProblems(new Instance(val));
//                                } catch (OntologyAccessException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        } else if (prop.equals("http://www.owl-ontologies.com/pm-web.owl#solution_rec")) {
//                            Iterator<String> i = ob.listPropertyValue(instance, prop);
//                            while (i.hasNext()) {
//                                String val = i.next();
//                                try {
//                                    ((Solution)cbrCase.getSolution()).setSolutions(new Instance(val));
//                                } catch (OntologyAccessException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//                    break;
//                }
//            }
//        }

        return this.caseBase;
    }

    private String getProperty(OntoBridge ob, Instance ins, String name)
    {
        List<String> properties = new ArrayList<String>();
        List<String> values = new ArrayList<String>();
        String str="";

        ob.listInstancePropertiesValues(ins.toString(), properties, values);
        int i = properties.indexOf(ob.getURI(name));
        if (i>-1) str = values.get(i).substring(0,values.get(i).indexOf('^'));
        else {
            str = ins.toString().substring(1).replace('_', '.');
        }

        return str;
    }

    /**
     * Метод CBR цикла.
     * @param cbrQuery Запрос.
     * @throws ExecutionException
     */
    @Override
    public void cycle(CBRQuery cbrQuery) throws ExecutionException {

        NNConfig config = CreateSimilarityConfig.experiment_1();

        config.setDescriptionSimFunction(new Average());

        this.query = cbrQuery;
        DescriptionQuery queryDescription = (DescriptionQuery) this.query.getDescription();
        float eth = queryDescription.getAvCheck();
        float min = 0;
        float max = 0;

        OntoBridge ob = OntoBridgeSingleton.getOntoBridge();
        Iterator it = this.caseBase.getCases().iterator();

        eth = queryDescription.getItemsCount();

        if (eth < 3) {
            min = 0; max = 3.0f;
        } else {
            min = 3.01f; max = 5;
        }

//        ob = OntoBridgeSingleton.getOntoBridge();
//        it = this.caseBase.getCases().iterator();
//        while (it.hasNext()) {
//            CBRCase cbrCase = (CBRCase) it.next();
//            Description d = (Description) cbrCase.getDescription();
//            Solution s = (Solution) cbrCase.getSolution();
//            float value = 0;
//            String avCheck = d.getItemsCount().toString();
//            Iterator jt = ob.listPropertyValue("http://www.owl-ontologies.com/pm-web.owl#" + avCheck, "http://www.owl-ontologies.com/pm-web.owl#value");
//            while (jt.hasNext()) {
//                String v = (String) jt.next();
//                v = v.replaceAll("\\^+.*$", "");
//                value = Float.parseFloat(v);
//            }
//
//            if (value > max || value < min) {
//                it.remove();
//            }
//        }

        this.eval = NNScoringMethod.evaluateSimilarity(this.caseBase.getCases(), this.query, config);

        this.selectedCase = SelectCases.selectTopK(this.eval, 1);
        Collection<RetrievalResult> retResultArray = SelectCases.selectTopKRR(this.eval, 1);

        for (CBRCase item : this.selectedCase) {
            this.result = item;
        }

        for (RetrievalResult rr : retResultArray) {
            this.evalValue = rr.getEval();
        }
    }

    /**
     * Метод действий, выполняемых после цикла.
     * @throws ExecutionException
     */
    @Override
    public void postCycle() throws ExecutionException {}
}
