package com.mycompany.oltasiprog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterPopUpControl implements Initializable {
    @FXML
    private Label popUpLabel;

    @FXML
    private Button closeButton;

    public String message;

    @FXML
    void closeButtonAction(ActionEvent event) {
        Stage stageToClose = (Stage) closeButton.getScene().getWindow();
        stageToClose.close();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        message = RegisterPage.getUzenet();
        popUpLabel.setText(message);
    }
}
