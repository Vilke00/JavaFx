/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projektnizadatak.nemanjavilic4050;

import Objekti.Projekcija;
import Baze.DatabaseProjekcije;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class IzmenaProjekcija extends Application {
    
   private TableView table = new TableView();

    public void refresh() {
        table.getItems().clear();
        table.getItems().addAll(DatabaseProjekcije.prikaziSveProjekcije());
    }

    @Override
    public void start(Stage primaryStage) {

        TextField tfIdProjekcije = new TextField();
        TextField tfIdFilma = new TextField();
        TextField tfLokacija = new TextField();
        TextField tfDatum = new TextField();
        TextField tfVreme = new TextField();
        TextField tfSala = new TextField();
        
        tfIdProjekcije.setTextFormatter(new Zabrane().getFormatterInteger());
        
        tfIdFilma.setTextFormatter(new Zabrane().getFormatterInteger());
        
        tfSala.setTextFormatter(new Zabrane().getFormatterInteger());

    
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
        
        table.setRowFactory(tv -> {
            TableRow<Projekcija> red = new TableRow<>();
            red.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!red.isEmpty())) {
                    Projekcija podaci = red.getItem();
                    tfIdProjekcije.setText(String.valueOf(podaci.getId_projekcije()));
                    tfIdFilma.setText(String.valueOf(podaci.getId_filma()));
                    tfLokacija.setText(podaci.getLokacija());
                    tfDatum.setText(podaci.getDatum().toString());
                    tfVreme.setText(String.valueOf(podaci.getVreme().toString()));
                    tfSala.setText(String.valueOf(podaci.getBroj_sale()));
                }
            });
            return red;
        });
        
        Button btnUpdate = new Button("Update");
        btnUpdate.setOnAction((ActionEvent event) -> {
            Projekcija p = (Projekcija) table.getSelectionModel().getSelectedItem();
            if (p == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Niste selektovali vrednost ", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            if (tfIdProjekcije.getText().equals("")  || tfIdFilma.getText().equals("") || tfLokacija.getText().equals("")
                    || tfDatum.getText().equals("") || tfVreme.getText().equals("")|| tfSala.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Niste uneli sve vrednosti ", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            DatabaseProjekcije.updateProjekciju(p.getId_projekcije(), Integer.parseInt(tfIdFilma.getText()), tfLokacija.getText(),
                    tfDatum.getText(), tfVreme.getText(), Integer.parseInt(tfSala.getText()));
            refresh();
        });
        
        Button btnObrisi = new Button("Obrisi zapis");
        btnObrisi.setOnAction((ActionEvent event) -> {
            Projekcija p = (Projekcija) table.getSelectionModel().getSelectedItem();
            if (p == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Niste selektovali vrednost ", ButtonType.OK);
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Obrisi  projekciju: " + p.getId_projekcije()+ " ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                boolean r = DatabaseProjekcije.obrisiProjekciju(p.getId_projekcije());
                if (r)
                    refresh();
            }
        });
        
        Button btnNazad = new Button("Nazad");
        btnNazad.setOnAction((ActionEvent event) -> {
                primaryStage.close();
                new Izbor().start(primaryStage);
        });

        BorderPane root = new BorderPane();
        Insets insets = new Insets(10);
        HBox h1 = new HBox(10, tfIdProjekcije, tfIdFilma, tfLokacija, tfDatum, tfVreme, tfSala);
        BorderPane.setMargin(h1, insets);
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);
        BorderPane.setMargin(h1, new Insets(12, 0, 12, 12));
        VBox v1 = new VBox(10, btnUpdate, btnObrisi, btnNazad);
        VBox.setMargin(btnNazad, new Insets(220,0,0,0));
        BorderPane.setMargin(v1, insets);
        root.setTop(h1);
        root.setCenter(table);
        root.setRight(v1);
        
        Scene scene = new Scene(root, 850, 400);
        scene.getStylesheets().add("/CSS/StyleSheet.css");
        
        primaryStage.setTitle("IzmenaProjekcija");
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
