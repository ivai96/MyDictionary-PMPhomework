package com.example.mydictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] getWords;
    Button searchBtn;
    ArrayList<String> showList = new ArrayList<>();


    InputStream inputStreamCounter;
    BufferedReader bufferedReaderCounter;

    InputStream inputStreamLoader;
    BufferedReader bufferedReaderLoader;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.myListView);
        searchBtn = findViewById(R.id.search_btn);
        final EditText insertedWord = findViewById(R.id.word_edit_text);

        inputStreamCounter = this.getResources().openRawResource(R.raw.engmk);
        bufferedReaderCounter = new BufferedReader(new InputStreamReader(inputStreamCounter));

        inputStreamLoader = this.getResources().openRawResource(R.raw.engmk);
        bufferedReaderLoader = new BufferedReader(new InputStreamReader(inputStreamLoader));

        //count the number of rows or lines in txt
        try {
            while (bufferedReaderCounter.readLine() != null) {
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        getWords = new String[counter];

        try {
            for (int i = 0; i < counter; i++) {
                getWords[i] = bufferedReaderLoader.readLine().replace("\\t", "\t\t");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showList.clear();
                String word = insertedWord.getText().toString();
                for (int i = 0; i < getWords.length; i++) {
                    if (getWords[i].contains(word) && ! (word.equals(""))) {
                        showList.add(getWords[i]);
                    }
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, showList);
                listView.setAdapter(adapter);


            }
        });


    }

}
