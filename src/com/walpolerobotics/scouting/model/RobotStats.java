package com.walpolerobotics.scouting.model;

import javafx.beans.property.*;

import java.util.HashMap;

/**
 * Created by Colleen on 2/21/2016.
 */
public class RobotStats {
    private final StringProperty defenseName;
    private final BooleanProperty didNotAttempt;
    private final IntegerProperty failed;
    private final IntegerProperty crossed;

   public RobotStats(){
       this.defenseName= new SimpleStringProperty ("");
       this.didNotAttempt= new SimpleBooleanProperty(true);
       this.failed= new SimpleIntegerProperty(0);
       this.crossed= new SimpleIntegerProperty(0);
   }
    public RobotStats(String defenseName, Boolean didNotAttempt,Integer failed, Integer crossed) {
        this.defenseName= new SimpleStringProperty (defenseName);
        this.didNotAttempt= new SimpleBooleanProperty(didNotAttempt);
        this.failed= new SimpleIntegerProperty(failed);
        this.crossed= new SimpleIntegerProperty(crossed);
    }
    public Boolean getDidNotAttempt(){
        return didNotAttempt.get();
    }
    public void setDidNotAttempt(Boolean didNotAttempt){
        this.didNotAttempt.set(didNotAttempt);
    }
    public BooleanProperty didNotAttemptProperty() {
        return didNotAttempt;
    }
    public Integer failed(){
        return failed.get();
    }
    public void setFailed(Integer failed){
        this.failed.set(failed);
    }
    public IntegerProperty failedProperty() {
        return failed;
    }
    public Integer crossed(){
        return crossed.get();
    }
    public void setCrossed(Integer crossed){
        this.crossed.set(crossed);
    }
    public IntegerProperty crossedProperty() {
        return crossed;
    }
    public String defenseName(){return defenseName.get();}
    public void setDefenseName(String defenseName){this.defenseName.set(defenseName);}
    public StringProperty defenseNameProperty() {return defenseName;}
}
