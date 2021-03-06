package com.faa1192.calc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.faa1192.calc.Calc.inputField;
import static com.faa1192.calc.Calc.memoryField;
import static com.faa1192.calc.Calc.resultField;
import static com.faa1192.calc.Calc.signField;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toSetOnClickListener();
        toDisableButtons();
        inputField = (TextView) findViewById(R.id.enter_field);
        resultField = (TextView) findViewById(R.id.result_field);
        signField = (TextView) findViewById(R.id.sign_field);
        memoryField = (TextView) findViewById(R.id.memory_field);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null) {
            Calc.setBundle(savedInstanceState.getBundle("state"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("state", Calc.getBundle());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.num_mc:
                Log.e("my", "mc");
                Calc.doOperation(Calc.operations.mc);
                break;
            case R.id.num_mr:
                Calc.doOperation(Calc.operations.mr);
                break;
            case R.id.num_ms:
                Calc.doOperation(Calc.operations.ms);
                break;
            case R.id.num_mplus:
                Calc.doOperation(Calc.operations.mplus);
                break;
            case R.id.num_mmin:
                Calc.doOperation(Calc.operations.mmin);
                break;
            case R.id.num_back:
                Calc.toBackSpace();
                break;
            case R.id.num_ce:
                Calc.clearEntry();
                break;
            case R.id.num_c:
                Calc.clear();
                break;
            case R.id.num_sign:
                Calc.toChangeSign();
                break;
            case R.id.num_root:
                break;
            case R.id.num_7:
                Calc.in(7);
                break;
            case R.id.num_8:
                Calc.in(8);
                break;
            case R.id.num_9:
                Calc.in(9);
                break;
            case R.id.num_div:
                Calc.doAction(Calc.action.dev);
                break;
            case R.id.num_proc:
                break;
            case R.id.num_4:
                Calc.in(4);
                break;
            case R.id.num_5:
                Calc.in(5);
                break;
            case R.id.num_6:
                Calc.in(6);
                break;
            case R.id.num_mult:
                Calc.doAction(Calc.action.mult);
                break;
            case R.id.num_1divx:
                break;
            case R.id.num_1:
                Calc.in(1);
                break;
            case R.id.num_2:
                Calc.in(2);
                break;
            case R.id.num_3:
                Calc.in(3);
                break;
            case R.id.num_min:
                Calc.doAction(Calc.action.min);
                break;
            case R.id.num_eq:
                Calc.doAction(Calc.action.eq);
                break;
            case R.id.num_0:
                Calc.in(0);
                break;
            case R.id.num_dot:
                Calc.addDot();
                break;
            case R.id.num_plus:
                Calc.doAction(Calc.action.plus);
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

    public void toDisableButtons() {
        ((Button) findViewById(R.id.num_mc)).setEnabled(true);
        ((Button) findViewById(R.id.num_mr)).setEnabled(true);
        ((Button) findViewById(R.id.num_ms)).setEnabled(true);
        ((Button) findViewById(R.id.num_mplus)).setEnabled(true);
        ((Button)  findViewById(R.id.num_mmin)).setEnabled(true);
        ((Button)   findViewById(R.id.num_back)).setEnabled(true);
        ((Button)    findViewById(R.id.num_ce)).setEnabled(true);
        ((Button)    findViewById(R.id.num_c)).setEnabled(true);
        ((Button)    findViewById(R.id.num_sign)).setEnabled(true);
        ((Button)     findViewById(R.id.num_root)).setEnabled(false);
        ((Button)     findViewById(R.id.num_7)).setEnabled(true);
        ((Button)     findViewById(R.id.num_8)).setEnabled(true);
        ((Button)     findViewById(R.id.num_9)).setEnabled(true);
        ((Button)     findViewById(R.id.num_div)).setEnabled(true);
        ((Button)     findViewById(R.id.num_proc)).setEnabled(false);
        ((Button)     findViewById(R.id.num_4)).setEnabled(true);
        ((Button)      findViewById(R.id.num_5)).setEnabled(true);
        ((Button)      findViewById(R.id.num_6)).setEnabled(true);
        ((Button)     findViewById(R.id.num_mult)).setEnabled(true);
        ((Button)     findViewById(R.id.num_1divx)).setEnabled(false);
        ((Button)     findViewById(R.id.num_1)).setEnabled(true);
        ((Button)     findViewById(R.id.num_2)).setEnabled(true);
        ((Button)     findViewById(R.id.num_3)).setEnabled(true);
        ((Button)     findViewById(R.id.num_min)).setEnabled(true);
        ((Button)     findViewById(R.id.num_eq)).setEnabled(true);
        ((Button)     findViewById(R.id.num_0)).setEnabled(true);;
        ((Button)     findViewById(R.id.num_dot)).setEnabled(true);
        ((Button)     findViewById(R.id.num_plus)).setEnabled(true);
    }

}
