package tcpserver.json;

import com.google.gson.Gson;

/**
 * Класс json интерфейса обмена с ruby клиентом.
 * @author M. Navrotskiy
 * @version 1.0
 */
public class Interface {
    
    /* Поля класса. */
    /** Код запроса. */
    private String code;
    /** Тело запроса. */
    private String body;
    
    /**
     * Конструктор создания объекта из json строки.
     * @param json JSON строка.
     */
    public Interface (String json) {
        new Gson().fromJson(json, this.getClass());
    }

    /**
     * Конструктор с параметрами.
     * @param code Код запроса.
     * @param body Тело запроса.
     */
    public Interface(String code, String body) {
        this.code = code;
        this.body = body;
    }

    /**
     * Метод изменения кода запроса.
     * @param code Код запроса.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Метод изменения тела запроса.
     * @param body Тело запроса.
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Метод преобразования в json строку.
     * @return json строка.
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    /**
     * Метод полученния кода запроса.
     * @return Код запроса в виде строки.
     */
    public String getCode() {
        return code;
    }

    /**
     * Метод получения тела запроса.
     * @return Тело запроса в виде строки.
     */
    public String getBody() {
        return body;
    }
    
}
