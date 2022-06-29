/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projektnizadatak.nemanjavilic4050;

import Baze.DatabaseUser;
import java.sql.SQLException;
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
public class Login extends Application {

    static int vrednost = 0;

    public static int prenosKorisnika() {
        return vrednost;
    }

    public Login() {
    }

    Label lab1 = new Label("Unesite korisničko ime: ");
    TextField tf1 = new TextField();
    Label lab2 = new Label("Unesite lozinku: ");
    PasswordField tf2 = new PasswordField();
    Button btn = new Button("Login");
    Button btn2 = new Button("Registracija");

    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {
        if (DatabaseUser.testCon() == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Niste povezani na bazu ", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        tf1.setAlignment(Pos.BOTTOM_RIGHT);
        tf2.setAlignment(Pos.BOTTOM_RIGHT);

        btn.setOnAction((ActionEvent event) -> {
            if (tf1.getText().equals("") || tf2.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Niste uneli sve podatke ", ButtonType.OK);
                alert.showAndWait();
                return;
            } else if (DatabaseUser.adminLogin(tf1.getText(), tf2.getText())) {
                primaryStage.close();
                new Izbor().start(primaryStage);
            } else if (DatabaseUser.login(tf1.getText(), tf2.getText())) {
                vrednost = DatabaseUser.loginId(tf1.getText(), tf2.getText());
                prenosKorisnika();
                primaryStage.close();
                new Pocetna().start(primaryStage);
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Pogresan username i password ", ButtonType.OK);
                alert.showAndWait();
                return;
            }
        });

        btn2.setOnAction((ActionEvent event) -> {
            primaryStage.close();
            new Registracija().start(primaryStage);
        });

        GridPane root = new GridPane();
        root.setHgap(5);
        root.setVgap(5);
        root.setAlignment(Pos.CENTER);
        root.add(lab1, 0, 0);
        root.add(tf1, 1, 0);
        root.add(lab2, 0, 1);
        root.add(tf2, 1, 1);
        root.add(btn, 0, 2);
        root.add(btn2, 1, 2);
        GridPane.setHalignment(btn, HPos.RIGHT);
        GridPane.setHalignment(btn2, HPos.RIGHT);
        GridPane.setHalignment(lab1, HPos.RIGHT);
        GridPane.setHalignment(lab2, HPos.RIGHT);

        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add("/CSS/StyleSheet.css");

        primaryStage.setTitle("Login");
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
