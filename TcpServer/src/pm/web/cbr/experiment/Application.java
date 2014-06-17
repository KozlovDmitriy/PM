package pm.web.cbr.experiment;

import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.connector.OntologyConnector;
import jcolibri.datatypes.Instance;
import jcolibri.exception.ExecutionException;
import jcolibri.exception.OntologyAccessException;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.*;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntCosine;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDeep;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDeepBasic;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDetail;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.selection.SelectCases;
import jcolibri.util.FileIO;
import jcolibri.util.OntoBridgeSingleton;
import pm.web.cbr.*;

import java.util.Collection;
import java.util.Iterator;

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
     * @throws jcolibri.exception.ExecutionException
     */
    @Override
    public void configure() throws ExecutionException {

        this.connector = new OntologyConnector();

        this.connector.initFromXMLfile(FileIO.findFile("ontoconfig.xml"));

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

        return this.caseBase;
    }

    /**
     * Метод CBR цикла.
     * @param cbrQuery Запрос.
     * @throws ExecutionException
     */
    @Override
    public void cycle(CBRQuery cbrQuery) throws ExecutionException {

        NNConfig config = this.getSimilarityConfig();

        config.setDescriptionSimFunction(new Average());

        this.query = cbrQuery;

        this.eval = NNScoringMethod.evaluateSimilarity(this.caseBase.getCases(), this.query, config);

        this.selectedCase = SelectCases.selectTopK(this.eval, 10);
        Collection<RetrievalResult> retResultArray = SelectCases.selectTopKRR(this.eval, 10);

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

    public NNConfig getSimilarityConfig()
    {
        NNConfig config = new NNConfig();
        Attribute attribute;
        LocalSimilarityFunction function;


        attribute = new Attribute("individualPlanSum",Description.class);
        function = localSimilFactory("OntDoubleMeasure", 100000);
        config.addMapping(attribute, function);
        config.setWeight(attribute, 0.05);

        attribute = new Attribute("individualPlanDone",Description.class);
        function = localSimilFactory("OntDoubleMeasure", 100000);
        config.addMapping(attribute, function);
        config.setWeight(attribute, 0.05);

        attribute = new Attribute("individualPlan",Description.class);
        function = localSimilFactory("OntDoubleMeasure", 100);
        config.addMapping(attribute, function);
        config.setWeight(attribute, 0.1);

        attribute = new Attribute("receiptSum",Description.class);
        function = localSimilFactory("OntDoubleMeasure", 1000);
        config.addMapping(attribute, function);
        config.setWeight(attribute, 0.1);

        attribute = new Attribute("receiptNum",Description.class);
        function = localSimilFactory("OntDoubleMeasure", 100);
        config.addMapping(attribute, function);
        config.setWeight(attribute, 0.05);

        attribute = new Attribute("receiptQuantity",Description.class);
        function = localSimilFactory("OntDoubleMeasure", 10);
        config.addMapping(attribute, function);
        config.setWeight(attribute, 0.1);

        attribute = new Attribute("individualPlan_min",Description.class);
        function = localSimilFactory("Equal",1);
        config.addMapping(attribute, function);
        config.setWeight(attribute, 0.2);

        attribute = new Attribute("receiptSum_min",Description.class);
        function = localSimilFactory("Equal", 1);
        config.addMapping(attribute, function);
        config.setWeight(attribute, 0.2);

        attribute = new Attribute("receiptQuantity_min",Description.class);
        function = localSimilFactory("Equal", 1);
        config.addMapping(attribute, function);
        config.setWeight(attribute, 0.2);

        attribute = new Attribute("experience",Description.class);
        function = localSimilFactory("Equal", 1);
        config.addMapping(attribute, function);
        config.setWeight(attribute, 0.1);

        attribute = new Attribute("period",Description.class);
        function = localSimilFactory("Equal", 1);
        config.addMapping(attribute, function);
        config.setWeight(attribute, 0.1);

        return config;
    }

    private LocalSimilarityFunction localSimilFactory(String name, int param)
    {
        if(name.equals("Equal"))
            return new Equal();
        else if(name.equals("Interval"))
            return new Interval(param);
//        else if(name.equals("TestSim"))
//            return new TestSim(param);
        else if(name.equals("Threshold"))
            return new Threshold(param);
        else if(name.equals("EnumCyclicDistance"))
            return new EnumCyclicDistance();
        else if(name.equals("EnumDistance"))
            return new EnumDistance();
        else if(name.equals("OntCosine"))
            return new OntCosine();
        else if(name.equals("OntDeep"))
            return new OntDeep();
        else if(name.equals("OntDeepBasic"))
            return new OntDeepBasic();
        else if(name.equals("OntDetail"))
            return new OntDetail();
        else if (name.equals("OntDoubleMeasure"))
            return new OntDoubleMeasure(param);
        else
        {
            org.apache.commons.logging.LogFactory.getLog(this.getClass()).error("Simil Function not found");
            return null;
        }
    }

    public int getK()
    {
        return 1;
    }

    public static void main (String[] argv) {

        Application app = new Application();

        try {
            app.configure();

            app.preCycle();

            CBRQuery q = new CBRQuery();

            Description d = new Description();
            d.setIndividualPlan(80.0);
            d.setReceiptQuantity(2.5);
            d.setReceiptSum(2400.0);
            d.setExperience(new Instance("ExperienceLarge"));
            q.setDescription(d);

            app.cycle(q);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (OntologyAccessException e) {
            e.printStackTrace();
        }
    }
}
