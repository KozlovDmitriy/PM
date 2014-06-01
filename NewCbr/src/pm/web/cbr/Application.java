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

import java.util.Collection;
import java.util.Iterator;

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

        OntoBridge ob = OntoBridgeSingleton.getOntoBridge();
        for (CBRCase cbrCase : this.caseBase.getCases()) {
            Iterator<String> caseInstances = ob.listInstances("Case");
            while (caseInstances.hasNext()) {
                String instance = caseInstances.next();
                String baseItem = ((Solution)cbrCase.getSolution()).getMainConcept().toString();
                if (instance.equals("http://www.owl-ontologies.com/pm-web.owl#" + baseItem)) {
                    Iterator<String> it = ob.listInstanceProperties(instance);
                    while (it.hasNext()) {
                        String prop = it.next();
                        if (prop.equals("http://www.owl-ontologies.com/pm-web.owl#index_problems")) {
                            Iterator<String> i = ob.listPropertyValue(instance, prop);
                            while (i.hasNext()) {
                                String val = i.next();
                                try {
                                    ((Solution)cbrCase.getSolution()).setProblems(new Instance(val));
                                } catch (OntologyAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else if (prop.equals("http://www.owl-ontologies.com/pm-web.owl#solution_rec")) {
                            Iterator<String> i = ob.listPropertyValue(instance, prop);
                            while (i.hasNext()) {
                                String val = i.next();
                                try {
                                    ((Solution)cbrCase.getSolution()).setSolutions(new Instance(val));
                                } catch (OntologyAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }

        return this.caseBase;
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

        this.eval = NNScoringMethod.evaluateSimilarity(this.caseBase.getCases(), this.query, config);

        this.selectedCase = SelectCases.selectTopK(this.eval, 1);

        for (CBRCase item : this.selectedCase) {
            this.result = item;
        }
    }

    /**
     * Метод действий, выполняемых после цикла.
     * @throws ExecutionException
     */
    @Override
    public void postCycle() throws ExecutionException {}
}
