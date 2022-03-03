package jfx;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class Counter extends Application implements CounterController  {

    public Label digits;
    public Label lines;
    public Label words;
    public Label chars;
    public TextArea inputText;
    Model model;
    Parent root;
    Counter controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        createGUI();
        primaryStage.setTitle("MicrosoftWord/Word/Char/digit Counter");
        primaryStage.setScene(new Scene(root, 615, 646));
        primaryStage.show();
    }


    public void addListener(Model model) {
        this.model = model;
        model.addListener(m->digits.setText("Number Of Digits :" + m.getDigitCount()));
        model.addListener(m->lines.setText("Number Of Lines :" + m.getLineCount()));
        model.addListener(m->words.setText("Number Of Words :" + m.getWordCount()));
        model.addListener(m->chars.setText("Number Of Chars :" + m.getCharCount()));
    }

    public void onClick() {
        processInput();
        model.notifyObservers();
    }

    public void processInput(){
        String text = inputText.getText();
        int charCt = text.length();
        model.setWordCount(processWords(text,charCt));
        model.setDigitCount(processDigits(text,charCt));
        model.setLineCount(processLines(text,charCt));
        model.setCharCount(charCt);
    }

    public int processWords(String text , int charCt){
        int wordCt = 0;
        for (int i = 0; i < charCt; i++) {
            boolean startOfWord;
            if ( Character.isLetter(text.charAt(i)) == false )
                startOfWord = false;
            else if (i == 0)
                startOfWord = true;
            else if ( Character.isLetter(text.charAt(i-1)) )
                startOfWord = false;
            else if ( text.charAt(i-1) == '\'' && i > 1
                    && Character.isLetter(text.charAt(i-2)) )
                startOfWord = false;
            else
                startOfWord = true;
            if (startOfWord)
                wordCt++;
        }
        return wordCt;
    }

    public int processLines(String text , int charCt) {
        int lineCt = 0;
        for (int i = 0; i < charCt; i++) {

            if (text.charAt(i) == '\n')
                lineCt++;
        }
        if(charCt!=0) {
            lineCt++;
        }
        return lineCt;
    }

    public int processDigits(String text , int charCt) {
        int digitCt=0;
        for (int i = 0; i < charCt; i++) {
            if (text.charAt(i) >= 48 && text.charAt(i) <= 57)
                digitCt++;
        }
        return digitCt;
    }

    @Override
    public void createGUI() {
        try {
            FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("counterView.fxml"));
            root = fxmlLoader.load();
            controller = fxmlLoader.getController();
            Model model = new Model();
            addModel(model);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addModel(Model counterModel) {
        controller.addListener(counterModel);
    }
}
