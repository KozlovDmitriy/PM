
package ontology.model;

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
