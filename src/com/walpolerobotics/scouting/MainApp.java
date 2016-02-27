package com.walpolerobotics.scouting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.walpolerobotics.scouting.model.Database;
import com.walpolerobotics.scouting.model.Robot;
import com.walpolerobotics.scouting.model.RobotData;
import com.walpolerobotics.scouting.view.RobotOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

    public class MainApp extends Application {

        private Stage primaryStage;
        private BorderPane rootLayout;
        private ObservableList<Robot> robotData= FXCollections.observableArrayList();

        private ArrayList<RobotData> RobotDataList=new ArrayList<RobotData>();

        public Database DB = null;
        public MainApp(){

            //robotData.add(new Robot(1153,"week 0", true, 2, 10));
            DB = new Database();
            ArrayList<Robot> tempList = DB.getRobotFromDB();
            for(Robot r:tempList){
                robotData.add(r);
            }
        }
        public ObservableList<Robot> getRobotData(){
            return robotData;
        }

        public void start(Stage primaryStage) {
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("RobotWeaknesses");

            initRootLayout();

            showRobotOverview();
        }

        /**
         * Initializes the root layout.
         */
        public void initRootLayout() {
            // Load root layout from fxml file.
           try {
               FXMLLoader loader = new FXMLLoader();
               loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
               rootLayout = (BorderPane) loader.load();
               // Show the scene containing the root layout.
               Scene scene = new Scene(rootLayout);
               primaryStage.setScene(scene);
               primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}

        /**
         * Shows the person overview inside the root layout.
         */
        public void showRobotOverview() {
            try {
                // Load person overview.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("view/RobotOverview.fxml"));
                AnchorPane robotOverview = (AnchorPane) loader.load();

                // Set person overview into the center of root layout.
                rootLayout.setCenter(robotOverview);
                RobotOverviewController controller = loader.getController();
                controller.setMainApp(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Stage getPrimaryStage() {
            return primaryStage;
            /**
             * @return
             */
        }

        static void main(String[] args) {
            launch(args);
        }}





