package com.mycompany.oltasiprog;

import java.io.IOException;
import java.util.Optional;

import com.mycompany.oltasiprog.customer.CustomerDAO;
import com.mycompany.oltasiprog.customer.CustomerDAOimpl;
import com.mycompany.oltasiprog.customer.Felhasznalo;
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
        String Email = EmailCim.getText();
        String Password = Jelszo.getText();
        try {
            Optional<Felhasznalo> optionalFelhasznalo = checkCustomer(Email,Password);
            if (optionalFelhasznalo.isPresent()) {
                MainPage.felhasznalo = optionalFelhasznalo.get();
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
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private Optional<Felhasznalo> checkCustomer(String e, String p){
        return customerDAO.getCustomerByEmailAndPassword(e,p);
    }

    @FXML
    void RegisterButtonEvent(ActionEvent event) throws IOException {
            App.setRoot("RegisterFX");

    }

}
