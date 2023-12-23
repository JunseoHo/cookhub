package main.http;

import java.util.Map;

public class HttpTransaction {

    private Map<String, String> headers;
    private String body;

    public String getHeader(String key) {
        return headers.get(key);
    }

    public void setHeader(String key, String value) {
        headers.put(key, value);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        StringBuilder httpTransaction = new StringBuilder("");
        for (String key : headers.keySet())
            httpTransaction.append(key).append(": ").append(headers.get(key)).append('\n');
        httpTransaction.append('\n').append(body);
        return httpTransaction.toString();
    }

}
