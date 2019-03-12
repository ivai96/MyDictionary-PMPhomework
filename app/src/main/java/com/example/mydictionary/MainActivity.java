package com.example.mydictionary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String getWords[];
    ArrayList<String> wordsFromFile = new ArrayList<>();
    Button searchBtn;
    ArrayList<String> showList = new ArrayList<>();


    String fileName = "engmk.txt";
    String[] words = new String[]{"nail\tшајка", "\nnail\tнокт", "\nbark\tкора",
            "\nbark\tлаење", "\npool\tбазен", "\npool\tбилијардо", "\nracket\tрекет", "\nracket\tбучава"};
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();
        final EditText insertedWord = findViewById(R.id.word_edit_text);
        searchBtn = findViewById(R.id.search_btn);
        listView = findViewById(R.id.myListView);

        FileOutputStream fileOutputStream;

        File file = getBaseContext().getFileStreamPath(fileName);
        if (!file.exists()) {
//zapisi ja listata vo txt
            try {
                fileOutputStream = openFileOutput(fileName, Context.MODE_APPEND);
                for (String s : words) {
                    fileOutputStream.write(s.getBytes());
                }
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//procitaj ja listata
        try {
            FileInputStream fis = context.openFileInput("engmk.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                wordsFromFile.add(line + System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showList.clear();
                String word = insertedWord.getText().toString();
                for (String s : wordsFromFile) {
                    if (s.contains(word) && !(word.equals(""))) {
                        showList.add(s);
                    }
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, showList);
                listView.setAdapter(adapter);
            }
        });
    }

    public void openInsertLayout(View view) {
        Intent intent = new Intent(this, InsertWordActivity.class);
        startActivity(intent);
    }
}

