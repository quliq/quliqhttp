package com.quliq.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;

public class QuliqHttpClientMethods {

    public static final Logger LOGGER = LoggerFactory.getLogger(QuliqHttpClientMethods.class);

    private QuliqHttpClientMethods() {
    }

    public static QuliqHttpClient get(String url) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

            HttpGet httpGet = new HttpGet(url);

            HttpResponse httpResponse = httpclient.execute(httpGet);

            int status = httpResponse.getStatusLine().getStatusCode();
            String response = EntityUtils.toString(httpResponse.getEntity()).replaceAll("\\s+", "");
            LOGGER.info("Url: {}, Status: {}, Response: {}", url, status, response);

            return new QuliqHttpClient(status, response);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Url: {}, Status: Failed", url);
            return new QuliqHttpClient(500, "Internal Server Error");
        }
    }

    public static QuliqHttpClient postJson(String url, String body, Map<String, String> headers) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

            HttpPost httpPost = new HttpPost(url);

            HttpEntity stringEntity = new StringEntity(body, ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);

            String headersLog = "";
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
                headersLog = Arrays.toString(httpPost.getAllHeaders());
            }

            HttpResponse httpResponse = httpclient.execute(httpPost);

            int status = httpResponse.getStatusLine().getStatusCode();
            String response = EntityUtils.toString(httpResponse.getEntity()).replaceAll("\\s+", "");
            LOGGER.info("Url: {}, Status: {}, Headers: {}, Body: {}, Response: {}", url, status,
                    headersLog, body, response);

            return new QuliqHttpClient(status, response);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Url: {}, Status: Failed, Body: {}", url, body);
            return new QuliqHttpClient(500, "Internal Server Error");
        }
    }

}
