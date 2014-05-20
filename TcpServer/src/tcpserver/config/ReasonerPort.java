
package tcpserver.config;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс хранения настроек порта TCP сервера.
 * @author M.Navrotskiy
 * @version 1.0
 */
public class ReasonerPort {
    
    /* Поля класса.*/
    /** Идентификатор. */
    private int id;
    /** Ключ. */
    private String key;
    /** Значение. */
    private String value;

    /**
     * Конструктор с параметрами.
     * @param id Идентификатор.
     * @param key Ключ настройки.
     * @param value Значение настройки.
     */
    public ReasonerPort(int id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    /**
     * Метод изменения значения.
     * @param value Значение из базы.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Метод изменения значения ключа.
     * @param key Значение ключа.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Метод изменения идентификатора.
     * @param id Значение идентификатора.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Метод получения значения.
     * @return Значение в базе.
     */
    public String getValue() {
        return value;
    }

    /**
     * Метод получения ключа.
     * @return Значение ключа.
     */
    public String getKey() {
        return key;
    }

    /**
     * Метод получения идентификатора.
     * @return Идентификатор.
     */
    public int getId() {
        return id;
    }
    
    /**
     * Метод получения значения из файла.
     * @param filename Имя файла.
     * @return Объект класса.
     */
    public static ReasonerPort fromJson (String filename) {
        
        try {
            Gson g = new Gson();
            return g.fromJson(new FileReader(filename), ReasonerPort.class);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReasonerPort.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
