package simplecbrapp.cbr.problems;

import com.google.gson.Gson;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.datatypes.Instance;

/**
 * Класс описания прецедента при поиске проблем.
 * @author M. Navrotskiy
 * @version 1.0
 */
public class ProblemCbrDescription implements CaseComponent {
    
    /* Поля класса. */
    /** Выполнение плана. */
    private Instance implPlan;
    /** Значение среднего чека. */
    private Instance avCheck;
    /** Количество позиций в чеке. */
    private Instance itemsCount;
    /** Общее количество чеков. */
    private Instance totalChecksCount;
    /** Проблема в работе персонала. */
    private Instance problem;
    /** Главный концепт. */
    private Instance mainConcept;

    /**
     * Метод преобразования объекта класса в строку.
     * @return Строка в формате json.
     */
    @Override
    public String toString() {
        String json = (new Gson()).toJson(this);
        return json;
    }

    /**
     * Конструктор с параметрами.
     * Инициализирует все поля класса.
     * @param implPlan Выполнение плана.
     * @param avCheck Средний чек.
     * @param itemsCount Количество позиций в чеке.
     * @param totalChecksCount Общее число чеков.
     * @param problem Проблема.
     * @param mainConcept Главный концепт.
     */
    public ProblemCbrDescription(Instance implPlan, Instance avCheck, 
            Instance itemsCount, Instance totalChecksCount, Instance problem, 
            Instance mainConcept) {
        this.implPlan = implPlan;
        this.avCheck = avCheck;
        this.itemsCount = itemsCount;
        this.totalChecksCount = totalChecksCount;
        this.problem = problem;
        this.mainConcept = mainConcept;
    }

    /**
     * Конструктор по умолчанию.
     * Инициализирует все поля как null.
     */
    public ProblemCbrDescription() {
        
        this.avCheck = null;
        this.implPlan = null;
        this.itemsCount = null;
        this.mainConcept = null;
        this.problem = null;
        this.totalChecksCount = null;
    }

    /**
     * Метод изменения значения главного концепта.
     * @param mainConcept Значение главного концепта.
     */
    public void setMainConcept(Instance mainConcept) {
        this.mainConcept = mainConcept;
    }

    /**
     * Метод получения значения главного концепта.
     * @return Значение главного концепта.
     */
    public Instance getMainConcept() {
        return mainConcept;
    }

    /**
     * Метод изменения общего количества чеков.
     * @param totalChecksCount Значение общего количества чеков.
     */
    public void setTotalChecksCount(Instance totalChecksCount) {
        this.totalChecksCount = totalChecksCount;
    }

    /**
     * Метод изменения значения проблемы, 
     * которая возникает в работе консультанта.
     * @param problem Значение проблемы, 
     * которая возникает в работе консультанта.
     */
    public void setProblem(Instance problem) {
        this.problem = problem;
    }

    /**
     * Метод измения значения количества позиций в чеке.
     * @param itemsCount Значение количества позиций в чеке.
     */
    public void setItemsCount(Instance itemsCount) {
        this.itemsCount = itemsCount;
    }

    /**
     * Метод изменения значения выполнения плана.
     * @param implPlan Значение выполненения плана.
     */
    public void setImplPlan(Instance implPlan) {
        this.implPlan = implPlan;
    }

    /**
     * Метод изменения значения среднего чека.
     * @param avCheck Значение среднего чека.
     */
    public void setAvCheck(Instance avCheck) {
        this.avCheck = avCheck;
    }

    /**
     * Метод получения значения общего количества чеков.
     * @return Значение общего количества чеков.
     */
    public Instance getTotalChecksCount() {
        return totalChecksCount;
    }

    /**
     * Метод получения проблемы, которая возникла в работе консультанта.
     * @return Значение проблемы в работе консультанта.
     */
    public Instance getProblem() {
        return problem;
    }

    /**
     * Метод получения значения количества позиций в чеке.
     * @return Значение количества позиций в чеке.
     */
    public Instance getItemsCount() {
        return itemsCount;
    }

    /**
     * Метод получения значения выполнения индивидуального плана.
     * @return Значение выполнения индивидуального плана.
     */
    public Instance getImplPlan() {
        return implPlan;
    }

    /**
     * Метод получения значения среднего чека.
     * @return Значение среднего чека.
     */
    public Instance getAvCheck() {
        return avCheck;
    }

    @Override
    public Attribute getIdAttribute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
