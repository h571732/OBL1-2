package com.example.namequiz_app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;


public class Add extends AppCompatActivity {

    private static final int PERMISSION_CODE = 1; //Code to receive permission to open gallery and choose
    private static final int IMAGE_PICK_CODE = 2; //The picked image code

    ImageView imageView;
    EditText editText;
    Button addAnimal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //Connecting with the views in the layout
        editText = findViewById(R.id.enterName);
        imageView = findViewById(R.id.chooseImage);
        addAnimal = findViewById(R.id.buttonAdd);


        // image click button
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check runtime permissions
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                        // permission not granted, request it
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        // show popup for runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);
                    } else {
                        // permission already granted
                        pickImageFromGallery();
                    }
                } else {
                    // system os is less than marshmallows
                    pickImageFromGallery();
                }
            }
        });

        //When clicks on the add Animal button, the animal will get uploaded
        addAnimal = (Button) findViewById(R.id.buttonAdd);
        addAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aName = editText.getText().toString();
                int aImage = R.drawable.ic_photo_size_select_actual_black_24dp;

                Intent intent = new Intent();
                intent.putExtra("aImage", aImage);
                intent.putExtra("aName", aName);
                setResult(RESULT_OK, intent);
                finish(); //When done, leaves this acitivity
            }
        });
    }

    // The application gets access to the gallery/photos on the cellphone/emulator
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] ==
                        getPackageManager().PERMISSION_GRANTED) {
                    // permission was granted
                    pickImageFromGallery();
                } else {
                    // permission denied
                    Toast.makeText(this, "permission denied..", Toast.LENGTH_LONG);
                }
            }
        }
    }

    //Choose pictures from the gallery
    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*"); //All types of images
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    //The results from pick image, sets chosen image to image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imageView.setImageURI(data.getData());

        }


    }
}