package simplecbrapp.cbr.solutions;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.datatypes.Instance;

/**
 * Класс описания решения при поиске рекомендаций.
 * @author M. Navrotskiy.
 * @version 1.0
 */
public class SolutionCbrSolution implements CaseComponent {
    
    /* Поля класса. */
    /** Концепт. */
    private Instance mainConcept;
    /** Рекомендация. */
    private Instance solution;

    @Override
    public Attribute getIdAttribute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
