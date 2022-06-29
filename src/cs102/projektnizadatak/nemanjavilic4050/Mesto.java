/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projektnizadatak.nemanjavilic4050;

import Objekti.Sediste;
import Baze.DatabaseMesto;
import Baze.DatabaseRezervacija;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

/**
 *
 * @author Baki
 */
public class Mesto extends Application {

    int count = 0;
    char x = 'A';
    List<Sediste> listaSedista = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        Font f = new Font("Arial", 44);

        Label l2 = new Label("Molimo vas, izaberite sediste");

        Rectangle r = new Rectangle(400, 125, Color.WHITE);
        r.setStroke(Color.BLACK);
        Text text1 = new Text(String.valueOf("Platno"));
        text1.setBoundsType(TextBoundsType.VISUAL);
        StackPane stackPlatno = new StackPane();
        stackPlatno.getChildren().addAll(r, text1);

        Circle c1 = new Circle(15);
        Circle c2 = new Circle(15);
        Circle c3 = new Circle(15);

        Label l6 = new Label("Legenda:");

        c1.setFill(Color.WHITE);
        c1.setStroke(Color.AQUA);
        Label l3 = new Label("Slobodno");

        c2.setFill(Color.GAINSBORO);
        c2.setStroke(Color.AQUA);
        Label l4 = new Label("Zauzeto");

        c3.setFill(Color.RED);
        c3.setStroke(Color.AQUA);
        Label l5 = new Label("Vasa rezervacija");

        Circle[][] circle = new Circle[5][5];
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);

        for (int i = 0; i < circle.length; i++) {
            HBox h1 = new HBox();
            h1.setSpacing(12);
            Label l = new Label(String.valueOf(x));
            l.setFont(f);
            h1.getChildren().add(l);
            for (int j = 0; j < 5; j++) {
                Text text = new Text(String.valueOf(j + 1));
                text.setBoundsType(TextBoundsType.VISUAL);
                StackPane stack = new StackPane();
                circle[i][j] = new Circle(25, Color.WHITE);
                circle[i][j].setStroke(Color.AQUA);
                stack.getChildren().addAll(circle[i][j], text);
                h1.getChildren().add(stack);
            }
            Label l1 = new Label(String.valueOf(x));
            l1.setFont(f);
            h1.getChildren().add(l1);
            vBox.getChildren().add(h1);
            x += 1;
        }
        x = 'A';
        for (int i = 0; i < circle.length; i++) {
            for (int j = 0; j < 5; j++) {
                for (Sediste sedista : DatabaseMesto.uzmiMesto()) {
                    if (x == sedista.getRed() && j + 1 == sedista.getKolona() && Projekcije.prenosSale() == sedista.getSala()) {
                        circle[i][j].setFill(Color.GAINSBORO);
                    }
                }
            }

            x += 1;
        }

        for (Circle[] c : circle) {
            for (Circle krug : c) {
                krug.setOnMouseClicked(e -> {
                    if (krug.getFill().toString().equals("0xffffffff")) {
                        if (count >= 4) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Mozete da izaberete maksimalno 4 sedista ", ButtonType.OK);
                            alert.showAndWait();
                            return;
                        } else {
                            krug.setFill(Color.RED);
                            count++;
                        }
                    } else if(krug.getFill().toString().equals("0xff0000ff")){
                        krug.setFill(Color.WHITE);
                        count--;
                    }
                });
            }
        }

        Button btnPotvrda = new Button("Potvrdi rezervaciju");
        btnPotvrda.setOnAction(e -> {
            x = 'A';
            for (int i = 0; i < circle.length; i++) {
                for (int j = 0; j < 5; j++) {
                    if (circle[i][j].getFill().toString().equals("0xff0000ff")) {
                        listaSedista.add(new Sediste(x += i, j + 1, Projekcije.prenosSale()));
                    }
                }
                x += 1;
            }
            if (listaSedista == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Morate izabrati sediste ", ButtonType.OK);
                alert.showAndWait();
                return;
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Da li ste sigurni da zelite sledecu rezervaciju " + Pocetna.prenosObjekta() + Projekcije.prenosProjekcije() + listaSedista, ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult().equals(ButtonType.YES)) {
                    for (Sediste s : listaSedista) {
                        DatabaseMesto.dodajMesto(Projekcije.prenosSale(), s.getRed(), s.getKolona());
                    }
                    int br = 0;
                    for (Sediste s : DatabaseMesto.uzmiMesto()) {
                        for (Sediste sed : listaSedista) {
                            if (s.getKolona() == sed.getKolona() && s.getRed() == sed.getRed() && s.getSala() == sed.getSala()) {
                                DatabaseRezervacija.dodajRezervaciju(Pocetna.prenosFilma(),
                                        Projekcije.prenosIdProjekta(), s.getId(), Login.prenosKorisnika());
                                br++;
                                if(br==1){
                                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Vasa rezervacija je prosla ", ButtonType.OK);
                                alert1.showAndWait();
                                if (alert1.getResult().equals(ButtonType.OK)) {
                                    primaryStage.close();
                                    new Pocetna().start(primaryStage);
                                }
                                }
                            }
                        }
                    }
                } else {
                    return;
                }
            }
        });

        Button btnNazad = new Button("Nazad");
        btnNazad.setOnAction((ActionEvent event) -> {
            primaryStage.close();
            new Projekcije().start(primaryStage);
        });

        HBox slobodno = new HBox(10, c1, l3);
        HBox zauzeto = new HBox(10, c2, l4);
        HBox rezervacija = new HBox(10, c3, l5);
        VBox legenda = new VBox(l6, slobodno, zauzeto, rezervacija);

        legenda.setSpacing(20);

        HBox poruka = new HBox(l2);
        HBox kvadrat = new HBox(stackPlatno);
        VBox komb = new VBox(poruka, kvadrat);

        komb.setSpacing(25);
        poruka.setAlignment(Pos.CENTER);
        kvadrat.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        vBox.setSpacing(12);
        VBox dugmici = new VBox(btnPotvrda, btnNazad);
        root.setCenter(vBox);
        root.setTop(komb);
        root.setRight(dugmici);
        root.setLeft(legenda);
        BorderPane.setMargin(vBox, new Insets(15, 0, 12, 110));
        VBox.setMargin(btnNazad, new Insets(350, 0, 0, 0));

        Scene scene = new Scene(root, 850, 600);
        scene.getStylesheets().add("/CSS/StyleSheet.css");

        primaryStage.setTitle("Mesto");
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
