package com.edutilos.client.worker;

import com.edutilos.client.worker.model.Worker;
import com.edutilos.client.worker.utils.WorkerParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by edutilos on 05.08.18.
 */
public class SimpleTester {
    public static void main(String[] args) {
//        testInsert();
        testFindAll();
    }


    private static void testFindAll() {
        ClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(factory);
        StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
        restTemplate.getMessageConverters().add(messageConverter);

        String baseURL = "http://localhost:8080/Restapi/worker";
        String url = baseURL + "/findAll";

        //here generics does not work
        HashMap all = restTemplate.getForObject(url, HashMap.class);
//        System.out.println(all.getClass().getName());
//        System.out.println(all.get("workers").getClass().getName());
        List workersStr = (List)all.get("workers");
        List<Worker> workers =  new WorkerParser().parseCurlyWorkers(workersStr);
        System.out.println("<<all workers>>");
        for(Object  w: workers) {
            System.out.println(w.toString());
        }
    }

    //based on spring's RestTemplate
    private static void testInsert() {
        try {
            ClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            RestTemplate restTemplate = new RestTemplate(factory);
            StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
            restTemplate.getMessageConverters().add(messageConverter);

            String baseURL = "http://localhost:8080/Restapi/worker";
            String url = baseURL + "/insert";
            HttpHeaders headers = new HttpHeaders();
           // headers.add("Content-Type", "application/x-www-form-urlencoded");
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("id", "3");
            body.add("name", "bim");
            body.add("age", "30");
            body.add("wage", "300.0");
            body.add("active", "false");

//            HttpEntity<Worker> request = new HttpEntity<>(new Worker(1, "foo", 10, 100.0, true), headers);
            HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(body, headers);
            String res = restTemplate.postForObject(url, request, String.class);
            System.out.println(res);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
