package pm.web.cbr;

/**
 * Класс, описывающий результат эксперимента.
 */
public class ExperimentResult {

        /* Поля класса. */
    /** Значение проблемы из CBR. */
    private String cbrProblem;
    /** Значение проблемы из отчета. */
    private String realProblem;
    /** Значение рекомендации из CBR. */
    private String cbrSolution;
    /** Значение рекомендации из отчета. */
    private String realSolution;

    /**
     * Конструктор с параметрами.
     * @param cbrProblem Новое значение проблемы из CBR.
     * @param realProblem Новое значение проблемы из отчета.
     * @param cbrSolution Новое значение рекомендации из CBR.
     * @param realSolution Новое значение рекомендации из отчета.
     */
    ExperimentResult(String cbrProblem, String realProblem, String cbrSolution, String realSolution) {
        this.cbrProblem = cbrProblem;
        this.realProblem = realProblem;
        this.cbrSolution = cbrSolution;
        this.realSolution = realSolution;
    }

    /**
     * Метод получения значения проблемы из CBR.
     * @return Значение проблемы из CBR.
     */
    public String getCbrProblem() {
        return cbrProblem;
    }

    /**
     * Метод изменения значения проблемы из CBR.
     * @param cbrProblem Новое значение проблемы из CBR.
     */
    public void setCbrProblem(String cbrProblem) {
        this.cbrProblem = cbrProblem;
    }

    /**
     * Метод получения значения проблемы из отчета.
     * @return Значение проблемы из отчета.
     */
    public String getRealProblem() {
        return realProblem;
    }

    /**
     * Метод изменения значения проблемы из отчета.
     * @param realProblem Новое значение проблемы из отчета.
     */
    public void setRealProblem(String realProblem) {
        this.realProblem = realProblem;
    }

    /**
     * Метод получения значения рекомендации из CBR.
     * @return Значение рекомендации из CBR.
     */
    public String getCbrSolution() {
        return cbrSolution;
    }

    /**
     * Метод изменения значения рекомендации из CBR.
     * @param cbrSolution Новое значение рекомендации из CBR.
     */
    public void setCbrSolution(String cbrSolution) {
        this.cbrSolution = cbrSolution;
    }

    /**
     * Метод получения значения рекомендации из отчета.
     * @return Значение рекомендации из отчета.
     */
    public String getRealSolution() {
        return realSolution;
    }

    /**
     * Метод изменения значения рекомендации из отчета.
     * @param realSolution Новое значение рекомендации из отчета.
     */
    public void setRealSolution(String realSolution) {
        this.realSolution = realSolution;
    }
}