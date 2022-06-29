/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Baki
 */
public class Download {

    public Download() {
    }

    static List<String> listaZanrova = new ArrayList<>();
    static List<String> listaTehnologija = new ArrayList<>();
    static List<String> listaImena = new ArrayList<>();
    static List<Double> listaOcena = new ArrayList<>();
    static List<Integer> listaTrajanja = new ArrayList<>();

    public static void dodajZanr() {
        try {
            Document doc = Jsoup.connect("https://www.cineplexx.rs/filmovi/arhiva/").get();
            Elements ostalo = doc.select(".span3 p");

            for (int i = 1; i < ostalo.size(); i += 3) {
                Element zanr = ostalo.get(i);
                listaZanrova.add(zanr.text());
            }
        } catch (IOException ex) {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void dodajTehnologiju() {
        try {
            Document doc = Jsoup.connect("https://www.cineplexx.rs/filmovi/arhiva/").get();
            Elements ostalo = doc.select(".span3 p");

            for (int i = 0; i < ostalo.size(); i += 3) {
                Element tehnologija = ostalo.get(i);
                if (tehnologija.text().equals("")) {
                    listaTehnologija.add("Digital 2D");
                } else {
                    String pom = tehnologija.text().substring(16);
                    listaTehnologija.add(pom);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void dodajImena() {
        try {
            Document doc = Jsoup.connect("https://www.cineplexx.rs/filmovi/arhiva/").get();
            Elements imenaFilmova = doc.select(".span3 h2 a");

            for (int i = 0; i < imenaFilmova.size(); i++) {
                Element film = imenaFilmova.get(i);
                listaImena.add(film.text());
            }
        } catch (IOException ex) {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void dodajOcene() {

        for (int i = 0; i < 12; i++) {
            double ocena = (Math.random() * (10 - 1)) + 1;
            ocena *= 100;
            ocena = Math.round(ocena);
            ocena /= 100;
            listaOcena.add(ocena);
        }
    }

    public static void dodajTrajanja() {

        Random r = new Random();

        for (int i = 0; i < 12; i++) {
            int trajanje = r.nextInt(150 - 80) + 80;
            listaTrajanja.add(trajanje);
        }
    }

    public static List<Double> getListaOcena() {
        return listaOcena;
    }

    public static void setListaOcena(List<Double> listaOcena) {
        Download.listaOcena = listaOcena;
    }

    public static List<Integer> getListaTrajanja() {
        return listaTrajanja;
    }

    public static void setListaTrajanja(List<Integer> listaTrajanja) {
        Download.listaTrajanja = listaTrajanja;
    }
    
    

    public static List<String> getListaZanrova() {
        return listaZanrova;
    }

    public static void setListaZanrova(List<String> listaZanrova) {
        Download.listaZanrova = listaZanrova;
    }

    public static List<String> getListaTehnologija() {
        return listaTehnologija;
    }

    public static void setListaTehnologija(List<String> listaTehnologija) {
        Download.listaTehnologija = listaTehnologija;
    }

    public static List<String> getListaImena() {
        return listaImena;
    }

    public static void setListaImena(List<String> listaImena) {
        Download.listaImena = listaImena;
    }

}
