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

    @Override
    public Attribute getIdAttribute() {
        return null;
    }
}
