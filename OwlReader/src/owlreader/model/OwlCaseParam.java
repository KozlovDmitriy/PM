
package owlreader.model;

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
