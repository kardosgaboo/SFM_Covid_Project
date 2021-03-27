package com.mycompany.oltasiprog;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

/**
 *
 * @author sarid
 */
public class LoginPage {
    
    @FXML
    private TextField TAJszam;

    @FXML
    private TextField EmailCim;

    @FXML
    private TextField Jelszo;
    
    @FXML
    private Button loginButton;

    @FXML
    private Button RegisterButton;

    @FXML
    void LoginButtonEvent(ActionEvent event) {
        String TajSzam = TAJszam.getText();
        String Email = EmailCim.getText();
        String Password = Jelszo.getText();
        
        //belépés megvalósítása
        System.out.println("  BELEPES ");
        
    }

    @FXML
    void RegisterButtonEvent(ActionEvent event) throws IOException {
            App.setRoot("RegisterFX");
    }
    
}
