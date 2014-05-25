package tcpserver.json;

import com.google.gson.Gson;

/**
 * Тело запроса на получение проблем.
 * @author M. Navrotskiy
 * @version 1.0
 */
public class Params {
    
    /* Поля класса. */
    /** Выполнение плана. */
    private String ImplPlan;
    /** Средний чек. */
    private String AvCheck;
    /** Количество позиций в чеке. */
    private String ItemsCount;
    /** Общее количество чеков. */
    private String TotalChecksCount;
    
    /**
     * Метод преобразования в массив.
     * @return Массив значений параметров.
     */
    public String[] toArray() {
        String[] result = {this.ImplPlan,this.AvCheck,this.ItemsCount, this.TotalChecksCount};
        return result;
    }

    /**
     * Метод получения общего количества чеков.
     * @return Общее количество чеков.
     */
    public String getTotalChecksCount() {
        return TotalChecksCount;
    }

    /**
     * Метод получения значения количества позиций в чеке.
     * @return Значение количества позиций в чеке.
     */
    public String getItemsCount() {
        return ItemsCount;
    }

    /**
     * Метод получения значения выполнения плана.
     * @return Значение выполнения плана.
     */
    public String getImplPlan() {
        return ImplPlan;
    }

    /**
     * Метод получения значения среднего чека.
     * @return Значение среднего чека.
     */
    public String getAvCheck() {
        return AvCheck;
    }

    /**
     * Создание объекта из json строки.
     * @param json JSON строка.
     */
    public Params (String json) {
        new Gson().fromJson(json, this.getClass());
    }
    
}
