package fr.pomie.working_mate.util;

import fr.pomie.working_mate.exercices.threads.Exercise;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.text.Text;

public class ExerciseCounter extends Text {

   private final SimpleBooleanProperty workingProperty = new SimpleBooleanProperty(true);

    public ExerciseCounter(Exercise exercise) {
      this.textProperty().bind(exercise.stringProperty());

      this.textProperty().addListener((/*ObservableValue<? extends String>*/ observableValue,
                                       /*String*/ s, /*String*/ t1) -> {
          if (t1.equals("00:00:00")) {
              this.workingProperty().setValue(!this.workingProperty().getValue());
          }
      });

    }

    public SimpleBooleanProperty workingProperty() {return workingProperty;}

}
