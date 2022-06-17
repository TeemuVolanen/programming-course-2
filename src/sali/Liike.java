package sali;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author teemu
 * @version Feb 26, 2021
 * email:tkvolane@student.jyu.fi
 *
 *Liike -luokka tietää liikkeen nimen. Osaa tarkistaa onko liikkeen nimi käytössä tai
 *onko liikkeen nimi tyhjä. Osaa muuttaa liikenimen merkkijonon liikkeen tiedoiksi.
 *Osaa antaa merkkijonona liikkeen nimen. Osaa laittaa merkkijonon liikkeen nimeksi.
 */
public class Liike {
    
    private String nimi = "";
    private int liikeId;
    private static int seuraavaLiikeId = 1;
    
    
    /**
     * @return Liikkeen nimi
     * @example
     * <pre name="test">
     * Liike l = new Liike();
     * l.setLiikeNimi("Takakyykky");
     * l.getNimi() === "Takakyykky";
     * </pre>
     */
    public String getNimi() {
        return this.nimi;
    }
    
    
    /**
     * Arvotaan satunnainen kokonaisluku väliltä ala-yla
     * @param ala arvonnan alaraja
     * @param yla arvonnan yläraja
     * @return satunnainen kokonaisluku väliltä ala-yla
     */
    public static int rand(int ala, int yla) {
        double n = (yla - ala) * Math.random() + ala;
        return (int)Math.round(n);
    }
    
    
    /**
     * Annetaan liikkeelle nimi testausta varten
     */
    public void taytaLiikeNimella() {
        this.nimi = "Liike" + rand(1000, 9999);
    }
    
    
    /**
     * @return liikkeen id numero
     * @example
     * <pre name="test">
     * Liike l = new Liike();
     * l.setLiikeId(1);
     * l.getLiikeId() === 1;
     * </pre>
     */
    public int getLiikeId() {
        return this.liikeId;
    }
    
    
    /**
     * Alustetaan liike tyhjäksi
     */
    public Liike() {
        //alustuslauseet hoitaa kaiken
    }
    
    
    /**
     * rekisteröidään liike eli annetaan sille liike id numero
     * @return rekisteröidyn liikkeen id numero
     * @example
     * <pre name="test">
     * Liike l1 = new Liike();
     * l1.getLiikeId() === 0;
     * l1.rekisteroi();
     * Liike l2 = new Liike();
     * l2.rekisteroi();
     * int n1 = l1.getLiikeId();
     * int n2 = l2.getLiikeId();
     * n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        this.liikeId = seuraavaLiikeId;
        seuraavaLiikeId++;
        return this.liikeId;
    }
    
    
    /**
     * tulostetaan liikkeen tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", liikeId) + " " + nimi);
    }
    
    
    /**
     * tulostetaan liikkeen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Palauttaa liikkeen tiedot merkkijonona |-merkillä erotettuna
     * @example
     * <pre name="test">
     * Liike l = new Liike();
     * l.parse("1|Takakyykky");
     * l.toString().startsWith("1|Takakyykky") === true;
     * </pre>
     */
    @Override
    public String toString() {
        return "" + liikeId + "|" + nimi;
    }
    
    
    /**
     * Selvitetään liikkeen tiedot |-merkillä erotetusta merkkijonosta
     * @param rivi merkkijono rivi josta tiedot selvitetään
     * @example
     * <pre name="test">
     * Liike l = new Liike();
     * l.parse("1|Takakyykky");
     * l.getLiikeId() === 1;
     * l.toString().startsWith("1|Takakyykky") === true;
     * l.rekisteroi();
     * int n = l.getLiikeId();
     * l.parse(""+(n+20));
     * l.rekisteroi();
     * l.getLiikeId() === n+20+1;
     * </pre>
     */
    public void parse(String rivi) {
        StringBuilder teksti = new StringBuilder(rivi);
        setLiikeId(Mjonot.erota(teksti, '|', getLiikeId()));
        nimi = Mjonot.erota(teksti, '|', nimi);
    }
    
    
    /**
     * Asetetaan liike id numero ja varmistetaan että seuraava liike id numero on
     * aina suurempi kuin tähän mennessä suurin
     * @param numero asetettava liike id
     */
    public void setLiikeId(int numero) {
        liikeId = numero;
        if (liikeId >= seuraavaLiikeId) seuraavaLiikeId = liikeId + 1;
    }
    

    /**
     * Asetetaan liikkeelle nimi
     * @param uusi liikkeen nimi
     */
    public void setLiikeNimi(String uusi) {
        this.nimi = uusi;
    }

        
    /**
     * Testipääohjelma
     * @param args ei käytetä
     */
    public static void main(String[] args) {
        Liike a = new Liike();
        Liike b = new Liike();
        
        a.rekisteroi();
        b.rekisteroi();
        
        a.tulosta(System.out);
        a.taytaLiikeNimella();
        a.tulosta(System.out);
        
        b.tulosta(System.out);
        b.taytaLiikeNimella();
        b.tulosta(System.out);
    }
}