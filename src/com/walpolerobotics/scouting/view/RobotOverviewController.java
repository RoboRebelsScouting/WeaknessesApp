package com.walpolerobotics.scouting.view;

import com.walpolerobotics.scouting.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.walpolerobotics.scouting.MainApp;
import com.walpolerobotics.scouting.model.Robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RobotOverviewController {
    @FXML
    private TableView<Robot> RobotTable;
    @FXML
    private TableView<RobotStats> RobotStatsTable;
    @FXML
    private TableColumn<Robot, String> eventNameColumn;
    @FXML
    private TableColumn<Robot, Integer> robotNumberColumn;
    @FXML
    private TableColumn<RobotStats, Boolean> didNotAttemptColumn;
    @FXML
    private TableColumn<RobotStats, Integer> failedColumn;
    @FXML
    private TableColumn<RobotStats, Integer> crossedColumn;
    @FXML
    private TableColumn<RobotStats, String> defenseNameColumn;

    private ObservableList<RobotStats> RobotStatsList= FXCollections.observableArrayList();
    private MainApp mainApp;

    public RobotOverviewController(){
    }
    private void showRobotDetails(Robot r) {
        if (mainApp != null) {
            Database DB = mainApp.DB;
            if (DB != null) {
                ArrayList<RobotData> tempList = mainApp.DB.getRobotDataFromDB(r.getRobotNumber(), r.getEventName());

                String[] MyDefenses = {"Moat", "Portcullis", "Cheval", "Ramparts", "SallyPort", "Drawbridge",
                        "RoughTerrain", "RockWall", "LowBar"};

                //System.out.println("got " + tempList.size() + " records");
                HashMap<String, Integer> HmCrossed = new HashMap<String, Integer>();
                HashMap<String, Integer> HmFailed = new HashMap<String, Integer>();
                for (RobotData rd : tempList) {
                    if (rd.eventData.equals("Cross")) {
                        if (HmCrossed.containsKey(rd.eventName)) {
                            HmCrossed.put(rd.eventName, HmCrossed.get(rd.eventName) + 1);
                        } else {
                            HmCrossed.put(rd.eventName, 1);
                        }
                    }
                    if (rd.eventData.equals("Fail")) {
                        if (Arrays.asList(MyDefenses).contains(rd.eventName)) {
                            if (HmFailed.containsKey(rd.eventName)) {
                                HmFailed.put(rd.eventName, HmFailed.get(rd.eventName) + 1);
                            } else {
                                HmFailed.put(rd.eventName, 1);
                            }
                        }
                    }
                }

                RobotStatsList.clear();
                for (String key : HmCrossed.keySet()) {
                    if (tableHasStat(key, RobotStatsList)) {
                        getStat(key, RobotStatsList).setCrossed(HmCrossed.get(key));
                    } else {
                        RobotStats rs = new RobotStats();
                        rs.setDefenseName(key);
                        rs.setCrossed(HmCrossed.get(key));
                        rs.setDidNotAttempt(false);
                        RobotStatsList.add(rs);
                    }
                }
                for (String key : HmFailed.keySet()) {
                    if (tableHasStat(key, RobotStatsList)) {
                        getStat(key, RobotStatsList).setFailed(HmFailed.get(key));
                    } else {
                        RobotStats rs = new RobotStats();
                        rs.setDefenseName(key);
                        rs.setFailed(HmFailed.get(key));
                        rs.setDidNotAttempt(false);
                        RobotStatsList.add(rs);
                    }
                }
                for (String currentDefense : MyDefenses) {
                    if (tableHasStat(currentDefense, RobotStatsList) == false) {
                        RobotStats rs = new RobotStats();
                        rs.setDefenseName(currentDefense);
                        RobotStatsList.add(rs);
                    }
                }

                //System.out.println("got here");
                RobotStatsTable.setItems(RobotStatsList);
            }
        }
    }
    private Boolean tableHasStat(String keyString, ObservableList<RobotStats>RobotStatsList){
        for(RobotStats rs : RobotStatsList){
            if(rs.defenseName().equals(keyString)){
                return true;
            }
        }
        return false;
    }
    private RobotStats getStat(String keyString, ObservableList<RobotStats>RobotStatsList){
        for(RobotStats rs : RobotStatsList){
            if(rs.defenseName().equals(keyString)){
                return rs;
            }
        }
        return null;
    }

    @FXML
    private void initialize(){
            RobotStatsList.clear();
            eventNameColumn.setCellValueFactory(cellData -> cellData.getValue().eventNameProperty());
            robotNumberColumn.setCellValueFactory(cellData -> cellData.getValue().robotNumberProperty().asObject());
            defenseNameColumn.setCellValueFactory(cellData -> cellData.getValue().defenseNameProperty());
            didNotAttemptColumn.setCellValueFactory(cellData -> cellData.getValue().didNotAttemptProperty());
            failedColumn.setCellValueFactory(cellData -> cellData.getValue().failedProperty().asObject());
            crossedColumn.setCellValueFactory(cellData -> cellData.getValue().crossedProperty().asObject());
            //Clear Robot details.
            showRobotDetails(null);

            RobotTable.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> showRobotDetails(newValue));
        }
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        RobotTable.setItems(mainApp.getRobotData());
    }

}
