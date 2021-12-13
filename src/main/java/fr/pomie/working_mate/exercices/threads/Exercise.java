package fr.pomie.working_mate.exercices.threads;

import fr.pomie.working_mate.util.AudioPlayer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.net.URISyntaxException;

public class Exercise implements Runnable{

    private final int WORK_TIME; //seconds
    private final int SESSION_TIME; //seconds

    private final SimpleIntegerProperty WorkProperty;
    private final SimpleIntegerProperty SessionProperty;
    private final SimpleStringProperty StringProperty;

    public static final Integer DEFAULT = 20;

    public Exercise(Integer w, Integer s) {
        this.WORK_TIME = w;
        this.SESSION_TIME = s;

        this.WorkProperty = new SimpleIntegerProperty(WORK_TIME);
        this.SessionProperty = new SimpleIntegerProperty(SESSION_TIME);
        this.StringProperty = new SimpleStringProperty();

    }

    // [hours, minutes, seconds]
    private int[] parseSeconds(int seconds) {
        int s = seconds % 60; //the remainder of minutes are seconds
        int m = (seconds / 60) % 60 ; //the remainder of hours are minutes
        int h = (seconds / 60) / 60 ; //same as above but we only keep the result which is the number of hours

        return new int[]{h,m,s};
    }

    private String prettyPrint(int seconds) {

        final int[] parsedSeconds = parseSeconds(seconds);

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append((parsedSeconds[0] < 10) ? "0" + parsedSeconds[0] : parsedSeconds[0]).append(':');
        strBuilder.append((parsedSeconds[1] < 10) ? "0" + parsedSeconds[1] : parsedSeconds[1]).append(':');
        strBuilder.append((parsedSeconds[2] < 10) ? "0" + parsedSeconds[2] : parsedSeconds[2]);
        return strBuilder.toString();
    }

    @Override
    public void run() {
        try {

            stringProperty().setValue(prettyPrint(workProperty().getValue()));

            while (true) {

                Thread.sleep(1000);

                if (workProperty().getValue() <= 0) { // if we stopped working

                    if (sessionProperty().getValue() == SESSION_TIME)
                        AudioPlayer.play("startSession");

                    else if (sessionProperty().getValue() <= 0) {
                        sessionProperty().setValue(SESSION_TIME);
                        workProperty().setValue(WORK_TIME);
                        AudioPlayer.play("stopSession");
                        continue;
                    }

                    sessionProperty().setValue(sessionProperty().subtract(1).getValue());
                    stringProperty().setValue(prettyPrint(sessionProperty().getValue()));
                } else { // time flows

                    workProperty().setValue(workProperty().subtract(1).getValue());
                    stringProperty().setValue(prettyPrint(workProperty().getValue()));
                }
            }
        }
        catch (InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public SimpleIntegerProperty workProperty() {
        return WorkProperty;
    }

    public SimpleIntegerProperty sessionProperty() {
        return SessionProperty;
    }

    public SimpleStringProperty stringProperty() {return StringProperty;}
}
