/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import Objekti.Film;
import java.util.LinkedHashSet;
import java.util.Set;
import javafx.scene.control.TableView;

/**
 *
 * @author Baki
 */
public class Bioskop implements Runnable {

    TableView tv;

    public Bioskop(TableView tv) {
        this.tv = tv;
    }

    static Set<Film> listaFilmova = new LinkedHashSet<>();

    @Override
    public void run() {
        listaFilmova.clear();
        Download.dodajImena();
        Download.dodajTehnologiju();
        Download.dodajZanr();
        Download.dodajTrajanja();
        Download.dodajOcene();
        for (int i = 0; i < Download.getListaImena().size(); i++) {
            Film f = new Film(Download.getListaImena().get(i), Download.getListaZanrova().get(i),
                    Download.getListaTehnologija().get(i), Download.getListaTrajanja().get(i), Download.getListaOcena().get(i));
            listaFilmova.add(f);
        }
        for (Film f : listaFilmova){
            tv.getItems().add(f);
        }
    }
}
