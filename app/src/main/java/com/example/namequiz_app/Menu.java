package com.example.namequiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends AppCompatActivity {


    public static final String SHARED_PREFS = "sharedPrefs"; //name for our sharedprefs
    public static final String OWNER = "owner"; //sharedpreferences for our ownernames

     static String name =""; //string used to save owner name in through shareprefs

    //Deklarerer button, edittext and textview
    Button btn2;
    EditText ownerName;
    TextView showName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Conncecting with the layouts through the id's
        showName =  (TextView) findViewById(R.id.textName);
        ownerName = (EditText) findViewById(R.id.insertName);

      //Load data and update views from shareprefs
        loadData();
        updateViews();
        showName.setText(name); //textviewet vil få innholdet fra string-et

        //Listening to the action from our keyboard in editText, when inserting name of owner
        ownerName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    String input;
                if (actionId == EditorInfo.IME_ACTION_DONE) //When done with the action on the keyboard/writing in Owner Name
                {
                    input= v.getText().toString(); //
                    name = input;
                    saveData(); //Saves the name of the owner, method below
                    ownerName.setVisibility(View.GONE); //EdiText box disappears
                    recreate(); //recreate activity after the action
                    Toast.makeText(getApplicationContext(),"Name Saved", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });




            //When the START button gets clicked
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutQuiz();
            }
        });
    }
//Saves the data by using editor
   private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(OWNER, ownerName.getText().toString());
        editor.apply();


    }
    //After saving the data, we gotta load the data too, therefore this method
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        name = sharedPreferences.getString(OWNER,"");

    }
    //A method that will update our views
    //will look at the variables from the loadData and applies them to our views
    private void updateViews() {
//Dersom det er noe i navn feltet, da skal "enter name" edittexten være synlig
        if(name.equals("")){
            ownerName.setVisibility(View.VISIBLE);
        }else { //Ellers skal den være borte
            ownerName.setVisibility(View.GONE);
        }


    }
//Starts the MainActivity
    private void openAboutQuiz() {
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);
    }

}