/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projektnizadatak.nemanjavilic4050;

import Objekti.Film;
import Baze.DatabaseFilm;
import java.sql.SQLException;
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
 * @author Baki
 */
public class IzmenaFilmova extends Application {

    private TableView table = new TableView();

    public void refresh() {
        table.getItems().clear();
        table.getItems().addAll(DatabaseFilm.prikaziSveFilmove());
    }

    @Override
    public void start(Stage primaryStage) {

        TextField tfId = new TextField();
        TextField tfIme = new TextField();
        TextField tfZanr = new TextField();
        TextField tfTehnologija = new TextField();
        TextField tfTrajanje = new TextField();
        TextField tfOcena = new TextField();
        
        tfId.setTextFormatter(new Zabrane().getFormatterInteger());
        tfIme.setTextFormatter(new Zabrane().getFormatterString());
        tfZanr.setTextFormatter(new Zabrane().getFormatterString());
        tfTehnologija.setTextFormatter(new Zabrane().getFormatterString());
        tfTrajanje.setTextFormatter(new Zabrane().getFormatterInteger());
        tfOcena.setTextFormatter(new Zabrane().getFormatterDouble());


        TableColumn<Integer, Film> id_filma = new TableColumn<>("ID");
        id_filma.setCellValueFactory(new PropertyValueFactory<>("id_filma"));

        TableColumn<String, Film> naziv = new TableColumn<>("Naziv");
        naziv.setCellValueFactory(new PropertyValueFactory<>("ime"));

        TableColumn<String, Film> zanr = new TableColumn<>("Zanr");
        zanr.setCellValueFactory(new PropertyValueFactory<>("zanr"));

        TableColumn<String, Film> tehnologija = new TableColumn<>("Tehnologija");
        tehnologija.setCellValueFactory(new PropertyValueFactory<>("tehnologija"));

        TableColumn<Integer, Film> trajanje = new TableColumn<>("Trajanje");
        trajanje.setCellValueFactory(new PropertyValueFactory<>("trajanje"));

        TableColumn<Double, Film> ocena = new TableColumn<>("Ocena");
        ocena.setCellValueFactory(new PropertyValueFactory<>("ocena"));

        table.getColumns().addAll(id_filma, naziv, zanr, tehnologija, trajanje, ocena);

        id_filma.prefWidthProperty().bind(table.widthProperty().divide(10).multiply(1));
        naziv.prefWidthProperty().bind(table.widthProperty().divide(10).multiply(1.5));
        zanr.prefWidthProperty().bind(table.widthProperty().divide(10).multiply(2.5));
        tehnologija.prefWidthProperty().bind(table.widthProperty().divide(10).multiply(3));
        trajanje.prefWidthProperty().bind(table.widthProperty().divide(10).multiply(1));
        ocena.prefWidthProperty().bind(table.widthProperty().divide(10).multiply(1));

        List<Film> lista = DatabaseFilm.prikaziSveFilmove();
        for (Film f : lista) {
            table.getItems().add(f);
        }

        table.setRowFactory(tv -> {
            TableRow<Film> red = new TableRow<>();
            red.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!red.isEmpty())) {
                    Film podaci = red.getItem();
                    tfId.setText(String.valueOf(podaci.getId_filma()));
                    tfIme.setText(podaci.getIme());
                    tfZanr.setText(podaci.getZanr());
                    tfTehnologija.setText(podaci.getTehnologija());
                    tfTrajanje.setText(String.valueOf(podaci.getTrajanje()));
                    tfOcena.setText(String.valueOf(podaci.getOcena()));
                }
            });
            return red;
        });

        Button btnUpdate = new Button("Update");
        btnUpdate.setOnAction((ActionEvent event) -> {
            Film f = (Film) table.getSelectionModel().getSelectedItem();
            if (f == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Niste selektovali vrednost ", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            if (tfId.getText().equals("") || tfIme.getText().equals("")|| tfZanr.getText().equals("")
                     || tfTehnologija.getText().equals("")|| tfTrajanje.getText().equals("") || tfOcena.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Niste uneli sve vrednosti ", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            DatabaseFilm.updateFilm(f.getId_filma(), tfIme.getText(), tfZanr.getText(), tfTehnologija.getText(),
                    Integer.parseInt(tfTrajanje.getText()), Double.parseDouble(tfOcena.getText()));
            refresh();
        });
        
        Button btnObrisi = new Button("Obrisi zapis");
        btnObrisi.setOnAction((ActionEvent event) -> {
            Film f = (Film) table.getSelectionModel().getSelectedItem();
            if (f == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Niste selektovali vrednost ", ButtonType.OK);
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Obrisi  " + f.getIme() + " ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                boolean r = DatabaseFilm.obrisiFilm(f.getId_filma());
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
        HBox h1 = new HBox(10, tfId, tfIme, tfZanr, tfTehnologija, tfTrajanje, tfOcena);
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

        Scene scene = new Scene(root, 1100, 400);
        scene.getStylesheets().add("/CSS/StyleSheet.css");

        primaryStage.setTitle("IzmenaFilmova");
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
