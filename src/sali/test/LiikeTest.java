package sali.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import sali.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.05.04 21:13:56 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class LiikeTest {



  // Generated by ComTest BEGIN
  /** testGetNimi27 */
  @Test
  public void testGetNimi27() {    // Liike: 27
    Liike l = new Liike(); 
    l.setLiikeNimi("Takakyykky"); 
    assertEquals("From: Liike line: 30", "Takakyykky", l.getNimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetLiikeId61 */
  @Test
  public void testGetLiikeId61() {    // Liike: 61
    Liike l = new Liike(); 
    l.setLiikeId(1); 
    assertEquals("From: Liike line: 64", 1, l.getLiikeId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi84 */
  @Test
  public void testRekisteroi84() {    // Liike: 84
    Liike l1 = new Liike(); 
    assertEquals("From: Liike line: 86", 0, l1.getLiikeId()); 
    l1.rekisteroi(); 
    Liike l2 = new Liike(); 
    l2.rekisteroi(); 
    int n1 = l1.getLiikeId(); 
    int n2 = l2.getLiikeId(); 
    assertEquals("From: Liike line: 92", n2-1, n1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString123 */
  @Test
  public void testToString123() {    // Liike: 123
    Liike l = new Liike(); 
    l.parse("1|Takakyykky"); 
    assertEquals("From: Liike line: 126", true, l.toString().startsWith("1|Takakyykky")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse139 */
  @Test
  public void testParse139() {    // Liike: 139
    Liike l = new Liike(); 
    l.parse("1|Takakyykky"); 
    assertEquals("From: Liike line: 142", 1, l.getLiikeId()); 
    assertEquals("From: Liike line: 143", true, l.toString().startsWith("1|Takakyykky")); 
    l.rekisteroi(); 
    int n = l.getLiikeId(); 
    l.parse(""+(n+20)); 
    l.rekisteroi(); 
    assertEquals("From: Liike line: 148", n+20+1, l.getLiikeId()); 
  } // Generated by ComTest END
}