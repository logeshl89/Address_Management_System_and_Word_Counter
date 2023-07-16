package com.example.code_soft_address;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class WordCountGUI extends Application {
    private TextArea textArea;
    private Label wordCountLabel;
    private Label uniqueWordsLabel;
    private Button countButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Word Count");

        textArea = new TextArea();
        textArea.setWrapText(true);

        wordCountLabel = new Label("Word Count: 0");

        uniqueWordsLabel = new Label("Unique Words: 0");

        countButton = new Button("Count Words");
        countButton.setOnAction(event -> countWords());


        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(textArea, wordCountLabel, uniqueWordsLabel, countButton);


        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void countWords() {
        String text = textArea.getText();


        String[] words = text.split("[\\s.,;:!?]+");


        int wordCount = 0;
        Set<String> uniqueWords = new HashSet<>();

        for (String word : words) {
            if (!word.isEmpty()) {
                wordCount++;
                uniqueWords.add(word.toLowerCase());
            }
        }


        wordCountLabel.setText("Word Count: " + wordCount);
        uniqueWordsLabel.setText("Unique Words: " + uniqueWords.size());
    }
}


