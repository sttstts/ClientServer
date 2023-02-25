import Tiron.Phone;

import java.io.*;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args)  {

        try (ServerSocket server = new ServerSocket (8000))
        {
            System.out.println("Server started!");

            while (true)
                try (Phone phone = new Phone(server)) {

                    new Thread(() -> {
                        String request = phone.readLine();
                        System.out.println("Request: " + request);
                        String responce = (int) (Math.random() * 30 - 10) + "";
                        try { Thread.sleep(4000); } catch (InterruptedException e) {}
                        phone.writeLine(responce);
                    }).start();

                }  catch (NullPointerException e) {
                        e.printStackTrace();
                }
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
