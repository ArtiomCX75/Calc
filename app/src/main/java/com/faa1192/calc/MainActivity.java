package com.faa1192.calc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
public TextView enterField;
public TextView resultField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toSetOnClickListener();
        toDisableButtons();
        enterField = (TextView) findViewById(R.id.enter_field);
        resultField = (TextView) findViewById(R.id.result_field);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.num_mc:
                break;
            case R.id.num_mr:
                break;
            case R.id.num_ms:
                break;
            case R.id.num_mplus:
                break;
            case R.id.num_mmin:
                break;
            case R.id.num_back:
                toBackSpace();
                break;
            case R.id.num_ce:
                enterField.setText("");
                trimZero();
                break;
            case R.id.num_c:
                enterField.setText("");
                resultField.setText("");
                trimZero();
                break;
            case R.id.num_sign:
                toChangeSign();
                break;
            case R.id.num_root:
                break;
            case R.id.num_7:
                enterNumber(7);
                break;
            case R.id.num_8:
                enterNumber(8);
                break;
            case R.id.num_9:
                enterNumber(9);
                break;
            case R.id.num_div:
                break;
            case R.id.num_proc:
                break;
            case R.id.num_4:
                enterNumber(4);
                break;
            case R.id.num_5:
                enterNumber(5);
                break;
            case R.id.num_6:
                enterNumber(6);
                break;
            case R.id.num_mult:
                break;
            case R.id.num_1divx:
                break;
            case R.id.num_1:
                enterNumber(1);
                break;
            case R.id.num_2:
                enterNumber(2);
                break;
            case R.id.num_3:
                enterNumber(3);
                break;
            case R.id.num_min:
                break;
            case R.id.num_eq:
                break;
            case R.id.num_0:
                enterNumber(0);
                break;
            case R.id.num_dot:
                addDot();
                break;
            case R.id.num_plus:
                break;


        }


    }

    public void toSetOnClickListener(){
        findViewById(R.id.num_mc).setOnClickListener(this);
        findViewById(R.id.num_mr).setOnClickListener(this);
        findViewById(R.id.num_ms).setOnClickListener(this);
        findViewById(R.id.num_mplus).setOnClickListener(this);
        findViewById(R.id.num_mmin).setOnClickListener(this);
        findViewById(R.id.num_back).setOnClickListener(this);
        findViewById(R.id.num_ce).setOnClickListener(this);
        findViewById(R.id.num_c).setOnClickListener(this);
        findViewById(R.id.num_sign).setOnClickListener(this);
        findViewById(R.id.num_root).setOnClickListener(this);
        findViewById(R.id.num_7).setOnClickListener(this);
        findViewById(R.id.num_8).setOnClickListener(this);
        findViewById(R.id.num_9).setOnClickListener(this);
        findViewById(R.id.num_div).setOnClickListener(this);
        findViewById(R.id.num_proc).setOnClickListener(this);
        findViewById(R.id.num_4).setOnClickListener(this);
        findViewById(R.id.num_5).setOnClickListener(this);
        findViewById(R.id.num_6).setOnClickListener(this);
        findViewById(R.id.num_mult).setOnClickListener(this);
        findViewById(R.id.num_1divx).setOnClickListener(this);
        findViewById(R.id.num_1).setOnClickListener(this);
        findViewById(R.id.num_2).setOnClickListener(this);
        findViewById(R.id.num_3).setOnClickListener(this);
        findViewById(R.id.num_min).setOnClickListener(this);
        findViewById(R.id.num_eq).setOnClickListener(this);
        findViewById(R.id.num_0).setOnClickListener(this);
        findViewById(R.id.num_dot).setOnClickListener(this);
        findViewById(R.id.num_plus).setOnClickListener(this);
    }

    public void enterNumber(Integer num){
        if(isSizeExceed())
            return;
        enterField.setText(enterField.getText()+num.toString());
        trimZero();
    }

    public void addDot(){
        if(!enterField.getText().toString().contains("."))
            enterField.setText(enterField.getText()+".");
        trimZero();
    }

    public void trimZero(){
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


    public void toDisableButtons() {
        ((Button) findViewById(R.id.num_mc)).setEnabled(false);
        ((Button) findViewById(R.id.num_mr)).setEnabled(false);
        ((Button) findViewById(R.id.num_ms)).setEnabled(false);
        ((Button) findViewById(R.id.num_mplus)).setEnabled(false);
        ((Button)  findViewById(R.id.num_mmin)).setEnabled(false);
        ((Button)   findViewById(R.id.num_back)).setEnabled(true);
        ((Button)    findViewById(R.id.num_ce)).setEnabled(true);
        ((Button)    findViewById(R.id.num_c)).setEnabled(true);
        ((Button)    findViewById(R.id.num_sign)).setEnabled(true);
        ((Button)     findViewById(R.id.num_root)).setEnabled(false);
        ((Button)     findViewById(R.id.num_7)).setEnabled(true);
        ((Button)     findViewById(R.id.num_8)).setEnabled(true);
        ((Button)     findViewById(R.id.num_9)).setEnabled(true);
        ((Button)     findViewById(R.id.num_div)).setEnabled(false);
        ((Button)     findViewById(R.id.num_proc)).setEnabled(false);
        ((Button)     findViewById(R.id.num_4)).setEnabled(true);
        ((Button)      findViewById(R.id.num_5)).setEnabled(true);
        ((Button)      findViewById(R.id.num_6)).setEnabled(true);
        ((Button)     findViewById(R.id.num_mult)).setEnabled(false);
        ((Button)     findViewById(R.id.num_1divx)).setEnabled(false);
        ((Button)     findViewById(R.id.num_1)).setEnabled(true);
        ((Button)     findViewById(R.id.num_2)).setEnabled(true);
        ((Button)     findViewById(R.id.num_3)).setEnabled(true);
        ((Button)     findViewById(R.id.num_min)).setEnabled(false);
        ((Button)     findViewById(R.id.num_eq)).setEnabled(false);
        ((Button)     findViewById(R.id.num_0)).setEnabled(true);;
        ((Button)     findViewById(R.id.num_dot)).setEnabled(true);
        ((Button)     findViewById(R.id.num_plus)).setEnabled(false);
    }

    public boolean isSizeExceed(){
        return enterField.getText().toString().length()>9?true:false;
    }

    public void toBackSpace(){
        String s = enterField.getText().toString();
        if(s.length()>0)
            enterField.setText(s.substring(0, s.length()-1));
        trimZero();
    }

    public void toChangeSign(){
        String s = enterField.getText().toString();
        if(s.substring(0, 1).equals("-"))
            enterField.setText(s.substring(1));
        else
            enterField.setText("-"+s);
        trimZero();
    }
}
