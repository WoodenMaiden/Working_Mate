package fr.pomie.working_mate;

import fr.pomie.working_mate.exercices.threads.Exercise;
import fr.pomie.working_mate.exercices.ExerciseScene;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainScene extends BorderPane {

    private final Spinner<Integer> spinner_work = new Spinner<Integer>(1, Integer.MAX_VALUE, Exercise.DEFAULT);
    private final Spinner<Integer> spinner_session = new Spinner<Integer>(1, Integer.MAX_VALUE, Exercise.DEFAULT);
    private final Button button_reset = new Button("Reset");
    private final Button button_exec = new Button("Let's go!");
    private final CheckBox checkBox_hide = new CheckBox("Hide counter");

    private final Text title = new Text("Welcome to Working Mate!");
    private final Label label_work = new Label("Time between each session (in minutes):");
    private final Label label_session = new Label("Time of session (in seconds):");

    MainScene(Stage stage) {

        //Filling the node
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(new Font("Arial", 30));
        StackPane SP = new StackPane(title);
        SP.setAlignment(Pos.CENTER);
        this.setTop(SP);

        VBox work = new VBox(label_work, spinner_work);
        VBox session = new VBox(label_session, spinner_session);

        work.setAlignment(Pos.CENTER);
        work.prefWidth(346);
        work.setSpacing(5);

        session.setAlignment(Pos.CENTER);
        session.prefWidth(346);
        session.setSpacing(5);

        TilePane TP = new TilePane(work, session);
        TP.prefWidth(700);
        TP.setAlignment(Pos.CENTER);

        this.setCenter(TP);

        //TODO implement the hide chechbkox
        VBox buttons = new VBox(/*checkBox_hide,*/ button_reset, button_exec);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(5);

        this.setBottom(buttons);

        //setting up listeners

        button_reset.setOnMouseClicked(event -> {
            System.out.println(spinner_session.getValue() + " " + spinner_session.getValue());
            spinner_session.getValueFactory().setValue(Exercise.DEFAULT);
            spinner_work.getValueFactory().setValue(Exercise.DEFAULT);
        });


        button_exec.setOnMouseClicked(event -> {
            //if (checkBox_hide.isSelected()) ;
            Exercise ex = new Exercise((Integer) spinner_work.getValue() * 60, (Integer) spinner_session.getValue());
            stage.close();
            ExerciseScene exerciseScene = new ExerciseScene(ex, stage);
            stage.setScene(new Scene(exerciseScene, 320, 240));
            stage.show();
        });
    } //Main Scene

}
