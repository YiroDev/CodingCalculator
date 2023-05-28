module com.example.codingcalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tpavlik.codingcalculator to javafx.fxml;
    exports com.tpavlik.codingcalculator;
    exports com.tpavlik.codingcalculator.shannonFano;
    opens com.tpavlik.codingcalculator.shannonFano to javafx.fxml;
}