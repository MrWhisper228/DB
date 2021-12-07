package com.example.db;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DB extends Application {
    static AgentsBase agentsBase = new AgentsBase();

    public static void main(String[] args) {

        Agent agent1 = new Agent("login1","password1");
        Agent agent2 = new Agent("login2","password2");
        Agent agent3 = new Agent("login3","password3");
        agentsBase.add(agent1);
        agentsBase.add(agent2);
        agentsBase.add(agent3);
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DB.class.getResource("DB.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PentagonDB");
        stage.setScene(scene);
        stage.show();
    }


}