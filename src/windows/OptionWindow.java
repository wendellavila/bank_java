package windows;


import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OptionWindow {
    
    public boolean option;
    
    public void display(String text, String title){
        
        Stage optionWindow = new Stage();
        
        //label
        Label label = new Label(text);
        label.getStyleClass().add("page-title");
        
        //buttons
        Button yesButton = new Button("Sim");
        yesButton.getStyleClass().add("options-button");
        yesButton.setPrefHeight(50);
        yesButton.setPrefWidth(100);
        yesButton.setOnAction(e -> {
            option = true;
            optionWindow.close();
        });
        Button noButton = new Button("Não");
        noButton.getStyleClass().add("options-button");
        noButton.setPrefHeight(50);
        noButton.setPrefWidth(100);
        noButton.setOnAction(e -> {
            option = false;
            optionWindow.close();
        });
        
        //grid
        GridPane grid = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        col1.setPercentWidth(50);
        col2.setPercentWidth(50);
        row1.setPercentHeight(60);
        row2.setPercentHeight(40);
        grid.getColumnConstraints().addAll(col1, col2);
        grid.getRowConstraints().addAll(row1, row2);
        grid.add(label, 0, 0, 2, 1); //colindex, rowindex, colspan, rowspan
        grid.add(yesButton, 0, 1);
        grid.add(noButton, 1, 1);
        GridPane.setHalignment(label, HPos.CENTER);
        GridPane.setHalignment(yesButton, HPos.CENTER);
        GridPane.setHalignment(noButton, HPos.CENTER);
        
        //scene
        Scene optionPage = new Scene(grid, 400, 250);
        
        //stage
        optionPage.getStylesheets().add("windows/stylesheet.css");
        optionWindow.initModality(Modality.APPLICATION_MODAL);
        optionWindow.setTitle(title);
        optionWindow.setScene(optionPage);
        optionWindow.showAndWait();
    }
    public boolean getOption(){
        return option;
    }
}
