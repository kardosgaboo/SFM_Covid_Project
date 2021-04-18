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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    private static void registerPopup(String hiba) {
        Stage newStage = new Stage();
        DialogPane popup = new DialogPane();
        Label szoveg = new Label("Sikertelen regisztráció! " + hiba + " már létezik.");
        popup.setContent(szoveg);
        newStage.setResizable(false);
        newStage.setTitle("Hiba!");
        newStage.initModality(Modality.APPLICATION_MODAL);
        Scene stageScene = new Scene(popup, 450, 100);
        newStage.setScene(stageScene);
        newStage.show();
    }

    @FXML
    void RegisztraciosGombEvent(ActionEvent event) throws IOException {

        String taj = TAJszam.getText().replaceAll(" ", "");
        String email = Email.getText();
        String mobile = MobilePhone.getText();
        String pass = Password.getText();
        LocalDate szulIdo = BirthDate.getValue();

        if (isExistingEmail(email)) {
            registerPopup("Email");
        } else if (isExistingTaj(taj)) {
            registerPopup("Taj szám");
        } else {
            Felhasznalo data = new Felhasznalo();
            data.setTaj(taj);
            data.setEmail(email);
            data.setMobil(mobile);
            data.setJelszo(pass);
            data.setSzulido(szulIdo);

            cdao.saveCustomer(data);
            App.setRoot("LoginFX");
        }
    }

    private boolean isExistingEmail(String email) {
        return !cdao.getCustomerByEmail(email).isEmpty();

    }

    private boolean isExistingTaj(String taj) {
        return !cdao.getCustomerByTaj(taj).isEmpty();

    }

}
