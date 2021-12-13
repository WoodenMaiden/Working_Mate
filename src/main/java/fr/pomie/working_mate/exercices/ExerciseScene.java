package fr.pomie.working_mate.exercices;

import fr.pomie.working_mate.exercices.threads.Exercise;
import fr.pomie.working_mate.util.ExerciseCounter;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ExerciseScene extends BorderPane {

    private final Thread thread;
    private final Text title = new Text("A song will play to announce a new session");
    private final ExerciseCounter counter;
    private final SimpleBooleanProperty workingProperty;

    public ExerciseScene(Exercise e, Stage stage){
        this.thread = new Thread(e);
        this.counter = new ExerciseCounter(e);
        this.workingProperty = this.counter.workingProperty();
        this.setTop(new StackPane(this.title));

        stage.setOnShowing(windowEvent -> this.thread.start());

        stage.setOnCloseRequest(windowEvent -> {
            try {
                this.thread.interrupt();
            } catch (Exception exception){
                System.out.println("thread stopped");
            }
        });

        this.setCenter(counter);
    } //ExerciseScene()

}