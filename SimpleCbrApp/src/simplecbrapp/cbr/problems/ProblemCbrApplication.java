package simplecbrapp.cbr.problems;

import java.util.Collection;
import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.connector.OntologyConnector;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.util.FileIO;

/**
 * Класс cbr приложения для поиска проблем.
 * @author M. Navrotskiy
 * @version 1.0
 */
public class ProblemCbrApplication implements StandardCBRApplication {
    
    /* Поля класса. */
    /** Запрос в базу прецедентов. */
    private CBRQuery query;
    /** Результат работы CBR. */
    private Collection<RetrievalResult> eval;
    /** База прецедентов. */
    private CBRCaseBase caseBase;
    /** Множество близких прецедентов. */
    private Collection<CBRCase> selectedCase;
    /** Соединение с онтологией. */
    private OntologyConnector connector;
    /** Объект класса. */
    private static ProblemCbrApplication app;
    /** Близкий прецедент. */
    private CBRCase result;

    /**
     * Конструктор по умолчанию.
     * Закрытый конструктор.
     */
    private ProblemCbrApplication() {
    }
    
    /**
     * Метод создания экземпляра класса - CBR приложения.
     * @return Экземпляр класса.
     */
    public static ProblemCbrApplication getInstance() {
        if (app == null)
            app = new ProblemCbrApplication();
        
        return app;
    }

    /**
     * Метод получения результата cbr цикла.
     * @return Значение результата CBR.
     */
    public CBRCase getResult() {
        return result;
    }

    /**
     * Метод конфигурирования решателя.
     */
    @Override
    public void configure() throws ExecutionException {
        
        this.connector = new OntologyConnector();
        
        this.connector.initFromXMLfile(FileIO.findFile("configurate.xml"));
        
        this.caseBase = new LinealCaseBase();
    }

    /**
     * Метод выполнения действий перед CBR циклом.
     * @return База прецедентов.
     */
    @Override
    public CBRCaseBase preCycle() throws ExecutionException {
        
        this.caseBase.init(this.connector);
        
        Collection<CBRCase> cases = this.caseBase.getCases();
        
        for (CBRCase item : cases) {
            System.out.println(item);
        }
        
        return this.caseBase;
    }
    
    /**
     * Метод формирования case, весов и мер близости.
     * @return Конфигурация case.
     */
    private static NNConfig getSimilarityConfig() {
        
        NNConfig result = new NNConfig();
        Attribute attribute;
        LocalSimilarityFunction function = new Equal();
        
        // 1) implPlan
        attribute = new Attribute("implPlan", ProblemCbrDescription.class);
        result.addMapping(attribute, function);
        result.setWeight(attribute, new Double(1.0));
        
        // 2) avCheck
        attribute = new Attribute("avCheck", ProblemCbrDescription.class);
        result.addMapping(attribute, function);
        result.setWeight(attribute, new Double(1.0));
        
        // 3) itemsCount
        attribute = new Attribute("itemsCount", ProblemCbrDescription.class);
        result.addMapping(attribute, function);
        result.setWeight(attribute, new Double(1.0));
        
        // 4) totalChecksCount
        attribute = new Attribute("totalChecksCount",
                ProblemCbrDescription.class);
        result.addMapping(attribute, function);
        result.setWeight(attribute, new Double(1.0));
        
        // 5) problem
        attribute = new Attribute("problem", ProblemCbrDescription.class);
        result.addMapping(attribute, function);
        result.setWeight(attribute, new Double(1.0));
        
        return result;
    }

    @Override
    public void cycle(CBRQuery query) throws ExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Метод выполнения действий после CBR цикла.
     */
    @Override
    public void postCycle() throws ExecutionException {}
    
}
