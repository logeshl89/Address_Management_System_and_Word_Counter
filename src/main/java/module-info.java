module com.example.code_soft_address {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.code_soft_address to javafx.fxml;
    exports com.example.code_soft_address;
}