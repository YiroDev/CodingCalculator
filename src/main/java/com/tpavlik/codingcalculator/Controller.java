package com.tpavlik.codingcalculator;

import com.tpavlik.codingcalculator.shannonFano.ShannonFanoMethod;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

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
    private TextArea visualResult;

    @FXML
    private TextArea result;

    @FXML
    private Label codeEfficiency;

    @FXML
    private Label codeLength;

    @FXML
    private Label count;

    @FXML
    private Label probability;

    @FXML
    private Tab shannonFanoTab;

    @FXML
    private Tab huffmanTab;

    List<TextField> textFields = new ArrayList<>();

    private Data shannonData = new Data();

    private Data huffmanData = new Data();

    @FXML
    protected void generate() {
        if (shannonFanoTab.isSelected()) {
            generateShannonFano();
        }
        if (huffmanTab.isSelected()) {
            generateHuffman();
        }
    }

    @FXML
    protected void onSelect() {
        if (shannonFanoTab != null && shannonFanoTab.isSelected())
            setData(shannonData);
        if (huffmanTab != null && huffmanTab.isSelected())
            setData(huffmanData);
    }

    private void generateShannonFano() {
        List<Item> items = getInput();

        ShannonFanoMethod shannonFanoMethod = new ShannonFanoMethod();
        StringBuilder visualResult = new StringBuilder();
        StringBuilder result = new StringBuilder();
        List<Item> resultList = shannonFanoMethod.main(items);

        for (Item item : resultList) {
            visualResult.append(item);
            visualResult.append("\n");

            result.append(item.getResultBit() + "\n");
        }

        setVisualResult(visualResult.toString());
        setResult(result.toString());

        String codeEfficiency = CodingUtils.codeEfficiency(resultList);
        String codeLength = CodingUtils.codingLength(resultList);
        setCodeEfficiency(codeEfficiency);
        setCodeLength(codeLength);

        shannonData = new Data(visualResult.toString(), result.toString(), codeEfficiency, codeLength);
    }

    private void generateHuffman() {
        List<Item> items = getInput();

        HuffmanMethod huffmanMethod = new HuffmanMethod();
        StringBuilder visualResult = new StringBuilder();
        StringBuilder result = new StringBuilder();
        Item[][] resultList = huffmanMethod.main(items);
        List<Item> dataList = new ArrayList<>();

        for (int i = 0; i < resultList.length; i++) {
            for (int j = 0; j < resultList[i].length - 1; j++) {
                if (resultList[j][i] == null) {
                    continue;
                }
                visualResult.append(String.format("%.2f", resultList[j][i].getProbability()) + " | ");
            }
            visualResult.append("\n");
            result.append(resultList[0][i].getResultBit() + "\n");
            dataList.add(resultList[0][i]);
        }

        setVisualResult(visualResult.toString());
        setResult(result.toString());

        String codeEfficiency = CodingUtils.codeEfficiency(dataList);
        String codeLength = CodingUtils.codingLength(dataList);
        setCodeEfficiency(codeEfficiency);
        setCodeLength(codeLength);

        huffmanData = new Data(visualResult.toString(), result.toString(), codeEfficiency, codeLength);
    }

    private List<Item> getInput() {
        List<Item> items = new ArrayList<>();

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
        probability.setText("p=" + CodingUtils.sumDouble(items));

        try {
            CodingUtils.validate(items);
        } catch (Exception e) {
            setVisualResult(e.getMessage());
            return Collections.emptyList();
        }

        return items;
    }

    private void setData(Data data) {
        setVisualResult(data.getVisualResult());
        setResult(data.getResult());
        setCodeEfficiency((data.getCodeEfficiency()));
        setCodeLength(data.getCodeLength());
    }

    private void setVisualResult(String visualResult) {
        if (this.visualResult != null)
            this.visualResult.setText(visualResult);
    }

    private void setResult(String result) {
        if (this.result != null)
            this.result.setText(result);
    }

    private void setCodeEfficiency(String efficiency) {
        if (this.codeEfficiency != null)
            this.codeEfficiency.setText("Code efficiency: " + efficiency + "%");
    }

    private void setCodeLength(String codeLength) {
        if (this.codeLength != null)
            this.codeLength.setText("Average codeword length: " + codeLength + "b");
    }

    @FXML
    protected void clear() {
        Data emptyData = new Data();

        if (shannonFanoTab.isSelected())
            shannonData = new Data();
        if (huffmanTab.isSelected())
            huffmanData = new Data();

        textFields.forEach(textField -> textField.setText(null));
        count.setText("n=0");
        probability.setText("p=0.0");

        setData(emptyData);
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
                value = BigDecimal.valueOf(1.0).subtract(sum);
            }
            textFields.get(i).setText(String.valueOf(value));
            sum = sum.add(value);
        }

        textFields.get(textFields.size() - 1).setText(String.valueOf(BigDecimal.valueOf(1.0).setScale(2, RoundingMode.HALF_UP).subtract(sum).doubleValue()));
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