/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Baze;

import Objekti.Projekcija;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class DatabaseProjekcije {
      private static java.sql.Connection con = null;
    private static String url = "jdbc:mysql://localhost/projekat";
    private static String username = "root";
    private static String password = "";
    
    

    public static List<Projekcija> prikaziSveProjekcijeZaFilm(int id) {
        List<Projekcija> listaProjekcija = new ArrayList<Projekcija>();
        String query = "SELECT * "
                + " FROM projekcije "
                + " INNER JOIN filmovi ON projekcije.id_filma=filmovi.id_filma "
                + " WHERE projekcije.id_filma = ?";
        try {
            con = DriverManager.getConnection(url, username, password);
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id_projekcije = rs.getInt("id_projekcije");
                int id_filma = rs.getInt("id_filma");
                String lokacija = rs.getString("lokacija");
                Date datum = rs.getDate("datum");
                Time vreme = rs.getTime("vreme");
                int broj_sale = rs.getInt("broj_sale");
                listaProjekcija.add(new Projekcija(id_projekcije, id_filma, lokacija, datum, vreme, broj_sale));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProjekcija;
    }
    
    public static List<Projekcija> prikaziSveProjekcije(){
        List<Projekcija> listaProjekcija = new ArrayList<Projekcija>();
        String query = "SELECT * "
                + " FROM projekcije";
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                int id_projekcije = rs.getInt("id_projekcije");
                int id_filma = rs.getInt("id_filma");
                String lokacija = rs.getString("lokacija");
                Date datum = rs.getDate("datum");
                Time vreme = rs.getTime("vreme");
                int broj_sale = rs.getInt("broj_sale");
                listaProjekcija.add(new Projekcija(id_projekcije, id_filma, lokacija, datum, vreme, broj_sale));
            }
            rs.close();
            s.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProjekcija;
    }
    
    public static void dodajProjekciju(int id_projekcije, int id_filma, String lokacija, String datum, String vreme, int broj_sale) {
        try {
            con = DriverManager.getConnection(url, username, password);
            PreparedStatement st = con.prepareStatement("INSERT INTO projekcije(id_projekcije, id_filma, lokacija, datum"
                    + ", vreme, broj_sale) VALUES (?, ?, ?, ?, ?, ?)");
            st.setInt(1, id_projekcije);
            st.setInt(2, id_filma);
            st.setString(3, lokacija);
            st.setString(4, datum);
            st.setString(5, vreme);
            st.setInt(6, broj_sale);
            st.execute();
            st.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean updateProjekciju(int id_projekcije, int id_filma, String lokacija, String datum, String vreme, int broj_sale) {
        try {
            con = DriverManager.getConnection(url, username, password);
            PreparedStatement st = con.prepareStatement("UPDATE projekcije "
                    + "SET id_filma = ?, lokacija = ?, datum = ?, vreme = ?, broj_sale = ? "
                    + "WHERE id_projekcije = ?"
            );
            st.setInt(1, id_filma);
            st.setString(2, lokacija);
            st.setString(3, datum);
            st.setString(4, vreme);
            st.setInt(5, broj_sale);
            st.setInt(6, id_projekcije);
            st.execute();
            st.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public static boolean obrisiProjekciju(int id) {
        try {
            con = DriverManager.getConnection(url, username, password);
            PreparedStatement st = con.prepareStatement("DELETE FROM projekcije "
                    + "WHERE id_projekcije = ?");

            st.setInt(1, id);

            int r = st.executeUpdate();
            st.close();
            con.close();
            return r == 1;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static List<Projekcija> prikaziProjekcije(String date, String cb) {
        List<Projekcija> listaProjekcija = new ArrayList<Projekcija>();
        String query = "SELECT * FROM projekcije WHERE datum LIKE ? AND lokacija LIKE ?";
        try {
            con = DriverManager.getConnection(url, username, password);
            PreparedStatement st = con.prepareStatement(query);
            if (cb.equals("Sve")) {
                cb = "%";
            }
            st.setString(1, date);
            st.setString(2, cb);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id_projekcije = rs.getInt("id_projekcije");
                int id_filma = rs.getInt("id_filma");
                String lokacija = rs.getString("lokacija");
                Date datum = rs.getDate("datum");
                Time vreme = rs.getTime("vreme");
                int broj_sale = rs.getInt("broj_sale");
                listaProjekcija.add(new Projekcija(id_projekcije, id_filma, lokacija, datum, vreme, broj_sale));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProjekcija;
    }
}
