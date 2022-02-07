package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Prelim extends Application
{
    public void start(Stage mainStage)
    {
        StackPane pane = new StackPane();
        Button firstButton = new Button("First Button");
        pane.getChildren().add(firstButton);
        Scene scene = new Scene(pane, 200, 100);
        mainStage.setTitle("One button in a pane");
        mainStage.setScene(scene);
        mainStage.show();
    }
}