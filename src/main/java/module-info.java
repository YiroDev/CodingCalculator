module com.example.codingcalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.codingcalculator to javafx.fxml;
    exports com.example.codingcalculator;
}