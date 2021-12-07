package com.example.db;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class DBController {
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
    public void initialize(){
        insideIcon.setImage(new Image(Objects.requireNonNull(getClass().getResource("images/985844_fbi-logo-fbi-seal-hd-png-download.png")).toExternalForm()));
    }

    public void btnCleaner(){
        passwordField.setText("");
        loginField.setText("");
    }
    public void onSignInBtnClick() {
        idField.setText(DB.agentsBase.Search(loginField.getText(), passwordField.getText()));
        btnCleaner();
    }
    public void onRegisterBtnClick() {
       idField.setText(DB.agentsBase.add(new Agent(loginField.getText(),passwordField.getText())));
        btnCleaner();
    }

}