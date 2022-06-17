/**
 * 
 */
package sali;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author teemu
 * @version Mar 2, 2021
 * email:tkvolane@student.jyu.fi
 *
 *Liikkeet -luokka pitää yllä liike rekisteriä, eli osaa lisätä ja poistaa liikkeen.
 *Lukee ja kirjoittaa liikkeiden tiedostoon. Osaa etsiä ja lajitella. Avustaja: Liike -luokka.
 */
public class Liikkeet {
    
    private static final int MAX_LIIKKEET = 5;
    private int liikkeetLkm = 0;
    private Liike[] alkiot;
    private String tiedostonPerusNimi = "OletusLiike";
    
    
    /**
     * @return liikkeiden lukumäärän
     */
    public int getLkm() {
        return this.liikkeetLkm;
    }
    
    
    /**
     * @param i monennenko liikkeen viite liikkeen viite halutaan
     * @return palauttaa viitteen i:teen liikkeeseen
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
     * @example
     * <pre name="test">
     * Liikkeet liikkeet = new Liikkeet();
     * Liike l1 = new Liike(), l2 = new Liike();
     * liikkeet.lisaa(l1); liikkeet.anna(0) === l1;
     * liikkeet.lisaa(l2); liikkeet.anna(1) === l2;
     * </pre>
     */
    public Liike anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || this.liikkeetLkm <= i) {
            throw new IndexOutOfBoundsException("laiton indeksi: " + i);
        }
        return alkiot[i];
    }
    
    
    /**
     * Luodaan alustava taulukko
     */
    public Liikkeet() {
        this.alkiot = new Liike[MAX_LIIKKEET];
    }
    
    
    /**
     * Lisätään liike tietorakenteeseen
     * @param liike lisättävä liike
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * Liikkeet liikkeet = new Liikkeet();
     * Liike l1 = new Liike(), l2 = new Liike();
     * liikkeet.getLkm() === 0;
     * liikkeet.lisaa(l1); liikkeet.getLkm() === 1;
     * liikkeet.lisaa(l2); liikkeet.getLkm() === 2;
     * liikkeet.lisaa(l1); liikkeet.getLkm() === 3;
     * liikkeet.lisaa(l1); liikkeet.getLkm() === 4;
     * liikkeet.lisaa(l1); liikkeet.getLkm() === 5;
     * </pre>
     */
    public void lisaa(Liike liike) {
        if (liikkeetLkm >= alkiot.length) {
            Liike[] uusi = new Liike[alkiot.length + 5];
            for (int i = 0; i < alkiot.length; i++) {
                uusi[i] = alkiot[i];
            }
            this.alkiot = uusi;
        }
        this.alkiot[this.liikkeetLkm] = liike;
        liikkeetLkm++;
    }

    
    /**
     * Tallentaa liikkeet tiedostoon
     * Tiedoston muoto:
     * 1 |takakyykky
     * 2 |etukyykky
     * 3 |vatsalihas
     * 4 |alaselkä
     * 5 |penkkipunnerrus
     * jossa ensimmäinen kohta on liikeId ja toinen on liikkeen nimi
     * @param tiedosto tiedosto johon tallennetaan
     * @throws SailoException jos tallennus epäonnistuu
     */
    public void tallenna(String tiedosto) throws SailoException {
        File ftiedosto = new File(tiedosto);
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftiedosto, false))) {
            for (int i = 0; i < getLkm(); i++) {
                Liike liike = anna(i);
                fo.println(liike);
            }
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto " + ftiedosto.getAbsolutePath() + " ei aukea!");
        }
    }
    
    
    /**
     * Lukee liikkeet tiedostosta
     * @param tiedosto tiedosto josta luetaan
     * @throws SailoException jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * #import java.io.File;
     * 
     * Liikkeet liikkeet = new Liikkeet();
     * 
     * Liike liike1 = new Liike();
     * liike1.parse("1|Takakyykky");
     * liikkeet.lisaa(liike1);
     * 
     * Liike liike2 = new Liike();
     * liike2.parse("2|Etukyykky");
     * liikkeet.lisaa(liike2);
     * 
     * String tiedNimi = "testiAjoData/liike.dat";
     * File file = new File(tiedNimi);
     * liikkeet.tallenna(tiedNimi);
     * 
     * liikkeet = new Liikkeet();
     * liikkeet.lueTiedostosta(tiedNimi);
     * 
     * Liike l1 = liikkeet.anna(0);
     * l1.toString().equals("1|Takakyykky");
     * Liike l2 = liikkeet.anna(1);
     * l2.toString().equals("2|Etukyykky");
     * liikkeet.tallenna(tiedNimi);
     * file.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String tiedosto) throws SailoException {
        File ftiedosto = new File(tiedosto);
        try (Scanner fi = new Scanner(new FileInputStream(ftiedosto))) {
            while (fi.hasNext()) {
                String rivi = "";
                rivi = fi.nextLine();
                Liike liike = new Liike();
                liike.parse(rivi);
                lisaa(liike);
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Ei saa luettua tiedostoa " + tiedosto);
        }
    }
    
    
    /**
     * Luetaan tietoja aikaisemmin luodusta tiedostosta
     * @throws SailoException jos ongelmia
     */
    public void lueTiedostosta() throws SailoException {
        lueTiedostosta(getTiedostonNimi());
    }
    
    
    /**
     * Antaa tiedoston nimen mihin tallennetaan
     * @return tiedoston nimi
     */
    public String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }


    /**
     * Asettaa tiedostolle tiedoston vakio nimen
     * @param nimi tiedoston nimi
     */
    public void setTiedostonPerusNimi(String nimi) {
        tiedostonPerusNimi = nimi;
    }


    /**
     * Antaa tiedoston nimen jota käytetään tallennukseen
     * @return tiedoston nimi
     */
    public String getTiedostonNimi() {
        return getTiedostonPerusNimi();
    }


    /**
     * Testipääohjelma
     * @param args ei käytetä
     */
    public static void main(String[] args) {
        Liikkeet liikkeet = new Liikkeet();
        
        try {
            liikkeet.lueTiedostosta("liike.dat");
        } catch (SailoException e1) {
            System.err.println(e1.getMessage());
        }
        
        Liike a = new Liike();
        Liike b = new Liike();
        a.rekisteroi();
        b.rekisteroi();
        a.taytaLiikeNimella();
        b.taytaLiikeNimella();
        
        liikkeet.lisaa(a);
        liikkeet.lisaa(b);
        
        try {
            liikkeet.tallenna("liike.dat");
        } catch (SailoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("------Liikkeet testi---------");
        
        for (int i = 0; i < liikkeet.getLkm(); i++) {
            Liike liike = liikkeet.anna(i);
            System.out.println("Liike indeksi: " + i);
            liike.tulosta(System.out);
        }
    }
}