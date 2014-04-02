package simplecbrapp;

import java.util.logging.Level;
import java.util.logging.Logger;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.datatypes.Instance;
import jcolibri.exception.ExecutionException;
import jcolibri.exception.OntologyAccessException;
import simplecbrapp.cbr.problems.ProblemCbrApplication;
import simplecbrapp.cbr.problems.ProblemCbrDescription;
import simplecbrapp.cbr.problems.ProblemCbrSolution;

/**
 * Простое cbr приложения для построения основы взаимодействия Rails и Java.
 * @author M. Navrotskiy.
 * @version 1.0
 */
public class SimpleCbrApp {

    /**
     * Главная функция приложения.
     * @param args Аргументы коммандной строки.
     */
    public static void main(String[] args) {
        
        try {
            String result = "";
            
            ProblemCbrApplication app = ProblemCbrApplication.getInstance();
            app.configure();
            app.preCycle();
            
            ProblemCbrDescription description = 
                    new ProblemCbrDescription(
                            new Instance("ImplPlan_24"),
                            new Instance("AvCheck_58"), 
                            new Instance("ItemsCount_20"), 
                            new Instance("TotalChecksCount_112"));
            
            CBRQuery query = new CBRQuery();
            query.setDescription(description);
            app.cycle(query);
            CBRCase c = app.getResult();
            ProblemCbrSolution solution = (ProblemCbrSolution) c.getSolution();
            
        } catch (ExecutionException | OntologyAccessException ex) {
            Logger.getLogger(SimpleCbrApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
