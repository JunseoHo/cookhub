package main;

/**
 * request message
 * 1. start-line
 * 2. header
 * 3. CRLF
 * 4. body
 */
public class HTTPRequest {
    //start-line
    public String method = "";
    public String target = "";
    public String httpVersion = "";
    public String hostInfo = "";
    //header
    public String headers = "";
    //message body
    public String requestBody = "";

    @Override
    public String toString() {
        String httpRequest = "";
        // start line
        httpRequest += method + " " + target + " " + httpVersion + "\n";
        httpRequest += hostInfo + "\n";
        // header
        httpRequest += "Headers: " + headers + "\n";
        httpRequest += "\n";
        // body
        httpRequest += requestBody;
        return httpRequest;
    }
}
