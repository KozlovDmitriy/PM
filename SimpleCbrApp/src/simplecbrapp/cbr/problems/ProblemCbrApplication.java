package simplecbrapp.cbr.problems;

import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
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
import jcolibri.exception.OntologyAccessException;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.selection.SelectCases;
import jcolibri.util.FileIO;
import jcolibri.util.OntoBridgeSingleton;
import simplecbrapp.SimpleCbrApp;

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
    private ProblemCbrApplication() {}
    
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
        result.setWeight(attribute, 1.0);
        
        // 2) avCheck
        attribute = new Attribute("avCheck", ProblemCbrDescription.class);
        result.addMapping(attribute, function);
        result.setWeight(attribute, 1.0);
        
        // 3) itemsCount
        attribute = new Attribute("itemsCount", ProblemCbrDescription.class);
        result.addMapping(attribute, function);
        result.setWeight(attribute, 1.0);
        
        // 4) totalChecksCount
        attribute = new Attribute("totalChecksCount",
                ProblemCbrDescription.class);
        result.addMapping(attribute, function);
        result.setWeight(attribute, 1.0);
        
        // 5) problem
        attribute = new Attribute("problem", ProblemCbrDescription.class);
        result.addMapping(attribute, function);
        result.setWeight(attribute, 1.0);
        
        return result;
    }
    
    /**
     * Статический метод проведения анализа.
     * @param args Значение параметров персонала.
     */
    public static void doAnalise (String[] args) {
        
        try {
            String result = "";
            
            if (args.length < 4) throw new Exception("Invalid size of input array!");
            
            ProblemCbrApplication app = ProblemCbrApplication.getInstance();
            app.configure();
            app.preCycle();
            
            ProblemCbrDescription description = 
                    new ProblemCbrDescription(
                            new Instance(args[0]),
                            new Instance(args[1]), 
                            new Instance(args[2]), 
                            new Instance(args[3]));
            
            CBRQuery query = new CBRQuery();
            query.setDescription(description);
            app.cycle(query);
            CBRCase c = app.getResult();
            ProblemCbrSolution solution = (ProblemCbrSolution) c.getSolution();
            
            ProblemCbrApplication.writeResultToFile(solution);
            
        } catch (ExecutionException | OntologyAccessException ex) {
            Logger.getLogger(SimpleCbrApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SimpleCbrApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Метод записи результатов CBR в файл.
     * @param solution Результат CBR.
     */
    public static void writeResultToFile (ProblemCbrSolution solution) {
        
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("result.txt"));
            for (String item : ProblemCbrApplication.getProblemsText(solution.getProblem().toString())) {
                out.write(item);
                out.newLine();
                out.flush();
            }
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ProblemCbrApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Метод преобразования объекта результата CBR в массив проблем.
     * @param uri URI результата в онтологии.
     * @return Массив строк - проблемы.
     */
    public static String[] getProblemsText (String uri) {
        String[] result = null;
        
        ArrayList list = new ArrayList();
        OntoBridge bridge = OntoBridgeSingleton.getOntoBridge();
        Iterator it = bridge.listPropertyValue(uri, "simpleProblemHasText");
        
        while (it.hasNext()) {
            
            String item = it.next().toString();
            
            if (item.contains("^")) {
                item = item.substring(0, item.indexOf('^'));
            } else if (item.contains("@")) {
                item = item.substring(0, item.indexOf("@"));
            }
            
            list.addAll(Arrays.asList(item.split("#")));
        }
        
        result = new String[list.size()];
        return result = (String[]) list.toArray(result);
    }

    /**
     * Метод реализации CBR цикла.
     * @param query Запрос к онтологии.
     */
    @Override
    public void cycle(CBRQuery query) throws ExecutionException {
        
        NNConfig conf = ProblemCbrApplication.getSimilarityConfig();
        conf.setDescriptionSimFunction(new Average());
        
        this.query = query;
        
        this.eval = NNScoringMethod.evaluateSimilarity(this.caseBase.getCases(), 
                this.query, conf);
        
        this.selectedCase = SelectCases.selectTopK(this.eval, 1);
        
        System.out.println("==================================");
        for (CBRCase c : this.selectedCase) {
            System.out.println(c);
            System.out.println(((RetrievalResult)this.eval.toArray()[0]).getEval());
            this.result = c;
        }
    }

    /**
     * Метод выполнения действий после CBR цикла.
     */
    @Override
    public void postCycle() throws ExecutionException {}
    
}
