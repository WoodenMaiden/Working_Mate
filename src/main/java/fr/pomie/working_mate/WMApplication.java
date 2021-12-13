package fr.pomie.working_mate;

import fr.pomie.working_mate.exercices.ExerciseScene;
import fr.pomie.working_mate.exercices.threads.Exercise;
import fr.pomie.working_mate.util.RessourceAccessor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class WMApplication extends Application {
    @Override
    public void start(Stage stage) {

        stage.getIcons().add(new Image(this.getClass().getResourceAsStream('/' + RessourceAccessor.PACKAGE + "images/black-48dp/1x/outline_visibility_black_48dp.png")));

        Scene scene = new Scene(new MainScene(stage), 320, 240);

        //ugly but works, stage.setResizable(false) doesn't
        stage.setMinWidth(600);stage.setMaxWidth(600);
        stage.setMinHeight(260);stage.setMaxHeight(260);


        stage.setTitle("Working Mate");
        stage.setScene(scene);
        stage.show();
    }


}