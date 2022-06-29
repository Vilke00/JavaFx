/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
public class DownloadTest {
    
    public DownloadTest() {
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
     * Test of dodajZanr method, of class Download.
     */
    @Test
    public void testDodajZanr() {
        System.out.println("dodajZanr");
        Download.dodajZanr();
        assertEquals("drama, ratni" , Download.getListaZanrova().get(3));
        assertTrue(Download.getListaZanrova().size() == 12);
    }

    /**
     * Test of dodajTehnologiju method, of class Download.
     */
    @Test
    public void testDodajTehnologiju() {
        System.out.println("dodajTehnologiju");
        Download.dodajTehnologiju();
        assertEquals("MX4D 2D" , Download.getListaTehnologija().get(1));
        assertTrue(Download.getListaTehnologija().size() == 12);
    }

    /**
     * Test of dodajImena method, of class Download.
     */
    @Test
    public void testDodajImena() {
        System.out.println("dodajImena");
        Download.dodajImena();
        assertEquals("Invazija" , Download.getListaImena().get(5));
        assertTrue(Download.getListaImena().size() == 12);
    }
    
}
