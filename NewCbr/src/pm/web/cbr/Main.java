package pm.web.cbr;

import com.google.common.collect.Lists;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.ExecutionException;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.*;
import java.util.List;

/**
 * Главный класс приложения.
 * @author Mikhail Navrotskiy
 * @version 1.0
 */
public class Main {

    private static double eval;

    private static Solution cbr(DescriptionQuery request) {

        Application app = new Application();

        try {
            app.configure();
            app.preCycle();

            DescriptionQuery query;
            query = request;

            CBRQuery q = new CBRQuery();
            q.setDescription(query);

            app.cycle(q);

            CBRCase result = app.getResult();
            eval = app.getEvalValue();

            return (Solution) result.getSolution();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Главная функция приложения.
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {

        // Формирование тестовых запросов.
        List<DescriptionQuery> data = Lists.newArrayList(
                new DescriptionQuery(3428.57f, 4.03f, 175, 82.84f, 600000.55f),
                new DescriptionQuery(3553.6f, 3.78f, 71, 48.4f, 252306.19f),
                new DescriptionQuery(2121.48f, 2.31f, 27, 19.78f, 57280.05f),
                new DescriptionQuery(1961.75f, 2.42f, 169, 55.32f, 331536.1f),
                new DescriptionQuery(2247.43f, 3, 15, 14.58f, 33711.45f),
                new DescriptionQuery(2286.18f, 3.01f, 256, 49.89f, 356643.64f),
                new DescriptionQuery(2408.91f, 4.06f, 173, 91.55f, 464920.56f),
                new DescriptionQuery(3104.56f, 3.88f, 200, 120.48f, 620912.51f),
                new DescriptionQuery(2609.16f, 3.85f, 157, 94.96f, 508786.21f),
                new DescriptionQuery(2248.86f, 3.58f, 233, 95.97f, 521734.93f),
                new DescriptionQuery(1762.36f, 2.76f, 169, 60.11f, 320749.32f),
                new DescriptionQuery(2284.93f, 4.32f, 149, 73.96f, 342740.08f),
                new DescriptionQuery(2387.35f, 4.3f, 74, 82.36f, 176664.18f),
                new DescriptionQuery(1910f, 2.8f, 91, 81.03f, 173809.64f),
                new DescriptionQuery(2059.88f, 3.5f, 82, 78.74f, 168910.3f),
                new DescriptionQuery(2178.03f, 3.4f, 40, 40.61f, 87121.15f),
                new DescriptionQuery(2070.28f, 2.9f, 55, 53.08f, 113865.15f));

        List<String[]> dataResult = Lists.newArrayList(
                new String[]{"-","A.d F"},
                new String[]{"A", "A.c F"},
                new String[]{"A B", "A.k A.l"},
                new String[]{"F", "J"},
                new String[]{"A", "A.f A.c A.a"},
                new String[]{"A K", "A.f C.a C.f C.c F"},
                new String[]{"-", "A.m A.h A.d"},
                new String[]{"-", "A.d A.m"},
                new String[]{"-", "A.c A.m"},
                new String[]{"-", "A.d A.m"},
                new String[]{"F", "A.j"},
                new String[]{"-", "A.f A.g C.a A.b"},
                new String[]{"K", "A.f A.e"},
                new String[]{"B", "A.b A.m A.e"},
                new String[]{"K", "A.c A.e"},
                new String[]{"B C", "C.m G A.e"},
                new String[]{"B C", "A.m G A.e"}
        );

        List<ExperimentResult> results = Lists.newArrayList();

        int index = 0;
        for (DescriptionQuery query : data) {
            Solution result = cbr(query);

            results.add(new ExperimentResult(result.getProbelmsString(), dataResult.get(index)[0],
                    result.getSolutionsString(), dataResult.get(index)[1], eval));
            index++;
        }

        Velocity.init();

        VelocityContext context = new VelocityContext();

        context.put("experiment_label", "Проведение эксперимента Эксперимент 3: InrecaLessIsBetter");
        context.put("table_head_1", "Полученное значение проблемы");
        context.put("table_head_2", "Значение проблемы из отчета");
        context.put("table_head_3", "Полученное значение рекомендации");
        context.put("table_head_4", "Значение рекомендации из отчета");
        context.put("results", results);

        Template template = null;

        template = Velocity.getTemplate("out.vm");

        StringWriter writer = new StringWriter();

        template.merge(context, writer);

        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("out.html"), "UTF-8"));
            out.write(writer.toString());
            out.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
