/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projektnizadatak.nemanjavilic4050;

/**
 *
 * @author User
 */
public class Podaci {

    private String[] listaZanrova = {"Muzički", "Horor", "Porodicni", "Drama", "Ratni", "Avantura", "Komedija",
        "Akcija", "Opera", "Animirani", "Triler", "Krimi"};

    private String[] listaTehnologija = {"Digital 2D", "Digital 3D", "MX4D 2D", "MX4D 3D", "Satelitski prenos uživo HD"};

    private String[] listaLokacija = {"Usce", "Delta", "Big", "Beo"};

    public Podaci() {
    }

    public String[] getListaZanrova() {
        return listaZanrova;
    }

    public void setListaZanrova(String[] listaZanrova) {
        this.listaZanrova = listaZanrova;
    }

    public String[] getListaTehnologija() {
        return listaTehnologija;
    }

    public void setListaTehnologija(String[] listaTehnologija) {
        this.listaTehnologija = listaTehnologija;
    }

    public String[] getListaLokacija() {
        return listaLokacija;
    }

    public void setListaLokacija(String[] listaLokacija) {
        this.listaLokacija = listaLokacija;
    }

    
}
