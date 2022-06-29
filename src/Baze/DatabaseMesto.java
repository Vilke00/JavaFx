/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Baze;

import Objekti.Sediste;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class DatabaseMesto {
    private static java.sql.Connection con = null;
    private static String url = "jdbc:mysql://localhost/projekat";
    private static String username = "root";
    private static String password = "";
     public static void dodajMesto(int broj_sale, char red, int sediste) {
        try {
            con = DriverManager.getConnection(url, username, password);
            PreparedStatement st = con.prepareStatement("INSERT INTO mesto(broj_sale, red, "
                    + "sediste) VALUES (?, ?, ?)");
            st.setInt(1, broj_sale);
            st.setString(2, String.valueOf(red));
            st.setInt(3, sediste);
            st.execute();
            st.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public static List<Sediste> uzmiMesto() {
        List<Sediste> listaSedista = new ArrayList<Sediste>();
        String query = "SELECT * FROM mesto";
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id_mesta");
                int broj_sale = rs.getInt("broj_sale");
                String str = rs.getString("red");
                char red = str.charAt(0);
                int sediste = rs.getInt("sediste");
                listaSedista.add(new Sediste(id, red, sediste, broj_sale));
            }
            rs.close();
            s.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaSedista;
    }
}
