package tcpserver.json;

import com.google.gson.Gson;

/**
 * Тело запроса на отправку рекомендаций при анализе.
 * @author M. Navrotskiy
 * @version 1.0
 */
public class Solutions {
    
    /* Поля класса. */
    /** URI ответа. */
    private String uri;
    /** Рекомендации. */
    private String[] solutions;

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
     * @param uri URI рекомендации в онтологии.
     * @param solutions Массив рекомендаций.
     */
    public Solutions(String uri, String[] solutions) {
        this.uri = uri;
        this.solutions = solutions;
    }

    /**
     * Метод изменения uri.
     * @param uri URI из онтологии.
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Метод изменения массива рекомендаций.
     * @param solutions Массив рекомендаций.
     */
    public void setSolutions(String[] solutions) {
        this.solutions = solutions;
    }

    /**
     * Метод получения uri.
     * @return URI из онтологии.
     */
    public String getUri() {
        return uri;
    }

    /**
     * Метод получения рекомендаций.
     * @return Массив рекомендаций.
     */
    public String[] getSolutions() {
        return solutions;
    }
    
}
