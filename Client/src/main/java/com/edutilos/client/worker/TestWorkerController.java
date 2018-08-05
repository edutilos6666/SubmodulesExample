package com.edutilos.client.worker;

import com.edutilos.client.worker.controller.WorkerMainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 * Created by edutilos on 05.08.18.
 */
public class TestWorkerController extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/fxml/WorkerMainScene.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception ex) {
            logger.severe(ex.getMessage());
        }
    }
}
