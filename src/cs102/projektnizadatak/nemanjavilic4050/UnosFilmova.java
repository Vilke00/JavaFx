/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projektnizadatak.nemanjavilic4050;

import Objekti.Film;
import Baze.DatabaseFilm;
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

/**
 *
 * @author Baki
 */
public class UnosFilmova extends Application {

    private TableView table = new TableView();
    
     public void refresh() {
        table.getItems().clear();
        table.getItems().addAll(DatabaseFilm.prikaziSveFilmove());
    }

    @Override
    public void start(Stage primaryStage) {
        
        
        
        ComboBox<String> cb1 = new ComboBox<>();
        cb1.getItems().setAll(Arrays.asList(new Podaci().getListaTehnologija()));
        cb1.setValue("");

        ComboBox<String> cb2 = new ComboBox<>();
        cb2.getItems().setAll(Arrays.asList(new Podaci().getListaZanrova()));
        cb2.setValue("");
        
        TextField tfId = new TextField();
        TextField tfIme = new TextField();
        TextField tfTrajanje = new TextField();
        TextField tfOcena = new TextField();
        
        tfId.setTextFormatter(new Zabrane().getFormatterInteger());
        
        tfTrajanje.setTextFormatter(new Zabrane().getFormatterInteger());

        tfOcena.setTextFormatter(new Zabrane().getFormatterDouble());

        tfIme.setTextFormatter(new Zabrane().getFormatterString());
        

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

        Button btnUnos = new Button("Unos");
        btnUnos.setOnAction((ActionEvent event) -> {
            if (tfId.getText().equals("")  || tfIme.getText().equals("") || cb1.getValue().equals("")
                    || cb2.getValue().equals("") || tfTrajanje.getText().equals("") || tfOcena.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Niste uneli sve vrednosti ", ButtonType.OK);
                alert.showAndWait();
                return;
            }else if(Integer.parseInt(tfTrajanje.getText())>150 || Double.parseDouble(tfOcena.getText())>10){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Trajanje ne moze biti preko 150 minuta"
                        + "i ocena ne moze biti iznad 10. Molimo vas, pokusajte ponovo", ButtonType.OK);
                alert.showAndWait();
                return;
            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Da li ste sigurni da zelite da dodate zapis "
                        + tfId.getText() + " " + tfIme.getText() + " " + cb1.getValue() + " " + cb2.getValue() + " "
                        + tfTrajanje.getText() + " " + tfOcena.getText() + " ?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    DatabaseFilm.dodajFilm(Integer.parseInt(tfId.getText()), tfIme.getText(), cb2.getValue(), cb1.getValue(),
                            Integer.parseInt(tfTrajanje.getText()), Double.parseDouble(tfOcena.getText()));
                    refresh();
                } else {
                    return;
                }
            }

        });
        
        Button btnNazad = new Button("Nazad");
        btnNazad.setOnAction((ActionEvent event) -> {
                primaryStage.close();
                new Izbor().start(primaryStage);
        });

        BorderPane root = new BorderPane();
        Insets insets = new Insets(10);
        HBox h1 = new HBox(10, tfId, tfIme, cb1, cb2, tfTrajanje, tfOcena);
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
        scene.getStylesheets().add("/CSS/StyleSheet.css");

        primaryStage.setTitle("UnosFilmova");
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
