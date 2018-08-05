package com.edutilos.client.worker.controller;

import com.edutilos.client.worker.model.Worker;
import com.edutilos.client.worker.utils.WorkerParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by edutilos on 05.08.18.
 */
public class WorkerInsertController {
    @FXML
    private TextField fieldId , fieldName, fieldAge,
                      fieldWage, fieldActive;

    @FXML
    private Button btnInsert, btnCancel;

    private RestTemplate restTemplate;
    //for wildfly
//    private String baseURL = "http://localhost:8080/Restapi/worker";
    //for tomcat
    private String baseURL = "http://localhost:8080/worker";
    private final Logger logger = Logger.getLogger(WorkerInsertController.class.getName());
    private final String newline = "\n";

    public WorkerInsertController() {
        ClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        restTemplate = new RestTemplate(factory);
        StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
        restTemplate.getMessageConverters().add(messageConverter);
    }


    @FXML
    private void handleBtnInsert(ActionEvent evt) {
        try {
            long id = Long.parseLong(fieldId.getText());
            String name = fieldName.getText();
            int age = Integer.parseInt(fieldAge.getText());
            double wage = Double.parseDouble(fieldWage.getText());
            boolean active = Boolean.parseBoolean(fieldActive.getText());


            String url = baseURL + "/insert";
            HttpHeaders headers = new HttpHeaders();
            // headers.add("Content-Type", "application/x-www-form-urlencoded");
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("id", fieldId.getText());
            body.add("name", fieldName.getText());
            body.add("age", fieldAge.getText());
            body.add("wage", fieldWage.getText());
            body.add("active", fieldActive.getText());

//            HttpEntity<Worker> request = new HttpEntity<>(new Worker(1, "foo", 10, 100.0, true), headers);
            HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(body, headers);
            String res = restTemplate.postForObject(url, request, String.class);
//            logger.info(res);  ::: returns always null
            findAll();
        } catch(Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Exception");
            StringBuilder sb = new StringBuilder();
            for(StackTraceElement el: ex.getStackTrace()) {
                sb.append(el.toString()).append(newline);
            }
            alert.setContentText(sb.toString());
            alert.setWidth(500);
            alert.setHeight(500);
            alert.showAndWait();
        }
    }

    private void findAll() {
        try {
            String url = baseURL + "/findAll";

            //here generics does not work
            HashMap all = restTemplate.getForObject(url, HashMap.class);
//        System.out.println(all.getClass().getName());
//        System.out.println(all.get("workers").getClass().getName());
            List workersStr = (List)all.get("workers");
            List<Worker> workers =  new WorkerParser().parseCurlyWorkers(workersStr);
            StringBuilder sb = new StringBuilder("<<all workers>>");
            sb.append(newline);
            for(Object  w: workers) {
                sb.append(w.toString()).append(newline);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("find all workers");
            alert.setContentText(sb.toString());
            alert.setWidth(500);
            alert.setHeight(500);
            alert.showAndWait();
        } catch(Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Exception");
            StringBuilder sb = new StringBuilder();
            for(StackTraceElement el: ex.getStackTrace()) {
                sb.append(el.toString()).append(newline);
            }
            alert.setContentText(sb.toString());
            alert.setWidth(500);
            alert.setHeight(500);
            alert.showAndWait();
        }
    }

    @FXML
    private void handleBtnCancel(ActionEvent evt) {
        Stage stage = (Stage)btnInsert.getScene().getWindow();
        stage.close();
    }
}
