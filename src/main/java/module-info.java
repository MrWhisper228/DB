module com.example.db {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.graphics;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires psec.java;


    opens com.example.db to javafx.fxml;
    exports com.example.db;
}