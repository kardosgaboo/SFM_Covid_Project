package com.mycompany.oltasiprog;

import java.io.IOException;
import java.time.LocalDate;

import com.mycompany.oltasiprog.customer.CustomerDAO;
import com.mycompany.oltasiprog.customer.CustomerDAOimpl;
import com.mycompany.oltasiprog.customer.Felhasznalo;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author sarid
 */


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RegisterPage{

    //Felhasznalo data = new Felhasznalo();
    CustomerDAO cdao = new CustomerDAOimpl();

    @FXML
    private TextField RegisztracioNev;

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
    private Button RegisztracioVissza;

    private static String uzenet;

    public static String getUzenet() {
        return uzenet;
    }

    @FXML
    void RegisztracioVisszaEvent(ActionEvent event) {
        try {
            App.setRoot("LoginFX");
        } catch (IOException e) {
            System.err.println("Could not find LoginPage" + e);
        }
    }

    @FXML
    void RegisztraciosGombEvent(ActionEvent event) throws IOException {
        //TAJszam.setFocusTraversable(false);
        String nev = RegisztracioNev.getText();
        String taj = TAJszam.getText().replaceAll(" ", "");
        String email = Email.getText();
        String mobile = MobilePhone.getText();
        String pass = Password.getText();
        LocalDate szulIdo = BirthDate.getValue();

        if (isExistingEmail(email)) {
            uzenet = "Ez az email cím már létezik!";
            registerPopUp();
        } else if (isExistingTaj(taj)) {
           uzenet = "Ez a tajszám már létezik!";
           registerPopUp();
        } else {
            Felhasznalo data = new Felhasznalo();
            data.setNev(nev);
            data.setTaj(taj);
            data.setEmail(email);
            data.setMobil(mobile);
            data.setJelszo(pass);
            data.setSzulido(szulIdo);
            if(!isValid(data)){
                uzenet = "Minden adat kitöltése kötelező!";
                registerPopUp();
            }else{
                cdao.saveCustomer(data);
                App.setRoot("LoginFX");
            }


        }
    }
    void registerPopUp()
    {
        try {
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("RegisterPopUpFX.fxml"));
            newStage.setResizable(false);
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValid(Felhasznalo f){
        return f.getTaj().length()>0 && f.getEmail().length()>0 && f.getJelszo().length()>0 && f.getMobil().length()>0
                && f.getNev().length()>0 && f.getSzulido() != null;
    }

    private boolean isExistingEmail(String email) {
        return !cdao.getCustomerByEmail(email).isEmpty();

    }

    private boolean isExistingTaj(String taj) {
        return !cdao.getCustomerByTaj(taj).isEmpty();

    }

}
