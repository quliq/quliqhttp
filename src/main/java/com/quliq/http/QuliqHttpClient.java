package com.quliq.http;

import java.util.Map;

public class QuliqHttpClient {
    // request url
    private String get;
    private String postJson;

    // request body
    private String body;

    // request headers
    private Map<String, String> headers;

    // response
    private Integer statusCode;
    private String responseBody;

    @Deprecated
    QuliqHttpClient() {
    }

    public QuliqHttpClient(String get, String postJson, String body, Map<String, String> headers) {
        this.get = get;
        this.postJson = postJson;
        this.body = body;
        this.headers = headers;
    }

    public QuliqHttpClient(String get, String postJson, Integer statusCode, String responseBody, String body, Map<String, String> headers) {
        this.get = get;
        this.postJson = postJson;
        this.statusCode = statusCode;
        this.responseBody = responseBody;
        this.body = body;
        this.headers = headers;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public static QuliqHttpClientBuilder builder() {
        return new QuliqHttpClientBuilder();
    }

    // Store after hit API
    public QuliqHttpClient(Integer statusCode, String responseBody) {
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

}
