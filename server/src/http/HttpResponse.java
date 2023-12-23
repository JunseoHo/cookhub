package main.http;

public class HttpResponse extends HttpTransaction {

    private String version = null;
    private Integer statusCode = null;
    private String reasonPhrase = null;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    @Override
    public String toString() {
        return version + ' ' +
                statusCode + ' ' +
                reasonPhrase + '\n' +
                super.toString();
    }

}
