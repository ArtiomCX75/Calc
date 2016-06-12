package com.faa1192.calc;

import android.util.Log;
import android.widget.TextView;

import java.math.BigDecimal;

import static com.faa1192.calc.Calc.action.eq;

/**
 * Created by faa11 on 11.06.2016.
 */

public class Calc {
    public static TextView enterField;
    public static TextView resultField;
    public static TextView signField;

    private static boolean isDotPresent = false;
    private static boolean isDotNeed = false;
    private static boolean isNeedClean = false;
    private static boolean isInputClean = true;
    private static Calc.action lastAct = null;

    private static BigDecimal curInput = new BigDecimal(0);
    private static BigDecimal curResult = new BigDecimal(0);

    enum action {
        eq, plus, min, mult, dev, proc
    }

    enum functions {
        mc, mr, ms, mplus, mmin, root, divx
    }

    static {
        isDotNeed=false;
        isDotPresent=false;
        curInput = new BigDecimal(0);
        curResult = new BigDecimal(0);
        lastAct = null;
    }

    public static void toChangeSign(){
        curInput=curInput.multiply(new BigDecimal(-1));
        refreshInput();
    }

    public static void addDot(){
        if(isDotPresent)
            return;
        isDotNeed=true;
        refreshInput();
    }

    public static void toBackSpace(){
        String s = enterField.getText().toString();
        String res = "";
        res=s.substring(0, s.length()-1);
        if(res.length()<1)
            res="0";
        if(res.length()>0) {
            enterField.setText(res);
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
        }
        else
            s = enterField.getText().toString() + i;
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
        enterField.setText(s);
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
        signField.setText(" ");
        if(tempAct==action.plus)
            signField.setText("+");
        if(tempAct==action.min)
            signField.setText("-");
        if(tempAct==action.dev)
            signField.setText("/");
        if(tempAct==action.mult)
            signField.setText("*");
    }

    private static void print(){
        Log.e("my", "isNeedClean: "+isNeedClean+"  isInputClean: "+isInputClean+"  last: "+lastAct);
    }
}