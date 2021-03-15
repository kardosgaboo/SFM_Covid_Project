package com.mycompany.oltasiprog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author sarid
 */


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterPage {

    @FXML
    private TextField TAJszam;

    @FXML
    private TextField Email;

    @FXML
    private TextField MobilePhone;

    @FXML
    private PasswordField Password;

    @FXML
    private PasswordField Password2;

    @FXML
    private Button RegisztraciosGomb;

    @FXML
    void RegisztraciosGombEvent(ActionEvent event) throws IOException {
            App.setRoot("LoginFX");
    }
    

}
