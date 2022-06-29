/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projektnizadatak.nemanjavilic4050;

import Baze.DatabaseProjekcije;
import Objekti.Projekcija;
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
public class ProjekcijeTest {
    
    public ProjekcijeTest() {
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
     * Test of prenosProjekta method, of class Projekcije.
     */
    @Test
    public void testPrenosIdProjekta() {
        System.out.println("prenosProjekta");
        int expResult = DatabaseProjekcije.prikaziSveProjekcijeZaFilm(5).get(1).getId_projekcije();
        int result = 5;
        assertEquals(expResult, result);
    }

    /**
     * Test of prenosSale method, of class Projekcije.
     */
    @Test
    public void testPrenosSale() {
        System.out.println("prenosSale");
        int expResult = DatabaseProjekcije.prikaziProjekcije("%", "%").get(8).getBroj_sale();
        int result = 3;
        assertEquals(expResult, result);
    }
    
}
