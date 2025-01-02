package com.example.pos.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class loginController extends Application {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginPassword;

    @FXML
    private TextField loginUsername;
    private Stage primaryStage;

    @FXML
    private Label timeLabel;

    @FXML
    void initialize() {

       loginButton.setOnAction(event -> {
           try {

               FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pos/Dashboard.fxml"));
               Parent root = loader.load();


               Stage stage = new Stage();
              stage.initStyle(StageStyle.UNDECORATED);
               stage.setScene(new Scene(root));
               stage.show();

               Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
               currentStage.close();
           } catch (Exception e) {
               e.printStackTrace();
           }


       });
       loginUsername.setOnKeyPressed(event -> {
           if (event.getCode() == KeyCode.ENTER) {
               loginPassword.requestFocus();
           }
       });
       loginPassword.setOnKeyPressed(event -> {
           loginButton.requestFocus();
       });

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pos/Dashbord.fxml"));
        Parent root = loader.load();


        // Set the stage to undecorated
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
