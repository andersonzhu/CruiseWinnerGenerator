import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import data.StudentList;

import java.io.*;
import java.util.*;

public class Controller {

    private static Map<String, String> map;

    static {
        try {
            map = StudentList.getInstance().getStudentList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<String> studentIDs = new ArrayList<>(map.keySet());

    private static String winner;

    @FXML
    private JFXButton button;

    @FXML
    private Label studentName;

    @FXML
    private Label studentID;

    @FXML
    private JFXSpinner jfxSpinner;

    public Controller() throws IOException {
    }

    public void initialize() throws IOException {

        jfxSpinner.setVisible(false);
    }

    public void handleClick() throws InterruptedException {

        button.setDisable(true);
        jfxSpinner.setVisible(true);

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            winner = winnerGenerator();
                            studentID.setText(winner);
                            studentName.setText(map.get(winner));
                            studentIDs.remove(winner);
                            button.setDisable(false);
                            jfxSpinner.setVisible(false);
                        }
                    });
                } catch (InterruptedException e){

                }
            }
        };

        new Thread(task).start();

    }


//    private static String capitalizeFirstLetter(String word) {
//        if (word == null || word.length() == 0) {
//            return word;
//        }
//
//        String result = "";
//        String[] wordPieces = word.split(" ");
//        for (String s : wordPieces) {
//            result += s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
//            result += " ";
//        }
//        return result;
//    }



//     static {
//        try {
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(in));
//
//            String line;
//            while ((line = br.readLine()) != null){
//                String[] itemPieces = line.split(",");
//
//                String studentID = itemPieces[0];
//                String firstName = capitalizeFirstLetter(itemPieces[2]);
//                String lastName = capitalizeFirstLetter(itemPieces[3]);
//                String name = firstName + " " + lastName;
//
//                studentIDs.add(studentID);
//                map.put(studentID, name);
//            }
//
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }

    private static String winnerGenerator(){
        for (int i=0; i<1000; i++) {
            Collections.shuffle(studentIDs);
        }
        return studentIDs.get(new Random().nextInt(studentIDs.size()));
    }
}
