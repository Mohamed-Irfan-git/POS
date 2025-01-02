package com.example.pos.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import Database.Quatation.qutation;
import com.example.pos.Model.Fields;
import com.example.pos.Model.Item;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Quatation {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField AvaQty;

    @FXML
    private Label BillNo;

    @FXML
    private MenuButton BillTypeMenu;

    @FXML
    private Label Billc;

    @FXML
    private Button BillcopyButton;

    @FXML
    private TextField ProfitPercentage;


    @FXML
    private TableColumn<Item, Integer> ItemNocol;

    @FXML
    private TableColumn<Item, Double> PriceCol;

    @FXML
    private TableColumn<Item, String> QuantityCol;

    @FXML
    private Label Calender;

    @FXML
    private ImageView CreditCard;

    @FXML
    private TextField CustomerCash;

    @FXML
    private Label Date;

    @FXML
    private Label DeleteBill;

    @FXML
    private TextField DiscountPercentage;

    @FXML
    private TextField DiscountPrice;

    @FXML
    private MenuItem Invoicetype;

    @FXML
    private TextField ItemCodeReader;

    @FXML
    private TableColumn<Item,String> ItemNamecol;

    @FXML
    private TableView<Item> ItemTable;

    @FXML
    private ImageView OnlineTransfer;


    @FXML
    private TextField SelQty;

    @FXML
    private TextField TotalDiscount;

    @FXML
    private TextField TotalProfit;

    @FXML
    private TextField Totalprice;

    @FXML
    private Label add;

    @FXML
    private TextField blanace;

    @FXML
    private TextField creditprice;

    @FXML
    private MenuItem credittype;

    @FXML
    private Label down;

    @FXML
    private Label editBill;

    @FXML
    private Label hold;

    @FXML
    private TextField invoiceorice;

    @FXML
    private TextField itemname;

    @FXML
    private ImageView masterCard;

    @FXML
    private TextField price;

    @FXML
    private TextField quatationprice;

    @FXML
    private MenuItem quattiontype;

    @FXML
    private Label removeBill;

    @FXML
    private Label salesRetun;

    @FXML
    private TextField searchBar;

    @FXML
    private TableColumn<Item, Double> totalcol;

    @FXML
    private TextField unit;

    @FXML
    private Label up;

    @FXML
    private TextField Costprice;


    @FXML
    private ListView<Item> searchList;

    @FXML
    private TextField lctotal;

    @FXML
    private ImageView visaCard;

    public String selectedItemName;

    qutation q = new qutation();

    private String currentMenu = "INVOICE";

    private int CaretPosition = 0;

    private String typedText = "";


    @FXML
    private TextField lcprice;

    private final ObservableList<Item> data = FXCollections.observableArrayList();
    private final ObservableList<Item> TotalLc = FXCollections.observableArrayList();

    private int itemNumber = 1;

    @FXML
    void initialize() {
        Platform.runLater(() -> {
            if (searchBar.getScene() != null) {
                Stage currentStage = (Stage) searchBar.getScene().getWindow();
                if (currentStage != null) {
                    setTime();
                    currentStage.getScene().setOnKeyPressed(event -> {
                        if (event.getCode() == KeyCode.F5) {
                            createMenuButton();
                            event.consume();
                        } else if (event.getCode() == KeyCode.F10) {
                            openNewWindow();
                            event.consume();
                        } else if (event.getCode() == KeyCode.ESCAPE) {
                            clearAllFields();
                            searchBar.requestFocus();
                            event.consume();
                            ItemTable.refresh();

                        }

                    });
                }
            }
        });

        ItemTable.setRowFactory(tv -> {
            TableRow<Item> row = new TableRow<>();
            row.setPrefHeight(30);  // Set the preferred height for each row
            return row;
        });



        searchList.setVisible(false);
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                typedText = newValue;
                List<Item> suggestion = null;
                try {
                    suggestion = q.filterItems(newValue);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                searchList.getItems().setAll(suggestion);
                int visibleItemCount = 25;
                searchList.setFixedCellSize(30.0);
                adjustListViewHeight(searchList, visibleItemCount);
                searchList.setVisible(true);

            } else {
                searchList.setVisible(false);
            }
        });

        searchList.setOnKeyPressed(event -> {
            int selectedIndex = searchList.getSelectionModel().getSelectedIndex();
            if (event.getCode() == KeyCode.UP) {
                event.consume();
                if (selectedIndex == 0) {
                    searchBar.requestFocus();
                    searchBar.setText(typedText);
                    searchBar.positionCaret(CaretPosition);



                } else {
                    searchList.getSelectionModel().selectPrevious();
                }
            } else if (event.getCode() == KeyCode.ENTER) {
                event.consume();
                Item selectedItem = searchList.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    CaretPosition = searchBar.getText().length();
                    searchBar.setText(typedText);
                    searchBar.positionCaret(CaretPosition);
                    try {
                        handleSearchBarEnter(selectedItem);
                        searchList.setVisible(false);
                        SelQty.requestFocus();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                } else {
                    try {
                        handleSearchBarEnter(selectedItem);
                        searchItem(selectedItem);
                        searchList.setVisible(false);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        searchBar.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()==KeyCode.ENTER){
                keyEvent.consume();
                String item = searchBar.getText();
                Item a = new Item(item);
                try {
                    handleSearchBarEnter(a);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                SelQty.requestFocus();
                searchList.setVisible(false);
            } else if (keyEvent.getCode() == KeyCode.DOWN) {
                keyEvent.consume();
                if(!searchList.getItems().isEmpty()){
                    CaretPosition = searchBar.getCaretPosition();
                    searchList.requestFocus();
                    searchList.getSelectionModel().select(0);
                    searchBar.positionCaret(CaretPosition);

                }if (searchBar.getText().isEmpty()){
                    ItemTable.requestFocus();

                }

            } else if (keyEvent.getCode() == KeyCode.UP) {
                keyEvent.consume();
                searchBar.setText(typedText);
                CaretPosition = searchBar.getCaretPosition();
                searchBar.positionCaret(CaretPosition);



            }
        });

        ItemCodeReader.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()==KeyCode.ENTER){
                keyEvent.consume();
                String item = ItemCodeReader.getText();
                Item code = new Item(item);
                try {
                    handleSearchBarEnter(code);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                SelQty.requestFocus();

            }
        });


        SelQty.setDisable(false);  // Ensure the quantity field is enabled
        price.setDisable(false);  // Ensure the price field is enabled
        balance();



        SelQty.setOnKeyPressed(Event -> {
            if(Event.getCode() == KeyCode.ENTER) {
                Event.consume();
                // Get the entered quantity and price values
                String quantityText = SelQty.getText();
                String priceText = price.getText();
                System.out.println("kholi");

                try {
                    // Convert to numbers and calculate total
                    double quantity = Double.parseDouble(quantityText);
                    double priceValue = Double.parseDouble(priceText);
                    double total = quantity * priceValue;

                    // Update the selected item in the table (if it's already in the table)
                    Item selectedItem = ItemTable.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        selectedItem.setQuantity(quantity);
                        selectedItem.setTotal(total);

                        ItemTable.requestFocus();
                        ItemTable.refresh();


                    }
                    else {
                        // If no item is selected, add a new item
                        addItem(itemname, price, SelQty,unit, ItemTable,lcprice,Costprice,creditprice);

                    }

                    if(Event.getCode()==KeyCode.ENTER){
                        if(SelQty.getText().isEmpty()){
                            CustomerCash.requestFocus();
                        }
                    }

                    // Update the total label
                    updateTotalLabel();
                    ItemTable.refresh();
                    clearAllFields();
                } catch (NumberFormatException e) {
                    // Handle invalid input for quantity or price
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid quantity and price.", ButtonType.OK);
                    alert.showAndWait();
                }
            } else if (Event.getCode() == KeyCode.DOWN) {
                price.requestFocus();

            } else if (Event.getCode() == KeyCode.UP ){
                searchBar.requestFocus();
                searchBar.clear();

            }else if(Event.getCode() == KeyCode.ENTER){
                if(SelQty.getText().isEmpty()){
                    CustomerCash.requestFocus();
                }
            }
        });
        CustomerCash.setOnKeyPressed(event -> {
            if(event.getCode()==KeyCode.UP){
                searchBar.requestFocus();
            }
        });


        price.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()==KeyCode.DOWN){
                SelQty.requestFocus();
                keyEvent.consume();
            }
        });
        ItemTable.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()==KeyCode.UP){

                ItemTable.refresh();
            } else if (keyEvent.getCode() == KeyCode.DELETE) {
                deleteSelectedItem();

            } else if (keyEvent.getCode() == KeyCode.ENTER) {
                CustomerCash.requestFocus();

            }
        });
        TableUpdate();
    }

    private void adjustListViewHeight(ListView<Item> searchList, int visibleItemCount) {
        int itemCount = searchList.getItems().size();
        int displayCount = Math.min(itemCount, visibleItemCount);
        double cellHeight = searchList.getFixedCellSize();
        double listViewHeight = displayCount * cellHeight;
        searchList.setPrefHeight(listViewHeight);
    }

    private void createMenuButton() {
        BillTypeMenu.setText(currentMenu);
        credittype.setOnAction(e -> selectMenuType("CREDIT"));
        Invoicetype.setOnAction(e -> selectMenuType("INVOICE"));
        quattiontype.setOnAction(e -> selectMenuType("QUOTATION"));
        cycleMenu(BillTypeMenu);
    }

    private void cycleMenu(MenuButton menuButton) {
        currentMenu = switch (currentMenu) {
            case "INVOICE" -> "QUOTATION";
            case "QUOTATION" -> "CREDIT";
            case "CREDIT" -> "INVOICE";
            default -> "INVOICE";
        };
        menuButton.setText(currentMenu);
        System.out.println("Current Menu: " + currentMenu);
    }

    private void selectMenuType(String menuType) {
        currentMenu = menuType;
        BillTypeMenu.setText(menuType);
        System.out.println("Selected Menu: " + menuType);
    }

    private void handleSearchBarEnter(Item selectedItem) throws SQLException {
        if (selectedItem != null) {
            String itemCode = selectedItem.getItemCode();

            // Get the price based on the current menu (INVOICE, QUOTATION, CREDIT)
            double priceValue = q.getItemPrice(itemCode, currentMenu);

            // Set the price in the price field
            price.setText(String.format("%.2f", priceValue));
            searchItem(selectedItem);
        }
    }


    private void openNewWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pos/AddItem.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.setScene(new Scene(root));
            newStage.setTitle("Add Item");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearAllFields() {
        searchBar.clear();
        ItemCodeReader.clear();
        SelQty.clear();
        AvaQty.clear();
        itemname.clear();
        unit.clear();
        invoiceorice.clear();
        quatationprice.clear();
        creditprice.clear();
        price.clear();
        Costprice.clear();
        lcprice.clear();
        ItemTable.getSelectionModel().clearSelection();

    }

    private void searchItem(Item selectedItem) throws SQLException {
        String item =selectedItem.getItemCode();

        TextField IName = itemname;
        TextField ICode = ItemCodeReader;
        TextField CostPrice = Costprice;
        TextField CreditPrice = creditprice;
        TextField IPrice = invoiceorice;
        TextField Qty = quatationprice;
        TextField unt = unit;
        TextField cooly = lcprice;

        Fields fields = new Fields(ICode,IName,CostPrice,CreditPrice,IPrice,Qty,unt,cooly);

        q.fetchDetails(item,fields);

    }
    private  void setTime(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd hh:mm:ss a");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            String currentTime = LocalDateTime.now().format(dateTimeFormatter);
            Date.setText(currentTime);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
    private void TableUpdate(){
        ItemNocol.setCellValueFactory(new PropertyValueFactory<>("ItemNumber"));
        ItemNamecol.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        PriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        QuantityCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getQuantity() + " " + cellData.getValue().getUnit())
        );
        totalcol.setCellValueFactory(new PropertyValueFactory<>("Total"));

        ItemTable.setItems(data);
        System.out.println("dhoni");


    }
    private void addItem(TextField itemNameField, TextField priceField, TextField quantityField, TextField unitField, TableView<Item> tableView, TextField lcTotal,TextField costprice,TextField creditprice) {
        try {
            String itemName = itemNameField.getText();
            double price = Double.parseDouble(priceField.getText());
            double quantity = Double.parseDouble(quantityField.getText());
            String unit = unitField.getText();
            double lc = Double.parseDouble(lcTotal.getText());

            double Cprice = Double.parseDouble(costprice.getText());
            double Crprice = Double.parseDouble(creditprice.getText());





            Item existingItem = findItemByName(itemName);

            if (existingItem != null) {
                // If the item already exists, update the quantity, price, and total
                double oldQnty = existingItem.getQuantity();
                double QDifference = quantity - oldQnty;
                double lcb = lc * quantity;
                existingItem.setLcValue(lcb);



                if (QDifference != 0 || price != existingItem.getPrice()) {
                    existingItem.setQuantity(quantity);
                    existingItem.setPrice(price);
                    existingItem.setCreditPrice(Crprice);
                    existingItem.setCost(Cprice);


                    existingItem.setTotal(existingItem.getPrice() * existingItem.getQuantity());
                    existingItem.setProfit((existingItem.getPrice() - existingItem.getCost()) * existingItem.getQuantity());
                    existingItem.setDiscount((existingItem.getCreditPrice() - existingItem.getPrice()) * existingItem.getQuantity());


                    ItemTable.refresh();
                }
            } else {

                double total = price * quantity;
                double lcb = lc * quantity;
                double TotalDiscount =(Crprice - price) * quantity;
                double Totalprofit = (price - Cprice) * quantity;

                Item newItem = new Item(itemNumber++, itemName, price, quantity, unit, total,lcb,TotalDiscount,Totalprofit, Cprice,Crprice);
                data.add(newItem);
                ItemTable.setItems(data); // Ensure the table updates
                ItemTable.refresh(); // Refresh the table to show the new item
            }

            // Clear input fields
            itemNameField.clear();
            priceField.clear();
            quantityField.clear();
            unitField.clear();

            // Update the total label
            updateTotalLabel();
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid values!", ButtonType.OK);
            alert.showAndWait();
        }
    }


    private void updateTotalLabel() {
        System.out.println("updateTotalLabel");
        double totalSum = data.stream().mapToDouble(Item::getTotal).sum();

        double totalvalue = data.stream().mapToDouble(Item::getLcValue).sum();

        double toalpr = data.stream().mapToDouble(Item::getProfit).sum();

        double totaldis =data.stream().mapToDouble(Item::getDiscount).sum();

        double ProfitDifferance = (totalSum - toalpr);
        double ProfitPecentage = (toalpr/ProfitDifferance) * 100;

        double DiscountDifference = totaldis + totalSum;
        Double DiscountPersentage = (totaldis/DiscountDifference) *100;

        double total = totalSum + totalvalue;
        lctotal.setText(String.format(" %.2f", totalvalue));
        Totalprice.setText(String.format(" %.2f", total));
        TotalProfit.setText(String.format(" %.2f",toalpr));
        TotalDiscount.setText(String.format("%.2f",totaldis));
        ProfitPercentage.setText(String.format("%.2f",ProfitPecentage) + " %");
        DiscountPercentage.setText(String.format("%.2f",DiscountPersentage) + " %");

        balance();
    }

    private Item findItemByName(String itemName) {
        for (Item item : data) {
            if (item.getItemName().equals(itemName)) {
                return item;  // Return the item if found
            }
        }
        return null;  // Return null if the item doesn't exist in the list
    }

    private void balance() {
        // Ensure Totalprice is initialized and numeric
        String totalText = Totalprice.getText().replaceAll("[^\\d.]"+".00", "");
        double totalPrice = totalText.isEmpty() ? 0 : Double.parseDouble(totalText);

        // Add listener for CustomerCash field
        CustomerCash.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (newValue.matches("\\d*(\\.\\d{0,2})?")) {
                    double payment = newValue.isEmpty() ? 0 : Double.parseDouble(newValue);
                    double balance = payment - totalPrice;
                    blanace.setText(String.format("%.2f", balance));
                } else {
                    CustomerCash.setText(oldValue); // Restore old value on invalid input
                }
            } catch (NumberFormatException e) {
                blanace.setText(""); // Clear balance on invalid input
            }
        });
    }

    private void deleteSelectedItem() {
        Item selectedItem = ItemTable.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            // Remove the selected item from the data list
            data.remove(selectedItem);

            // Refresh the table view to reflect the changes
            ItemTable.refresh();


            // Optionally, you can update the total price after removal
            updateTotalLabel();
        } else {
            // Optionally, show an alert if no item is selected
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an item to delete.", ButtonType.OK);
            alert.showAndWait();
        }

    }


}
