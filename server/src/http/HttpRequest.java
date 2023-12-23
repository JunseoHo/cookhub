package main.http;

public class HttpRequest extends HttpTransaction {

    public HttpRequestMethod method;
    public String target;
    public String version;

    public HttpRequest(String httpRequest) {
        /* PARSE */
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
