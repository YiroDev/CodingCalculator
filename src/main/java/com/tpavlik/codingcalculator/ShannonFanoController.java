package com.tpavlik.codingcalculator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class ShannonFanoController implements Initializable {

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
    private TextArea resultShannon;

    @FXML
    private TextArea result;

    @FXML
    private Label codeEffectivity;

    @FXML
    private Label codeLength;

    @FXML
    private Label count;

    @FXML
    private Label probability;

    List<TextField> textFields = new ArrayList<>();

    @FXML
    protected void generate() {
        List<Item> items = new ArrayList<>();
        ShannonFanoMethod shannonFanoMethod = new ShannonFanoMethod();

        for(TextField textField : textFields) {
            if (textField.getText() == null || textField.getText().equals(".")) {
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

        count.setText("n=" + items.size());
        probability.setText("p=" + shannonFanoMethod.sumDouble(items));

        try {
            shannonFanoMethod.validate(items);
        } catch (Exception e) {
            resultShannon.setText(e.getMessage());
            return;
        }

        StringBuilder resultShannonText = new StringBuilder();
        StringBuilder resultText = new StringBuilder();
        List<Item> resultList = shannonFanoMethod.main(items);

        for (Item item : resultList) {
            resultShannonText.append(item);
            resultShannonText.append("\n");

            resultText.append(item.getResultBit() + "\n");
        }

        resultShannon.setText(resultShannonText.toString());

        result.setText(resultText.toString());

        codeEffectivity.setText("Code effectivity: " + shannonFanoMethod.codeEffectivity(resultList) + "%");
        codeLength.setText("Average codeword length: " + shannonFanoMethod.codingLength(resultList) + "b");
    }

    @FXML
    protected void clear() {
        textFields.forEach(textField -> textField.setText(null));
        resultShannon.setText(null);
        result.setText(null);
        count.setText("n=0");
        probability.setText("p=0.0");
        codeEffectivity.setText("Code effectivity:");
        codeLength.setText("Average codeword length:");
    }

    @FXML
    protected void randomNumber() {
        Random random = new Random();
        BigDecimal sum = BigDecimal.ZERO;
        double min = 0.01;
        double max = 0.30;
        for (int i = 0; i < textFields.size() - 1; i++) {
            BigDecimal value = BigDecimal.valueOf((max - min) * random.nextDouble() + min).setScale(2, RoundingMode.HALF_UP);
            if (sum.add(value).doubleValue() > 1.0) {
                value = BigDecimal.valueOf(1).subtract(sum);
            }
            textFields.get(i).setText(String.valueOf(value));
            sum = sum.add(value);
        }

        textFields.get(textFields.size() - 1).setText(String.valueOf(BigDecimal.valueOf(1).setScale(2, RoundingMode.HALF_UP).subtract(sum).doubleValue()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textFields = List.of(A, B, C, D, E, F, G, H);
        for (TextField textField : textFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null && !newValue.matches("^([+-]?\\d*\\.?\\d*)$")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
        }
    }
}