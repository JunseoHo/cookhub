package main.http;

public class HttpRequest {

    // start-line
    public HttpRequestMethod httpRequestMethod;
    public String requestTarget;
    public String httpVersion;
    // header
    // ...
    // body
    public String requestBody;

    public HttpRequest(String httpRequest) {
        /* PARSE */
    }
}
