package simplecbrapp.cbr.solutions;

import com.google.gson.Gson;
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
     * Метод преобразования объекта в json строку.
     * @return Объект в виде json строки.
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
     * Метод получения значения главного концепта.
     * @return Значение главного концепта.
     */
    public Instance getMainConcept() {
        return mainConcept;
    }

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

    /**
     * Метод получения значения идентификатора аттрибута.
     * @return Значение идентификатора аттрибута.
     */
    @Override
    public Attribute getIdAttribute() {
        return new Attribute("mainConcept", this.getClass());
    }
    
}
