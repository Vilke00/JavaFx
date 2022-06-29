/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projektnizadatak.nemanjavilic4050;

import Objekti.Film;
import Web.Bioskop;
import Baze.DatabaseFilm;
import java.sql.SQLException;
import java.util.Arrays;
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
import javafx.scene.control.ComboBox;
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
public class Pocetna extends Application {

    
    static int vrednost = 0;
    static Film film;
    
    public static int prenosFilma(){
        return vrednost;
    }
    
    public static Film prenosObjekta(){
        return film;
    }

    private TableView table = new TableView();
    

    @Override
    public void start(Stage primaryStage) {
        ComboBox<String> cb2 = new ComboBox<>();
        cb2.getItems().setAll(Arrays.asList(new Podaci().getListaTehnologija()));
        cb2.setValue("Svi");
        Label lTeh = new Label("Tehnologija: ");

        ComboBox<String> cb3 = new ComboBox<>();
        cb3.getItems().setAll(Arrays.asList(new Podaci().getListaZanrova()));
        cb3.setValue("Svi");
        Label lZanr = new Label("Zanr: ");

        
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

        table.getColumns().addAll(naziv, zanr, tehnologija, trajanje, ocena);

        naziv.prefWidthProperty().bind(table.widthProperty().divide(10).multiply(2));
        zanr.prefWidthProperty().bind(table.widthProperty().divide(10).multiply(3));
        tehnologija.prefWidthProperty().bind(table.widthProperty().divide(10).multiply(3));
        trajanje.prefWidthProperty().bind(table.widthProperty().divide(10).multiply(1));
        ocena.prefWidthProperty().bind(table.widthProperty().divide(10).multiply(1));
        
        Button btnIzbor = new Button("Izaberi");
        btnIzbor.setOnAction((ActionEvent event) -> {
            Film f = (Film) table.getSelectionModel().getSelectedItem();
            if (f == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Niste izabrali film ", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            vrednost=f.getId_filma();
            prenosFilma();
            film = f;
            prenosObjekta();
            new Projekcije().start(primaryStage);
        });
        
        Button btnPrikaziRezultat = new Button("Prikazi rezultate");
        btnPrikaziRezultat.setOnAction((ActionEvent event) -> {
            table.getItems().clear();
            List<Film> lista = DatabaseFilm.prikaziFilmove(cb2.getValue(), cb3.getValue());
            for(Film f:lista){
                table.getItems().add(f);
            }
        });
        
        Button btnArhiva = new Button("Prikazi ahrivu");
        btnArhiva.setOnAction((ActionEvent event) -> {
            table.getItems().clear();
            Bioskop b = new Bioskop(table);
            Thread nit1 = new Thread(b);
            nit1.start();
        });
        
        Button btnNazad = new Button("Nazad");
        btnNazad.setOnAction((ActionEvent event) -> {
            try {
                primaryStage.close();
                new Login().start(primaryStage);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Pocetna.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Insets insets = new Insets(10);
        BorderPane root = new BorderPane();
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);
        HBox h1 = new HBox(8, lTeh, cb2,lZanr, cb3);
        VBox v1 = new VBox(10, btnPrikaziRezultat,btnIzbor, btnArhiva, btnNazad);
        root.setTop(h1);
        VBox.setMargin(btnArhiva, new Insets(180,0,0,0));
        VBox.setMargin(btnNazad, new Insets(5,0,0,0));
        BorderPane.setMargin(h1, insets);
        root.setRight(v1);
        BorderPane.setMargin(v1, insets);
        root.setCenter(vbox);
        BorderPane.setMargin(h1, new Insets(12, 0, 12, 12));

        Scene scene = new Scene(root, 850, 400);
        scene.getStylesheets().add("/CSS/StyleSheet.css");

        primaryStage.setTitle("Pocetna");
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
