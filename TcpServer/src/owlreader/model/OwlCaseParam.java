
package owlreader.model;

import com.google.gson.Gson;

/**
 * Класс параметра case в owl.
 * @author M. Navrotskiy
 * @version 1.0
 */
public class OwlCaseParam {
    
    /* Поля класса. */
    /** URI параметра в онтологии. */
    private String uri;
    /** Значение параметра в онтологии. */
    private String value;

    /**
     * Метод преобразования объекта класса в JSON строку.
     * @return Объект класса в виде JSON строки.
     */
    @Override
    public String toString() {
        return (new Gson()).toJson(this);
    }

    /**
     * Метод изменения значения параметра в онтологии.
     * @param value Новое значение параметра в онтологии.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Метод изменения значения URI параметра в онтологии.
     * @param uri Новое значение URI параметра в онтологии.
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Метод получения значения параметра в онтологии.
     * @return Значение параметра в онтологии.
     */
    public String getValue() {
        return value;
    }

    /**
     * Метод получения значения URI параметра в онтологии.
     * @return Значение URI параметра в онтологии.
     */
    public String getUri() {
        return uri;
    }

    /**
     * Конструктор по умолчанию.
     */
    public OwlCaseParam() {
        this.uri = "";
        this.value = "";
    }

    /**
     * Конструктор с параметрами.
     * @param uri Значение URI параметра в онтологии.
     * @param value Значение параметра в онтологии.
     */
    public OwlCaseParam(String uri, String value) {
        this.uri = uri;
        this.value = value;
    }
    
}
