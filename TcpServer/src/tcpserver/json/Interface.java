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
    /** Параметры. */
    private Params params;
    /** Проблемы. */
    private Problems problems;
    /** Рекомендации. */
    private Solutions solutions;
    
    /**
     * Метод создания объекта из json строки.
     * @param json JSON строка.
     * @return Объект класса.
     */
    public static Interface fromJson (String json) {
        Interface result = new Gson().fromJson(json, Interface.class);
        return result;
    }

    /**
     * Метод изменения проблем.
     * @param problems Проблемы в работе консультанта.
     */
    public void setProblems(Problems problems) {
        this.problems = problems;
    }

    /**
     * Метод изменения рекомендаций.
     * @param solutions Рекомендации по устранению проблем.
     */
    public void setSolutions(Solutions solutions) {
        this.solutions = solutions;
    }

    /**
     * Метод получения проблем.
     * @return Проблемы в работе консультанта.
     */
    public Problems getProblems() {
        return problems;
    }

    /**
     * Метод получения параметров анализа.
     * @return Параметры анализа.
     */
    public Params getParams() {
        return params;
    }

    /**
     * Конструктор с параметрами.
     * @param code Код запроса.
     * @param body Тело запроса.
     */
//    public Interface(String code, Params body) {
//        this.code = code;
//        this.body = body;
//    }

    /**
     * Метод изменения кода запроса.
     * @param code Код запроса.
     */
    public void setCode(String code) {
        this.code = code;
    }

//    /**
//     * Метод изменения тела запроса.
//     * @param body Тело запроса.
//     */
//    public void setBody(Params body) {
//        this.body = body;
//    }

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

//    /**
//     * Метод получения тела запроса.
//     * @return Тело запроса в виде строки.
//     */
//    public Params getBody() {
//        return body;
//    }
    
}
