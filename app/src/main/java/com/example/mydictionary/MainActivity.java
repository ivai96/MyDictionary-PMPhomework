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


public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> wordsFromFile = new ArrayList<>();
    Button searchBtn;
    ArrayList<String> showList = new ArrayList<>();

    EditText insertedWord;


    String fileName = "engmk.txt";
    String[] words = new String[]{"nail\t\t\tшајка", "\nnail\t\t\tнокт", "\nbark\t\t\tкора",
            "\nbark\t\t\tлаење", "\npool\t\t\tбазен", "\npool\t\t\tбилијардо", "\nracket\t\t\tрекет", "\nracket\t\t\tбучава"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();

        insertedWord = findViewById(R.id.word_edit_text);
        searchBtn = findViewById(R.id.search_btn);
        listView = findViewById(R.id.myListView);

        FileModifier fm = new FileModifier(this);
        fm.writeWordsToFile();
        wordsFromFile = fm.readWordsFromFile();


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

