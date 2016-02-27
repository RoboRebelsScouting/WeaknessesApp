package com.walpolerobotics.scouting.model;

import com.walpolerobotics.scouting.model.Robot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    Connection con = null;
    String url = "jdbc:mysql://localhost:3306/roborebels";
    String user = "root";
    String password = "roborebels1153";

    public Database() {
        try {
            this.con = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException var3) {
            Logger lgr = Logger.getLogger(Database.class.getName());
            lgr.log(Level.SEVERE, var3.getMessage(), var3);
        }

    }

    public ArrayList<Robot> getRobotFromDB() {
        ArrayList<Robot>tempList = new ArrayList<Robot>();
        Statement stmt = null;
        ResultSet rs = null;

            try {
                stmt = this.con.createStatement();
                String ex = "SELECT * FROM matchTable;";
                rs = stmt.executeQuery(ex);
                Boolean dataExists = Boolean.valueOf(false);

                String ex1;
                while(rs.next()) {
                    Integer RobotNumber = Integer.valueOf(rs.getInt("RobotNumber"));
                    String rmd = rs.getString("FirstCompetition");
                    Robot tempRobot = new Robot (RobotNumber, rmd);
                    tempList.add(tempRobot);
                }

            } catch (SQLException var14) {
                System.out.println("SQLException: " + var14.getMessage());
            }
        return tempList;
        }
    public ArrayList<RobotData> getRobotDataFromDB(Integer RobotNumber, String competitionName) {
        ArrayList<RobotData>tempList = new ArrayList<RobotData>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = this.con.createStatement();
            String ex = "SELECT * FROM matchData;";
            rs = stmt.executeQuery(ex);
            Boolean dataExists = Boolean.valueOf(false);

            String ex1;
            while(rs.next()) {
                Integer tempRobotNumber=rs.getInt("RobotNumber");
                String rmd = rs.getString("FirstCompetition");
                String eventName =rs.getString("gameEvent");
                String subEventName=rs.getString("subEvent");
                //System.out.println(eventName + " "+ subEventName);
                if((tempRobotNumber.intValue() == RobotNumber.intValue())&& (rmd.equals(competitionName))){
                    RobotData tempRobotData = new RobotData (eventName, subEventName);
                    tempList.add(tempRobotData);
                }
            }

        } catch (SQLException var14) {
            System.out.println("SQLException: " + var14.getMessage());
        }
        return tempList;
    }

}

