
package tcpserver;

import java.net.Socket;

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
    public HttpConnect(Socket socket) {
        
        this.socket = socket;
        this.setPriority(Thread.NORM_PRIORITY - 1);
        this.start();
    }
       
}
