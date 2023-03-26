package com.example.codingcalculator;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InputController {

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    private TextField textField3;

    @FXML
    private TextField textField4;

    @FXML
    private TextField textField5;

    @FXML
    private TextArea textArea;

    @FXML
    protected void onButtonClick() {
        ShannonFanoMethod shannonFanoMethod = new ShannonFanoMethod();
        List<Item> items = new ArrayList<>();
        items.add(new Item("A", Double.parseDouble(textField1.getText())));
        items.add(new Item("B", Double.parseDouble(textField2.getText())));
        items.add(new Item("C", Double.parseDouble(textField3.getText())));
        items.add(new Item("D", Double.parseDouble(textField4.getText())));
        items.add(new Item("E", Double.parseDouble(textField5.getText())));

        if (!shannonFanoMethod.validate(items)) {
            textArea.setText("Sum of probabilities must be equal to 1");
            return;
        }

        StringBuilder resultText = new StringBuilder();
        List<Item> resultList = shannonFanoMethod.main(items);
        resultList.sort(Comparator.comparing(Item::countOfBits));

        for (Item item : resultList) {
            resultText.append(item);
            resultText.append("\n");
        }
        textArea.setText(resultText.toString());
    }
}