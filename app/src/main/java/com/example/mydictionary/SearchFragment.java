package com.example.mydictionary;


import android.os.Bundle;
import android.support.annotation.Nullable;
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

    //  @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        Bundle bundle = getArguments();
//        zemiLista = bundle.getStringArrayList("listata");
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchBtn = view.findViewById(R.id.search_btn);
        insertedWord = view.findViewById(R.id.word_edit_text);

        ListView lw = view.findViewById(R.id.myListView);

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, showList);
        lw.setAdapter(adapter);

        return view;
    }


}
