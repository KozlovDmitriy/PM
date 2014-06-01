package pm.web.cbr;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class Main {

    public static void main(String[] args) {

        Velocity.init();

        VelocityContext context = new VelocityContext();

        context.put("name", "Velocity");

        Template template = null;

        template = Velocity.getTemplate("out.vm");

        StringWriter writer = new StringWriter();

        template.merge(context, writer);

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("out.html"));
            out.write(writer.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
