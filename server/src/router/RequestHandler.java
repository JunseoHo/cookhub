package main.router;

import main.http.HttpRequest;
import main.http.HttpResponse;

public interface RequestHandler {

    HttpResponse handle(HttpRequest httpRequest);

}
