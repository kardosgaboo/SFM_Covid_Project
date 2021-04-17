package com.mycompany.oltasiprog;

import java.io.IOException;
import java.time.LocalDate;

import com.mycompany.oltasiprog.customer.CustomerDAO;
import com.mycompany.oltasiprog.customer.CustomerDAOimpl;
import com.mycompany.oltasiprog.customer.Felhasznalo;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author sarid
 */


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterPage {
    
    //Felhasznalo data = new Felhasznalo();
    CustomerDAO cdao = new CustomerDAOimpl();
    
    @FXML
    private TextField TAJszam;

    @FXML
    private TextField Email;

    @FXML
    private TextField MobilePhone;

    @FXML
    private PasswordField Password;

    @FXML
    private Button RegisztraciosGomb;

    @FXML
    private DatePicker BirthDate;

    @FXML
    void RegisztraciosGombEvent(ActionEvent event) throws IOException {

        Felhasznalo data = new Felhasznalo();

        String taj = TAJszam.getText();
        String email = Email.getText();
        String mobile = MobilePhone.getText();
        String pass = Password.getText();
        LocalDate szulIdo = BirthDate.getValue();

        data.setTaj(taj.replaceAll(" ", ""));
        data.setEmail(email);
        data.setMobil(mobile);
        data.setJelszo(pass);
        data.setSzulido(szulIdo);

        cdao.saveCustomer(data);
        App.setRoot("LoginFX");
    }
    
}
