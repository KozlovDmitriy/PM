package pm.web.cbr;

import com.google.gson.Gson;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.datatypes.Instance;

/**
 * Класс описания запроса к CBR.
 * @author Mikhail Navrotskiy
 * @version 1.0
 */
public class DescriptionQuery implements CaseComponent {

    /* Поля класса. */
    /** Значение среднего чека. */
    private Float avCheck;
    /** Количество позиций в чеке. */
    private Float itemsCount;
    /** Количество чеков. */
    private Integer checksCount;
    /** Выполнение плана в % */
    private Float impl;
    /** Выполнение плана в рублях. */
    private Float implPlan;
    /** Больничный. */
    private Instance sickList;
    /** Отпуск. */
    private Instance vacation;
    /** Главный концепт. */
    private Instance mainConcept;

    /**
     * Конструктор по умолчанию.
     */
    public  DescriptionQuery() {}

    /**
     * Конструктор класса с параметрами.
     * @param avCheck Значение среднего чека.
     * @param itemsCount Количество позиций в чеке.
     * @param checksCount Количество чеков.
     * @param impl Выполнение плана в %.
     * @param implPlan Выполнение плана в рублях.
     * @param sickList Больничный
     * @param vacation Отпуск.
     */
    public DescriptionQuery (float avCheck, float itemsCount, int checksCount, float impl, float implPlan,
                             Instance sickList, Instance vacation) {
        this.avCheck = avCheck;
        this.itemsCount = itemsCount;
        this.checksCount = checksCount;
        this.impl = impl;
        this.implPlan = implPlan;
        this.sickList = sickList;
        this.vacation = vacation;
        this.mainConcept = new Instance();
    }

    /**
     * Метод преобразования объекта класса в строку.
     * @return JSON строка.
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    /**
     * Метод получения значения количества чеков.
     * @return Значение количества чеков.
     */
    public Integer getChecksCount() {
        return checksCount;
    }

    /**
     * Метод получения значения отпуска.
     * @return Значение отпуска.
     */
    public Instance getVacation() {
        return vacation;
    }

    /**
     * Метод получения значения больничного.
     * @return Значение больничного.
     */
    public Instance getSickList() {
        return sickList;
    }

    /**
     * Метод получения значения главного концепта.
     * @return Значение главного концепта.
     */
    public Instance getMainConcept() {
        return mainConcept;
    }

    /**
     * Метод получения значения количества позиций в чеке.
     * @return Значение количества позиций в чеке.
     */
    public Float getItemsCount() {
        return itemsCount;
    }

    /**
     * Метод получения значения выполнения плана в руб.
     * @return Значение выполнения плана в руб.
     */
    public Float getImplPlan() {
        return implPlan;
    }

    /**
     * Метод получения значения выполнения плана в %.
     * @return Значение выполнения плана в %.
     */
    public Float getImpl() {
        return impl;
    }

    /**
     * Метод получения значения среднего чека.
     * @return Значение среднего чека.
     */
    public Float getAvCheck() {
        return avCheck;
    }

    /**
     * Метод изменения значения отпуска.
     * @param vacation Новое значение отпуска.
     */
    public void setVacation(Instance vacation) {
        this.vacation = vacation;
    }

    /**
     * Метод изменения значения больничного.
     * @param sickList Новое значение больничного.
     */
    public void setSickList(Instance sickList) {
        this.sickList = sickList;
    }

    /**
     * Метод изменения значения главного концепта.
     * @param mainConcept Новое значение главного концепта.
     */
    public void setMainConcept(Instance mainConcept) {
        this.mainConcept = mainConcept;
    }

    /**
     * Метод изменения значения количества позиций в чеке.
     * @param itemsCount Новое значение количества позиций в чеке.
     */
    public void setItemsCount(Float itemsCount) {
        this.itemsCount = itemsCount;
    }

    /**
     * Метод изменения значения выполнения плана в руб.
     * @param implPlan Новое значение выполнения плана в руб.
     */
    public void setImplPlan(Float implPlan) {
        this.implPlan = implPlan;
    }

    /**
     * Метод изменения значения выполнения плана в %.
     * @param impl Новое значение выполнения плана в %.
     */
    public void setImpl(Float impl) {
        this.impl = impl;
    }

    /**
     * Метод изменения значения количества чеков.
     * @param checksCount Новое значение количества чеков.
     */
    public void setChecksCount(Integer checksCount) {
        this.checksCount = checksCount;
    }

    /**
     * Метод изменения значения среднего чека.
     * @param avCheck Новое значение среднего чека.
     */
    public void setAvCheck(Float avCheck) {
        this.avCheck = avCheck;
    }

    @Override
    public Attribute getIdAttribute() {
        return new Attribute("mainConcept", this.getClass());
    }
}
