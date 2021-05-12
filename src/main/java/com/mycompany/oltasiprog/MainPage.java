package com.mycompany.oltasiprog;

import com.mycompany.oltasiprog.customer.Felhasznalo;
import com.mycompany.oltasiprog.oltopont.Oltopont;
import com.mycompany.oltasiprog.oltopont.OltopontDAO;
import com.mycompany.oltasiprog.oltopont.OltopontDAOimpl;
import com.mycompany.oltasiprog.raktarkeszlet.Raktarkeszlet;
import com.mycompany.oltasiprog.raktarkeszlet.RaktarkeszletDAO;
import com.mycompany.oltasiprog.raktarkeszlet.RaktarkeszletDAOimpl;
import com.mycompany.oltasiprog.rendeles.Rendeles;
import com.mycompany.oltasiprog.rendeles.RendelesDAO;
import com.mycompany.oltasiprog.rendeles.RendelesDAOimpl;
import com.mycompany.oltasiprog.vakcina.Vakcina;
import com.mycompany.oltasiprog.vakcina.VakcinaDAO;
import com.mycompany.oltasiprog.vakcina.VakcinaDAOimpl;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class MainPage implements Initializable {

    @FXML
    private ComboBox<String> rendeles_combobox;

    @FXML
    private Button logoutButton;

    @FXML
    void LogoutButtonEvent(ActionEvent event) {
        try {
            App.setRoot("LoginFX");
        } catch (IOException e) {
            System.err.println("Could not find LoginPage" + e);
        }

    }

    @FXML
    private ImageView vakcinainfoKep;

    @FXML
    private ComboBox Comb;
    @FXML
    private ComboBox<String> rendeles_combobox_helyszin;

    @FXML
    private Label VakcinaText;

    @FXML
    private Label elerheto_mennyiseg_label;

    @FXML
    private Label rendelesNev;

    @FXML
    private Label rendelesEmail;

    @FXML
    private Label rendelesTAJ;

    @FXML
    private Label rendelesSzulDat;

    @FXML
    private Label rendelesRendelesInfo;

    @FXML
    private Label darabLabel;

    @FXML
    private Button rendeles_tovabb_button;

    @FXML
    private Label rendelesTeszt;

    @FXML
    private Button OrderCancelButton;

    @FXML
    private Button OrderYesButton;

    @FXML
    private Button OrderNoButton;
    @FXML
    private Label popUpLabel;

    @FXML
    private ListView<Oltopont> Oltopont_neve;

    @FXML
    private Label oltopont_cim;

    List<Oltopont> oltopontok;
    @FXML
    void OrderNoButtonAction(ActionEvent event) {
        Stage stageToClose = (Stage) OrderNoButton.getScene().getWindow();
        stageToClose.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        oltopontok = oltopontDAO.getOltopontok();
        ObservableList<Oltopont> list = FXCollections.observableArrayList();

        for (Oltopont cime:
                oltopontok) {
            list.add(cime);
        }

        Oltopont_neve.setItems(list);

        if(isMainPageInitialized==false) {
            Comb.getItems().removeAll(Comb.getItems());
            VakcinaText.setText("");
            Comb.getItems().addAll(vakcinaDAO.getVakcina().stream().map(Vakcina::getName).collect(Collectors.toList()));

            rendeles_combobox.getItems().removeAll(rendeles_combobox.getItems());
            rendeles_combobox.getItems().addAll(vakcinaDAO.getVakcina().stream().map(Vakcina::getName).collect(Collectors.toList()));

            rendeles_combobox_helyszin.getItems().removeAll(rendeles_combobox_helyszin.getItems());
            rendeles_combobox_helyszin.getItems().addAll(oltopontDAO.getOltopontok().stream().map(Oltopont::getName).collect(Collectors.toList()));
            darabLabel.setText("");
            rendeles_tovabb_button.setDisable(true);
            //OrderCancelButton.setDisable(true);
            oltopontok();
            isMainPageInitialized=true;
        }

    }

    @FXML
    public void megnyomtam(javafx.scene.input.MouseEvent mouseEvent) {
        for (int i = 0; i < oltopontok.size(); i++) {
            //System.out.println(Oltopont_neve.getSelectionModel().getSelectedItems().toString());
            //System.out.println(oltopontok.get(i).getName());
            if(("["+oltopontok.get(i).getName()+"]").equals(Oltopont_neve.getSelectionModel().getSelectedItems().toString()))
                oltopont_cim.setText(oltopontok.get(i).getAddress());

        }

    }

    @FXML
    void OrderYesButtonAction(ActionEvent event) {
        handleOrderCancel();
        Stage stageToClose = (Stage) OrderYesButton.getScene().getWindow();
        stageToClose.close();

    }



    @FXML
    void OrderCancelButtonAction(ActionEvent event) {
        orderCancelPopup();
    }

    public void handleOrderCancel(){
        List<Rendeles> rendelesList = rendelesDAO.getRendeles().stream()
                .filter(r->r.getFelhasznalo().getId().equals(felhasznalo.getId())).collect(Collectors.toList());
        rendelesList.forEach(r->{
            Long vakcinaId = r.getVakcina().getId();
            Long oltopontId = r.getOltopont().getId();
            Raktarkeszlet raktarkeszlet = raktarkeszletDAO.getRaktarkeszlet()
                    .stream().filter(e->e.getVakcina().getId().equals(vakcinaId)
                            && e.getOltopont().getId().equals(oltopontId)).findFirst().get();
            raktarkeszlet.increaseQuantity();
            raktarkeszletDAO.updateRaktarkeszlet(raktarkeszlet);
            rendelesDAO.deleteRendeles(r);
        });

    }

    public static Felhasznalo felhasznalo;

    private final OltopontDAO oltopontDAO = new OltopontDAOimpl();
    private final VakcinaDAO vakcinaDAO = new VakcinaDAOimpl();
    private final RaktarkeszletDAO raktarkeszletDAO = new RaktarkeszletDAOimpl();
    private final RendelesDAO rendelesDAO = new RendelesDAOimpl();

    public static boolean isMainPageInitialized = false;

    /*@FXML
    public void initialize() {
        if(isMainPageInitialized==false) {
            Comb.getItems().removeAll(Comb.getItems());
            VakcinaText.setText("");
            Comb.getItems().addAll(vakcinaDAO.getVakcina().stream().map(Vakcina::getName).collect(Collectors.toList()));

            rendeles_combobox.getItems().removeAll(rendeles_combobox.getItems());
            rendeles_combobox.getItems().addAll(vakcinaDAO.getVakcina().stream().map(Vakcina::getName).collect(Collectors.toList()));

            rendeles_combobox_helyszin.getItems().removeAll(rendeles_combobox_helyszin.getItems());
            rendeles_combobox_helyszin.getItems().addAll(oltopontDAO.getOltopontok().stream().map(Oltopont::getName).collect(Collectors.toList()));
            darabLabel.setText("");
            rendeles_tovabb_button.setDisable(true);
            //OrderCancelButton.setDisable(true);
            oltopontok();
            isMainPageInitialized=true;
        }
    }*/

    @FXML
    void RendelesVakcinaDropdown(ActionEvent event) {
        String vakcina = rendeles_combobox.getValue();
        String helyszin = rendeles_combobox_helyszin.getValue();
        if(vakcina != null && helyszin != null){
            List<Raktarkeszlet> raktarkeszletList = raktarkeszletDAO.getRaktarkeszlet();
            Optional<Raktarkeszlet> raktarkeszlet = raktarkeszletList.stream()
                    .filter(d->d.getVakcina().getName().equals(vakcina) && d.getOltopont().getName().equals(helyszin))
                    .findFirst();
            if(raktarkeszlet.isPresent()){
                Long aktualisDarabszam = raktarkeszlet.get().getQuantity();
                darabLabel.setText(aktualisDarabszam.toString());
                rendeles_tovabb_button.setDisable(aktualisDarabszam == 0L);
            }
        }
    }


    @FXML
    void oltopontok(){
        List<Oltopont> oltopontok = oltopontDAO.getOltopontok();
        ObservableList<Oltopont> list = FXCollections.observableArrayList();

        for (Oltopont kaka:
             oltopontok) {
            list.add(kaka);
        }

        Oltopont_neve.setItems(list);
    }


    @FXML
    void orderCancelPopup() {

        try {
            Stage newStage;
            Parent root;
            newStage = new Stage();
            root = FXMLLoader.load(getClass().getResource("OrderCancelPopUp.fxml"));
            newStage.setResizable(false);
            newStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void RendelesHelyszinDropdown(ActionEvent event) {
        RendelesVakcinaDropdown(event);
    }

    @FXML
    void Rendeles_tovabb_action(ActionEvent event) {
        Optional<Vakcina> optionalVakcina = vakcinaDAO.getVakcina().stream()
                .filter(v->v.getName().equals(rendeles_combobox.getValue())).findFirst();
        Optional<Oltopont> optionalOltopont = oltopontDAO.getOltopontok().stream()
                .filter(o->o.getName().equals(rendeles_combobox_helyszin.getValue())).findFirst();

        if(optionalVakcina.isPresent() && optionalOltopont.isPresent()){
            Optional<Raktarkeszlet> optionalRaktarkeszlet = raktarkeszletDAO.getRaktarkeszlet().stream()
                    .filter(r->r.getOltopont().getId().equals(optionalOltopont.get().getId())
                    && r.getVakcina().getId().equals(optionalVakcina.get().getId())).findFirst();
            if(optionalRaktarkeszlet.isPresent()) {
                Raktarkeszlet raktarkeszlet = optionalRaktarkeszlet.get();
                if(raktarkeszlet.getQuantity() > 0L) {
                    Rendeles egyrendeles = new Rendeles(felhasznalo, optionalOltopont.get(),
                            optionalVakcina.get(), LocalDateTime.now());
                    rendelesDAO.saveRendeles(egyrendeles);
                    OrderCancelButton.setDisable(false);
                    raktarkeszlet.decreaseQuantity();
                    raktarkeszletDAO.updateRaktarkeszlet(raktarkeszlet);
                    darabLabel.setText(raktarkeszlet.getQuantity().toString());
                }else{rendelesTeszt.setText("Raktárkészlet < 0");}
            }else{rendelesTeszt.setText("Nem létezik a raktárkészlet");}
        }else{rendelesTeszt.setText("Vakcina vagy oltopont nem létezik");}
    }


    @FXML
    void SetText(ActionEvent event){


        //vakcinainfoKep = new ImageView(image);


        //vakcinainfoKep = new ImageView(image);
        if (Comb.getValue().equals("Pfizer")) {
            VakcinaText.setText("Pfizer-BioNTech vakcina összetétele\n" +
                    "\tA vakcina a vírus molekuláris szerkezetén alapuló technológia segítségével készült.\n" +
                    "Az oltóanyag mRNS-alapú, egy lipid nanorészecskébe csomagolt RNS molekulát (mRNS) \ntartalmaz.\n\n" +
                    "Hogyan véd a vakcina?\n" +
                    "\tA vakcina elősegíti, hogy az immunrendszer (a szervezet természetes védelme) \nantitesteket és \n" +
                    "a vírus ellen ható vérsejteket termeljen, így nyújtson védelmet a COVID 19 ellen.\n\n" +
                    "Kinél alkalmazható a védőoltás?\n" +
                    "\tA Comirnaty 16 éves és idősebb személyeknél alkalmazható.\n\n" +
                    "Hány adag szükséges?\n" +
                    "\t2 adag. Az oltási protokoll március 1-jei változásával 35 nap különbséggel adják be \naz első és a " +
                    "második adagot.\n\n" +
                    "Meddig tart a védettség?\n" +
                    "\tA vakcina által biztosított védelem ideje még nem ismert, meghatározása folyamatban van \na jelenleg" +
                    "zajló klinikai vizsgálatok során.");
                   // vakcinainfoKep.setImage(image);
        } else if (Comb.getValue().equals("Moderna")) {

            VakcinaText.setText("COVID 19 Moderna Vakcina vakcina összetétele\n" +
                    "\tA vakcina a vírus molekuláris szerkezetén alapuló technológia segítségével készült. Az \n" +
                    "oltóanyag mRNS-alapú, egy lipid nanorészecskébe csomagolt RNS molekulát (mRNS) \n" +
                    "tartalmaz.\n\n" +
                    "Hogyan véd a vakcina?\n" +
                    "\tA vakcina elősegíti, hogy az immunrendszer (a szervezet természetes védelme) \nantitesteket és " +
                    "a vírus ellen ható vérsejteket termeljen, így nyújtson védelmet a COVID 19 ellen.\n\n" +
                    "Kinél alkalmazható a védőoltás?\n" +
                    "\tCOVID 19 Moderna Vakcina 18 éves és idősebb személyeknél alkalmazható.\n\n" +
                    "Okoz –e betegséget a vakcina?\n" +
                    "\tMivel a COVID 19 Moderna Vakcina nem a vírust tartalmazza az immunitás kiváltásához, \n" +
                    "így nem okozhat Önnek COVID-19-betegséget.\n\n" +
                    "Hány adag szükséges a védettséghez?\n" +
                    "\t2 adag, 28 nap különbséggel.\n" +
                    "A védettség a második adag után kb. a 14 napon alakul ki.\n\n" +
                    "Meddig tart a védettség?\n" +
                    "\tA vakcina által biztosított védelem ideje még nem ismert, meghatározása folyamatban \nvan a " +
                    "jelenleg zajló klinikai vizsgálatok során.\n\n" +
                    "Felcserélhetőség\n" +
                    "\tA COVID 19 Moderna Vakcina nem cserélhető fel más COVID-19 elleni oltóanyagokkal.");
        } else if (Comb.getValue().equals("Astrazeneca")) {
            VakcinaText.setText("COVID 19 AstraZenaca Vakcina összetétele\n" +
                    "\tSARS-CoV-2 (ChAdOx1-S)* tüske fehérjét kódoló csimpánz adenovírus.\n\n" +
                    "Hogyan véd a vakcina?\n" +
                    "\tA vakcina elősegíti, hogy az immunrendszer (a szervezet természetes védelme) antitesteket és \na vírus " +
                    "ellen ható vérsejteket termeljen, így nyújtson védelmet a COVID 19 ellen.\n\n" +
                    "Kinél alkalmazható a védőoltás?\n" +
                    "\tCOVID-19 Vaccine AstraZeneca 18 éves és idősebb személyeknél alkalmazható. \n\n" +
                    "Okoz-e betegséget a vakcina?\n" +
                    "\tAz oltóanyag megbetegedést nem okozhat. A szövődményekkel járó illetve súlyos lefolyású \nhalállal " +
                    "végződő betegség kialakulása ellen nyújt védelmet.\n\n" +
                    "Hány adag szükséges?\n" +
                    "\t2 adag, 4-12 hét különbséggel beadott vakcina szükséges. Magyarországon az oltási protokoll \n" +
                    "március 1-jei változásával – ellenkező döntésig - 12 hét különbséggel alkalmazzuk a vakcinát.\n\n" +
                    "Meddig tart a védettség?\n" +
                    "\tA vakcina által biztosított védelem ideje még nem ismert, meghatározása folyamatban van a \njelenleg " +
                    "zajló klinikai vizsgálatok során.\n\n" +
                    "Felcserélhetőség\n" +
                    "\tA COVID 19 Vaccine AstraZeneca nem cserélhető fel más COVID-19 elleni oltóanyagokkal. \nAzoknak, " +
                    "akik már megkapták az első adag COVID-19 Vaccine AstraZeneca-t, egy második adag \nCOVID-19 " +
                    "Vaccine AstraZeneca-t kell kapniuk a vakcinasorozat befejezéséhez.");
            //vakcinainfoKep.setImage(image);
        }
        else if (Comb.getValue().equals("Sputnik V")){
            VakcinaText.setText("Szputnyik V Vakcina összetétele\n" +
                    "\tKombinált, vektor alapú vakcina, a SARS-CoV-2 vírus által okozott koronavírus-fertőzés megelőzésére \n" +
                    "\t\tAz I. komponens (az adagolási sorozat 1. adagja) a SARS-CoV-2 tüskefehérjéjének génjét tartalmazó, \n" +
                    "rekombináns, 26-os szerotípusú adenovírus-részecskét tartalmazza.\n" +
                    "\t\tA II. komponens (az adagolási sorozat 2. adagja) a SARS-CoV-2 tüskefehérjéjének génjét tartalmazó, \n" +
                    "rekombináns, 5-ös szerotípusú adenovírus-részecskét tartalmazza.\n\n" +
                    "Hogyan véd a vakcina?\n" +
                    "\tA vakcina elősegíti, hogy az immunrendszer (a szervezet természetes védelme) antitesteket és a vírus \n" +
                    "ellen ható vérsejteket termeljen, így nyújtson védelmet a COVID 19 ellen.\n\n" +
                    "Kinél alkalmazható a védőoltás?\n" +
                    "\tA Szputnyik V vakcina 18 éves és idősebb személyeknél alkalmazható. \n\n" +
                    "Okoz –e betegséget a vakcina?\n" +
                    "\tAz oltóanyag megbetegedést nem okozhat. A szövődményekkel járó illetve súlyos lefolyású halállal \n" +
                    "végződő betegség kialakulása ellen nyújt védelmet.\n\n" +
                    "Hány adag szükséges a védettséghez?\n" +
                    "\tA védettség kialakulásához 2 adag, I. és II. komponens, 21 nap különbséggel történő beadása \n" +
                    "szükséges.");

        }
        else if (Comb.getValue().equals("Johnson&Johnson")){
            VakcinaText.setText("Milyen oltóanyag a Johnson&Johnson egyadagos vakcinája? Mit kell tudni róla?\n" +
                    "\tVektor-alapú oltóanyagról van szó, amiből csak egyetlen adagot kell beadni. 2 hét elteltével a vakcina hatékonysága jelenleg 67%-os.\n" +
                    "\n" +
                    "Ez is vektor alapú vakcina, mint az AstraZeneca és a Szputnyik V\n" +
                    "\tEgy ártalmatlan, szaporodásra képtelen hordozóvírus juttatja be a szervezetbe a \n" +
                    "Covid-19 egyik tipikus génszakaszát. Jelen esetben a hordozóvírus adenovírus " +
                    "\n(Ad26), a génszakasz pedig az új típusú koronavírus (SARS-CoV-2) \n" +
                    "tüskefehérjéjének az információját kódolja.\n\n" +
                    "Hatásosság és biztonságosság\n" +
                    "A vakcina klinikai vizsgálatait Dél-Afrikában, Dél-Amerika bizonyos országaiban, \n" +
                    "Mexikóban és az USA-ban végezték el, körülbelül 40000 fő bevonásával.");

        }
        else if (Comb.getValue().equals("Sinopharm")){
            VakcinaText.setText("Sinopharm vakcina összetétele\n" +
                    "\tInaktivált SARS-CoV-2 (Vero Cell) vakcina alumínium-hidroxid adjuvánssal.\n\n" +
                    "Hogyan véd a vakcina?\n" +
                    "\tA vakcina elősegíti, hogy az immunrendszer (a szervezet természetes védelme) antitesteket és a vírus \n" +
                    "ellen ható vérsejteket termeljen, így nyújtson védelmet a COVID 19 ellen.\n\n" +
                    "Kinél alkalmazható a védőoltás?\n" +
                    "\tA Sinopharm-vakcina 18 éves és idősebb személyeknél alkalmazható. \n\n" +
                    "Okoz–e betegséget a vakcina?\n" +
                    "\tAz oltóanyag megbetegedést nem okozhat. A szövődményekkel járó illetve súlyos lefolyású halállal \n" +
                    "végződő betegség kialakulása ellen nyújt védelmet.\n\n" +
                    "Hány adag szükséges a védettséghez?\n" +
                    "\tA védettség kialakulásához 2 adag, 21-28 nap időközzel történő beadása szükséges. \n" +
                    "Magyarországon 28 nap különbséget tartunk a 2 oltás beadása között.");

        }


    }



}



