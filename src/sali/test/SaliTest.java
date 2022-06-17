package sali.test;
// Generated by ComTest BEGIN
import sali.SailoException;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
import sali.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.23 09:35:18 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class SaliTest {


  // Generated by ComTest BEGIN  // Sali: 18
   private Sali sali; 
   private Liike l1; 
   private Liike l2; 
   private Liike l3; 
   private Liike l4; 
   private Tulos t1; 
   private Tulos t2; 
   private Tulos t3; 
   private Tulos t4; 

   public void alustaSali() {
     sali = new Sali(); 
     l1 = new Liike(); l1.parse("1|Takakyykky"); 
     l2 = new Liike(); l2.parse("2|Etukyykky"); 
     l3 = new Liike(); l3.parse("3|Penkkipunnerrus"); 
     l4 = new Liike(); l4.parse("4|Pystypunnerrus"); 
     t1 = new Tulos(); t1.parse("1|1|1|2021-01-01|10|kg. eka tulos."); 
     t2 = new Tulos(); t2.parse("2|1|2|2021-01-01|20|kg. toka tulos."); 
     t3 = new Tulos(); t3.parse("3|1|3|2021-01-01|30|kg. kolmas tulos."); 
     t4 = new Tulos(); t4.parse("4|1|4|2021-01-01|40|kg. neljäs tulos."); 
     try {
     sali.lisaa(l1); 
     sali.lisaa(l2); 
     sali.lisaa(l3); 
     sali.lisaa(l4); 
     sali.lisaa(t1); 
     sali.lisaa(t2); 
     sali.lisaa(t3); 
     sali.lisaa(t4); 
     } catch ( Exception e) {
        System.err.println(e.getMessage()); 
     }
   }
  // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaLiike66 */
  @Test
  public void testAnnaLiike66() {    // Sali: 66
    alustaSali(); 
    assertEquals("From: Sali line: 68", "1|Takakyykky", sali.annaLiike(0).toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaTulos82 */
  @Test
  public void testAnnaTulos82() {    // Sali: 82
    alustaSali(); 
    assertEquals("From: Sali line: 84", "1|1|1|2021-01-01|10|kg. eka tulos.", sali.annaTulos(l1, "2021-01-01").toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaTulosNro97 */
  @Test
  public void testAnnaTulosNro97() {    // Sali: 97
    alustaSali(); 
    assertEquals("From: Sali line: 99", "10", sali.annaTulosNro(l1)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaLisatiedot112 */
  @Test
  public void testAnnaLisatiedot112() {    // Sali: 112
    alustaSali(); 
    assertEquals("From: Sali line: 114", "kg. eka tulos.", sali.annaLisatiedot(l1)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetLiikkeet125 */
  @Test
  public void testGetLiikkeet125() {    // Sali: 125
    alustaSali(); 
    assertEquals("From: Sali line: 127", 4, sali.getLiikkeet()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLisaa140 
   * @throws SailoException when error
   */
  @Test
  public void testLisaa140() throws SailoException {    // Sali: 140
    alustaSali(); 
    assertEquals("From: Sali line: 143", 4, sali.getLiikkeet()); 
    Liike liike = new Liike(); 
    liike.parse("5|Ylätalja"); 
    sali.lisaa(liike); 
    assertEquals("From: Sali line: 147", 5, sali.getLiikkeet()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLisaa159 */
  @Test
  public void testLisaa159() {    // Sali: 159
    alustaSali(); 
    assertEquals("From: Sali line: 161", 1, sali.annaListaTuloksista(l1).size()); 
    Tulos tulos = new Tulos(); 
    tulos.parse("1|1|1|2021-02-02|10|kg. testi"); 
    sali.lisaa(tulos); 
    assertEquals("From: Sali line: 165", 2, sali.annaListaTuloksista(l1).size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testKorvaaTaiLisaa178 
   * @throws SailoException when error
   */
  @Test
  public void testKorvaaTaiLisaa178() throws SailoException {    // Sali: 178
    alustaSali(); 
    assertEquals("From: Sali line: 181", 1, sali.annaListaTuloksista(l1).size()); 
    sali.korvaaTaiLisaa(t1); 
    assertEquals("From: Sali line: 183", 1, sali.annaListaTuloksista(l1).size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaTulosNro244 */
  @Test
  public void testAnnaTulosNro244() {    // Sali: 244
    alustaSali(); 
    assertEquals("From: Sali line: 246", "10", sali.annaTulosNro(t1)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaLisatiedot259 */
  @Test
  public void testAnnaLisatiedot259() {    // Sali: 259
    alustaSali(); 
    assertEquals("From: Sali line: 261", "kg. eka tulos.", sali.annaLisatiedot(t1)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testPoistaTulos273 
   * @throws Exception when error
   */
  @Test
  public void testPoistaTulos273() throws Exception {    // Sali: 273
    alustaSali(); 
    assertEquals("From: Sali line: 276", 1, sali.annaListaTuloksista(l1).size()); 
    sali.poistaTulos(t1); 
    assertEquals("From: Sali line: 278", 0, sali.annaListaTuloksista(l1).size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaListaTuloksista291 */
  @Test
  public void testAnnaListaTuloksista291() {    // Sali: 291
    alustaSali(); 
    Liike liike = new Liike(); 
    List<Tulos> loytyneet; 
    loytyneet = sali.annaListaTuloksista(l1); 
    assertEquals("From: Sali line: 297", 1, loytyneet.size()); 
    loytyneet = sali.annaListaTuloksista(l2); 
    assertEquals("From: Sali line: 299", 1, loytyneet.size()); 
    loytyneet = sali.annaListaTuloksista(liike); 
    assertEquals("From: Sali line: 301", 0, loytyneet.size()); 
  } // Generated by ComTest END
}