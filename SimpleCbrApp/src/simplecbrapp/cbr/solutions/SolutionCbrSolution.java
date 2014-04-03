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

    /**
     * Конструктор с параметрами.
     * @param mainConcept Значение главного концепта.
     * @param solution Значение рекомендации.
     */
    public SolutionCbrSolution(Instance mainConcept, Instance solution) {
        this.mainConcept = mainConcept;
        this.solution = solution;
    }

    /**
     * Конструктор по умолчанию.
     * Инициализирует все поля в null.
     */
    public SolutionCbrSolution() {
        this.mainConcept = null;
        this.solution = null;
    }

    @Override
    public Attribute getIdAttribute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
