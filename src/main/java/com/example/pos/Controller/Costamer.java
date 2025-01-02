package com.example.pos.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Costamer {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button CustomerreportButton;

    @FXML
    private ToggleGroup Human1;

    @FXML
    private AnchorPane anchorpaneCreatecostamer;

    @FXML
    private AnchorPane anchorpanecostamerDetail;

    @FXML
    private Button costamerDetailButton;

    @FXML
    private Button createCusromerButton;

    @FXML
    private ImageView image;

    @FXML
    void initialize() {



    }
   @FXML
    private void handleButtonAction(javafx.event.ActionEvent event) {
        anchorpaneCreatecostamer.setVisible(false);
        anchorpanecostamerDetail.setVisible(false);
        image.setVisible(true);

        if(event.getSource() == createCusromerButton) {
            anchorpaneCreatecostamer.setVisible(true);
            image.setVisible(false);

        }else if(event.getSource() == costamerDetailButton) {
            anchorpanecostamerDetail.setVisible(true);
            image.setVisible(false);
        }
    }

    
}
