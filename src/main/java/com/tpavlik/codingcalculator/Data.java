package com.tpavlik.codingcalculator;

public class Data {

    private String visualResult;

    private String result;

    private String codeEfficiency;

    private String codeLength;

    public Data() {
        this.visualResult = null;
        this.result = null;
        this.codeEfficiency = "0,0";
        this.codeLength = "0,0";
    }

    public Data(String visualResult, String result, String codeEfficiency, String codeLength) {
        this.visualResult = visualResult;
        this.result = result;
        this.codeEfficiency = codeEfficiency;
        this.codeLength = codeLength;
    }

    public String getVisualResult() {
        return visualResult;
    }

    public String getResult() {
        return result;
    }

    public String getCodeEfficiency() {
        return codeEfficiency;
    }

    public String getCodeLength() {
        return codeLength;
    }

}
