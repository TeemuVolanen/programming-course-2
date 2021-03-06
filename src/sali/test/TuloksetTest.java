package sali.test;
// Generated by ComTest BEGIN
import sali.*;
import java.io.File;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.22 16:44:38 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class TuloksetTest {



  // Generated by ComTest BEGIN
  /** 
   * testKorvaaTaiLisaa68 
   * @throws SailoException when error
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testKorvaaTaiLisaa68() throws SailoException,CloneNotSupportedException {    // Tulokset: 68
    Tulokset tulokset = new Tulokset(); 
    Tulos t1 = new Tulos(), t2 = new Tulos(); 
    t1.rekisteroi(); t2.rekisteroi(); 
    assertEquals("From: Tulokset line: 74", 0, tulokset.getLkm()); 
    tulokset.korvaaTaiLisaa(t1); assertEquals("From: Tulokset line: 75", 1, tulokset.getLkm()); 
    tulokset.korvaaTaiLisaa(t2); assertEquals("From: Tulokset line: 76", 2, tulokset.getLkm()); 
    Tulos t3 = t1.clone(); 
    t3.taytaTuloksella(5,5); 
    tulokset.korvaaTaiLisaa(t3); assertEquals("From: Tulokset line: 79", 2, tulokset.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaTulos100 */
  @Test
  public void testAnnaTulos100() {    // Tulokset: 100
    Tulokset tulokset = new Tulokset(); 
    Tulos t = new Tulos(); 
    t.parse("1|1|1|2021-01-01|90|kg"); 
    tulokset.lisaa(t); 
    assertEquals("From: Tulokset line: 105", t.toString(), tulokset.annaTulos(1, "2021-01-01").toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaTulosNro124 */
  @Test
  public void testAnnaTulosNro124() {    // Tulokset: 124
    Tulokset tulokset = new Tulokset(); 
    Tulos t = new Tulos(); 
    t.parse("1|1|1|2021-01-01|90|kg"); 
    tulokset.lisaa(t); 
    assertEquals("From: Tulokset line: 129", "90", tulokset.annaTulosNro(1)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaLisatiedot148 */
  @Test
  public void testAnnaLisatiedot148() {    // Tulokset: 148
    Tulokset tulokset = new Tulokset(); 
    Tulos t = new Tulos(); 
    t.parse("1|1|1|2021-01-01|90|kg"); 
    tulokset.lisaa(t); 
    assertEquals("From: Tulokset line: 153", "kg", tulokset.annaLisatiedot(1)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta198 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta198() throws SailoException {    // Tulokset: 198
    Tulokset tulokset = new Tulokset(); 
    Tulos tulos1 = new Tulos(); 
    tulos1.parse("1|1|1|04/04/2021|95|kg. 3x10"); 
    tulokset.lisaa(tulos1); 
    Tulos tulos2 = new Tulos(); 
    tulos2.parse("2|1|2|04/04/2021|19|kg. 3x10"); 
    tulokset.lisaa(tulos2); 
    String tiedNimi = "testiAjoData/tulokset.dat"; 
    File file = new File(tiedNimi); 
    tulokset.tallenna(tiedNimi); 
    tulokset = new Tulokset(); 
    tulokset.lueTiedostosta(tiedNimi); 
    Tulos t1 = tulokset.getTulosAlkioista(0); 
    t1.toString().equals("1|1|1|04/04/2021|95|kg. 3x10"); 
    Tulos t2 = tulokset.getTulosAlkioista(1); 
    t2.toString().equals("2|1|2|04/04/2021|19|kg. 3x10"); 
    tulokset.tallenna(tiedNimi); 
    assertEquals("From: Tulokset line: 224", true, file.delete()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaLisatiedot306 */
  @Test
  public void testAnnaLisatiedot306() {    // Tulokset: 306
    Tulokset tulokset = new Tulokset(); 
    Tulos t = new Tulos(); 
    t.parse("1|1|1|2021-01-01|90|kg"); 
    tulokset.lisaa(t); 
    assertEquals("From: Tulokset line: 311", "kg", tulokset.annaLisatiedot(t)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testPoista330 
   * @throws SailoException when error
   */
  @Test
  public void testPoista330() throws SailoException {    // Tulokset: 330
    Tulokset tulokset = new Tulokset(); 
    Tulos t1 = new Tulos(); t1.taytaTuloksella(1,1); 
    Tulos t2 = new Tulos(); t2.taytaTuloksella(2,2); 
    Tulos t3 = new Tulos(); t3.taytaTuloksella(3,3); 
    Tulos t4 = new Tulos(); t4.taytaTuloksella(4,4); 
    tulokset.lisaa(t1); 
    tulokset.lisaa(t2); 
    tulokset.lisaa(t3); 
    tulokset.lisaa(t4); 
    assertEquals("From: Tulokset line: 342", 4, tulokset.getLkm()); 
    tulokset.poista(t4); 
    assertEquals("From: Tulokset line: 344", 3, tulokset.getLkm()); 
    tulokset.poista(t3); 
    assertEquals("From: Tulokset line: 346", 2, tulokset.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaListaTuloksista359 */
  @Test
  public void testAnnaListaTuloksista359() {    // Tulokset: 359
    Tulokset tulokset = new Tulokset(); 
    Liike l1 = new Liike(); 
    l1.parse("1|Takakyykky"); 
    Liike l2 = new Liike(); 
    l2.parse("2|Etukyykky"); 
    Liike l3 = new Liike(); 
    l3.parse("3|Penkkipunnerrus"); 
    Tulos t1 = new Tulos(); t1.taytaTuloksella(1,1); 
    Tulos t2 = new Tulos(); t2.taytaTuloksella(2,2); 
    Tulos t3 = new Tulos(); t3.taytaTuloksella(3,2); 
    tulokset.lisaa(t1); 
    tulokset.lisaa(t2); 
    tulokset.lisaa(t3); 
    List<Tulos> loytyneet; 
    loytyneet = tulokset.annaListaTuloksista(l1); 
    assertEquals("From: Tulokset line: 377", 1, loytyneet.size()); 
    loytyneet = tulokset.annaListaTuloksista(l2); 
    assertEquals("From: Tulokset line: 379", 2, loytyneet.size()); 
    loytyneet = tulokset.annaListaTuloksista(l3); 
    assertEquals("From: Tulokset line: 381", 0, loytyneet.size()); 
  } // Generated by ComTest END
}