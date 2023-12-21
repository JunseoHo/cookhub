package main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(40055)) {
            System.out.println("Server socket open.");
            while (true) {
                System.out.println("Server socket is waiting...");
                Socket socket = serverSocket.accept();
                System.out.println("Request is accepted.");
                new Thread(() -> handleRequest(socket)).start();
            }
        } catch (IOException e) {
            System.err.println("Server socket failed.");
        }
    }

    private static String readFile(String path) {
        File file = new File(path);
        if (!file.exists()) return null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String content = "";
            String line;
            while ((line = reader.readLine()) != null) content += line + "\n";
            return content;
        } catch (IOException e) {
            return null;
        }
    }

    private static void handleRequest(Socket socket) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            HTTPResponse httpResponse = new HTTPResponse();
            httpResponse.httpVersion = "HTTP/1.1";
            httpResponse.statusCode = 200;
            httpResponse.reasonPhrase = "OK";
            httpResponse.contentType = "text/html";
            httpResponse.responseBody = readFile("server/resources/welcome.html");
            System.out.println(httpResponse);
            byte[] responseBytes = httpResponse.toString().getBytes(StandardCharsets.UTF_8);
            outputStream.write(responseBytes);
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Socket failed.");
        }
    }

}
