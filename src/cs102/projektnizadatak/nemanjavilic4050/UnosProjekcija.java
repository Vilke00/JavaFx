/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projektnizadatak.nemanjavilic4050;

import Objekti.Projekcija;
import Baze.DatabaseProjekcije;
import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tornadofx.control.DateTimePicker;

/**
 *
 * @author User
 */
public class UnosProjekcija extends Application {

    private TableView table = new TableView();

    public void refresh() {
        table.getItems().clear();
        table.getItems().addAll(DatabaseProjekcije.prikaziSveProjekcije());
    }

    Podaci p = new Podaci();
    @Override
    public void start(Stage primaryStage) {

        ComboBox<String> cb1 = new ComboBox<>();
        cb1.getItems().setAll(Arrays.asList(p.getListaLokacija()));
        cb1.setValue("");
        cb1.setId("cbLokacija");

        TextField tfIdProjekcije = new TextField();
        TextField tfIdFilma = new TextField();
        TextField tfSala = new TextField();
        
        tfIdProjekcije.setTextFormatter(new Zabrane().getFormatterInteger());
        
        tfIdFilma.setTextFormatter(new Zabrane().getFormatterInteger());
        
        tfSala.setTextFormatter(new Zabrane().getFormatterInteger());

        DateTimePicker picker = new DateTimePicker();
    
        TableColumn<Integer, Projekcija> id_projekcije = new TableColumn<>("ID projekcije");
        id_projekcije.setCellValueFactory(new PropertyValueFactory<>("id_projekcije"));

        TableColumn<Integer, Projekcija> id_filma = new TableColumn<>("ID filma");
        id_filma.setCellValueFactory(new PropertyValueFactory<>("id_filma"));
        
        TableColumn<String, Projekcija> lokacija = new TableColumn<>("Lokacija");
        lokacija.setCellValueFactory(new PropertyValueFactory<>("lokacija"));

        TableColumn<Date, Projekcija> datum = new TableColumn<>("Datum");
        datum.setCellValueFactory(new PropertyValueFactory<>("datum"));

        TableColumn<Time, Projekcija> vreme = new TableColumn<>("Vreme");
        vreme.setCellValueFactory(new PropertyValueFactory<>("vreme"));

        TableColumn<Integer, Projekcija> broj_sale = new TableColumn<>("Broj sale");
        broj_sale.setCellValueFactory(new PropertyValueFactory<>("broj_sale"));


        table.getColumns().addAll(id_projekcije, id_filma, lokacija, datum, vreme, broj_sale);
        
        id_projekcije.prefWidthProperty().bind(table.widthProperty().divide(8));
        id_filma.prefWidthProperty().bind(table.widthProperty().divide(8));
        datum.prefWidthProperty().bind(table.widthProperty().divide(4));
        vreme.prefWidthProperty().bind(table.widthProperty().divide(4));
        broj_sale.prefWidthProperty().bind(table.widthProperty().divide(8));
        lokacija.prefWidthProperty().bind(table.widthProperty().divide(8));
        
        List<Projekcija> lista = DatabaseProjekcije.prikaziSveProjekcije();
        for (Projekcija p : lista) {
            table.getItems().add(p);
        }
        

        Button btnUnos = new Button("Unos");
        btnUnos.setId("btnUnos");
        btnUnos.setOnAction((ActionEvent event) -> {
           String str = null;
           String str1 = null;
            if (picker.getValue() == null)
                str = "";
            else{
                str = picker.getValue().toString();
                str1 = picker.getDateTimeValue().toLocalTime().toString();
            }
            if (tfIdProjekcije.getText().equals("")  || tfIdFilma.getText().equals("") || cb1.getValue().equals("")
                    || tfSala.getText().equals("") || str.equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Niste uneli sve vrednosti ", ButtonType.OK);
                alert.showAndWait();
                return;
            }else if(Integer.parseInt(tfSala.getText())>4 && Integer.parseInt(tfSala.getText())<0){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Sala mora biti od 1 do 4", ButtonType.OK);
                alert.showAndWait();
                return;
            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Da li ste sigurni da zelite da dodate zapis "
                        + tfIdProjekcije.getText() + " " + tfIdFilma.getText() + " " + cb1.getValue() + " " + tfSala.getText() + " "
                         +  str + " " + str1 +  "?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    DatabaseProjekcije.dodajProjekciju(Integer.parseInt(tfIdProjekcije.getText()),Integer.parseInt(tfIdFilma.getText()),
                             cb1.getValue(), str , str1, Integer.parseInt(tfSala.getText()));
                    refresh();
                } else {
                    return;
                }
            }

        });
        
        Button btnNazad = new Button("Nazad");
        btnNazad.setId("btnNazad");
        btnNazad.setOnAction((ActionEvent event) -> {
                primaryStage.close();
                new Izbor().start(primaryStage);
        });

        BorderPane root = new BorderPane();
        Insets insets = new Insets(10);
        HBox h1 = new HBox(10, tfIdProjekcije, tfIdFilma, cb1, picker, tfSala);
        BorderPane.setMargin(h1, insets);
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);
        BorderPane.setMargin(h1, new Insets(12, 0, 12, 12));
        VBox v1 = new VBox(10, btnUnos, btnNazad);
        VBox.setMargin(btnNazad, new Insets(260,0,0,0));
        BorderPane.setMargin(v1, insets);
        root.setTop(h1);
        root.setCenter(table);
        root.setRight(v1);

        Scene scene = new Scene(root, 850, 400);
        scene.getStylesheets().add("/CSS/UnosProjekcija.css");

        primaryStage.setTitle("UnosProjekcija");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
