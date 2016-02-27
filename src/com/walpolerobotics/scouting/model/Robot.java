package com.walpolerobotics.scouting.model;

import javafx.beans.property.*;

public class Robot {
    private final IntegerProperty robotNumber;
    private final StringProperty eventName;

/**
 *Default constructor.
 */
    public Robot() {
    this(null, null);
}

    public Robot(Integer robotNumber, String eventName){
        this.robotNumber= new SimpleIntegerProperty(robotNumber);
        this.eventName= new SimpleStringProperty(eventName);


    }
    public Integer getRobotNumber(){
        return robotNumber.get();
    }
    public void setRobotNumber(Integer robotNumber){
        this.robotNumber.set(robotNumber);
    }
    public IntegerProperty robotNumberProperty() {
        return robotNumber;
    }
    public String getEventName(){
        return eventName.get();
    }
    public void setEventName(String eventName){
        this.eventName.set(eventName);
    }
    public StringProperty eventNameProperty() {
        return eventName;
    }

}