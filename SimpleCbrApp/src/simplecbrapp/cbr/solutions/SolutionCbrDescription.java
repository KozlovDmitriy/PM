package simplecbrapp.cbr.solutions;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.datatypes.Instance;

/**
 * Класс описания прецедента при поиске рекомендаций.
 * @author M. Navrotskiy
 * @version 1.0
 */
public class SolutionCbrDescription implements CaseComponent {
    
    /* Поля класса. */
    /** Проблема в работе персонала. */
    private Instance problem;
    /** Рекомендация по устранению проблемы. */
    private Instance solution;
    /** Главный концепт. */
    private Instance mainConcept;

    @Override
    public Attribute getIdAttribute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
