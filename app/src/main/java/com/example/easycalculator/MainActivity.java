package com.example.easycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView resultTv,solutionTv;
    MaterialButton button_c,button_open_bracket, button_close_bracket,button_dot,button_equals;
    MaterialButton button_one,button_two,button_three,button_four,button_five,button_six, button_seven,button_eight,button_nine,button_zero;
    MaterialButton button_plus, button_minus,button_div, button_mul, button_ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv=findViewById(R.id.result_tv);
        solutionTv=findViewById(R.id.solution_tv);

        assignId(button_c,R.id.button_c);
        assignId(button_ac,R.id.button_AC);
        assignId(button_close_bracket,R.id.button_close_bracket);
        assignId(button_open_bracket,R.id.button_open_bracket);
        assignId(button_one,R.id.button_one);
        assignId(button_two,R.id.button_two);
        assignId(button_three,R.id.button_three);
        assignId(button_four,R.id.button_four);
        assignId(button_five,R.id.button_five);
        assignId(button_six,R.id.button_six);
        assignId(button_seven,R.id.button_seven);
        assignId(button_eight,R.id.button_eight);
        assignId(button_nine,R.id.button_nine);
        assignId(button_zero,R.id.button_zero);
        assignId(button_plus,R.id.button_plus);
        assignId(button_minus,R.id.button_minus);
        assignId(button_mul,R.id.button_mul);
        assignId(button_div,R.id.button_div);
        assignId(button_dot,R.id.button_dot);
        assignId(button_equals,R.id.button_equals);

    }
    void assignId(MaterialButton btn, int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button= (MaterialButton) view;
        String buttonText=button.getText().toString();
        String dataToCalculate =solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else{
            dataToCalculate=dataToCalculate+buttonText;
        }

        solutionTv.setText(dataToCalculate);
        String finalResult=getResult(dataToCalculate);
        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }
    }

    String getResult(String data){
        try {
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initSafeStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }
        catch (Exception e){
            return "Err";
        }
    }
}