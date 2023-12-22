package main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            Logger.info("CookHub server is running. (Port : " + serverSocket.getLocalPort() + ")");
            while (true) {
                Socket socket = serverSocket.accept();
                Logger.info("Request accepted.");
                new Thread(()->handleRequest(socket)).start();
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

            // 입력 스트림을 통해 클라이언트의 요청 읽기
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            // HTTP 요청 헤더 읽기
            StringBuilder requestStringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                requestStringBuilder.append(line).append("\n");
            }

            // 요청 정보 출력
            System.out.println("Received HTTP Request:\n" + requestStringBuilder.toString());

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
