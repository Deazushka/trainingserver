package by.training.echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        while (true) {
            Socket socket = server.accept();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try (InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                         BufferedReader reader = new BufferedReader(isr)) {
                        String line = reader.readLine();
                        Message message = new Message(line);
                        String name = message.getName();
                        Thread.sleep(1000);
                        String answer = "Hello, " + name;
                        socket.getOutputStream().write(answer.getBytes("UTF-8"));
                    } catch (Exception e) {
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread.start();
        }
    }
}
