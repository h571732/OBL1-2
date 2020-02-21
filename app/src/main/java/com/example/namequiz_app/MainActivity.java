package com.example.namequiz_app;

import static com.example.namequiz_app.Menu.name;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_ANIMAL = 1;
    public static final String SHARED_PREFS = "sharedPrefs"; //name for our sharedprefs

    Button takeQuiz;
    Button addAnimalBtn;
    Button saveBtn;
    ArrayList<Animals> aList = new ArrayList<>();

    RecyclerView recyclerView; //Will contain the recyclerView which we made in the XML
    RecyclerView.Adapter adapter; //Works like a bridge between our data (The arrayList) and the recyclerView


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loadAnimalsList(); // Loads the animals list when starts if saved

        //When clicking on the save button
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAnimalsList();
            }
        });



        //Start add activity
        addAnimalBtn = findViewById(R.id.add);
        addAnimalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addingActivity();

            }
        });

        //Starts the quiz activity
       takeQuiz = findViewById(R.id.quizBtn);
        takeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizActivity();
                                        }
                                    });

        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // creating recyclerview adapter
        adapter = new Adapter(this,aList );
        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }

    //When the chosen context in the menu is selected
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        //Handle item selection
        switch (item.getItemId()) {
            case R.id.OwnerName:
                Toast.makeText(this, "Name of owner is + " + name, Toast.LENGTH_SHORT).show();
                return true;
                case R.id.Help:
                Toast.makeText(this, "help",Toast.LENGTH_SHORT).show();
                return true;
                default:
                return super.onContextItemSelected(item);

        }
    }

    //Creates the option menu to show OwnerName
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.name_menu, menu);
        return true;
    }


    //Quiz Acttivity
    private void quizActivity() {
        Intent intent = new Intent(this, QuizActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("arraylist", aList);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

//Add activity
    private void addingActivity() {
        Intent intent = new Intent(this, Add.class);
        startActivityForResult(intent, ADD_ANIMAL);


    }
// Result waiting for the add acivity
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // results that comes from the add animal
        if (requestCode == ADD_ANIMAL) {
            if (resultCode == RESULT_OK) {
                String newName = data.getStringExtra("aName");

                int newImage = data.getIntExtra("aImage", 0);
                Animals newAnimals = new Animals(newImage, newName);
                aList.add(newAnimals);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Added: " + newName, Toast.LENGTH_LONG).show();
            }



        }

    }
        //Save our ArrayList to sharedPreferences
    private  void saveAnimalsList() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedprefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson(); //new instance of Gson which is used to convert java object into json
        String json = gson.toJson(aList);//contain our arraylist in json form, json is used for data interchanging
        editor.putString("Animals list", json);
        editor.apply();


    }

    //To load our ArrayList from sharedPreferences
    private void loadAnimalsList() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedprefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json =sharedPreferences.getString("Animals list", null);
        Type type = new TypeToken<ArrayList<Animals>>() {}.getType();
        aList = gson.fromJson(json, type);

        //If our Arraylist is empty
        if(aList == null) {
            //Then adds our already chosen animals to the list
            aList = new ArrayList<Animals>();
            aList.add(new Animals(R.drawable.penguin, "Penguin"));
            aList.add(new Animals(R.drawable.rabbit, "Rabbit"));
            aList.add(new Animals(R.drawable.sloth, "sloth"));
            aList.add(new Animals(R.drawable.lion, "lion"));
        }




    }
}