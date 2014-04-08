package owlreader;

import com.google.gson.Gson;
import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import java.io.BufferedWriter;
import java.io.IOException;
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
     * Файл чтения параметра из онтологии.
     * @param className Имя класса в онтологии.
     * @param propertyName Имя свойства класса, которое хранит значения.
     * @return Список всех объектов класса.
     */
    public static ArrayList getParamByName (String className, String propertyName) {
        try {
            OntologyConnector connector = new OntologyConnector();
            connector.initFromXMLfile(FileIO.findFile("configurate.xml"));
            String[] result = null;        
            ArrayList list = new ArrayList();
            OntoBridge bridge = OntoBridgeSingleton.getOntoBridge();
            Iterator it = bridge.listInstances(className);
            
            while (it.hasNext()) {
                
                String item = it.next().toString();                
                Iterator jt = bridge.listPropertyValue(item, propertyName);
                
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
     * Метод записи в файл.
     * @param list Список элементов класса онтологии.
     * @param out Поток вывода.
     */
    public static void toFile (ArrayList list, BufferedWriter out) {
        
        try {
            Gson gson = new Gson();
            String str = gson.toJson(list);
            
            out.write(str);
            out.newLine();
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(OwlReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
