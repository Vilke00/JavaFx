/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Baze;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class DatabaseRezervacija {
    private static java.sql.Connection con = null;
    private static String url = "jdbc:mysql://localhost/projekat";
    private static String username = "root";
    private static String password = "";

    public static void dodajRezervaciju(int id_filma, int id_projekcije, int id_mesta, int id_korisnika) {
        try {
            con = DriverManager.getConnection(url, username, password);
            PreparedStatement st = con.prepareStatement("INSERT INTO rezervacija(id_filma, id_projekcije, "
                    + "id_mesta, id_korisnika) VALUES (?, ?, ?, ?)");
            st.setInt(1, id_filma);
            st.setInt(2, id_projekcije);
            st.setInt(3, id_mesta);
            st.setInt(4, id_korisnika);
            st.execute();
            st.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
