package sample;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class Main {

    private static ServerSocket server;
    private static Server serverAcceptClient;
    public static Map<Socket,Client> clients=new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        start();
        zapuskServerAcceptClient();//сервер принимает клиента
        finish();

    }

    private static void start(){
        try {
            server=new ServerSocket(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void zapuskServerAcceptClient() throws InterruptedException {
        //принятие клиентов
       serverAcceptClient =new Server(server);
       serverAcceptClient.start();
       readClients();
    }

    private static void readClients(){
        while(true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static void finish()  {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(-1);
    }

    public static void otpravPaket(Socket client,Paket paket){
        try {
            DataOutputStream dos=new DataOutputStream(client.getOutputStream());
            paket.write(dos);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
