package simplecbrapp.cbr.solutions;

import com.google.gson.Gson;
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

    /**
     * Метод преобразования объекта класса в строку.
     * @return Объект класса в виде json строки.
     */
    @Override
    public String toString() {
        return (new Gson()).toJson(this);
    }

    /**
     * Метод изменения значения рекомендации.
     * @param solution Новое значение рекомендации.
     */
    public void setSolution(Instance solution) {
        this.solution = solution;
    }

    /**
     * Метод изменения значения проблемы.
     * @param problem Новое значение проблемы.
     */
    public void setProblem(Instance problem) {
        this.problem = problem;
    }

    /**
     * Метод изменения значения главного концепта.
     * @param mainConcept Новое значение главного концепта.
     */
    public void setMainConcept(Instance mainConcept) {
        this.mainConcept = mainConcept;
    }

    /**
     * Метод получения значения рекомендации.
     * @return Значение рекомендации.
     */
    public Instance getSolution() {
        return solution;
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
