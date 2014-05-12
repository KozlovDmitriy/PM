
package ontology.model;

import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import java.util.ArrayList;
import java.util.Iterator;
import jcolibri.connector.OntologyConnector;
import jcolibri.exception.InitializingException;
import jcolibri.util.FileIO;
import jcolibri.util.OntoBridgeSingleton;

/**
 * Класс простой проблемы в онтологии.
 * @author M. Navrotskiy
 * @version 1.0
 */
public class SimpleProblem {
    
    /* Поля класса. */
    /** Описание проблемы. */
    private String description;
    /** URI проблемы в онтологии. */
    private String URI;
    /** Идентификатор проблемы в базе данных. */
    private int dbId;
    
    /**
     * Метод поиск проблемы по uri.
     * @param uri Значение uri.
     * @return Проблема.
     * @throws InitializingException 
     */
    public static SimpleProblem findByUri (String uri) throws InitializingException {
        SimpleProblem result = null;
        
        OntologyConnector connector = new OntologyConnector();
        connector.initFromXMLfile(FileIO.findFile("configurate.xml"));
        ArrayList list = new ArrayList();
        OntoBridge bridge = OntoBridgeSingleton.getOntoBridge();
        Iterator it = bridge.listInstances("SimpleProblem");
        
        while (it.hasNext()) {
            
            String uriValue = (String) it.next();
            Iterator jt = bridge.listPropertyValue(uriValue, "db_id");
            
            if (uriValue.equals(uri)) {
                String desc = bridge.listPropertyValue(uriValue, "description").next();
                return new SimpleProblem(desc, uriValue, Integer.parseInt(jt.next().toString()));
            }
        }
        
        return result;
    }
    
    /**
     * Метод поиска проблемы по идентификатору.
     * @param id Идентификатор.
     * @return Проблема.
     * @throws InitializingException 
     */
    public static SimpleProblem find (int id) throws InitializingException {
        SimpleProblem result = null;
        
        OntologyConnector connector = new OntologyConnector();
        connector.initFromXMLfile(FileIO.findFile("configurate.xml"));
        ArrayList list = new ArrayList();
        OntoBridge bridge = OntoBridgeSingleton.getOntoBridge();
        Iterator it = bridge.listInstances("SimpleProblem");
        
        while (it.hasNext()) {
            
            String uriValue = (String) it.next();
            Iterator jt = bridge.listPropertyValue(uriValue, "db_id");
            
            while (jt.hasNext()) {
                String temp = jt.next().toString();
                
                if (Integer.parseInt(temp) == id) {
                    String desc = bridge.listPropertyValue(uriValue, "description").next();
                    return new SimpleProblem(desc, uriValue, id);
                }
            }
        }
        
        return result;
    }
    
    /**
     * Метод изменения проблемы в онтологии.
     * @throws InitializingException 
     */
    public void update() throws InitializingException {
        this.remove();
        this.save();
    }
    
    /**
     * Метод удаления проблемы из онтологии.
     * @throws InitializingException 
     */
    public void remove() throws InitializingException {
        OntologyConnector connector = new OntologyConnector();
        connector.initFromXMLfile(FileIO.findFile("configurate.xml"));
        OntoBridge bridge = OntoBridgeSingleton.getOntoBridge();
        bridge.delete(this.URI);
    }
    
    /**
     * Метод сохранения экземпляра в онтологии.
     * @throws InitializingException 
     */
    public void save() throws InitializingException {
        
        OntologyConnector connector = new OntologyConnector();
        connector.initFromXMLfile(FileIO.findFile("configurate.xml"));
        OntoBridge bridge = OntoBridgeSingleton.getOntoBridge();
        bridge.createInstance("SimpleProblem", this.URI);
        bridge.createDataTypeProperty(this.URI, "db_id", Integer.toString(this.dbId), "integer");
        bridge.createDataTypeProperty(this.URI, "description", this.description, "string");
    }
    
    /**
     * Метод получения значения типа проблемы.
     * @return Тип проблемы.
     */
    public String getType () {
        return "simple";
    }

    /**
     * Конструктор с параметрами.
     * @param description Описание проблемы.
     * @param URI uri проблемы в онтологии.
     * @param dbId Идентификатор проблемы в бд.
     */
    public SimpleProblem(String description, String URI, int dbId) {
        this.description = description;
        this.URI = URI;
        this.dbId = dbId;
    }

    /**
     * Конструктор по умолчанию.
     */
    public SimpleProblem() {
        this.description = "qwe";
        this.URI = "qwe";
        this.dbId = -1;
    }

    /**
     * Метод изменения значения идентификатора проблемы в бд.
     * @param id Идентификатор проблемы в бд.
     */
    public void setId(int id) {
        this.dbId = id;
    }

    /**
     * Метод получения значения идентификатора проблемы в бд.
     * @return Значение идентификатора проблемы в онтологии.
     */
    public int getId() {
        return dbId;
    }

    /**
     * Метод изменения значения uri в онтологии.
     * @param URI Новое значение uri в онтологии.
     */
    public void setURI(String URI) {
        this.URI = URI;
    }

    /**
     * Метод изменения значения описания проблемы.
     * @param description Новое значение описания проблемы.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Метод получения URI проблемы в онтологии.
     * @return uri проблемы в онтологии.
     */
    public String getURI() {
        return URI;
    }

    /**
     * Метод получения значения описания проблемы.
     * @return Значение описания проблемы.
     */
    public String getDescription() {
        return description;
    }
    
}
