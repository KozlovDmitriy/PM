
package tcpserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс создания сокетного соединения.
 * @author M.Navrotskiy
 * @version 1.0
 */
public class HttpConnect extends Thread {
    
    /* Поля класса. */
    /** Сокет. */
    private Socket socket;
    
    /* Константы общения с клиентом. */
    /** Конец общения. */
    public static final int END_CONNECTION = 0x01;
    /** Начало блока параметров работы консультанта. */
    public static final int PARAMS_BLOCK_START = 0x02;
    /** Конец блока параметров работы консультанта. */
    public static final int PARAMS_BLOCK_END = 0x03;
    /** Начало блока проблем в работе консультанта. */
    public static final int PROBLEM_BLOCK_START = 0x04;
    /** Конец блока проблем в работе консультанта. */
    public static final int PROBLEM_BLOCK_END = 0x05;
    /** Начало блока ответа в виде множества проблем. */
    public static final int RESPONSE_PROBLEM_BLOCK_START = 0x06;
    /** Конец блока ответа в виде множества проблем. */
    public static final int RESPONSE_PROBLEM_BLOCK_END = 0x07;
    /** Начало блока ответа в виде множества рекомендаций. */
    public static final int RESPONSE_SOLUTION_BLOCK_START = 0x08;
    /** Конец блока ответа в виде множества рекомендаций. */
    public static final int RESPONSE_SOLUTION_BLOCK_END = 0x09;
    
    /**
     * Метод ведения лога.
     * @param message Сообщение в лог.
     */
    private void log (String message) {
        
        File file = new File("./log/java_server.log");
        
        try(PrintWriter out = new PrintWriter(new BufferedWriter
        (new FileWriter(file.getName(), true)))) {
            out.println((new Date()).toString() + ": " + message + "\n");
        } catch (IOException ex) {
            Logger.getLogger(HttpConnect.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    /**
     * Конструктор с параметром.
     * @param socket Сокет по которому организуется связь.
     */
    public HttpConnect (Socket socket) {
        
        this.socket = socket;
        this.setPriority(Thread.NORM_PRIORITY - 1);
        System.out.println("client successfully connected");
        this.start();
    }
    
    /**
     * Метод чтения запросов клиента.
     * @param request Запрос клиента.
     * @param pw Канал ответа.
     */
    private void parseClientRequest(String request, PrintWriter pw) {
        System.out.println(request);
        
        if (Integer.parseInt(request) == HttpConnect.PARAMS_BLOCK_END) {
            // Окончание получения параметров и отправка ответа.
            this.sendProblems(pw);
        } else if (Integer.parseInt(request) == HttpConnect.PROBLEM_BLOCK_END) {
            // Окончание получения проблем и отправка рекомендаций.
            this.sendSolutions(pw);
        }
    }
    
    /**
     * Метод отсылки проблем клиенту.
     * @param pw Канал ответа клиенту.
     */
    private void sendProblems(PrintWriter pw) {
        String[] problems = {"problem1", "problem2", "problem3"}; // Фейковые проблемы.
        this.log("start write problems");
        pw.println(HttpConnect.RESPONSE_PROBLEM_BLOCK_START);
        for (String item : problems) {
            pw.println(item);
            this.log("write " + item);
        }
        pw.println(HttpConnect.RESPONSE_PROBLEM_BLOCK_END);
        this.log("end write problems");
        pw.flush();
    }
    
    /**
     * Метод отсылки рекомендаций клиенту.
     * @param pw Канал ответа клиенту.
     */
    private void sendSolutions (PrintWriter pw) {
        String[] solutions = {"solution1", "solution2"}; // Фейковые рекомендации.
        this.log("start write solutions");
        pw.println(HttpConnect.RESPONSE_SOLUTION_BLOCK_START);
        for (String item : solutions) {
            pw.println(item);
            this.log("write " + item);
        }
        pw.println(HttpConnect.RESPONSE_SOLUTION_BLOCK_END);
        this.log("end write solutions");
        pw.flush();
    }
    
    @Override
    /**
     * Метод запска процесса.
     */
    public void run() {
        try {
            PrintWriter pw = new PrintWriter(
                    new OutputStreamWriter(this.socket.getOutputStream()), true);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(this.socket.getInputStream()));
            String req; // Строка запроса.
            
            do { // Чтение запросов клиента.
                req = br.readLine();
                this.log(req);
                this.parseClientRequest(req, pw); // Парсинг команды.
            } while (!req.equals(Integer.toString(HttpConnect.END_CONNECTION)));
            
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }
       
}
