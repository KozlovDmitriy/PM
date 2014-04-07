package owlreader;

import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import jcolibri.connector.OntologyConnector;
import jcolibri.exception.InitializingException;
import jcolibri.util.FileIO;
import jcolibri.util.OntoBridgeSingleton;
import owlreader.model.OwlCaseParam;

/**
 * Главный класс приложения.
 * @author M. Navrotskiy
 * @version 1.0
 */
public class OwlReader {
    
    /**
     * Функция чтения выполнения плана из онтологии.
     * @return Список всех объектов класса.
     */
    private static ArrayList getImplPlan() {
        try {
            OntologyConnector connector = new OntologyConnector();
            connector.initFromXMLfile(FileIO.findFile("configurate.xml"));
            String[] result = null;
            
            ArrayList list = new ArrayList();
            OntoBridge bridge = OntoBridgeSingleton.getOntoBridge();
            Iterator it = bridge.listInstances("ImplPlan");
            
            while (it.hasNext()) {
                
                String item = it.next().toString();
                
                Iterator jt = bridge.listPropertyValue(item, "implPlanHasValue");
                
                while (jt.hasNext()) {
                    
                    String string = jt.next().toString();
                    
                    if (string.contains("^")) {
                        string = string.substring(0, string.indexOf('^'));
                    } else if (string.contains("@")) {
                        string = string.substring(0, string.indexOf("@"));
                    }
                    
                    OwlCaseParam param = new OwlCaseParam(item, string);
                    list.add(param);
                }
            }
            
            return list;
        } catch (InitializingException ex) {
            Logger.getLogger(OwlReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    /**
     * Главная функция приложения
     * @param args Аргументы коммандной строки.
     */
    public static void main(String[] args) {
        
        OwlReader.getImplPlan(); // Чтение выполнения плана.
    }
    
}
