package main.http;

public class HttpRequest extends HttpTransaction {

    private HttpRequestMethod method;
    private String target;
    private String version;

    public HttpRequest(String httpRequest) {
        int lineNumber = 0;
        String[] lines = httpRequest.split("\n");
        String[] startLines = lines[lineNumber++].split(" ");
        method = HttpRequestMethod.valueOf(startLines[0]);
        target = startLines[1];
        version = startLines[2];
        while (lineNumber < lines.length) {
            if (lines[lineNumber].equals("\n") || lines[lineNumber].isBlank()) break;
            String[] headers = lines[lineNumber].split(":");
            setHeader(headers[0], headers[1]);
            ++lineNumber;
        }
        while (++lineNumber < lines.length) {
            if (getBody() == null) setBody(lines[lineNumber]);
            else setBody(getBody() + "\n" + lines[lineNumber]);
        }
    }

    public HttpRequestMethod getMethod() {
        return method;
    }

    public void setMethod(HttpRequestMethod method) {
        this.method = method;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return method.toString() + ' ' +
                target + ' ' +
                version + '\n' +
                super.toString();
    }
}
