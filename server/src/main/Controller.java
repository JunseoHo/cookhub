package main.main;

import main.http.HttpRequest;
import main.http.HttpRequestMethod;
import main.http.HttpResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Controller {

    private static final String RESOURCE_PATH = "server/resources";
    private static final String WELCOME_FILE = "/welcome.html";

    public HttpResponse control(HttpRequest httpRequest) {
        HttpResponse httpResponse = null;
        switch (httpRequest.getMethod()) {
            case GET -> httpResponse = GET(httpRequest);
            case POST -> httpResponse = POST(httpRequest);
        }
        return httpResponse;
    }

    public HttpResponse GET(HttpRequest httpRequest) {
        if (httpRequest.getTarget().equals("/")) httpRequest.setTarget(WELCOME_FILE);
        File file = new File(RESOURCE_PATH + httpRequest.getTarget());
        if (!file.exists()) return new HttpResponse("HTTP/1.1", 404, "Not Found");
        if (!file.canRead()) return new HttpResponse("HTTP/1.1", 403, "Forbidden");
        HttpResponse httpResponse = new HttpResponse("HTTP/1.1", 200, "OK");
        String body = readFile(file);
        if (body == null) return new HttpResponse("HTTP/1.1", 500, "Internal Error");
        httpResponse.setHeader("Content-Type", "text/html");
        httpResponse.setBody(body);
        return httpResponse;
    }

    public HttpResponse POST(HttpRequest httpRequest) {
        return null;
    }

    public String readFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) builder.append(line).append('\n');
            return builder.toString();
        } catch (IOException e) {
            return null;
        }
    }


}
