/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objekti;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Baki
 */
public class Projekcija {
    
    private int id_projekcije;
    private int id_filma;
    private String lokacija;
    private Date datum;
    private Time vreme;
    private int broj_sale;

    public Projekcija() {
    }

    public Projekcija(String lokacija, Date datum, Time vreme, int broj_sale) {
        this.lokacija = lokacija;
        this.datum = datum;
        this.vreme = vreme;
        this.broj_sale = broj_sale;
    }

    public Projekcija(int id_projekcije, int id_filma, String lokacija, Date datum, Time vreme, int broj_sale) {
        this.id_projekcije = id_projekcije;
        this.id_filma = id_filma;
        this.lokacija = lokacija;
        this.datum = datum;
        this.vreme = vreme;
        this.broj_sale = broj_sale;
    }

    public int getId_projekcije() {
        return id_projekcije;
    }

    public void setId_projekcije(int id_projekcije) {
        this.id_projekcije = id_projekcije;
    }

    public int getId_filma() {
        return id_filma;
    }

    public void setId_filma(int id_filma) {
        this.id_filma = id_filma;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Time getVreme() {
        return vreme;
    }

    public void setVreme(Time vreme) {
        this.vreme = vreme;
    }

    public int getBroj_sale() {
        return broj_sale;
    }

    public void setBroj_sale(int broj_sale) {
        this.broj_sale = broj_sale;
    }

    @Override
    public String toString() {
        return " Lokacija: " + lokacija + " Datum: " + datum + " Vreme: " + vreme + " Sala: " + broj_sale;
    }
    
    
    
    
}
