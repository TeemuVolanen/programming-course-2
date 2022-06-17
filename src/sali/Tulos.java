/**
 * 
 */
package sali;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author teemu
 * @version Mar 8, 2021
 * email:tkvolane@student.jyu.fi
 *
 *Tulos -luokka tietää tuloksen kentät/sisällön. Osaa muuttaa "1|1|90|kg. 3x10" merkkijonon
 *tuloksen tiedoiksi. Osaa antaa merkkijonona i:n kentän tiedot. Osaa laittaa merkkijonon
 *i:nneksi kentäksi.
 */
public class Tulos implements Cloneable {
    
    private int tulosId;
    private int suorittajaId;
    private int liikeId;
    private String pvm = "";
    private int tulos;
    private String lisatiedot = "";
    private static int seuraavaTulosId = 1;
    
    
    /**
     * @return Tuloksen arvo kokonaislukuna
     * @example
     * <pre name="test">
     * Tulos t = new Tulos();
     * t.setTulos("1");
     * t.getTulos() === 1;
     * </pre>
     */
    public int getTulos() {
        return this.tulos;
    }

    
    /**
     * @return Tuloksen lisätiedot
     * @example
     * <pre name="test">
     * Tulos t = new Tulos();
     * t.setLisatieto("kg");
     * t.getLisatiedot() === "kg";
     * </pre>
     */
    public String getLisatiedot() {
        return this.lisatiedot;
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
     * Annetaan tulokselle tietoja testausta varten
     * Paitsi nyt se auttaa uuden tuloksen lisaamisessa
     * @param s suorittajan id nro
     * @param l liikkeen id nro
     * @example
     * <pre name="test">
     * Tulos t = new Tulos();
     * t.taytaTuloksella(1, 1);
     * t.getSuorittajaId() === 1;
     * t.getLiikeId() === 1;
     * </pre>
     */
    public void taytaTuloksella(int s, int l) {
        this.suorittajaId = s;
        this.liikeId = l;
    }
    
    
    /**
     * @return tuloksen id numero
     * @example
     * <pre name="test">
     * Tulos t = new Tulos();
     * t.setTulosId(1);
     * t.getTulosId() === 1;
     * </pre>
     */
    public int getTulosId() {
        return this.tulosId;
    }
    
    
    /**
     * @return tuloksen suorittajan id numero
     * @example
     * <pre name="test">
     * Tulos t = new Tulos();
     * t.taytaTuloksella(1, 1);
     * t.getSuorittajaId() === 1;
     * </pre>
     */
    public int getSuorittajaId() {
        return this.suorittajaId;
    }

    
    /**
     * @return tuloksen liikkeen id numero
     * @example
     * <pre name="test">
     * Tulos t = new Tulos();
     * t.taytaTuloksella(1, 1);
     * t.getLiikeId() === 1;
     * </pre>
     */
    public int getLiikeId() {
        return this.liikeId;
    }
    

    /**
     * @return tuloksen paivamaara
     * @example
     * <pre name="test">
     * Tulos t = new Tulos();
     * t.setPvm("1991-09-29");
     * t.getPvm() === "1991-09-29";
     * </pre>
     */
    public String getPvm() {
        return this.pvm;
    }
    
    
    /**
     * Alustetaan liike tyhjäksi
     */
    public Tulos() {
        //alustuslauseet hoitaa kaiken
    }
    
    
    /**
     * rekisteröidään liike eli annetaan sille liike id numero
     * @return rekisteröidyn liikkeen id numero
     * @example
     * <pre name="test">
     * Tulos t1 = new Tulos();
     * t1.getTulosId() === 0;
     * t1.rekisteroi();
     * Tulos t2 = new Tulos();
     * t2.rekisteroi();
     * int n1 = t1.getTulosId();
     * int n2 = t2.getTulosId();
     * n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        this.tulosId = seuraavaTulosId;
        seuraavaTulosId++;
        return this.tulosId;
    }
    
    
    /**
     * tulostetaan tuloksen tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tulosId)+
                " " + String.format("%03d", suorittajaId) +
                " " + String.format("%03d", liikeId) +
                " " + pvm + " " + tulos + " " + lisatiedot);
    }
    
    
    /**
     * tulostetaan tuloksen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Palauttaa tuloksen tiedot merkkijonona |-merkillä erotettuna
     * @example
     * <pre name="test">
     * Tulos t = new Tulos();
     * t.parse("1|1|1");
     * t.toString().startsWith("1|1|1|") === true;
     * </pre>
     */
    @Override
    public String toString() {
        return "" + tulosId + "|" +
                    suorittajaId + "|" +
                    liikeId + "|" +
                    pvm + "|" +
                    tulos + "|" +
                    lisatiedot;
    }
    
    
    /**
     * Selvitetään tuloksen tiedot |-merkillä erotetusta merkkijonosta
     * @param rivi merkkijono rivi josta tiedot selvitetään
     * @example
     * <pre name="test">
     * Tulos t = new Tulos();
     * t.parse("1|1|1");
     * t.getTulosId() === 1;
     * t.toString().startsWith("1|1|1|") === true;
     * t.rekisteroi();
     * int n = t.getTulosId();
     * t.parse(""+(n+20));
     * t.rekisteroi();
     * t.getTulosId() === n+20+1;
     * </pre>
     */
    public void parse(String rivi) {
        StringBuilder teksti = new StringBuilder(rivi);
        setTulosId(Mjonot.erota(teksti, '|', getTulosId()));
        suorittajaId = Mjonot.erota(teksti, '|', suorittajaId);
        liikeId = Mjonot.erota(teksti, '|', liikeId);
        pvm = Mjonot.erota(teksti, '|', pvm);
        tulos = Mjonot.erota(teksti, '|', tulos);
        lisatiedot = Mjonot.erota(teksti, '|', lisatiedot);
    }
    
    
    /**
     * Asetetaan liike id numero ja varmistetaan että seuraava liike id numero on
     * aina suurempi kuin tähän mennessä suurin
     * @param numero asetettava liike id
     */
    public void setTulosId(int numero) {
        tulosId = numero;
        if (tulosId >= seuraavaTulosId) seuraavaTulosId = tulosId + 1;
    }

    
    /**
     * Tehdään identtinen klooni tuloksesta
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * Tulos t = new Tulos();
     * t.parse("1|1|1");
     * Tulos kopio = t.clone();
     * kopio.toString() === t.toString();
     * t.parse("2|2|2");
     * kopio.toString().equals(t.toString()) === false;
     * </pre>
     */
    @Override
    public Tulos clone() throws CloneNotSupportedException {
        Tulos uusi;
        uusi = (Tulos) super.clone();
        return uusi;
    }
    
    
    /**
     * @param merkkijono asetettava tulos merkkijonona
     * @return null jos ei virhettä
     * @throws NumberFormatException jos virhe
     */
    public String setTulos(String merkkijono) throws NumberFormatException {
        try {
            tulos = Integer.parseInt(merkkijono);
            return null;
        } catch (final NumberFormatException e) {
            return "Ei ole numero!";
        }
    }
    

    /**
     * @param uusi uusi merkkijono lisatietokohtaan
     * @return null jos ei virhetta
     */
    public String setLisatieto(String uusi) {
        lisatiedot = uusi;
        return null;
    }

    
    /**
     * @param uusi paivamaara uusi
     * @return null jos ei virhetta
     */
    public String setPvm(String uusi) {
        pvm = uusi;
        return null;
    }

    
    /**
     * Testipääohjelma
     * @param args ei käytetä
     */
    public static void main(String[] args) {
        Tulos tulos1 = new Tulos();
        tulos1.taytaTuloksella(1, 1);
        tulos1.rekisteroi();
        
        tulos1.tulosta(System.out);
    }
}