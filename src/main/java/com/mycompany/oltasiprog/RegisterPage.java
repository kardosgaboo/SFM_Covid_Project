package com.mycompany.oltasiprog;

import java.io.IOException;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author sarid
 */


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RegisterPage {
    
    DataStorage data = new DataStorage();
    
  //  final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
   // final EntityManager entityManager = entityManagerFactory.createEntityManager();
    
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
    private TextField Age;

    @FXML
    void RegisztraciosGombEvent(ActionEvent event) throws IOException {
           int taj = Integer.parseInt(TAJszam.getText());
           String email = Email.getText();
           int mobile = Integer.parseInt(MobilePhone.getText());
           String pass = Password.getText();
           int age = Integer.parseInt(Age.getText());

           data.setTajSzam(taj);
           data.setEmail(email);
           data.setMobilePhone(mobile);
           data.setPassword(pass);
           data.setAge(age);
           
        //entityManager.getTransaction().begin();
      //  entityManager.persist(data);
       // entityManager.getTransaction().commit();
           
           App.setRoot("LoginFX");
    }
    
}
