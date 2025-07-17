package com.asd.logic.servicedown.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;

public class HttpClientUtil {
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();
    private static final ObjectMapper objectMapper = new ObjectMapper();



    {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static <T> T sendGetRequest(String url, TypeReference<T> responseType) throws Exception {
//        HttpGet httpGet = new HttpGet("http://");
//        HttpResponse response = (HttpResponse) httpClient.execute(httpGet);
//        HttpEntity entity = response.previousResponse().get();
//
//        if (entity != null) {
//            String jsonString = EntityUtils.toString(entity, StandardCharsets.UTF_8);
//
//            return objectMapper.readValue(jsonString, responseType);
//        }

        return null;
    }

    public static <T> T sendPostRequest(String url, Object requestBody, TypeReference<T> responseType ) throws IOException {

        System.out.println("URL : " + url);

        HttpPost httpPost = new HttpPost(url);
        String jsonBody = null;
        try {
            jsonBody = objectMapper.writeValueAsString(requestBody);
            System.out.println("Request : " + jsonBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        httpPost.setEntity(new StringEntity(jsonBody, StandardCharsets.UTF_8));
        httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");


        try (CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpPost)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String jsonString = EntityUtils.toString(entity, StandardCharsets.UTF_8).trim();
System.out.println("Response : " + jsonString);

            }
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static <T> T sendPostRequestWithHeader(String url, Object requestBody, TypeReference<T> responseType, Map<String , String > headerRequest , boolean JSON_STRING_ONLY ) throws IOException {
        System.out.println("URL : " + url);
        HttpPost httpPost = new HttpPost(url);
        String jsonBody = null;
        try {

            if (!JSON_STRING_ONLY ) {
                jsonBody = objectMapper.writeValueAsString(requestBody);
            }
else {
    jsonBody = requestBody.toString() ;
            }
            System.out.println("Request : " + jsonBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        httpPost.setEntity(new StringEntity(jsonBody, StandardCharsets.UTF_8));
        httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
        if ( headerRequest != null && headerRequest.size() > 0 ) {
            for (Map.Entry<String , String> entry : headerRequest.entrySet()){
                httpPost.setHeader(entry.getKey() , entry.getValue());
            }
        }

        System.out.println("fff");

        try (CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpPost)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                System.out.println("length " +    response.getStatusLine().getReasonPhrase() );

                String jsonString = EntityUtils.toString(entity, StandardCharsets.UTF_8).trim();

                System.out.println("Response : " + jsonString);
                if (jsonString.isEmpty()) {
                    if (responseType.getType() instanceof ParameterizedType &&
                            Collection.class.isAssignableFrom((Class<?>) ((ParameterizedType) responseType.getType()).getRawType())) {
                        return objectMapper.readValue("[]", responseType);
                    } else {

                        return null;
                    }
                } else {
                    return objectMapper.readValue(jsonString, responseType);
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }


    public static <T> T sendPutRequest(String url, Object requestBody, TypeReference<T> responseType) throws Exception {
//        String jsonBody = objectMapper.writeValueAsString(requestBody);
//        System.out.println(jsonBody);
//
//        HttpPut httpPut = new HttpPut("http://"+ NativeHost.NATIVE_HOST_PUBLIC+url);
//
//        httpPut.setEntity(new StringEntity(jsonBody, StandardCharsets.UTF_8));
//        httpPut.setHeader("Content-Type", "application/json; charset=UTF-8");
//
//        HttpResponse response = (HttpResponse) httpClient.execute(httpPut);
//        HttpEntity entity = response.getEntity();
//
//        if (entity != null) {
//            String jsonString = EntityUtils.toString(entity, StandardCharsets.UTF_8);
//
//            return objectMapper.readValue(jsonString, responseType);
//        }

        return null;
    }

    public static void sendDeleteRequest(String url) throws Exception {
//        HttpDelete httpDelete = new HttpDelete("http://"+ NativeHost.NATIVE_HOST_PUBLIC+url);
//
//        HttpResponse response = httpClient.execute(httpDelete);
//        int statusCode = response.getStatusLine().getStatusCode();
//
//        if (statusCode != 204) {
//            throw new RuntimeException("DELETE request failed with status code: " + statusCode);
//        }
    }
}