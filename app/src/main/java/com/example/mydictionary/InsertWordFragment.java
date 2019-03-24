package com.example.mydictionary;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class InsertWordFragment extends Fragment {

    private Button writeBtn;
    private EditText enterText1;
    private EditText enterText2;
    private Button goBackBtn;
    FileModifier fm;


    public InsertWordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insert_word, container, false);
        fm = new FileModifier(getActivity());


        writeBtn = view.findViewById(R.id.insertBtn);
        enterText1 = view.findViewById(R.id.eng_edit_text);
        enterText2 = view.findViewById(R.id.mk_edit_text);
        goBackBtn = view.findViewById(R.id.goBack);

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileContents = "\n" + enterText1.getText().toString() + "\t\t\t" + enterText2.getText().toString() + "\n";
                fm.addNewWordsToFile(fileContents);
            }
        });

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
