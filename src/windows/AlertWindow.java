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

public class AlertWindow {
    
    public void display(String text, String title){
        
        Stage alertWindow = new Stage();
        
        //label
        Label label = new Label(text);
        label.getStyleClass().add("page-title");
        
        //buttons
        Button closeButton = new Button("Fechar");
        closeButton.getStyleClass().add("options-button");
        closeButton.setPrefHeight(50);
        closeButton.setPrefWidth(150);
        closeButton.setOnAction(e -> {
            alertWindow.close();
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
        grid.add(closeButton, 0, 1, 2, 1);
        GridPane.setHalignment(label, HPos.CENTER);
        GridPane.setHalignment(closeButton, HPos.CENTER);
        
        //scene
        Scene alertPage = new Scene(grid, 800, 250);
        
        //stage
        alertPage.getStylesheets().add("windows/stylesheet.css");
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle(title);
        alertWindow.setScene(alertPage);
        alertWindow.showAndWait();
    }
}
