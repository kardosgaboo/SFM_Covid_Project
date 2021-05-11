package com.mycompany.oltasiprog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * JavaFX App
 */

//ez egy komment
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("LoginFx"), 720, 720);
        //scene = new Scene(loadFXML("MainPage"), 720, 720);//Lehet választani, hogy tesztnél ne kelljen mindig belépni.
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        scene.getStylesheets().add("custom_css.css");
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    
    
        
    public static void main(String[] args) throws SQLException {
        startDatabase();

        /*Felhasznalo data = new Felhasznalo();
        data.setSzulido(LocalDate.now());
        data.setNev("Anna");
        data.setEmail("asdw");
        data.setMobil("231");
        data.setJelszo("ASDWQ");
        data.setTaj("23132");*/
        
        /*entityManager.getTransaction().begin();
        entityManager.persist(data);
        entityManager.getTransaction().commit();*/

        launch();
    }
    
    private static void startDatabase() throws SQLException {
        new Server().runTool("-tcp", "-web", "-ifNotExists");
    }


}