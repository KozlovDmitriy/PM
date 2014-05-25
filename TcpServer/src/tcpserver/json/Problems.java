
package tcpserver.json;

import com.google.gson.Gson;

/**
 * Тело запроса на отправку проблем при анализе.
 * @author M. Navrotskiy
 * @version 1.0
 */
public class Problems {
    
    /* Поля класса. */
    /** URI ответа. */
    private String uri;
    /** Проблемы. */
    private String[] problems;

    /**
     * Конструктор создания объекта из json строки.
     * @param json JSON строка.
     */
    public Problems (String json) {
        new Gson().fromJson(json, this.getClass());
    }

    /**
     * Метод преобрвазования объекта в json строку.
     * @return JSON строка.
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    /**
     * Конструктор с параметрами.
     * @param uri URI проблемы в онтологии.
     * @param problems Массив проблем.
     */
    public Problems (String uri, String[] problems) {
        this.uri = uri;
        this.problems = problems;
    }

    /**
     * Метод изменения uri.
     * @param uri URI из онтологии.
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Метод изменения массива проблем.
     * @param problems Массив проблем.
     */
    public void setProblems(String[] problems) {
        this.problems = problems;
    }

    /**
     * Метод получения uri.
     * @return URI из онтологии.
     */
    public String getUri() {
        return uri;
    }

    /**
     * Метод получения проблем.
     * @return Массив проблем.
     */
    public String[] getProblems() {
        return problems;
    }
}
