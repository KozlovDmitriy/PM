package pm.web.cbr;

import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.datatypes.Instance;
import jcolibri.exception.ExecutionException;
import jcolibri.exception.OntologyAccessException;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Главный класс приложения.
 * @author Mikhail Navrotskiy
 * @version 1.0
 */
public class Main {

    private static void cbr() {

        Application app = new Application();

        try {
            app.configure();
            app.preCycle();

            DescriptionQuery query = new DescriptionQuery(1.0f, 1.0f, 1, 1.0f, 1.0f,
                    new Instance("Sick-list_NO"), new Instance("Vacation_NO"));

            CBRQuery q = new CBRQuery();
            q.setDescription(query);

            app.cycle(q);

            CBRCase result = app.getResult();
            Solution solution = (Solution) result.getSolution();
            System.out.println(solution.toString());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (OntologyAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Главная функция приложения.
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {

        cbr();

//        Velocity.init();
//
//        VelocityContext context = new VelocityContext();
//
//        context.put("name", "Velocity");
//
//        Template template = null;
//
//        template = Velocity.getTemplate("out.vm");
//
//        StringWriter writer = new StringWriter();
//
//        template.merge(context, writer);
//
//        try {
//            BufferedWriter out = new BufferedWriter(new FileWriter("out.html"));
//            out.write(writer.toString());
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
