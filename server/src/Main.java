package main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main {


    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(40056)) {
            Logger.info("CookHub server is running. (Port : " + serverSocket.getLocalPort() + ")");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> handleRequest(socket)).start();
            }
        } catch (IOException e) {
            Logger.fatal(e.getMessage());
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
            byte[] responseBytes = httpResponse.toString().getBytes(StandardCharsets.UTF_8);
            outputStream.write(responseBytes);
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            Logger.error(e.getMessage());
        }
    }

}
