package Database.Product;

import Database.DatabaseConnection;
import com.example.pos.Model.Fields;
import com.example.pos.Model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
    public void addItem(Item item) throws SQLException {
        String sql = "INSERT INTO products (ItemCode,ItemName,CostPrice,CreditPrice,InvoicePrice,QuatationPrice,Unit,LcValue)VALUES (?,?,?,?,?,?,?,?)";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, item.getItemCode());
        pstmt.setString(2, item.getItemName());
        pstmt.setDouble(3, item.getCost());
        pstmt.setDouble(4,item.getCreditPrice());
        pstmt.setDouble(5,item.getInvoicePrice());
        pstmt.setDouble(6,item.getQuatationPrice());
        pstmt.setString(7,item.getUnit());
        pstmt.setDouble(8,item.getLcValue());
        pstmt.executeUpdate();


    }
    public void billUpdateNo(  Fields label) throws SQLException {
        String sql = "SELECT ItemNUmber FROM products ORDER BY ItemNUmber DESC LIMIT 1";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();


        if (rs.next()) {
            int lastBillNo = rs.getInt("ItemNUmber");
            int newBillNo = lastBillNo + 1;
            label.getItemNo().setText(String.valueOf(newBillNo));
        } else {
            label.getItemNo().setText("Bill No: 1"); // If no bills exist, start with 1
        }

    }

}
