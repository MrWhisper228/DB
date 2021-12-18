package com.example.db;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

public class DBController {
    public static int variant;
    public Button register;
    @FXML
    ImageView insideIcon;
    @FXML
    Button signIn;
    @FXML
    TextField loginField;
    @FXML
    TextField passwordField;
    @FXML
    TextField idField;

    @FXML
    public void initialize() throws IOException {
        DB.deserialize();
        variant = 0;
        insideIcon.setImage(new Image(Objects.requireNonNull(getClass().getResource("images/985844_fbi-logo-fbi-seal-hd-png-download.png")).toExternalForm()));

    }

    public void fieldCleaner(int i) {
        if (i == 0) {
            passwordField.setText("");
        } else {
            passwordField.setText("");
            loginField.setText("");
        }
    }


    public void onSignInBtnClick() {
        if (variant == 1) {
            for (int i = 0; i < DB.agentsBase.getSize(); i++) {
                if (DB.agentsBase.getAgent(i).getLogin().equals(loginField.getText())) {
                    DB.agentsBase.getAgent(i).setPassword(passwordField.getText());
                    idField.setText("Password updated successfully, here is your id:" + DB.agentsBase.getAgent(i).getAgId());
                }
            }
            fieldCleaner(1);
            variant = 0;
        } else {
            if ((DB.agentsBase.Search(loginField.getText(), passwordField.getText()).equals("Registration date too old, reset password"))) {
                fieldCleaner(0);
                idField.setText("Registration date too old, reset password. \n Input your password in password field and press Sign in");
                variant = 1;
            } else {
                idField.setText(DB.agentsBase.Search(loginField.getText(), passwordField.getText()));
                fieldCleaner(1);
            }

        }
    }

    public void onRegisterBtnClick() {
        Agent agent = new Agent();
        agent.setLogin(loginField.getText());
        agent.setRegisterDate(LocalDateTime.now());

        if (agent.setPassword(passwordField.getText())) {
            agent.setPassword(passwordField.getText());
            idField.setText(DB.agentsBase.add(agent));

            fieldCleaner(1);
        } else {
            idField.setText("Incapable password!Must contain capital,digit and no white space!");
            fieldCleaner(0);
        }

    }

}