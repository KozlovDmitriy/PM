/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tcpserver;

import java.io.IOException;
import java.net.ServerSocket;
import tcpserver.config.ReasonerPort;

/**
 * Главный класс приложения.
 * @author M. Navrotskiy
 * @version 1.0
 */
public class TcpServer {

    /**
     * Главная функция приложения.
     * @param args Аргументы коммандной строки.
     */
    public static void main(String[] args) {
        
        try {
            ReasonerPort port = ReasonerPort.fromJson("config/reasoner.config");
            ServerSocket socket = new ServerSocket(Integer.parseInt(port.getValue()));
            
            while (true) {                
                new HttpConnect(socket.accept());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
}
