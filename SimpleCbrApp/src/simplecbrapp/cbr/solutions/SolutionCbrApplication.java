package simplecbrapp.cbr.solutions;

import java.util.Collection;
import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.connector.OntologyConnector;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;
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

    @Override
    public void cycle(CBRQuery query) throws ExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postCycle() throws ExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
