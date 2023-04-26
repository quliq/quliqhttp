package com.quliq.http;

import java.util.Map;

public class QuliqHttpClientBuilder {
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

    QuliqHttpClientBuilder() {
    }

    public QuliqHttpClientBuilder get(String get) {
        this.get = get;
        return this;
    }

    public QuliqHttpClientBuilder postJson(String postJson) {
        this.postJson = postJson;
        return this;
    }

    public QuliqHttpClientBuilder body(String body) {
        this.body = body;
        return this;
    }

    public QuliqHttpClientBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public QuliqHttpClient build() {

        QuliqHttpClient result = null;

        if (get != null) {
            result = QuliqHttpClientMethods.get(get);
            return apiResponse(result);
        }

        if (postJson != null) {
            result = QuliqHttpClientMethods.postJson(postJson, body, headers);
            return apiResponse(result);
        }

        return null;
    }

    private QuliqHttpClient apiResponse(QuliqHttpClient result) {
        return new QuliqHttpClient(get, postJson, result.getStatusCode(), result.getResponseBody(), body, headers);
    }
}
