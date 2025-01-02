package com.example.pos.Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Database.Product.Product;
import com.example.pos.Model.Fields;
import com.example.pos.Model.Item;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class AddItem {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button CANCELBUTTON;

    @FXML
    private Button CREATEBUTTON;

    @FXML
    private TextField CREDITPRICE;

    @FXML
    private TextField INVOICEPRICE;

    @FXML
    private TextField ITEMCODEFIELD;

    @FXML
    private TextField ITEMNAMEFIELD;

    @FXML
    private Label ITEMNUMBERLABEL;

    @FXML
    private TextField LCFIELD;

    @FXML
    private TextField QUATATIONPRICE;

    @FXML
    private TextField UNITFIELD;

    @FXML
    private TextField COSTFIELD;


    @FXML
    void initialize() throws SQLException {
        Platform.runLater(() -> {
                    if (ITEMCODEFIELD.getScene() != null) {
                        Stage currentStage = (Stage) ITEMCODEFIELD.getScene().getWindow();
                        if (currentStage != null) {
                            try {
                                addItemNo();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }
                });



        ITEMCODEFIELD.setOnKeyPressed(KeyEvent -> {
            if (KeyEvent.getCode() == KeyCode.ENTER) {
                ITEMNAMEFIELD.requestFocus();
            }
        });
        ITEMNAMEFIELD.setOnKeyPressed(KeyEvent -> {
            if (KeyEvent.getCode() == KeyCode.ENTER) {
                COSTFIELD.requestFocus();

            }
        });
        COSTFIELD.setOnKeyPressed(KeyEvent -> {
            if (KeyEvent.getCode() == KeyCode.ENTER) {
                CREDITPRICE.requestFocus();
            }
        });
        CREDITPRICE.setOnKeyPressed(KeyEvent -> {
            if (KeyEvent.getCode() == KeyCode.ENTER) {
                INVOICEPRICE.requestFocus();
            }
        });
        INVOICEPRICE.setOnKeyPressed(KeyEvent -> {
            if (KeyEvent.getCode() == KeyCode.ENTER) {
                QUATATIONPRICE.requestFocus();
            }
        });
        QUATATIONPRICE.setOnKeyPressed(KeyEvent -> {
            if (KeyEvent.getCode() == KeyCode.ENTER) {
                UNITFIELD.requestFocus();
            }
        });
        UNITFIELD.setOnKeyPressed(KeyEvent -> {
            if (KeyEvent.getCode() == KeyCode.ENTER) {
                LCFIELD.requestFocus();
            }
        });
        LCFIELD.setOnKeyPressed(KeyEvent -> {
            if (KeyEvent.getCode() == KeyCode.ENTER) {
                CREATEBUTTON.requestFocus();
            }
        });

        CREATEBUTTON.setOnKeyPressed(KeyEvent -> {
            if (KeyEvent.getCode() == KeyCode.ENTER) {
                try {
                    createItem();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                ITEMCODEFIELD.clear();
                ITEMNAMEFIELD.clear();
                COSTFIELD.clear();
                CREDITPRICE.clear();
                INVOICEPRICE.clear();
                QUATATIONPRICE.clear();
                UNITFIELD.clear();
                LCFIELD.clear();
                System.out.println("hello");

            }
        });


    }

    private void createItem() throws SQLException {

        String itemCode = ITEMCODEFIELD.getText();
        String itemName = ITEMNAMEFIELD.getText();
        double cost = Double.parseDouble(COSTFIELD.getText());
        double creditPrice = Double.parseDouble(CREDITPRICE.getText());
        double invoicePrice =Double.parseDouble(INVOICEPRICE.getText());
        double quatationPrice =Double.parseDouble(QUATATIONPRICE.getText());
        String unit = UNITFIELD.getText();
        double lc = Double.parseDouble(LCFIELD.getText());


        Item item = new Item(itemCode, itemName, cost, creditPrice, invoicePrice, quatationPrice, unit, lc);
        Product product = new Product();
        product.addItem(item);

    }

    private void addItemNo() throws SQLException {
        Fields fields = new Fields(ITEMNUMBERLABEL);

        Product product = new Product();
        product.billUpdateNo(fields);
    }

}

