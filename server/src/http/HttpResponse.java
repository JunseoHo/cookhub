package main.http;

public class HttpResponse {

    private String httpVersion = null;
    private Integer statusCode = null;
    private String reasonPhrase = null;
    private String contentType = null;
    private String responseBody = null;

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        if (httpVersion == null || statusCode == null || reasonPhrase == null) return null;
        StringBuilder httpResponse = new StringBuilder();
        httpResponse.append(httpVersion).append(' ').append(statusCode).append(' ').append(reasonPhrase).append('\n');
        if (contentType != null) httpResponse.append("Content-Type: ").append(contentType).append('\n');
        httpResponse.append('\n');
        httpResponse.append(responseBody);
        return httpResponse.toString();
    }

}
