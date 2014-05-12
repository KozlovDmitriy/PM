
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
     * Метод получения значения описания проблемы.
     * @return Значение описания проблемы.
     */
    public String getDescription() {
        return description;
    }
    
}
