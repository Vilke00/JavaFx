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
public class Korisnik {
    
    private int id;
    private String ime;
    private String prezime;
    private String username;
    private String password;

    public Korisnik(int id, String ime, String prezime, String username, String password) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }

    public Korisnik(String ime, String prezime, String username, String password) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }
    
    

    public Korisnik() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return " Ime: " + ime + " Prezime: " + prezime;
    }
    
    
    
}
