package com.quliq.http;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class QuliqHttpClientTest {

    @Test
    public void givenParam_whenGet_thenSuccess() {
        // given - precondition or setup
        String url = "https://jsonplaceholder.typicode.com/todos/1";

        // when - action or the behaviour that we are going to test
        QuliqHttpClient get = QuliqHttpClient.builder()
                .get(url)
                .build();

        // then - verify the output
        assertThat(get.getStatusCode()).isEqualTo(200);
        assertThat(get.getResponseBody()).isNotEmpty();
    }

    @Test
    public void givenParam_whenPost_thenSuccess() {
        // given - precondition or setup
        String url = "https://jsonplaceholder.typicode.com/todos";

        // when - action or the behaviour that we are going to test
        QuliqHttpClient post = QuliqHttpClient.builder()
                .postJson(url)
                .body("{ \"title\": \"demo-1\", \"completed\": false }")
                .build();

        // then - verify the output
        assertThat(post.getStatusCode()).isEqualTo(201);
    }

    @Test
    public void givenParam_whenPostWithHeader_thenSuccess() {
        // given - precondition or setup
        String url = "https://jsonplaceholder.typicode.com/todos";
        Map<String, String> headers = new HashMap<>();
        headers.put("test-key", "key-value");
        headers.put("test-sign", "sign-value");

        // when - action or the behaviour that we are going to test
        QuliqHttpClient post = QuliqHttpClient.builder()
                .postJson(url)
                .body("{ \"title\": \"demo-2\", \"completed\": false }")
                .headers(headers)
                .build();

        // then - verify the output
        assertThat(post.getStatusCode()).isEqualTo(201);
    }
}
