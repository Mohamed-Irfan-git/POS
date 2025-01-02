package com.example.pos.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DashboardController extends Application  {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox EnviButton;

    @FXML
    private HBox customerButton;

    @FXML
    private HBox emplyeeButton;

    @FXML
    private HBox grnButton;

    @FXML
    private HBox invoiceButton;

    @FXML
    private HBox logoutButton;

    @FXML
    private HBox cancelButton;

    @FXML
    private HBox quatationButton;

    @FXML
    private HBox remiButton;

    @FXML
    private HBox settingButton;

    @FXML
    private HBox supplierButton;

    @FXML
    void initialize() {
        emplyeeButton.setOnMouseClicked(event -> {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/com/example/pos/employee.fxml"));
            Parent root = null;
            try {
                root = Loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();



        });
        customerButton.setOnMouseClicked(event -> {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/com/example/pos/customer.fxml"));
            Parent root = null;
            try {
                root = Loader.load();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
        });
        quatationButton.setOnMouseClicked(event -> {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/com/example/pos/quatation.fxml"));
            Parent root = null;
            try {
                root = Loader.load();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
        });


    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("com/example/pos/employee.fxml"));
        Parent root = loader.load();

        // Set the stage to undecorated
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.show();
    }


}
