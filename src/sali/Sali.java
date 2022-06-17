/**
 * 
 */
package sali;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * @author teemu
 * @version Mar 2, 2021
 * email:tkvolane@student.jyu.fi
 * 
 * Sali -luokka huolehtii Liike ja Tulos luokkien välisestä yhteistyöstä ja välittää
 * näitä tietoja pyydettäessä. Lukee ja kirjoittaa salin tiedostoon pyytämällä apua
 * avustajiltaan. Avustajat: Liikkeet ja Tulokset -luokat.
 * @example
 * <pre name="testJAVA">
 * #import sali.SailoException;
 *  private Sali sali;
 *  private Liike l1;
 *  private Liike l2;
 *  private Liike l3;
 *  private Liike l4;
 *  private Tulos t1;
 *  private Tulos t2;
 *  private Tulos t3; 
 *  private Tulos t4; 
 *  
 *  public void alustaSali() {
 *    sali = new Sali();
 *    l1 = new Liike(); l1.parse("1|Takakyykky");
 *    l2 = new Liike(); l2.parse("2|Etukyykky");
 *    l3 = new Liike(); l3.parse("3|Penkkipunnerrus");
 *    l4 = new Liike(); l4.parse("4|Pystypunnerrus");
 *    t1 = new Tulos(); t1.parse("1|1|1|2021-01-01|10|kg. eka tulos.");
 *    t2 = new Tulos(); t2.parse("2|1|2|2021-01-01|20|kg. toka tulos."); 
 *    t3 = new Tulos(); t3.parse("3|1|3|2021-01-01|30|kg. kolmas tulos.");
 *    t4 = new Tulos(); t4.parse("4|1|4|2021-01-01|40|kg. neljäs tulos.");
 *    try {
 *    sali.lisaa(l1);
 *    sali.lisaa(l2);
 *    sali.lisaa(l3);
 *    sali.lisaa(l4);
 *    sali.lisaa(t1);
 *    sali.lisaa(t2);
 *    sali.lisaa(t3);
 *    sali.lisaa(t4);
 *    } catch ( Exception e) {
 *       System.err.println(e.getMessage());
 *    }
 *  }
 * </pre>
 */
public class Sali {
    
    private Liikkeet liikkeet = new Liikkeet();
    private Tulokset tulokset = new Tulokset();
    
    
    /**
     * Antaa Liikkeiden i:n liike
     * @param i monesko liike (alkaa nollasta)
     * @return liike paikasta i
     * @example
     * <pre name="test">
     * alustaSali();
     * sali.annaLiike(0).toString() === "1|Takakyykky";
     * </pre>
     */
    public Liike annaLiike(int i) {
        return this.liikkeet.anna(i);
    }
    
    
    /**
     * Etsitään liikettä vastaava tulos olio
     * @param liike minkä liikkeen tulosta etsitään
     * @param pvm minka paivan tulosta etsitaan
     * @return tulos olio
     * @example
     * <pre name="test">
     * alustaSali();
     * sali.annaTulos(l1, "2021-01-01").toString() === "1|1|1|2021-01-01|10|kg. eka tulos.";
     * </pre>
     */
    public Tulos annaTulos(Liike liike, String pvm) {
        return this.tulokset.annaTulos(liike.getLiikeId(), pvm);        
    }

    
    /**
     * Antaa tuloksien i:n tuloksen numeron. Eli ei oliota
     * @param liike (aluksi oli i monesko tulos (alkaa nollasta))
     * @return tuloksen numero merkkijonona paikasta i
     * @example
     * <pre name="test">
     * alustaSali();
     * sali.annaTulosNro(l1) === "10";
     * </pre>
     */
    public String annaTulosNro(Liike liike) {
        return this.tulokset.annaTulosNro(liike.getLiikeId());
    }
    

    /**
     * Antaa tuloksen lisätiedot
     * @param liike minkä liikkeen tulos annetaan
     * @return tuloksen lisätiedot
     * @example
     * <pre name="test">
     * alustaSali();
     * sali.annaLisatiedot(l1) === "kg. eka tulos.";
     * </pre>
     */
    public String annaLisatiedot(Liike liike) {
        return this.tulokset.annaLisatiedot(liike.getLiikeId());
    }


    /**
     * @return liikkeiden lukumäärä
     * @example
     * <pre name="test">
     * alustaSali();
     * sali.getLiikkeet() === 4;
     * </pre>
     */
    public int getLiikkeet() {
        return this.liikkeet.getLkm();
    }
    
    
    /**
     * Lisätään uusi liike
     * @param liike lisättävä liike
     * @throws SailoException jos lisääminen ei onnistu
     * @example
     * <pre name="test">
     * #THROWS SailoException  
     * alustaSali();
     * sali.getLiikkeet() === 4;
     * Liike liike = new Liike();
     * liike.parse("5|Ylätalja");
     * sali.lisaa(liike);
     * sali.getLiikkeet() === 5;
     * </pre>
     */
    public void lisaa(Liike liike) throws SailoException {
        this.liikkeet.lisaa(liike);
    }
    
    
    /**
     * Lisätään uusi tulos
     * @param tulos lisättävä tulos
     * @example
     * <pre name="test">
     * alustaSali();
     * sali.annaListaTuloksista(l1).size() === 1;
     * Tulos tulos = new Tulos();
     * tulos.parse("1|1|1|2021-02-02|10|kg. testi");
     * sali.lisaa(tulos);
     * sali.annaListaTuloksista(l1).size() === 2;
     * </pre>
     */
    public void lisaa(Tulos tulos) {
        this.tulokset.lisaa(tulos);
    }
    
    
    /**
     * Korvaa tuloksen
     * @param tulos korvattavan tuloksen viite
     * @throws SailoException jos ongelmia
     * @example
     * <pre name="test">
     * #THROWS SailoException  
     * alustaSali();
     * sali.annaListaTuloksista(l1).size() === 1;
     * sali.korvaaTaiLisaa(t1);
     * sali.annaListaTuloksista(l1).size() === 1;
     * </pre>
     */
    public void korvaaTaiLisaa(Tulos tulos) throws SailoException {
        tulokset.korvaaTaiLisaa(tulos);
    }
    
    
    /**
     * Luetaan käyttäjän/suorittajan tiedot tiedostosta
     * @param tiedNimi tiedosto jota luetaan eli sen nimi
     * @throws SailoException jos lukeminen ei onnistu
     */
    public void lueTiedostosta(String tiedNimi) throws SailoException {
        liikkeet = new Liikkeet();
        tulokset = new Tulokset();
        
        setTiedosto(tiedNimi);
        liikkeet.lueTiedostosta();
        tulokset.lueTiedostosta();
    }
    
    
    /**
     * Asetetaan tiedostoille nimet mitä luetaan ja mihin tallennetaan
     * @param tiedNimi uusi tiedoston nimi
     */
    public void setTiedosto(String tiedNimi) {
        File directory = new File(tiedNimi);
        directory.mkdirs();
        String hakemistonNimi = "";
        if (!tiedNimi.isEmpty()) hakemistonNimi = tiedNimi + "/";
        tulokset.setTiedostonPerusNimi(hakemistonNimi + "tulokset.dat");
        liikkeet.setTiedostonPerusNimi("kaikki/liike.dat");
    }

    
    /**
     * Tallentaa suorittajan/käyttäjän tiedot tiedostoon.  
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void tallenna() throws SailoException {
        try {
            liikkeet.tallenna(liikkeet.getTiedostonNimi());
        } catch ( SailoException ex ) {
            throw new SailoException("Ei saa tallennettua liikkeitä, " + ex.getMessage());
        }

        try {
            tulokset.tallenna(tulokset.getTiedostonNimi());
        } catch ( SailoException ex ) {
            throw new SailoException("Ei saa tallennettua tuloksia, " + ex.getMessage());
        }
    }    


    /**
     * Antaa tuloksen tulosarvon merkkijonona
     * @param tulosKohdalla tulos jonka tulosarvo annetaan
     * @return tuloksen numeroarvo merkkijonona
     * @example
     * <pre name="test">
     * alustaSali();
     * sali.annaTulosNro(t1) === "10";
     * </pre>
     */
    public String annaTulosNro(Tulos tulosKohdalla) {
        return this.tulokset.annaTulosNro(tulosKohdalla);
    }


    /**
     * Annetaan tuloksen lisatiedot
     * @param tulosKohdalla tulos jonka lisatiedot annetaan
     * @return lisatiedot merkkijonona
     * @example
     * <pre name="test">
     * alustaSali();
     * sali.annaLisatiedot(t1) === "kg. eka tulos.";
     * </pre>
     */
    public String annaLisatiedot(Tulos tulosKohdalla) {
        return this.tulokset.annaLisatiedot(tulosKohdalla);
    }


    /**
     * Poistetaan tulos
     * @param poistettava poistettava tulos
     * @example
     * <pre name="test">
     * #THROWS Exception
     * alustaSali();
     * sali.annaListaTuloksista(l1).size() === 1;
     * sali.poistaTulos(t1);
     * sali.annaListaTuloksista(l1).size() === 0;
     * </pre>
     */
    public void poistaTulos(Tulos poistettava) {
        tulokset.poista(poistettava);
    }


    /**
     * Annetaan lista tuloksista
     * @param liikeKohdalla minka liikkeen tulokset kerataan listaan
     * @return lista tietyn liikkeen kaikista tuloksista
     * @example
     * <pre name="test">
     * #import java.util.*;
     * alustaSali();
     * Liike liike = new Liike();
     * List<Tulos> loytyneet;
     * loytyneet = sali.annaListaTuloksista(l1);
     * loytyneet.size() === 1; 
     * loytyneet = sali.annaListaTuloksista(l2);
     * loytyneet.size() === 1; 
     * loytyneet = sali.annaListaTuloksista(liike);
     * loytyneet.size() === 0; 
     * </pre>
     */
    public List<Tulos> annaListaTuloksista(Liike liikeKohdalla) {
        List<Tulos> lista = tulokset.annaListaTuloksista(liikeKohdalla);
        Collections.reverse(lista);
        return lista;
    }

    
    /**
     * Testipääohjelma
     * @param args ei käytetä
     */
    public static void main(String[] args) {
        Sali sali = new Sali();
        Liike a = new Liike();
        Liike b = new Liike();
        a.rekisteroi();
        b.rekisteroi();
        a.taytaLiikeNimella();
        b.taytaLiikeNimella();
        
        try {
            sali.lisaa(b);
            sali.lisaa(a);
            sali.lisaa(b);
            sali.lisaa(b);
            sali.lisaa(b);
            sali.lisaa(b);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        
        for (int i = 0; i < sali.getLiikkeet(); i++) {
            Liike liike = sali.annaLiike(i);
            liike.tulosta(System.out);
        }
    }
}