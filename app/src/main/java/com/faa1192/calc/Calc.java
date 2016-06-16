package com.faa1192.calc;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;

import static com.faa1192.calc.Calc.action.eq;

/**
 * Created by faa11 on 11.06.2016.
 */

public class Calc {
    public static TextView inputField;
    public static TextView resultField;
    public static TextView signField;
    public static TextView memoryField;

    private static boolean isDotPresent = false;
    private static boolean isDotNeed = false;
    private static boolean isNeedClean = false;
    private static boolean isInputClean = true;
    private static Calc.action lastAct = null;

    private static BigDecimal curInput = new BigDecimal(0);
    private static BigDecimal curResult = new BigDecimal(0);
    private static BigDecimal curMemory = new BigDecimal(0);



    enum action {
        eq, plus, min, mult, dev, proc
    }

    enum operations {
        mc, mr, ms, mplus, mmin, root, divx
    }

    static {
        isDotNeed=false;
        isDotPresent=false;
        curInput = new BigDecimal(0);
        curResult = new BigDecimal(0);
        curMemory = new BigDecimal(0);
        lastAct = null;
    }

    public static void toChangeSign(){
        curInput=curInput.multiply(new BigDecimal(-1));
        refreshInput();
    }

    public static void addDot(){
        if(isDotPresent)
            return;
        if(isNeedClean) {
            lastAct=null;
            curResult = new BigDecimal(0);
            refreshResult();
            isNeedClean=false;
            printMessage("");
        }
        isDotNeed=true;
        refreshInput();
    }

    public static void toBackSpace(){
        String s = inputField.getText().toString();
        String res = "";
        res=s.substring(0, s.length()-1);
        if(res.length()<1)
            res="0";
        if(res.length()>0) {
            inputField.setText(res);
            curInput = new BigDecimal(res);
            isDotPresent = res.contains(".");
        }
    }

    public static void clearEntry(){
        isInputClean = true;
        isDotPresent=false;
        isDotNeed=false;
        curInput = new BigDecimal(0);
        refreshInput();
    }

    public static void clear(){
        lastAct=null;
        resultField.setText("");
        printMessage("");
        clearEntry();
    }


    public static void in(Integer i){
        String s;
        if(isNeedClean) {
            s = i.toString();
            lastAct=null;
            curResult = new BigDecimal(0);
            refreshResult();
            isNeedClean=false;
            printMessage("");
        }
        else
            s = inputField.getText().toString() + i;
        curInput = new BigDecimal(s);
        refreshInput();
        isInputClean = false;
    }

    private static void refreshInput(){
        String s = curInput.toPlainString();
        if(isDotNeed&&!isDotPresent) {
            s += '.';
            isDotPresent=true;
            isDotNeed=false;
        }
        inputField.setText(s);
}

    private static void refreshResult(){
        curResult = new BigDecimal(curResult.toPlainString());
        resultField.setText(curResult.toPlainString());
    }

    public static void doAction(Calc.action tempAct){
        printSign(tempAct);
        isNeedClean = false;
        if(!isInputClean) {
            if (lastAct == action.plus) {
                curResult = curResult.add(curInput);
                refreshResult();
            }
            if (lastAct == action.min) {
                curResult = curResult.subtract(curInput);
                refreshResult();
            }
            if (lastAct == action.mult) {
                curResult = curResult.multiply(curInput);
                refreshResult();
            }
            if (lastAct == action.dev) {
                try {
                    curResult = curResult.divide(curInput, MathContext.DECIMAL64);
                }
                catch (ArithmeticException e){
                    clear();
                    printMessage("Division by zero");
                    isNeedClean=true;
                    return;
                }
                refreshResult();
            }
            if (lastAct == action.eq) {
                if (resultField.getText().toString().isEmpty()) {
                    curResult = curInput;
                    refreshResult();
                }
            }
            if (lastAct == null) {
                String s = resultField.getText().toString();
                if (s.isEmpty() || s.equals("0")) {
                    curResult = curInput;
                    refreshResult();
                }
            }
        }
        if(tempAct!=eq)
            lastAct = tempAct;
        else
            isNeedClean=true;
        clearEntry();
    }

    private static void printSign(action tempAct) {
        signField.setText("");
        if(tempAct==action.eq)
            signField.setText("=");
        if(tempAct==action.plus)
            signField.setText("+");
        if(tempAct==action.min)
            signField.setText("-");
        if(tempAct==action.dev)
            signField.setText("/");
        if(tempAct==action.mult)
            signField.setText("*");
    }

    private static void printMessage(String s){
        signField.setText(s);
    }

    private static void printMemoryStatus(){
        if(!curMemory.toString().equals("0")&&!curMemory.toString().equals("0.0"))
            memoryField.setText("M");
        else
            memoryField.setText(" ");
    }

    private static void print(){
        Log.e("my", "isNeedClean: "+isNeedClean+"  isInputClean: "+isInputClean+"  last: "+lastAct);
    }


    public static Bundle getBundle(){
        Bundle b = new Bundle();
        b.putBoolean("isDotPresent", isDotPresent);
        b.putBoolean("isDotNeed", isDotNeed);
        b.putBoolean("isNeedClean", isNeedClean);
        b.putBoolean("isInputClean", isInputClean);
        if(lastAct!=null)
            b.putString("lastAct", lastAct.name());
        else
            b.putString("lastAct", null);
        b.putString("curInput", curInput.toPlainString());
        b.putString("curResult", curResult.toPlainString());
        b.putString("curMemory", curMemory.toPlainString());
        Log.e("my", "get: "+b.toString());
        return b;
    }

    public static void setBundle(Bundle b){
        Log.e("my", "set: "+b.toString());
        isDotPresent = b.getBoolean("isDotPresent", isDotPresent);
        isDotNeed = b.getBoolean("isDotNeed", isDotNeed);
        isNeedClean = b.getBoolean("isNeedClean", isNeedClean);
        isInputClean = b.getBoolean("isInputClean", isInputClean);
        if(b.getString("lastAct")!=null)
            lastAct = action.valueOf(b.getString("lastAct"));
        else
            lastAct=null;
        curInput = new BigDecimal(b.getString("curInput"));
        curResult = new BigDecimal(b.getString("curResult"));
        curMemory = new BigDecimal(b.getString("curMemory"));
        refreshResult();
        printSign(lastAct);
        refreshInput();
        if((isDotPresent==true)&&!(inputField.getText().toString().contains("."))){
            isDotPresent=false;
            addDot();
        }
        printMemoryStatus();
    }

    public static void doOperation(Calc.operations op){
        if(op==operations.ms){
            String s = resultField.getText().toString();
            if(s.length()>0&&!s.equals("0")&&!s.equals("0.0"))
                curMemory = new BigDecimal(s);
            else{
                s = inputField.getText().toString();
                if(s.length()>0)
                    curMemory = new BigDecimal(s);
            }
            printMemoryStatus();
            return;
        }
        if(op==operations.mc){
            curMemory = new BigDecimal(0);
            printMemoryStatus();
            return;
        }
        if(op==operations.mr){
            curInput = new BigDecimal(curMemory.toPlainString());
            refreshInput();
            printMemoryStatus();
            return;
        }
        if(op==operations.mplus){
            String s = resultField.getText().toString();
            if(s.length()>0&&!s.equals("0")&&!s.equals("0.0"))
            curMemory = curMemory.add(curResult);
            else{
                s = inputField.getText().toString();
                if(s.length()>0)
                    curMemory = curMemory.add(curInput);
            }
            printMemoryStatus();
            return;
        }
        if(op==operations.mmin){
            String s = resultField.getText().toString();
            if(s.length()>0&&!s.equals("0")&&!s.equals("0.0"))
                curMemory = curMemory.subtract(curResult);
            else{
                s = inputField.getText().toString();
                if(s.length()>0)
                    curMemory = curMemory.subtract(curInput);
            }
            printMemoryStatus();
            return;
        }
    }
}