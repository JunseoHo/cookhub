package main.router;

import main.http.HttpRequest;
import main.http.HttpRequestMethod;
import main.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class Router {

    private final Map<HttpRequestMethod, Map<String, RequestHandler>> routingTable = new HashMap<>();

    public Router() {
        for (HttpRequestMethod method : HttpRequestMethod.values())
            routingTable.put(method, new HashMap<>());
    }

    public void register(HttpRequestMethod method, String resource, RequestHandler handler) {
        routingTable.get(method).put(resource, handler);
    }

    public HttpResponse route(HttpRequest request) {
        RequestHandler handler = routingTable.get(request.getMethod()).get(request.getTarget());
        if (handler == null) return null;
        else return handler.handle(request);
    }

}
