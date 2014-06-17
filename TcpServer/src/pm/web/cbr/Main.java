package pm.web.cbr;

import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.ExecutionException;

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
//
//        // Формирование тестовых запросов.
////        List<DescriptionQuery> data = Lists.newArrayList(
////                new DescriptionQuery(3065.65f, 3.47f, 0, 111.33f, 0f),
////                new DescriptionQuery(1835.79f, 3.2f, 0, 77.65f, 0f),
////                new DescriptionQuery(2201.45f, 3.62f, 0,49.85f, 0f),
////                new DescriptionQuery(2387.03f, 2.49f, 0, 81.54f, 0f),
////                new DescriptionQuery(1727.67f, 2.97f, 0, 61.23f, 0f),
////                new DescriptionQuery(1412.97f, 1.97f, 0, 52.91f, 0f),
////                new DescriptionQuery(3326.18f, 3.73f, 0, 98.14f, 0f),
////                new DescriptionQuery(1718.43f, 2.5f, 0, 41.29f, 0f),
////                new DescriptionQuery(2962.95f, 3.7f, 0, 60.24f, 0f),
////                new DescriptionQuery(3553.60f, 3.78f, 0, 48.4f, 0f),
////                new DescriptionQuery(1961.75f, 2.42f, 0, 55.32f, 0),
////                new DescriptionQuery(2956.61f, 4.8f, 0, 102.9f, 0),
////                new DescriptionQuery(2273.37f, 3f,0, 74.03f, 0),
////                new DescriptionQuery(2954.23f, 3.8f, 0, 56.4f, 0),
////                new DescriptionQuery(1203.37f, 2.7f, 0, 48.62f, 0),
////                new DescriptionQuery(1606.38f, 3f, 0, 127.23f, 0),
////                new DescriptionQuery(2330.54f, 3.7f, 0, 55.49f, 0),
////                new DescriptionQuery(1866.15f, 3.3f, 0, 74.19f, 0),
////                new DescriptionQuery(2106.32f, 3.1f, 0, 55.82f, 0),
////                new DescriptionQuery(1783.61f, 2.8f, 0, 57.69f, 0)
////            );
//        List<DescriptionQuery> data = Lists.newArrayList(
//                new DescriptionQuery(3065.65f, 3.47f, 111.33f, "", "", "Leadership_qualities_high"),
//                new DescriptionQuery(1835.79f, 3.2f, 77.65f, "", "", "Leadership_qualities_middle"),
//                new DescriptionQuery(2201.45f, 3.62f, 49.85f, "Expierence_low", "Dynamics_negative", "Leadership_qualities_middle"),
//                new DescriptionQuery(2387.03f, 2.49f, 81.54f, "Expierence_high", "", "Leadership_qualities_high"),
//                new DescriptionQuery(1727.67f, 2.97f, 61.23f, "", "", "Leadership_qualities_middle"),
//                new DescriptionQuery(1412.97f, 1.97f, 52.91f, "Expierence_low", "", "Leadership_qualities_low"),
//                new DescriptionQuery(3326.18f, 3.73f, 98.14f, "", "", "Leadership_qualities_high"),
//                new DescriptionQuery(1718.43f, 2.5f, 41.29f, "Expierence_low", "", "Leadership_qualities_low"),
//                new DescriptionQuery(2962.95f, 3.7f, 60.24f, "", "", "Leadership_qualities_middle"),
//                new DescriptionQuery(3553.60f, 3.78f, 48.4f, "", "", "Leadership_qualities_middle"),
//                new DescriptionQuery(1961.75f, 2.42f, 55.32f, "", "", "Leadership_qualities_middle"),
//                new DescriptionQuery(2956.61f, 4.8f, 102.9f, "", "", "Leadership_qualities_high"),
//                new DescriptionQuery(2273.37f, 3f, 74.03f, "", "", "Leadership_qualities_middle"),
//                new DescriptionQuery(2954.23f, 3.8f, 56.4f, "", "", "Leadership_qualities_middle"),
//                new DescriptionQuery(1203.37f, 2.7f, 48.62f, "", "", "Leadership_qualities_middle"),
//                new DescriptionQuery(1606.38f, 3f, 127.23f, "", "", "Leadership_qualities_high"),
//                new DescriptionQuery(2330.54f, 3.7f, 55.49f, "", "", "Leadership_qualities_high"),
//                new DescriptionQuery(1866.15f, 3.3f, 74.19f, "", "", "Leadership_qualities_middle"),
//                new DescriptionQuery(2106.32f, 3.1f, 55.82f, "", "", "Leadership_qualities_middle"),
//                new DescriptionQuery(1783.61f, 2.8f, 57.69f, "", "", "Leadership_qualities_middle")
//            );
//
//        List<String[]> dataResult = Lists.newArrayList(
//                new String[]{"-","A.d F"},
//                new String[]{"A", "A.c F"},
//                new String[]{"A B", "A.k A.l"},
//                new String[]{"F", "J"},
//                new String[]{"A", "A.f A.c A.a"},
//                new String[]{"A K", "A.f C.a C.f C.c F"},
//                new String[]{"-", "A.m A.h A.d"},
//                new String[]{"-", "A.d A.m"},
//                new String[]{"-", "A.c A.m"},
//                new String[]{"-", "A.d A.m"},
//                new String[]{"F", "A.j"},
//                new String[]{"-", "A.f A.g C.a A.b"},
//                new String[]{"K", "A.f A.e"},
//                new String[]{"B", "A.b A.m A.e"},
//                new String[]{"K", "A.c A.e"},
//                new String[]{"B C", "C.m G A.e"},
//                new String[]{"B C", "A.m G A.e"}
//        );
//
//        List<ExperimentResult> results = Lists.newArrayList();
//
//        int index = 0;
//        for (DescriptionQuery query : data) {
//            Solution result = cbr(query);
//
////            results.add(new ExperimentResult(result.getProbelmsString(), dataResult.get(index)[0],
////                    result.getSolutionsString(), dataResult.get(index)[1], eval));
//            index++;
//        }
//
//        Velocity.init();
//
//        VelocityContext context = new VelocityContext();
//
//        context.put("experiment_label", "Проведение эксперимента Эксперимент 5: McSherryLessIsBetter");
//        context.put("table_head_1", "Полученное значение проблемы");
//        context.put("table_head_2", "Значение проблемы из отчета");
//        context.put("table_head_3", "Полученное значение рекомендации");
//        context.put("table_head_4", "Значение рекомендации из отчета");
//        context.put("results", results);
//
//        Template template = null;
//
//        template = Velocity.getTemplate("out.vm");
//
//        StringWriter writer = new StringWriter();
//
//        template.merge(context, writer);
//
//        Writer out = null;
//        try {
//            out = new BufferedWriter(new OutputStreamWriter(
//                    new FileOutputStream("out.html"), "UTF-8"));
//            out.write(writer.toString());
//            out.close();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
