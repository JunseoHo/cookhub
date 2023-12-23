package main.main;

import main.http.HttpRequest;
import main.http.HttpResponse;
import main.util.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {

    private final Controller controller = new Controller();

    public void run(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Logger.info("CookHub server is running. (Port : " + serverSocket.getLocalPort() + ")");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> handleRequest(socket)).start();
            }
        } catch (IOException e) {
            Logger.fatal(e.getMessage());
        }
    }

    private void handleRequest(Socket socket) {
        try (socket) {
            StringBuilder httpRequestStr = new StringBuilder();
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) httpRequestStr.append(line).append("\n");
            HttpRequest httpRequest = new HttpRequest(httpRequestStr.toString());
            HttpResponse httpResponse = controller.control(httpRequest);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(httpResponse.toString().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
            Logger.info("Transaction : (" + httpRequest.getMethod() + ", " + httpRequest.getTarget() + ")");
        } catch (IOException e) {
            Logger.error(e.getMessage());
        }
    }


}
