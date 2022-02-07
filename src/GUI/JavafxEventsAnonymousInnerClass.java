package GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JavafxEventsAnonymousInnerClass extends Application
{
    private int yesvotecount = 0;
    private int novotecount = 0;

    public void start(Stage mainStage)
    {
        HBox hbx = new HBox(50);
        hbx.setAlignment(Pos.TOP_CENTER);
        Button buttyes = new Button("Yes");
        Button buttno = new Button("NO");


        buttyes.setOnAction(    event ->
                {
                    yesvotecount++;
                    System.out.println("Another yes vote received");
                }

        );



        buttno.setOnAction( event ->
            {
                novotecount++;
                System.out.println("Another no vote received");
            }
        );

        hbx.getChildren().addAll(buttyes, buttno);

        Scene scene = new Scene(hbx, 300, 300, Color.AQUAMARINE);
        mainStage.setTitle("Voting Choices");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void stop()
    {
        System.out.printf("Voting completed; results: yes = %3d, no = %3d%n",
                yesvotecount, novotecount);
    }

} // end class JavafxEventsAnonymousInnerClass