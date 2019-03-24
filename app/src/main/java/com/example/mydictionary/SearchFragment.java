package com.example.mydictionary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }

    ArrayList<String> wordsFromFile = new ArrayList<>();
    ArrayList<String> showList = new ArrayList<>();
    Button searchBtn;
    EditText insertedWord;
    ListView listView;
    Button addNewBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        insertedWord = view.findViewById(R.id.word_edit_text);
        searchBtn = view.findViewById(R.id.search_btn);
        listView = view.findViewById(R.id.myListView);
        addNewBtn = view.findViewById(R.id.openInsertActivity);

        FileModifier fm = new FileModifier(getActivity());
        fm.writeWordsToFile();
        wordsFromFile = fm.readWordsFromFile();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showList.clear();
                String word = insertedWord.getText().toString();
                for (String s : wordsFromFile) {
                    if (s.contains(word) && !(word.equals(""))) {
                        showList.add(s);

                    }
                }
                insertedWord.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, showList);
        listView.setAdapter(adapter);

        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InsertWordActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
