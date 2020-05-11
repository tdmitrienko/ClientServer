package sample;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Thread {
    private final Socket client;

    public Client(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            if (!readData()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean readData(){
        try {
            DataInputStream dis=new DataInputStream(client.getInputStream());//то что хочет отправить клиент
            if(dis.available()>0){//если  есть в сообщении хоть что нибудь что можно читать
                //чтение и обработка
                Paket paket=new Paket();
                paket.read(dis);
                paket.otpravitAll();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       return false;
    }

}
