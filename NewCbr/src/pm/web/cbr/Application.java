package pm.web.cbr;

import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.connector.OntologyConnector;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;

import java.util.Collection;

/**
 * Класс CBR приложения.
 * @author Mikhail Navrotskiy
 * @version 1.0
 */
public class Application implements StandardCBRApplication {

    /* Поля класса. */
    /** Запрос. */
    private CBRQuery query;
    /** Результат CBR цикла. */
    private Collection<RetrievalResult> eval;
    /** БД прецедентов. */
    private CBRCaseBase caseBase;
    /** Выбранные прецеденты. */
    private Collection<CBRCase> selectedCase;
    /** Коннектор с онтологией. */
    private OntologyConnector connector;
    /** Экземпляр класса. */
    private static Application instance;
    /** Значение CBR (конкретный case). */
    private CBRCase result;

    /**
     * Метод получения значения результата.
     * @return Значение результата CBR цикла.
     */
    public CBRCase getResult() {
        return this.result;
    }

    /**
     * Конструктор по умолчанию.
     */
    public Application(){}

    @Override
    public void configure() throws ExecutionException {

    }

    @Override
    public CBRCaseBase preCycle() throws ExecutionException {
        return null;
    }

    @Override
    public void cycle(CBRQuery cbrQuery) throws ExecutionException {

    }

    @Override
    public void postCycle() throws ExecutionException {

    }
}
