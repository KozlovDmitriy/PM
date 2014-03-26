
package tcpserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * Класс создания сокетного соединения.
 * @author M.Navrotskiy
 * @version 1.0
 */
public class HttpConnect extends Thread {
    
    /* Поля класса. */
    /** Сокет. */
    private Socket socket;

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
            String req = br.readLine();
            System.out.println("Request: " + req);
            StringTokenizer st = new StringTokenizer(req);
            if ((st.countTokens() >= 2) && st.nextToken().equals("POST")) {
                if ((req = st.nextToken()).endsWith("/") || req.equals("")) {
                    req += "build.xml";
                }
                try {
                    File f = new File(req);
                    BufferedReader bfr = new BufferedReader(new FileReader(f));
                    char[] data = new char[(int)f.length()];
                    bfr.read(data);
                    pw.println("HTTP/1.1 200 OK\n");
                    pw.write(data);
                    pw.flush();
                } catch (FileNotFoundException ex) {
                    pw.println("HTTP/1.1 404 Not Found\n");
                } catch (IOException e) {
                    System.out.println(e);
                }
            } else {
                pw.println("HTTP/1.1 400 Bad Request\n");
                this.socket.close();
            }
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }
       
}
