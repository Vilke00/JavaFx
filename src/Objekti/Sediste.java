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
public class Sediste {
    private int id;
    private char red;
    private int kolona;
    private int sala;

    public Sediste(char red, int kolona, int sala) {
        this.red = red;
        this.kolona = kolona;
        this.sala = sala;
    }

    public Sediste(int id, char red, int kolona, int sala) {
        this.id = id;
        this.red = red;
        this.kolona = kolona;
        this.sala = sala;
    }
    
    

    public Sediste(char red, int kolona) {
        this.red = red;
        this.kolona = kolona;
    }
    
    
    

    public char getRed() {
        return red;
    }

    public void setRed(char red) {
        this.red = red;
    }

    public int getKolona() {
        return kolona;
    }

    public void setKolona(int kolona) {
        this.kolona = kolona;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return " Red: " + red + " Broj sedista: " + kolona;
    }
    
   
}
