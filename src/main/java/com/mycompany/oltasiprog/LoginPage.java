package com.mycompany.oltasiprog;

import java.io.IOException;

import com.mycompany.oltasiprog.customer.CustomerDAO;
import com.mycompany.oltasiprog.customer.CustomerDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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
    private Button closeButton;

    @FXML
    private Label popUpLabel;

    @FXML
    void closeButtonAction()
    {
        Stage stageToClose = (Stage) closeButton.getScene().getWindow();
        stageToClose.close();
    }

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
                loginPopup();
            }
        }catch (IOException e){
            System.err.println("Could not find MainPage" + e);
        }
        
    }
    @FXML
    void loginPopup() {
        try {
            Stage newStage;
            Parent root;
            newStage = new Stage();
            root = FXMLLoader.load(getClass().getResource("LoginPopUpFX.fxml"));
            newStage.setResizable(false);
            newStage.initModality(Modality.APPLICATION_MODAL);
            //popUpLabel.setText("kkkkk");
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkCustomer(String e, String p){
        return customerDAO.getCustomerByEmailAndPassword(e,p).isPresent();
    }

    @FXML
    void RegisterButtonEvent(ActionEvent event) throws IOException {
            App.setRoot("RegisterFX");

    }

}
