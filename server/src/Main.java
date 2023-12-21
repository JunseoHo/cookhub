package main;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main {


    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server socket open.");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> handleRequest(socket)).start();
            }
        } catch (IOException e) {
            System.err.println("Server socket failed.");
        }
    }

    private static void handleRequest(Socket socket) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            String response = "HTTP/1.1 200 OK\r\nContent-Type: text/hi\r\n\r\nHello, World!";
            byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);
            outputStream.write(responseBytes);
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Socket failed.");
        }
    }

}
