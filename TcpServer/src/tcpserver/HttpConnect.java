
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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.ExecutionException;
import owlreader.OwlReader;
import pm.web.cbr.Application;
import pm.web.cbr.DescriptionQuery;
import simplecbrapp.cbr.problems.ProblemCbrApplication;
import simplecbrapp.cbr.solutions.SolutionCbrApplication;
import tcpserver.json.Interface;
import tcpserver.json.Params;
import tcpserver.json.Problems;
import tcpserver.json.Solutions;

/**
 * Класс создания сокетного соединения.
 * @author M.Navrotskiy
 * @version 1.0
 * @deprecated см. пакет tcpserver.json
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
    /** Запрос на получение всех значений выполнения индивидуального плана. */
    public static final int IMPL_PLAN = 0x10;
    /** Запрос на получение всех значений среднего чека. */
    public static final int AV_CHECK = 0x11;
    /** Запрос на получение всех значений количества позиций в чеке. */
    public static final int ITEMS_COUNT = 0x12;
    /** Запрос на получение общего числа чеков. */
    public static final int TOTAL_CHECKS_COUNT = 0x13;
    /** Запрос на создание новой проблемы. */
    public static final int CREATE_NEW_PROBLEM = 0x14;
    /** Запрос на создание новой рекомендации. */
    public static final int CREATE_NEW_SOLUTION = 0x15;
    
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
     * Метод чтения параметра из онтологии.
     * @param className Имя класса параметра.
     * @param propertyName Свойство значения параметра.
     * @param pw Канал записи.
     */
    public void getParam (String className, String propertyName, PrintWriter pw) {
        
        this.log("read param " + className + " strart");
        OwlReader.toFile(OwlReader.getParamByName(className, propertyName), new BufferedWriter(pw));
        this.log("read param " + className + " end");
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
     * Функция создания новой проблемы.
     * @param br Чтение потока rails.
     * @throws IOException 
     */
    private void createNewProblem (BufferedReader br) throws IOException {
        
        String type = br.readLine();
        String description = br.readLine();
        this.log("create new problem");
    }
    
    /**
     * Функция чтения новой рекомендации.
     * @param br Чтение потока rails.
     * @throws IOException 
     */
    private void createNewSolution(BufferedReader br) throws IOException {
        
        String type = br.readLine();
        String description = br.readLine();
        this.log("create new solution");
    }
    
    /**
     * Метод чтения запросов клиента.
     * @param request Запрос клиента.
     * @param br Канал чтения.
     * @param pw Канал ответа.
     */
    private void parseClientRequest
        (String request, BufferedReader br, PrintWriter pw) throws IOException {
        try {
            System.out.println(request);

            Interface i = Interface.fromJson(request);

            if (i.getCode().equals(Integer.toString(HttpConnect.PARAMS_BLOCK_START))) {
                // Чтение параметров и отправка ответа.
    //            this.readParams(br, pw);
                this.findProblems(i.getParams(), pw);
            } else if (i.getCode().equals(Integer.toString(HttpConnect.PROBLEM_BLOCK_START))) {
                this.findSolutions(i.getProblems(), pw);
            } else if (request.equals(Integer.toString(HttpConnect.IMPL_PLAN))) {
                this.getParam("ImplPlan", "ImplPlan", pw);
            } else if (request.equals(Integer.toString(HttpConnect.AV_CHECK))) {
                this.getParam("AvCheck", "avCheckHasValue", pw);
            } else if (request.equals(Integer.toString(HttpConnect.ITEMS_COUNT))) {
                this.getParam("ItemsCount", "itemsCountHasValue", pw);
            } else if (request.equals(Integer.toString(HttpConnect.TOTAL_CHECKS_COUNT))) {
                this.getParam("TotalChecksCount", "totalChecksCountHasValue", pw);
            } else if (request.equals(Integer.toString(HttpConnect.CREATE_NEW_PROBLEM))) {
                this.createNewProblem(br);
            } else if (request.equals(Integer.toString(HttpConnect.CREATE_NEW_SOLUTION))) {
                this.createNewSolution(br);
            }
        } catch (Exception ex) {
            System.out.println("req: " + request);
            System.out.println("exc: " + ex.getMessage());
        }
    }
        
    private void findSolutions (Problems problems, PrintWriter pw) {
        // Анализ
        String[] ss = SolutionCbrApplication.doAnalise(problems.getUri());
        // Отправка результата
        this.sendFindedSolutions(pw, ss);
    }
    
    private void sendFindedSolutions (PrintWriter pw, String[] ps) {
        
        String[] probs = new String[ps.length - 1];
        System.arraycopy(ps, 1, probs, 0, ps.length - 1);
        Solutions solutions = new Solutions(ps[0], probs);
        Interface i = new Interface();
        i.setCode(Integer.toString(RESPONSE_SOLUTION_BLOCK_START));
        i.setSolutions(solutions);
        pw.println(i.toString());
    }
        
    /**
     * Метод чтения проблем от клиента.
     * @param br Канал чтения.
     * @param pw Канал записи.
     * @throws IOException 
     */
    @Deprecated
    private void readProblems (BufferedReader br, PrintWriter pw) throws IOException {
        String value;
        ArrayList problems = new ArrayList();
        this.log("start read problems");
        do {
            value = br.readLine();
            problems.add(value);
            this.log("read value " + value);
        } while (!value.equals(Integer.toString(HttpConnect.PROBLEM_BLOCK_END)));
        this.log("end read problems");
        // Анализ
        String[] args = new String[problems.size()];
        String[] ss = SolutionCbrApplication.doAnalise(((String[]) problems.toArray(args))[0]);
        // Отправка ответа.
        this.sendSolutions(pw, ss);
    }
    
    /**
     * Метод чтения параметров анализа.
     * @param br Канал чтения.
     * @param pw Канал записи.
     * @throws IOException Исключение ошибки чтения канала.
     * @deprecated см. пакет tcpserver.json
     */
    @Deprecated
    private void readParams(BufferedReader br, PrintWriter pw) throws IOException {
        String value;
        ArrayList params = new ArrayList();
        this.log("start read params");
        do {
            value = br.readLine();
            params.add(value);
            this.log("read param value is " + value);
        } while (!value.equals(Integer.toString(HttpConnect.PARAMS_BLOCK_END)));
        this.log("end read params");
        // Анализ
        String[] args = new String[params.size()];
        String[] ps = ProblemCbrApplication.doAnalise((String[]) params.toArray(args));
        // Отправка ответа.
        this.sendProblems(pw, ps);
    }
    
    /**
     * Метод поиска проблем.
     * @param params Параметры анализа.
     * @param pw Писатель в клиента.
     */
    private void findProblems (Params params, PrintWriter pw) {
        try {
            // Анализ
            DescriptionQuery dq = new DescriptionQuery(Float.parseFloat(params.getAvCheck()),
                    Float.parseFloat(params.getItemsCount()), Float.parseFloat(params.getImplPlan()));
            Application app = new Application();
            
            app.configure();
            
            app.preCycle();
            
            CBRQuery q = new CBRQuery();
            q.setDescription(dq);
            
            app.cycle(q);
            
            CBRCase result = app.getResult();
            
            pm.web.cbr.Solution sol = (pm.web.cbr.Solution) result.getSolution();
            String[] ps = new String[1];
//        String[] ps = ProblemCbrApplication.doAnalise(params.toArray());
            // Отправка ответа.
//        this.sendFindedProblems(pw, ps);
        } catch (ExecutionException ex) {
            Logger.getLogger(HttpConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Отправка найденных проблем.
     * @param pw Писатель в клиент.
     * @param ps Массив проблем.
     */
    private void sendFindedProblems (PrintWriter pw, String[] ps) {
        
        String[] probs = new String[ps.length - 1];
        System.arraycopy(ps, 1, probs, 0, ps.length - 1);
        Problems problems = new Problems(ps[0], probs);
        Interface i = new Interface();
        i.setCode(Integer.toString(RESPONSE_PROBLEM_BLOCK_START));
        i.setProblems(problems);
        pw.println(i.toString());
    }
    
    /**
     * Метод отсылки проблем клиенту.
     * @param pw Канал ответа клиенту.
     * @param ps Массив проблем после анализа.
     */
    @Deprecated
    private void sendProblems(PrintWriter pw, String[] ps) throws IOException {
        String[] problems = ps;
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
     * Метод отсыкли рекомендаций клиенту.
     * @param pw Канал ответа клиенту.
     * @param solutions Массив рекомендаций после анализа.
     */
    @Deprecated
    private void sendSolutions (PrintWriter pw, String[] solutions) {
        
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
                    new OutputStreamWriter(this.socket.getOutputStream(), "UTF8"), true);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(this.socket.getInputStream(), Charset.forName("UTF-8")));
            String req; // Строка запроса.
            Interface i;
            
            do { // Чтение запросов клиента.
                req = br.readLine();
                this.log(req);
                i = Interface.fromJson(req);
                this.parseClientRequest(req, br, pw); // Парсинг команды.
            } while (!i.getCode().equals(Integer.toString(HttpConnect.END_CONNECTION)));
            
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }
       
}
