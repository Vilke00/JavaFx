/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Baze;

import Objekti.Film;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class DatabaseFilm {
     private static java.sql.Connection con = null;
    private static String url = "jdbc:mysql://localhost/projekat";
    private static String username = "root";
    private static String password = "";
    
     public static List<Film> prikaziFilmove(String cb1, String cb2) {
        List<Film> listaFilmova = new ArrayList<Film>();
        String query = "SELECT * FROM filmovi WHERE tehnologija LIKE ? AND zanr LIKE ?";
        try {
            con = DriverManager.getConnection(url, username, password);
            PreparedStatement st = con.prepareStatement(query);
            if (cb1.equals("Svi")) {
                cb1 = "";
            }
            if (cb2.equals("Svi")) {
                cb2 = "";
            }
            StringBuilder sb1 = new StringBuilder(cb1);
            sb1.insert(0, "%");
            sb1.insert(cb1.length() + 1, "%");
            StringBuilder sb2 = new StringBuilder(cb2);
            sb2.insert(0, "%");
            sb2.insert(cb2.length() + 1, "%");
            st.setString(1, sb1.toString());
            st.setString(2, sb2.toString());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_filma");
                String ime = rs.getString("ime");
                String zanr = rs.getString("zanr");
                String tehnologija = rs.getString("tehnologija");
                int trajanje = rs.getInt("trajanje");
                double ocena = rs.getDouble("ocena");
                listaFilmova.add(new Film(id, ime, zanr, tehnologija, trajanje, ocena));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaFilmova;
    }

    public static List<Film> prikaziSveFilmove() {
        List<Film> listaFilmova = new ArrayList<Film>();
        String query = "SELECT * FROM filmovi";
        try {
            con = DriverManager.getConnection(url, username, password);
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_filma");
                String ime = rs.getString("ime");
                String zanr = rs.getString("zanr");
                String tehnologija = rs.getString("tehnologija");
                int trajanje = rs.getInt("trajanje");
                double ocena = rs.getDouble("ocena");
                listaFilmova.add(new Film(id, ime, zanr, tehnologija, trajanje, ocena));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaFilmova;
    }

    public static boolean updateFilm(int id, String tf1, String tf2, String tf3, int tf4, double tf5) {
        try {
            con = DriverManager.getConnection(url, username, password);
            PreparedStatement st = con.prepareStatement("UPDATE filmovi "
                    + "SET ime = ?, zanr = ?, tehnologija = ?, trajanje = ?, ocena = ? "
                    + "WHERE id_filma = ?"
            );
            st.setString(1, tf1);
            st.setString(2, tf2);
            st.setString(3, tf3);
            st.setInt(4, tf4);
            st.setDouble(5, tf5);
            st.setInt(6, id);
            st.execute();
            st.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public static boolean obrisiFilm(int id) {
        try {
            con = DriverManager.getConnection(url, username, password);
            PreparedStatement st = con.prepareStatement("DELETE FROM filmovi "
                    + "WHERE id_filma = ?");

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

    public static void dodajFilm(int id, String ime, String zanr, String tehnologija, int trajanje, double ocena) {
        try {
            con = DriverManager.getConnection(url, username, password);
            PreparedStatement st = con.prepareStatement("INSERT INTO filmovi(id_filma, ime, zanr, tehnologija, trajanje, ocena) "
                    + "VALUES (?, ?, ?, ?, ?, ?)");
            st.setInt(1, id);
            st.setString(2, ime);
            st.setString(3, zanr);
            st.setString(4, tehnologija);
            st.setInt(5, trajanje);
            st.setDouble(6, ocena);
            st.execute();
            st.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
