package com.mycompany.oltasiprog;

import java.io.IOException;

import com.mycompany.oltasiprog.customer.CustomerDAO;
import com.mycompany.oltasiprog.customer.CustomerDAOimpl;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author sarid
 */
public class LoginPage {
    private final CustomerDAO customerDAO = new CustomerDAOimpl();



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
        //String TajSzam = TAJszam.getText(); kikerült mert redundáns azonosítás szempontjából
        String Email = EmailCim.getText();
        String Password = Jelszo.getText();
        try {
            if (checkCustomer(Email, Password)) {
                App.setRoot("MainPage");
            }
            else{
                System.out.println("Sikertelen belépés");
                showStage();
            }
        }catch (IOException e){
            System.err.println("Could not find MainPage" + e);
        }
        
    }

    private static void showStage(){
        Stage newStage = new Stage();
        DialogPane popup = new DialogPane();
        Label szoveg = new Label("Sikertelen belépés! Email cím vagy jelszó nem egyezik");
        popup.setContent(szoveg);
        newStage.setResizable(false);
        newStage.setTitle("Hiba!");
        newStage.initModality(Modality.APPLICATION_MODAL);
        Scene stageScene = new Scene(popup, 450, 100);
        newStage.setScene(stageScene);
        newStage.show();
    }

    private boolean checkCustomer(String e, String p){
        return customerDAO.getCustomerByEmailAndPassword(e,p).isPresent();
    }

    @FXML
    void RegisterButtonEvent(ActionEvent event) throws IOException {
            App.setRoot("RegisterFX");
    }
    
}
