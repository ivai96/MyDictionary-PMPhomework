package com.example.mydictionary;


import android.app.AlertDialog;
import android.content.DialogInterface;
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

                new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK)
                        .setTitle("Insert new word")
                        .setMessage("Are you sure you want to insert this entry?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                String fileContents = "\n" + enterText1.getText().toString() + "\t\t\t" + enterText2.getText().toString() + "\n";
                                fm.addNewWordsToFile(fileContents);
                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
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
