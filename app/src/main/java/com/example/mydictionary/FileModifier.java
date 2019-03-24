package com.example.mydictionary;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileModifier {

    final String fileName = "engmk.txt";
    String[] words = new String[]{"nail\t\t\tшајка", "\nnail\t\t\tнокт", "\nbark\t\t\tкора",
            "\nbark\t\t\tлаење", "\npool\t\t\tбазен", "\npool\t\t\tбилијардо", "\nracket\t\t\tрекет", "\nracket\t\t\tбучава"};
    ArrayList<String> wordsFromFile = new ArrayList<>();
    FileOutputStream fileOutputStream;

    private Context mContext;

    public FileModifier(Context context) {
        mContext = context;
    }

    public void writeWordsToFile() {
        File file = mContext.getFileStreamPath(fileName);
        if (!file.exists()) {
            try {
                fileOutputStream = mContext.openFileOutput(fileName, Context.MODE_APPEND);
                for (String s : words) {
                    fileOutputStream.write(s.getBytes());
                }
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<String> readWordsFromFile() {
        try {
            FileInputStream fis = mContext.openFileInput("engmk.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                wordsFromFile.add(line + System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wordsFromFile;
    }

    public void addNewWordsToFile(String newLineOfWords) {
        try {
            FileOutputStream fOut = mContext.openFileOutput(fileName, Context.MODE_APPEND);
            Writer w = new BufferedWriter(new OutputStreamWriter(fOut, StandardCharsets.UTF_8));
            w.write(newLineOfWords);
            w.close();
            Toast.makeText(mContext, "Word Saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
