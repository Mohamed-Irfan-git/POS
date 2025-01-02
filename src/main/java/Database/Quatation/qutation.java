package Database.Quatation;

import Database.DatabaseConnection;
import com.example.pos.Controller.Quatation;
import com.example.pos.Model.Fields;
import com.example.pos.Model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class qutation {

    public List<Item> filterItems(String searchText) throws SQLException {
        List<Item> items = new ArrayList<>();


        String query = "SELECT * FROM products WHERE ItemName LIKE ? or ItemCode LIKE ?";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement =connection.prepareStatement(query);
        statement.setString(1, "%" + searchText + "%"); // Wildcard search
        statement.setString(2, "%" + searchText + "%");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
           String itemName = resultSet.getString("ItemName");
           String itemCode = resultSet.getString("ItemCode");
           items.add(new Item(itemName,itemCode));

        }
        return items;
    }


    public static double getItemPrice(String itemCode, String billType) throws SQLException {
        String priceColumn = null;

        // Select the correct price column based on the bill type
        switch (billType) {
            case "CREDIT":
                priceColumn = "CreditPrice";
                break;
            case "INVOICE":
                priceColumn = "InvoicePrice";
                break;
            case "QUOTATION":
                priceColumn = "QuatationPrice";
                break;
            default:
                throw new IllegalArgumentException("Invalid bill type: " + billType);
        }

        // Query to get the price for the specific item
        String query = "SELECT " + priceColumn + " FROM products WHERE ItemCode = ?"; // Ensure table name is correct (produts -> products)

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            throw new SQLException("Failed to connect to the database.");
        }

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, itemCode); // Set the item code parameter

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            double price = resultSet.getDouble(priceColumn); // Return the price for the selected bill type
            return price;
        } else {
            System.out.println("Item not found with code: " + itemCode);
            return 0.0;
        }
    }

    public void fetchDetails(String itemCode, Fields items) throws SQLException {
        String query = "SELECT * FROM products WHERE ItemCode = ? or ItemName = ?";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, itemCode);
        statement.setString(2, itemCode);
        ResultSet resultSet = statement.executeQuery();


        if (resultSet.next()) {

            items.getItemCode().setText(resultSet.getString("ItemCode"));
            items.getItemName().setText(resultSet.getString("ItemName"));
            items.getCostPrice().setText(String.valueOf(resultSet.getDouble("CostPrice")));
            items.getCreditPrice().setText(String.valueOf(resultSet.getDouble("CreditPrice")));
            items.getInvoicePrice().setText(String.valueOf(resultSet.getDouble("InvoicePrice")));
            items.getQuotationPrice().setText(String.valueOf(resultSet.getDouble("QuatationPrice")));
            items.getUnit().setText(resultSet.getString("Unit"));
            items.getLc().setText(String.valueOf(resultSet.getDouble("LcValue")));


        }


    }






}

