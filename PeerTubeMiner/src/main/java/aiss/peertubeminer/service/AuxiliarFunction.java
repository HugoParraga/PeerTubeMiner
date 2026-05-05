package aiss.peertubeminer.service;

import org.springframework.http.HttpHeaders;

public class AuxiliarFunction {

    public static HttpHeaders getApiKeyHeader(String apiKey){
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.set("X-API-KEY", apiKey);
        return headers;
    }
}
