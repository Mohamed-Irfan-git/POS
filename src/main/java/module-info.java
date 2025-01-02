module com.example.pos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires java.sql;


    opens com.example.pos to javafx.fxml;
    opens com.example.pos.Model to javafx.base;
    exports com.example.pos;
    exports com.example.pos.Controller;
    opens com.example.pos.Controller to javafx.fxml;
}