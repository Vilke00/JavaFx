/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projektnizadatak.nemanjavilic4050;

import Baze.DatabaseFilm;
import Objekti.Film;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Baki
 */
public class PocetnaTest {
    
    public PocetnaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of prenosFilma method, of class Pocetna.
     */
    @Test
    public void testPrenosFilma() {
        System.out.println("prenosFilma");
        int expResult = DatabaseFilm.prikaziFilmove("MX4D 2D", "Horor").get(0).getId_filma();
        int result = 2;
        assertEquals(expResult, result);
    }
    
}
