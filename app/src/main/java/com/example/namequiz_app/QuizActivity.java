package com.example.namequiz_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    String correctA;
    ImageView quizImage;
    EditText answer;
    TextView score;
    int image;
    Button checkAnswer, backButton;
    static int scorePoints = 0; // Remains im memory while the program is running
    static boolean answ = false;

    ArrayList<Animals> aList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        backButton = findViewById(R.id.back);
        quizImage = findViewById(R.id.quizImage);
        answer = findViewById(R.id.answer);
        score = findViewById(R.id.score);
        checkAnswer = findViewById(R.id.checkA);


        score.setText("Score: " + scorePoints );

        //Used to retrieve our arrayList into the quiz as a bundle
        Bundle bundle = getIntent().getBundleExtra("bundle");
        aList = (ArrayList<Animals>) bundle.getSerializable("arraylist");

        //Generate random numbers between our quiz's animals
        final Random r = new Random();
        int random = r.nextInt(aList.size());

        //Lager random quiz ved å ta hente navn og bilde fra listen
        correctA = aList.get(random).getName().toLowerCase();
        image = aList.get(random).getImage();
        quizImage.setImageResource(image); //expects an image from drawable

        //Tar deg tilbake til forrige acitivity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Ved trykk på denne sjekk svar knappen
        checkAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (answer.getText().toString().toLowerCase().equals(correctA)) {
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                    scorePoints++;
                    score.setText("Score: " + scorePoints);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong.. correct answer is " +correctA, Toast.LENGTH_SHORT).show();
                    score.setText("Score: " + scorePoints);
                }
                //Fjerner editTexten fra user input og genererer en ny random bilde og navn fra listen med dyr som neste slide i quizzen
                answer.setText(null); //går bort d du har skrevet inn også fortsetter quizzn
                int newRandom = r.nextInt(aList.size());
                image = aList.get(newRandom).getImage();
                correctA = aList.get(newRandom).getName().toLowerCase();
                quizImage.setImageResource(image);
                updateScorePoints(answ); //oppdaterer score for unit test

            }
        });

    }
        //Bruker denne metoden for å oppdatere points i test
    public static void updateScorePoints(Boolean answ) {
            if (answ) {
                scorePoints++;
            }
    }
}





