/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Baze;

import cs102.projektnizadatak.nemanjavilic4050.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Baki
 */
public class DatabaseUser {

    private static java.sql.Connection con = null;
    private static String url = "jdbc:mysql://localhost/projekat";
    private static String username = "root";
    private static String password = "";
    
    public static boolean testCon() {
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static boolean login(String tf1, String tf2) {
        boolean nadjen = false;
        try {
            String query = "SELECT * FROM korisnici";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement s = con.createStatement();

            //vracanje rezultata upita
            ResultSet rs = s.executeQuery(query);

            //provera da li korisnik postoji u bazi podataka
            while (rs.next()) {
                if (rs.getString("username").equals(tf1)
                        && rs.getString("password").equals(tf2)) {
                    nadjen = true;
                }

            }
            rs.close();
            s.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nadjen;
    }

    public static int loginId(String tf1, String tf2) {
        int id=0;
        try {
            String query = "SELECT * FROM korisnici";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement s = con.createStatement();

            //vracanje rezultata upita
            ResultSet rs = s.executeQuery(query);

            while (rs.next()) {
                if (rs.getString("username").equals(tf1)
                        && rs.getString("password").equals(tf2)) {
                    id = rs.getInt("id");
                }
            }

            rs.close();
            s.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public static boolean adminLogin(String tf1, String tf2) {
        boolean nadjen = false;
        try {
            String query = "SELECT * FROM admin";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement s = con.createStatement();

            //vracanje rezultata upita
            ResultSet rs = s.executeQuery(query);

            //provera da li korisnik postoji u bazi podataka
            while (rs.next()) {
                if (rs.getString("username").equals(tf1)
                        && rs.getString("password").equals(tf2)) {
                    nadjen = true;
                }

            }
            rs.close();
            s.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nadjen;
    }

    public static boolean registracija(String tf1, String tf2, String tf3, String tf4) {
        boolean registrovan = false;
        try {
            con = DriverManager.getConnection(url, username, password);
            PreparedStatement st = con.prepareStatement("INSERT INTO korisnici(ime, prezime, username, password) "
                    + "VALUES (?, ?, ?, ?)");

            st.setString(1, tf1);
            st.setString(2, tf2);
            st.setString(3, tf3);
            st.setString(4, tf4);
            st.execute();
            st.close();
            con.close();
            registrovan = true;

        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registrovan;
    }
    
}
