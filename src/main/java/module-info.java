module com.example.curs3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.curs3 to javafx.fxml;
    exports com.example.curs3;
}