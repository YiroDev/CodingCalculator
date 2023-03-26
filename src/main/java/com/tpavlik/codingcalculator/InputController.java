package com.tpavlik.codingcalculator;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class InputController {

    @FXML
    private TextField A;

    @FXML
    private TextField B;

    @FXML
    private TextField C;

    @FXML
    private TextField D;

    @FXML
    private TextField E;

    @FXML
    private TextField F;

    @FXML
    private TextField G;

    @FXML
    private TextField H;

    @FXML
    private TextArea textArea;

    @FXML
    protected void onButtonClick() {
        List<TextField> textFields = List.of(A, B, C, D, E, F, G, H);
        List<Item> items = new ArrayList<>();
        ShannonFanoMethod shannonFanoMethod = new ShannonFanoMethod();

        for(TextField textField : textFields) {
            if (textField.getText().equals(".")) {
                continue;
            }
            if (!textField.getText().matches("^([+-]?\\d*\\.?\\d*)$")) {
                continue;
            }
            if (textField.getText().isEmpty() || Double.parseDouble(textField.getText()) == 0) {
                continue;
            }
            items.add(new Item(textField.getId(), Double.parseDouble(textField.getText())));
        }

        try {
            shannonFanoMethod.validate(items);
        } catch (Exception e) {
            textArea.setText(e.getMessage());
            return;
        }

        StringBuilder resultText = new StringBuilder();
        List<Item> resultList = shannonFanoMethod.main(items);

        for (Item item : resultList) {
            resultText.append(item);
            resultText.append("\n");
        }
        textArea.setText(resultText.toString());
    }
}