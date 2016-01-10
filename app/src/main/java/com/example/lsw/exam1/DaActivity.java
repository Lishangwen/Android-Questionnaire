package com.example.lsw.exam1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class DaActivity extends AppCompatActivity {
    private int count;
    private int current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da);

        DBService dbService=new DBService();
        final List<Question> list=dbService.GetDisbilityAssementQuetions();
        count=list.size();
        current=0;

        final TextView tv_question=(TextView)findViewById(R.id.question_da);
        final RadioButton[] radioButtons=new RadioButton[4];
        radioButtons[0]=(RadioButton)findViewById(R.id.answerA_da);
        radioButtons[1]=(RadioButton)findViewById(R.id.answerB_da);
        radioButtons[2]=(RadioButton)findViewById(R.id.answerC_da);
        radioButtons[3]=(RadioButton)findViewById(R.id.answerD_da);

        Button btn_next=(Button)findViewById(R.id.next_da);
        Button btn_previous=(Button)findViewById(R.id.previous_da);

        final RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radioGroup_da);

        Question q=list.get(0);
        tv_question.setText(q.question);
        radioButtons[0].setText(q.answerA);
        radioButtons[1].setText(q.answerB);
        radioButtons[2].setText(q.answerC);
        radioButtons[3].setText(q.answerD);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current < count - 1) {
                    current++;
                    Question q = list.get(current);
                    tv_question.setText(q.question);
                    radioButtons[0].setText(q.answerA);
                    radioButtons[1].setText(q.answerB);
                    radioButtons[2].setText(q.answerC);
                    radioButtons[3].setText(q.answerD);

                    if ((current >= 25) && (current <= 27)) {
                        radioButtons[0].setVisibility(View.VISIBLE);
                        radioButtons[1].setVisibility(View.VISIBLE);
                        radioButtons[2].setVisibility(View.INVISIBLE);
                        radioButtons[3].setVisibility(View.INVISIBLE);
                    } else {
                        radioButtons[0].setVisibility(View.VISIBLE);
                        radioButtons[1].setVisibility(View.VISIBLE);
                        radioButtons[2].setVisibility(View.VISIBLE);
                        radioButtons[3].setVisibility(View.VISIBLE);
                    }


                    radioGroup.clearCheck();

                    if (q.select_answer != -1) {
                        radioButtons[q.select_answer].setChecked(true);
                    }

                }

            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current > 0) {
                    current--;
                    Question q = list.get(current);
                    tv_question.setText(q.question);
                    radioButtons[0].setText(q.answerA);
                    radioButtons[1].setText(q.answerB);
                    radioButtons[2].setText(q.answerC);
                    radioButtons[3].setText(q.answerD);
                    radioGroup.clearCheck();


                    if ((current >= 25) && (current <= 26)) {
                        radioButtons[0].setVisibility(View.VISIBLE);
                        radioButtons[1].setVisibility(View.VISIBLE);
                        radioButtons[2].setVisibility(View.INVISIBLE);
                        radioButtons[3].setVisibility(View.INVISIBLE);
                    } else {
                        radioButtons[0].setVisibility(View.VISIBLE);
                        radioButtons[1].setVisibility(View.VISIBLE);
                        radioButtons[2].setVisibility(View.VISIBLE);
                        radioButtons[3].setVisibility(View.VISIBLE);
                    }
                    if (q.select_answer != -1) {
                        radioButtons[q.select_answer].setChecked(true);
                    }

                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < 4; i++) {
                    if (radioButtons[i].isChecked() == true) {
                        list.get(current).select_answer = i;
                        break;
                    }
                }
            }
        });
    }
}
