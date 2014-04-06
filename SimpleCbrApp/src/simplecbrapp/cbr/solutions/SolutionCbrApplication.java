package simplecbrapp.cbr.solutions;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.connector.OntologyConnector;
import jcolibri.datatypes.Instance;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.selection.SelectCases;
import jcolibri.util.FileIO;

/**
 * Класс CBR приложения для поиска рекомендаций.
 * @author M. Navrotskiy
 * @version 1.0
 */
public class SolutionCbrApplication implements StandardCBRApplication {

    /* Поля класса. */
    /** Запрос в базу прецедентов. */
    private CBRQuery query;
    /** Результат работы CBR. */
    private Collection<RetrievalResult> eval;
    /** База прецедентов. */
    private CBRCaseBase caseBase;
    /** Множество близкий прецедентов. */
    private Collection<CBRCase> selectedCase;
    /** Соединение с онтологией. */
    private OntologyConnector connector;
    /** Объект класса. */
    private static SolutionCbrApplication app;
    /** Ближайший прецедент. */
    private CBRCase result;

    /**
     * Конструктор по умолчанию.
     * Закрытый конструктор.
     */
    private SolutionCbrApplication() {}
    
    /**
     * Метод создания экземпляра класса - CBR приложения.
     * @return Экземпляр класса.
     */
    public static SolutionCbrApplication getInstance() {
        if (app == null) {
            app = new SolutionCbrApplication();
        }
        
        return app;
    }
    
    /** 
     * Метод получения результата CBR цикла.
     * @return Значение результата CBR.
     */
    public CBRCase getResult() {
        return this.result;
    }
    
    /**
     * Метод конфигуриривания решателя.
     * @throws ExecutionException 
     */
    @Override
    public void configure() throws ExecutionException {
        
        this.connector = new OntologyConnector();
        this.connector.initFromXMLfile(FileIO.findFile("solution_cbr_config.xml"));
        this.caseBase = new LinealCaseBase();
    }

    /**
     * Метод выполнения действий перед CBR-циклом.
     * @return База прецедентов.
     * @throws ExecutionException 
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
     * Метод формирования структуры case.
     * @return Конфигурация case.
     */
    private static NNConfig getSimilarityConfig() {
        
        NNConfig result = new NNConfig();
        Attribute attribute;
        LocalSimilarityFunction function = new Equal();
        
        // 1) problem
        attribute = new Attribute("problem", SolutionCbrDescription.class);
        result.addMapping(attribute, function);
        result.setWeight(attribute, 1.0);
        
        // 2) solution
        attribute = new Attribute("solution", SolutionCbrDescription.class);
        result.addMapping(attribute, function);
        result.setWeight(attribute, 1.0);
        
        return result;
    }
    
    /**
     * Статический метод проведения анализа.
     * @param args Значение проблемы.
     */
    public static void doAnalise (String args) {
        
        try {
            if (args == null || args.equals("")) throw new Exception("Invalid value of request!");
            
            SolutionCbrApplication app = SolutionCbrApplication.getInstance();
            
            app.configure();
            app.preCycle();
            
            SolutionCbrDescription description = 
                    new SolutionCbrDescription(new Instance(args), null, null);
            CBRQuery query = new CBRQuery();
            query.setDescription(description);
            
            app.cycle(query);
            CBRCase result = app.getResult();
            
            SolutionCbrSolution solution = (SolutionCbrSolution) result.getDescription();
            
//            SolutionCbrApplication.writeResultToFile(solution);
            
        } catch (Exception ex) {
            Logger.getLogger(SolutionCbrApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Метод реализации CBR-цикла.
     * @param query Запрос к CBR.
     * @throws ExecutionException 
     */
    @Override
    public void cycle(CBRQuery query) throws ExecutionException {
        
        NNConfig conf = SolutionCbrApplication.getSimilarityConfig();
        conf.setDescriptionSimFunction(new Average());
        
        this.query = query;
        
        this.eval = NNScoringMethod.evaluateSimilarity(this.caseBase.getCases(),
                this.query, conf);
        this.selectedCase = SelectCases.selectTopK(this.eval, 1);
        
        System.out.println("==================================");
        for (CBRCase item : this.selectedCase) {
            System.out.println(item);
            System.out.println(
                    ((RetrievalResult)this.eval.toArray()[0]).getEval());
            this.result = item;
        }
    }

    /**
     * Метод выполнения действий после CBR цикла.
     * @throws ExecutionException 
     */
    @Override
    public void postCycle() throws ExecutionException {}
    
}
