package pm.web.cbr;

import com.google.gson.Gson;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.datatypes.Instance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private Instance[] problemsArray;
    /** Множество рекомендаций. */
    private Instance solutions;
    private Instance[] solutionsArray;

    /**
     * Метод преобразования объекта класса в строку.
     * @return JSON строка.
     */
    @Override
    public String toString() {
        return "Solution{" +
                "solutions=" + solutions +
                ", problems=" + problems +
                ", mainConcept=" + mainConcept +
                '}';
    }

    /**
     * Конструктор по умолчанию.
     */
    public Solution() {
    }

    /**
     * Конструктор с параметрами.
     * @param problems Значение проблем.
     * @param solutions Значение рекомендаций.
     */
    public Solution(Instance problems, Instance solutions) {

        this.problems = problems;
        this.problemsArray = new Instance[]{this.problems};
        this.solutions = solutions;
        this.solutionsArray = new Instance[]{this.solutions};
    }

    /**
     * Метод получения значения главного концепта.
     * @return Значение главного концепта.
     */
    public Instance getMainConcept() {
        return mainConcept;
    }

    /**
     * Метод изменения значения главного концепта.
     * @param mainConcept Новое значение главного концепта.
     */
    public void setMainConcept(Instance mainConcept) {
        this.mainConcept = mainConcept;
    }

    /**
     * Метод получения значения проблем.
     * @return Значение проблем.
     */
    public Instance getProblems() {
        return problems;
    }

    /**
     * Метод изменения значения проблем.
     * @param problems Новое значение проблем.
     */
    public void setProblems(Instance problems) {

        List<Instance> temp = new ArrayList<Instance>();
        if (this.problemsArray != null) {Collections.addAll(temp, this.problemsArray);}

        if (!temp.contains(problems)) {temp.add(problems);}
        this.problemsArray = new Instance[temp.size()];
        temp.toArray(this.problemsArray);

        this.problems = problems;
    }

    /**
     * Метод получения значения рекомендаций.
     * @return Значение рекомендациий.
     */
    public Instance getSolutions() {
        return solutions;
    }

    /**
     * Метод проеобразования в строку проблем.
     * @return Строка проблем.
     */
    public String getProbelmsString() {
        return new Gson().toJson(this.problemsArray);
    }

    /**
     * Метод преобразования в строку рекомендаций.
     * @return Строка рекомендаций.
     */
    public String getSolutionsString() {
        return new Gson().toJson(this.solutionsArray);
    }

    /**
     * Метод изменения значения рекомендаций.
     * @param solutions Новое значение рекомендаций.
     */
    public void setSolutions(Instance solutions) {

        List<Instance> temp = new ArrayList<Instance>();
        if (this.solutionsArray != null) {
            Collections.addAll(temp, this.solutionsArray);
        }

        if (!temp.contains(solutions)) temp.add(solutions);
        this.solutionsArray = new Instance[temp.size()];
        temp.toArray(this.solutionsArray);

        this.solutions = solutions;
    }

    @Override
    public Attribute getIdAttribute() {
        return new Attribute("mainConcept", this.getClass());
    }
}
