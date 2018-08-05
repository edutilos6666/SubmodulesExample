package com.edutilos.client.worker.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 * Created by edutilos on 05.08.18.
 */
public class WorkerMainController {
    @FXML
    private Button btnInsert, btnUpdate, btnDelete,
                   btnFindById, btnFindByName, btnFindByAge,
                   btnFindByWage, btnFindByActive, btnFindAll;
    @FXML
    private TextField fieldUpdate, fieldDelete, fieldFindById,
                      fieldFindByName, fieldFindByAge, fieldFindByWage, fieldFindByActive;


    private final Logger logger = Logger.getLogger(getClass().getName());

    @FXML
    private void handleBtnInsert(ActionEvent evt) {
        try {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/fxml/WorkerInsertScene.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
        } catch(Exception ex) {
            ex.printStackTrace();
            logger.severe(ex.getMessage());
        }
    }

    @FXML
    private void handleBtnUpdate(ActionEvent evt) {

    }

    @FXML
    private void handleBtnDelete(ActionEvent evt) {

    }

    @FXML
    private void handleBtnFindById(ActionEvent evt) {

    }

    @FXML
    private void handleBtnFindByName(ActionEvent evt) {

    }

    @FXML
    private void handleBtnFindByAge(ActionEvent evt) {

    }

    @FXML
    private void handleBtnFindByWage(ActionEvent evt) {

    }

    @FXML
    private void handleBtnFindByActive(ActionEvent evt) {

    }

    @FXML
    private void handleBtnFindAll(ActionEvent evt) {

    }



}
