/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projektnizadatak.nemanjavilic4050;

import Baze.DatabaseUser;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Baki
 */
public class Registracija extends Application {

    Label lab1 = new Label("Unesite ime: ");
    TextField tf1 = new TextField();
    Label lab2 = new Label("Unesite prezime: ");
    TextField tf2 = new TextField();
    Label lab3 = new Label("Unesite korisničko ime: ");
    TextField tf3 = new TextField();
    Label lab4 = new Label("Unesite lozinku: ");
    PasswordField tf4 = new PasswordField();
    Button btn = new Button("Registracija");

    @Override
    public void start(Stage primaryStage) {

        btn.setOnAction((ActionEvent event) -> {
            if (tf1.getText().equals("") || tf2.getText().equals("") || tf3.getText().equals("") || tf4.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Niste uneli sve podatke ", ButtonType.OK);
                alert.showAndWait();
                return;
            } else {
                try {
                    if (DatabaseUser.registracija(tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText())) {
                        primaryStage.close();
                        new Login().start(primaryStage);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Registracija.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Registracija.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Button btnNazad = new Button("Nazad na Login");
        btnNazad.setOnAction((ActionEvent event) -> {
            try {
                primaryStage.close();
                new Login().start(primaryStage);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Pocetna.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        GridPane root = new GridPane();
        root.setHgap(5);
        root.setVgap(5);
        root.setAlignment(Pos.CENTER);
        root.add(lab1, 0, 0);
        root.add(tf1, 1, 0);
        root.add(lab2, 0, 1);
        root.add(tf2, 1, 1);
        root.add(lab3, 0, 2);
        root.add(tf3, 1, 2);
        root.add(lab4, 0, 3);
        root.add(tf4, 1, 3);
        root.add(btn, 1, 4);
        root.add(btnNazad, 0, 4);
        GridPane.setHalignment(btn, HPos.RIGHT);
        GridPane.setHalignment(lab1, HPos.RIGHT);
        GridPane.setHalignment(lab2, HPos.RIGHT);
        GridPane.setHalignment(lab3, HPos.RIGHT);
        GridPane.setHalignment(lab4, HPos.RIGHT);

        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add("/CSS/StyleSheet.css");

        primaryStage.setTitle("Registracija");
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
