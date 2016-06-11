package com.faa1192.calc;

import android.util.Log;
import android.widget.TextView;

import java.math.BigDecimal;

/**
 * Created by faa11 on 11.06.2016.
 */

public class Calc {
    public static TextView enterField;
    public static TextView resultField;
    private static boolean isDotPresent = false;
    private static boolean isDotNeed = false;

    //    private static boolean sign; //false = '-', true = '+'
    private static BigDecimal curInput = new BigDecimal(0);
static {
    isDotNeed=false;
    isDotPresent=false;
    curInput = new BigDecimal(0);
}


    public static void toChangeSign(){
      /*  String s = enterField.getText().toString();
        if(s.substring(0, 1).equals("-"))
            enterField.setText(s.substring(1));
        else
            enterField.setText("-"+s);
        trimZero();*/
        curInput=curInput.multiply(new BigDecimal(-1));
        resultField.setText(curInput.toString());
        refreshInput();
    }

    private static void trimZero1(){
        String sign = "";
        String s = enterField.getText().toString();
        if(s.length()==0||s.equals("0")) {
            enterField.setText("0");
            return;
        }

        if(s.substring(0, 1).equals("-")){
            sign="-";
            s=s.substring(1);
        }

        for(int i=0; i<s.length();i++) {
            if (s.substring(0, 1).equals("0")) {
                s = s.substring(1);
            } else
                break;
        }
        if(s.length()==0||s.equals("0")) {
            s = "0";
            sign="";
        }
        if(s.substring(0, 1).equals("."))
            s="0"+s;
        enterField.setText(sign+s.toString());
    }

    public static void addDot(){
        Log.e("my", "dotPresent: "+isDotPresent);
        Log.e("my", "dotNeed: "+isDotNeed);
        if(isDotPresent)
            return;
        isDotNeed=true;
        Log.e("my", "dotPresent: "+isDotPresent);
        Log.e("my", "dotNeed: "+isDotNeed);
        refreshInput();
    }

    public static boolean isSizeExceed(){
        return enterField.getText().toString().length()>9?true:false;
    }

    public static void toBackSpace(){
        String s = enterField.getText().toString();
        String res = "";
        res=s.substring(0, s.length()-1);
        if(s.length()>0) {
            enterField.setText(res);
            curInput = new BigDecimal(res);
            isDotPresent = res.contains(".");

        }
      //  trimZero();
    }

    public static void clearEntry(){
        isDotPresent=false;
        isDotNeed=false;
        curInput = new BigDecimal(0);
        refreshInput();
    }

    public static void clear(){
        resultField.setText("");
        clearEntry();
    }

    private static void getInput1(){
        isDotPresent = enterField.getText().toString().contains(".");
        curInput = new BigDecimal(enterField.getText().toString());
    }
    public static void in(Integer i){
        setInput(i);
    }

    private static void setInput(Integer i){
        curInput = new BigDecimal(enterField.getText().toString()+i);
        Log.e("my", "enter: "+i);
        refreshInput();
    }
private static void refreshInput(){
    String s = curInput.toString();
    if(isDotNeed&&!isDotPresent) {
        s += '.';
        isDotPresent=true;
        isDotNeed=false;
    }

    Log.e("my", "enterField: "+curInput.toString());
    enterField.setText(s);
}


}
