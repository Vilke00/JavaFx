/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objekti;

/**
 *
 * @author Baki
 */
public class Film{
    
    private int id_filma;
    private String ime;
    private String zanr;
    private String tehnologija;
    private int trajanje;
    private double ocena;

    public Film() {
    }

    public Film(String ime, String zanr, String tehnologija, int trajanje, double ocena) {
        this.ime = ime;
        this.zanr = zanr;
        this.tehnologija = tehnologija;
        this.trajanje = trajanje;
        this.ocena = ocena;
    }

    public Film(int id_filma, String ime, String zanr, String tehnologija, int trajanje, double ocena) {
        this.id_filma = id_filma;
        this.ime = ime;
        this.zanr = zanr;
        this.tehnologija = tehnologija;
        this.trajanje = trajanje;
        this.ocena = ocena;
    }

    public int getId_filma() {
        return id_filma;
    }

    public void setId_filma(int id_filma) {
        this.id_filma = id_filma;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public String getTehnologija() {
        return tehnologija;
    }

    public void setTehnologija(String tehnologija) {
        this.tehnologija = tehnologija;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }

    @Override
    public String toString() {
        return " Naziv filma: " + ime + " Zanr:" + zanr + " Tehnologija: " + tehnologija + " Trajanje: " + trajanje + " Ocena: " + ocena;
    }
    
    
}
