package com.example.mydictionary;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class InsertWordActivity extends AppCompatActivity {

    private Button writeBtn;
    private EditText enterText1;
    private EditText enterText2;
    //  InputStream nesto = this.getResources().openRawResource(R.raw.engmk);
    String file = "engmk.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_word);

        writeBtn = findViewById(R.id.insertBtn);
        enterText1 = findViewById(R.id.mk_edit_text);
        enterText2 = findViewById(R.id.eng_edit_text);

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileContents = "\n" + enterText1.getText().toString() + "\t" + enterText2.getText().toString() + "\n";

                try {
                    FileOutputStream fOut = openFileOutput(file, Context.MODE_APPEND);
                    fOut.write(fileContents.getBytes());
                    fOut.close();
                    Toast.makeText(getBaseContext(), "Word Saved", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
