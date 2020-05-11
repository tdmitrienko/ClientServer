package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server extends Thread{
    private ServerSocket server;

    public Server(ServerSocket server){
        this.server=server;
     }

     @Override
     public void run(){
         while (true){//вечный цикл для обработки клиентов
            //точка остановки пока к серверу ни кто не подключился
             try {
                 Socket client = server.accept();
                Client ypravClient= new Client(client);
                //запуск потока который управляет клиентом
                ypravClient.start();
                //кладем клиента в лист
                Main.clients.put(client,ypravClient);
             } catch (SocketException e) {
                 return;
             }catch (IOException ex){ex.printStackTrace();}
             try {
                 Thread.sleep(10);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
    }
}
