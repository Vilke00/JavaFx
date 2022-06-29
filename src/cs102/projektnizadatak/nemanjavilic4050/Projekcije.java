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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Baki
 */
public class Projekcije extends Application {

    static int vrednost = 0;
    static Projekcija projekcija;
    static int brSale = 0;

    public static int prenosIdProjekta() {
        return vrednost;
    }

    public static int prenosSale() {
        return brSale;
    }

    public static Projekcija prenosProjekcije() {
        return projekcija;
    }

    private TableView table = new TableView();

    Podaci p = new Podaci();

    @Override
    public void start(Stage primaryStage) {

        ComboBox<String> cb1 = new ComboBox<>();
        cb1.getItems().setAll(Arrays.asList(p.getListaLokacija()));
        cb1.setValue("Sve");
        cb1.setId("cbLokacija");
        Label lblLokacija = new Label("Lokacija: ");

        DatePicker datePicker = new DatePicker();
        Label lblDatum = new Label("Datum: ");

        TableColumn<Date, Projekcija> datum = new TableColumn<>("Datum");
        datum.setCellValueFactory(new PropertyValueFactory<>("datum"));

        TableColumn<Time, Projekcija> vreme = new TableColumn<>("Vreme");
        vreme.setCellValueFactory(new PropertyValueFactory<>("vreme"));

        TableColumn<Integer, Projekcija> broj_sale = new TableColumn<>("Broj sale");
        broj_sale.setCellValueFactory(new PropertyValueFactory<>("broj_sale"));

        TableColumn<String, Projekcija> lokacija = new TableColumn<>("Lokacija");
        lokacija.setCellValueFactory(new PropertyValueFactory<>("lokacija"));

        table.getColumns().addAll(datum, vreme, broj_sale, lokacija);

        datum.prefWidthProperty().bind(table.widthProperty().divide(4));
        vreme.prefWidthProperty().bind(table.widthProperty().divide(4));
        broj_sale.prefWidthProperty().bind(table.widthProperty().divide(4));
        lokacija.prefWidthProperty().bind(table.widthProperty().divide(4));

        List<Projekcija> lista = DatabaseProjekcije.prikaziSveProjekcijeZaFilm(Pocetna.prenosFilma());
        for (Projekcija p : lista) {
            table.getItems().add(p);
        }
        Button btnIzaberi = new Button("Izaberi projekciju");
        btnIzaberi.setId("btnIzaberi");
        btnIzaberi.setOnAction((ActionEvent event) -> {
            Projekcija p = (Projekcija) table.getSelectionModel().getSelectedItem();
            if (p == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Niste izabrali projekciju ", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            vrednost = p.getId_projekcije();
            prenosIdProjekta();
            projekcija = p;
            prenosProjekcije();
            brSale = p.getBroj_sale();
            prenosSale();
            new Mesto().start(primaryStage);
        });

        Button btnFilter = new Button("Pretrazi po filteru");
        btnFilter.setId("btnFilter");
        btnFilter.setOnAction((ActionEvent event) -> {
            table.getItems().clear();
            String str = null;
            if (datePicker.getValue() == null) {
                str = "%";
            } else {
                str = datePicker.getValue().toString();
            }
            List<Projekcija> lista2 = DatabaseProjekcije.prikaziProjekcije(str, cb1.getValue().toString());
            for (Projekcija p : lista2) {
                table.getItems().add(p);
            }
        }
        );
        
        Button btnNazad = new Button("Nazad");
        btnNazad.setId("btnNazad");
        btnNazad.setOnAction((ActionEvent event) -> {
                primaryStage.close();
                new Pocetna().start(primaryStage);
        });

        Insets insets = new Insets(10);
        BorderPane root = new BorderPane();
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(
        new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);
        HBox h1 = new HBox(10, lblDatum, datePicker,lblLokacija, cb1);
        VBox v1 = new VBox(10, btnFilter, btnIzaberi, btnNazad);
        VBox.setMargin(btnNazad, new Insets(220,0,0,0));
        root.setTop(h1);

        BorderPane.setMargin(h1, insets);

        root.setRight(v1);

        BorderPane.setMargin(v1, insets);

        root.setCenter(vbox);

        BorderPane.setMargin(h1,
                new Insets(12, 0, 12, 12));

        Scene scene = new Scene(root, 850, 400);
        scene.getStylesheets().add("/CSS/Projekcije.css");

        primaryStage.setTitle("Projekcije");
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
