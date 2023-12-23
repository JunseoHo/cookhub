package main.http;

public class HttpResponse extends HttpTransaction {

    private String version = null;
    private Integer statusCode = null;
    private String reasonPhrase = null;

    public HttpResponse(String version, Integer statusCode, String reasonPhrase) {
        this.version = version;
        this.statusCode = statusCode;
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
