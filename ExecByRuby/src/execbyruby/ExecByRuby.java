package execbyruby;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Тестовое приложения для проверки запуска его из rails.
 * @author M. Navrotskiy
 * @version 1.0
 */
public class ExecByRuby {

    /**
     * @param args Аргументы конммандной строки.
     */
    public static void main(String[] args) {
        String result = "";
        if (args.length == 0) {
            result = "nothing";
        } else {
            StringBuilder builder = new StringBuilder();
            for (String s : args) {
                builder.append(s);
            }
            result = builder.toString();
        }
        
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("t.txt"));
            writer.write(result);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {System.out.println(e.getMessage());}
        }
    }
    
}
