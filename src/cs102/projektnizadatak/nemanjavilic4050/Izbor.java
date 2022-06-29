/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projektnizadatak.nemanjavilic4050;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Baki
 */
public class Izbor extends Application {
    
    
    @Override
    public void start(Stage primaryStage) {
        
        Label l1 = new Label("Dobrodosli Nemanja");
        Label l2 = new Label("Molim vas izaberite opciju");
        
        Button btn1 = new Button("Unos novog filma");
        btn1.setOnAction((ActionEvent event) -> {
                primaryStage.close();
            new UnosFilmova().start(primaryStage);
        });
        Button btn2 = new Button("Izmena postojecih filmova");
        btn2.setOnAction((ActionEvent event) -> {
                primaryStage.close();
            new IzmenaFilmova().start(primaryStage);
        });
        
        Button btn3 = new Button("Unos nove projekcije");
        btn3.setOnAction((ActionEvent event) -> {
                primaryStage.close();
            new UnosProjekcija().start(primaryStage);
        });
        Button btn4 = new Button("Izmena postojecih projekcija");
        btn4.setOnAction((ActionEvent event) -> {
                primaryStage.close();
            new IzmenaProjekcija().start(primaryStage);
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
        
        BorderPane root = new BorderPane();
        HBox hbox1 = new HBox(btn1, btn2);
        HBox hbox2 = new HBox(btn3, btn4);
        VBox vbox = new VBox(l1, l2, hbox1, hbox2, btnNazad);
        vbox.setAlignment(Pos.CENTER);
        VBox.setMargin(btnNazad, new Insets(20,0,0,0));
        vbox.setPadding(new Insets(10, 10, 0, 10));
        hbox1.setSpacing(5);
        hbox1.setPadding(new Insets(10, 0, 0, 140));
        hbox2.setSpacing(5);
        hbox2.setPadding(new Insets(10, 0, 0, 130));
        root.setCenter(vbox);
        
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add("/CSS/StyleSheet.css");
        
        primaryStage.setTitle("Izbor");
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
