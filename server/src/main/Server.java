package main.main;

import main.http.HttpRequest;
import main.http.HttpRequestMethod;
import main.http.HttpResponse;
import main.router.Router;
import main.util.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {

    private Router router = new Router();

    public void run(int port) {
        router.register(HttpRequestMethod.GET, "/welcome.html", Server::service);
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
            for (byte b : socket.getInputStream().readAllBytes()) httpRequestStr.append((char) b);
            HttpRequest httpRequest = new HttpRequest(httpRequestStr.toString());
            HttpResponse httpResponse = router.route(httpRequest);
            // 1. read file
            // 2. create http response
            // 3. write response
        } catch (IOException e) {
            Logger.error(e.getMessage());
        }
    }

    private static HttpResponse service(HttpRequest request) {
        /* process */
        return null;
    }


}
