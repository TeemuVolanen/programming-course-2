package sali.test;
// Generated by ComTest BEGIN
import java.io.File;
import static org.junit.Assert.*;
import org.junit.*;
import sali.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.22 16:53:48 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class LiikkeetTest {



  // Generated by ComTest BEGIN
  /** testAnna41 */
  @Test
  public void testAnna41() {    // Liikkeet: 41
    Liikkeet liikkeet = new Liikkeet(); 
    Liike l1 = new Liike(), l2 = new Liike(); 
    liikkeet.lisaa(l1); assertEquals("From: Liikkeet line: 44", l1, liikkeet.anna(0)); 
    liikkeet.lisaa(l2); assertEquals("From: Liikkeet line: 45", l2, liikkeet.anna(1)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLisaa68 
   * @throws SailoException when error
   */
  @Test
  public void testLisaa68() throws SailoException {    // Liikkeet: 68
    Liikkeet liikkeet = new Liikkeet(); 
    Liike l1 = new Liike(), l2 = new Liike(); 
    assertEquals("From: Liikkeet line: 72", 0, liikkeet.getLkm()); 
    liikkeet.lisaa(l1); assertEquals("From: Liikkeet line: 73", 1, liikkeet.getLkm()); 
    liikkeet.lisaa(l2); assertEquals("From: Liikkeet line: 74", 2, liikkeet.getLkm()); 
    liikkeet.lisaa(l1); assertEquals("From: Liikkeet line: 75", 3, liikkeet.getLkm()); 
    liikkeet.lisaa(l1); assertEquals("From: Liikkeet line: 76", 4, liikkeet.getLkm()); 
    liikkeet.lisaa(l1); assertEquals("From: Liikkeet line: 77", 5, liikkeet.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta123 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta123() throws SailoException {    // Liikkeet: 123
    Liikkeet liikkeet = new Liikkeet(); 
    Liike liike1 = new Liike(); 
    liike1.parse("1|Takakyykky"); 
    liikkeet.lisaa(liike1); 
    Liike liike2 = new Liike(); 
    liike2.parse("2|Etukyykky"); 
    liikkeet.lisaa(liike2); 
    String tiedNimi = "testiAjoData/liike.dat"; 
    File file = new File(tiedNimi); 
    liikkeet.tallenna(tiedNimi); 
    liikkeet = new Liikkeet(); 
    liikkeet.lueTiedostosta(tiedNimi); 
    Liike l1 = liikkeet.anna(0); 
    l1.toString().equals("1|Takakyykky"); 
    Liike l2 = liikkeet.anna(1); 
    l2.toString().equals("2|Etukyykky"); 
    liikkeet.tallenna(tiedNimi); 
    assertEquals("From: Liikkeet line: 149", true, file.delete()); 
  } // Generated by ComTest END
}