package main.http;

public class HttpResponse {

    public String httpVersion = "";
    public int statusCode = -1;
    public String reasonPhrase = "";
    public String contentType = "";
    public String responseBody = "";

    @Override
    public String toString() {
        String httpResponse = "";
        // start line
        httpResponse += httpVersion + " " + statusCode + " " + reasonPhrase + "\n";
        // header
        httpResponse += "Content-Type: " + contentType + "\n";
        httpResponse += "\n";
        // body
        httpResponse += responseBody;
        return httpResponse;
    }

}
