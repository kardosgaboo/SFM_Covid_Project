package com.mycompany.oltasiprog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import java.io.IOException;

public class MainPage {

    @FXML
    private Button logoutButton;

    @FXML
    void LogoutButtonEvent(ActionEvent event) {
        try {
            App.setRoot("LoginFX");
        } catch (IOException e) {
            System.err.println("Could not find LoginPage" + e);
        }

    }

        @FXML
        private Text TextBox;

        @FXML
        private ComboBox Comb;
        
        @FXML
        private Label VakcinaText;


        @FXML
        public void initialize() {
            Comb.getItems().removeAll(Comb.getItems());
            VakcinaText.setText("");
            Comb.getItems().addAll("Pfizer", "Moderna", "Astrazeneca");
            //Comb.getSelectionModel().select("Moderna");
        }
        @FXML
        void SetText(ActionEvent event) {
            if (Comb.getValue().equals("Pfizer")) {
                VakcinaText.setText("Ez a pfizer vakcina");
            } else if (Comb.getValue().equals("Moderna")) {
                VakcinaText.setText("Ez a Moderna vakcina");
            } else if (Comb.getValue().equals("Astrazeneca")) {
                VakcinaText.setText("Ez a Astrazeneca vakcina");
            }
        }



}



