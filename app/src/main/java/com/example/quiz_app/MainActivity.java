package com.example.quiz_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button trueButton;
    private Button falseButton;
    private TextView questionTextView;
    private ImageButton nextButton;
    private ImageButton previousButton;
    private int currentQuestionIndex=0;

    private Question[] questions= new Question[]{
            new Question(R.string.question_amendments, false),
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Question question= new Question(R.string.question_declaration,true);

        trueButton=findViewById(R.id.true_button);
        falseButton=findViewById(R.id.false_button);
        nextButton=findViewById(R.id.next_button);
        previousButton=findViewById(R.id.previous_button);
        questionTextView=findViewById(R.id.answer_text_view);


        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.true_button:
                checkAnswer(true);break;

            case R.id.false_button:
                checkAnswer(false);break;

            case R.id.next_button:
               currentQuestionIndex= (currentQuestionIndex+1)%questions.length;
               updateQuestion();
               break;

            case R.id.previous_button:
                if(currentQuestionIndex>0){
                currentQuestionIndex= (currentQuestionIndex-1)%questions.length;
                updateQuestion();}
                break;
        }
    }

    public void updateQuestion(){
        questionTextView.setText(questions[currentQuestionIndex].getAnswerResId());
    }

    private void checkAnswer(boolean userChooseAnswer){
       boolean correctAnswer= questions[currentQuestionIndex].isAnswerTrue();
       int toastMessageId;

       if(userChooseAnswer==correctAnswer){
//           Toast.makeText(MainActivity.this,R.string.correct_answer,Toast.LENGTH_SHORT).show();
           toastMessageId=R.string.correct_answer;
       }else {
           toastMessageId=R.string.wrong_answer;
       }

       Toast.makeText(MainActivity.this, toastMessageId,Toast.LENGTH_SHORT).show();
    }
}
