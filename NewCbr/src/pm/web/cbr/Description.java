package pm.web.cbr;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.datatypes.Instance;

/**
 * Класс описания прецедента (case index).
 * @author Mikhail Navrotskiy
 * @version 1.0
 */
public class Description implements CaseComponent {

    /* Поля класса. */
    /** Значение среднего чека. */
    private Instance avCheck;
    /** Количество позиций в чеке. */
    private Instance itemsCount;
    /** Количество чеков. */
    private Instance checksCount;
    /** Выполнение плана в % */
    private Instance impl;
    /** Выполнение плана в рублях. */
    private Instance implPlan;
    /** Больничный. */
    private Instance sickList;
    /** Отпуск. */
    private Instance vacation;
    /** Главный концепт. */
    private Instance mainConcept;

    /**
     * Метод получения значения среднего чека.
     * @return Значение среднего чека.
     */
    public Instance getAvCheck() {
        return avCheck;
    }

    /**
     * Метод получения значения количества чеков.
     * @return Значение количества чеков.
     */
    public Instance getChecksCount() {
        return checksCount;
    }

    /**
     * Метод получения значения выполнения плана в %.
     * @return Значение выполнения плана в %.
     */
    public Instance getImpl() {
        return impl;
    }

    /**
     * Метод получения значения выполнения плана в руб.
     * @return Значение выполнения плана в руб.
     */
    public Instance getImplPlan() {
        return implPlan;
    }

    /**
     * Метод получения значения количества позиций в чеке.
     * @return Значение количества позиций в чеке.
     */
    public Instance getItemsCount() {
        return itemsCount;
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
    public void setItemsCount(Instance itemsCount) {
        this.itemsCount = itemsCount;
    }

    /**
     * Метод изменения значения выполнения плана в руб.
     * @param implPlan Новое значение выполнения плана в руб.
     */
    public void setImplPlan(Instance implPlan) {
        this.implPlan = implPlan;
    }

    /**
     * Метод изменения значения выполнения плана в %.
     * @param impl Новое значение выполнения плана в %.
     */
    public void setImpl(Instance impl) {
        this.impl = impl;
    }

    /**
     * Метод измения значения количества чеков.
     * @param checksCount Новое значение количества чеков.
     */
    public void setChecksCount(Instance checksCount) {
        this.checksCount = checksCount;
    }

    /**
     * Метод изменения значения среднего чека.
     * @param avCheck Новое значение среднего чека.
     */
    public void setAvCheck(Instance avCheck) {
        this.avCheck = avCheck;
    }

    /**
     * Метод получения значения больничного.
     * @return Значение больничного.
     */
    public Instance getSickList() {
        return sickList;
    }

    /**
     * Метод получения значения отпуска.
     * @return Значение отпуска.
     */
    public Instance getVacation() {
        return vacation;
    }

    /**
     * Метод получения значения главного концепта.
     * @return Значение главного концепта.
     */
    public Instance getMainConcept() {
        return mainConcept;
    }

    @Override
    public Attribute getIdAttribute() {
        return new Attribute("mainConcept", this.getClass());
    }
}
