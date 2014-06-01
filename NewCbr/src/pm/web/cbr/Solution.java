package pm.web.cbr;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.datatypes.Instance;

/**
 * Класс части решения прецедента (case solution).
 * @author Mikhail Navrotskiy
 * @version 1.0
 */
public class Solution implements CaseComponent {

    /* Поля класса. */
    /** Главный концепт. */
    private Instance mainConcept;
    /** Множество проблем. */
    private Instance problems;
    /** Множество рекомендаций. */
    private Instance solutions;

    @Override
    public Attribute getIdAttribute() {
        return null;
    }
}
