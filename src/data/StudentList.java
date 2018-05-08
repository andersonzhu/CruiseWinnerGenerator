package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentList {

    private static StudentList ourInstance = new StudentList();

    private static final Map<String, String> map = new HashMap<>();

    public static StudentList getInstance() {
        return ourInstance;
    }

    private StudentList() {}

    private String capitalizeFirstLetter(String word) {
        if (word == null || word.length() == 0) {
            return word;
        }

        String result = "";
        String[] wordPieces = word.split(" ");
        for (String s : wordPieces) {
            result += s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
            result += " ";
        }
        return result;
    }

    public Map<String, String> getStudentList() throws IOException {

        InputStream in = this.getClass().getResourceAsStream("data.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        try {
            String line;
            while ((line = br.readLine()) != null){
                String[] itemPieces = line.split(",");

                String studentID = itemPieces[0];
                String firstName = capitalizeFirstLetter(itemPieces[2]);
                String lastName = capitalizeFirstLetter(itemPieces[3]);
                String name = firstName + " " + lastName;

                map.put(studentID, name);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return map;
    }
}