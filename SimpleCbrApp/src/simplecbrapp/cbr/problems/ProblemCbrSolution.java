package simplecbrapp.cbr.problems;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.datatypes.Instance;

/**
 * Класс описания решения при поиске проблем.
 * @author M. Navrotskiy
 * @version 1.0
 */
public class ProblemCbrSolution implements CaseComponent {
    
    /* Поля класса. */
    /** Концепт. */
    private Instance mainConcept;
    /** Проблема. */
    private Instance problem;

    /**
     * Метод изменения значения проблемы.
     * @param problem Новое значение проблемы.
     */
    public void setProblem(Instance problem) {
        this.problem = problem;
    }

    /**
     * Метод изменения значения главного концепта.
     * @param mainConcept Значение главного концепта.
     */
    public void setMainConcept(Instance mainConcept) {
        this.mainConcept = mainConcept;
    }

    /**
     * Метод получения значения проблемы.
     * @return Значение проблемы.
     */
    public Instance getProblem() {
        return problem;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
