/**
 * 
 */
package sali;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * @author teemu
 * @version Mar 8, 2021
 * email:tkvolane@student.jyu.fi
 * 
 *Tulokset -luokka pitää yllä tulosrekisteriä eli osaa lisätä ja poistaa tuloksen. Lukee ja
 *kirjoittaa tulosten tiedostoon. Osaa lajitella ja etsiä. Avustaja: Tulos -luokka.
 */
public class Tulokset implements Iterable<Tulos>{

    private final List<Tulos> alkiot = new ArrayList<Tulos>();
    private String tiedostonPerusNimi = "OletusTulokset";

    
    /**
     * Alustetaan tulokset
     */
    public Tulokset() {
        //luokka hoitaa automaattisesti
    }
    
    
    /**
     * @param t lisättävä tulos
     */
    public void lisaa(Tulos t) {
        alkiot.add(t);
    }
    
    
    /**
     * Palautetaan tulos listasta indeksistä i
     * @param i indeksi
     * @return tulos olio indexistä i
     */
    public Tulos getTulosAlkioista(int i) {
        return alkiot.get(i);
    }
    
    
    /**
     * @return Palautetaan tulosten lukumaara
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    
    /**
     * Korvataan tulos. tämä on nyt lisätty
     * @param tulos korvattavan tuloksen viite
     * @throws SailoException jos ongelmia
     * @example
     * <pre name="test">
     * #THROWS SailoException,CloneNotSupportedException
     * #PACKAGEIMPORT
     * Tulokset tulokset = new Tulokset();
     * Tulos t1 = new Tulos(), t2 = new Tulos();
     * t1.rekisteroi(); t2.rekisteroi();
     * tulokset.getLkm() === 0;
     * tulokset.korvaaTaiLisaa(t1); tulokset.getLkm() === 1;
     * tulokset.korvaaTaiLisaa(t2); tulokset.getLkm() === 2;
     * Tulos t3 = t1.clone();
     * t3.taytaTuloksella(5,5);
     * tulokset.korvaaTaiLisaa(t3); tulokset.getLkm() === 2;
     * </pre>
     */
    public void korvaaTaiLisaa(Tulos tulos) throws SailoException {
        int id = tulos.getTulosId();
        for (int i = 0; i < alkiot.size(); i++) {
            if (alkiot.get(i).getTulosId() == id) {
                alkiot.set(i, tulos);
                return;
            }
        }
        lisaa(tulos);
    }


    /**
     * Etsitään liikeid:tä ja paivamaaraa vastaava tulos olio
     * @param liikeId minkä liikkeen tulosta etsitään
     * @param pvm minka paivan tulosta etsitaan
     * @return tulos olio
     * @example
     * <pre name="test">
     * Tulokset tulokset = new Tulokset();
     * Tulos t = new Tulos();
     * t.parse("1|1|1|2021-01-01|90|kg");
     * tulokset.lisaa(t);
     * tulokset.annaTulos(1, "2021-01-01").toString() === t.toString();
     * </pre>
     */
    public Tulos annaTulos(int liikeId, String pvm) {
        for (Tulos t : alkiot) {
            if (t == null) continue;
            if (t.getLiikeId() == liikeId && t.getPvm().equals(pvm)) {
                return t;
            }
        }
        return null;
    }

    
    /**
     * Etsitään liikkeen tulos numerona
     * @param liikeTunnus minkä liikkeen tuloksia etsitään
     * @return löydetty tulos numerona
     * @example
     * <pre name="test">
     * Tulokset tulokset = new Tulokset();
     * Tulos t = new Tulos();
     * t.parse("1|1|1|2021-01-01|90|kg");
     * tulokset.lisaa(t);
     * tulokset.annaTulosNro(1) === "90";
     * </pre>
     */
    public String annaTulosNro(int liikeTunnus) {
        int tulos = 0;
        for (Tulos t : alkiot) {
            if (t.getLiikeId() == liikeTunnus) {
                tulos = t.getTulos();
            }
        }
        return "" + tulos;
    }
    

    /**
     * Palauttaa tietyn liikkeen lisätiedot
     * @param liikeTunnus liikkeen id numero jonka lisätiedot halutaan
     * @return lisätiedot merkkijono
     * @example
     * <pre name="test">
     * Tulokset tulokset = new Tulokset();
     * Tulos t = new Tulos();
     * t.parse("1|1|1|2021-01-01|90|kg");
     * tulokset.lisaa(t);
     * tulokset.annaLisatiedot(1) === "kg";
     * </pre>
     */
    public String annaLisatiedot(int liikeTunnus) {
        String lisa = "";
        for (Tulos l : alkiot) {
            if (l.getLiikeId() == liikeTunnus) {
                lisa = l.getLisatiedot();
            }
        }
        return lisa;
    }
    
    
    /**
     * Tallentaa tulokset tiedostoon
     * Tiedoston muoto:
     * 1 |1         |1      |13.01.2021|20   |Tulos kg. Suorituksia 3x10
     * 2 |1         |2      |14.01.2021|40   |Tulos kg. Suorituksia 3x10
     * 3 |2         |3      |15.01.2021|15   |Tulos kg. Suorituksia 3x10
     * 4 |2         |1      |16.01.2021|55   |Tulos kg. Suorituksia 3x10
     * 5 |3         |1      |17.01.2021|100  |Tulos kg. Suorituksia 3x10
     * 6 |3         |3      |18.01.2021|10   |Tulos kg. Suorituksia 3x10
     * jossa ensimmäinen osa on tulosId, toinen on käyttäjä/suorittaja id, kolmas
     * liikkeen id, neljäs pvm, viides tulos ja viimeinen lisätiedot
     * @param tiedosto tiedosto johon tallennetaan
     * @throws SailoException jos tallennus epäonnistuu
     */
    public void tallenna(String tiedosto) throws SailoException {
        File ftiedosto = new File(tiedosto);
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftiedosto, false))) {
            for (Tulos t : alkiot) {
                fo.println(t);
            }
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto " + ftiedosto.getAbsolutePath() + " ei aukea!");
        }
    }

    
    /**
     * Luetaan tulokset tiedostosta
     * @param tiedosto tiedosto jota luetaan 
     * @throws SailoException jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * #import java.io.File;
     * 
     * Tulokset tulokset = new Tulokset();
     * 
     * Tulos tulos1 = new Tulos();
     * tulos1.parse("1|1|1|04/04/2021|95|kg. 3x10");
     * tulokset.lisaa(tulos1);
     * 
     * Tulos tulos2 = new Tulos();
     * tulos2.parse("2|1|2|04/04/2021|19|kg. 3x10");
     * tulokset.lisaa(tulos2);
     * 
     * String tiedNimi = "testiAjoData/tulokset.dat";
     * File file = new File(tiedNimi);
     * tulokset.tallenna(tiedNimi);
     * 
     * tulokset = new Tulokset();
     * tulokset.lueTiedostosta(tiedNimi);
     * 
     * Tulos t1 = tulokset.getTulosAlkioista(0);
     * t1.toString().equals("1|1|1|04/04/2021|95|kg. 3x10");
     * Tulos t2 = tulokset.getTulosAlkioista(1);
     * t2.toString().equals("2|1|2|04/04/2021|19|kg. 3x10");
     * tulokset.tallenna(tiedNimi);
     * file.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String tiedosto) throws SailoException {
        File ftiedosto = new File(tiedosto);
        try (Scanner fi = new Scanner(new FileInputStream(ftiedosto))) {
            while (fi.hasNext()) {
                String rivi = "";
                rivi = fi.nextLine();
                Tulos tulos = new Tulos();
                tulos.parse(rivi);
                lisaa(tulos);
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Ei ole vielä olemassa tiedostoa nimellä: " + tiedosto);
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
     * Antaa tuloksen numeroarvon merkkijonona
     * @param tulosKohdalla tulos jonka tulosarvo annetaan
     * @return numeroarvo merkkijonona
     * Tulokset tulokset = new Tulokset();
     * Tulos t = new Tulos();
     * t.parse("1|1|1|2021-01-01|90|kg");
     * tulokset.lisaa(t);
     * tulokset.annaTulosNro(t) === "90";
     */
    public String annaTulosNro(Tulos tulosKohdalla) {
        int tulos = 0;
        for (Tulos t : alkiot) {
            if (t == null) break;
            if (t.getTulosId() == tulosKohdalla.getTulosId()) {
                tulos = t.getTulos();
            }
        }
        return "" + tulos;
    }


    /**
     * Antaa tuloksen lisatiedot merkkijonona
     * @param tulosKohdalla tulos jonka lisatiedot annetaan
     * @return lisatiedot merkkijonona
     * @example
     * <pre name="test">
     * Tulokset tulokset = new Tulokset();
     * Tulos t = new Tulos();
     * t.parse("1|1|1|2021-01-01|90|kg");
     * tulokset.lisaa(t);
     * tulokset.annaLisatiedot(t) === "kg";
     * </pre>
     */
    public String annaLisatiedot(Tulos tulosKohdalla) {
        String lisa = "";
        for (Tulos l : alkiot) {
            if (l == null) break;
            if (l.getTulosId() == tulosKohdalla.getTulosId()) {
                lisa = l.getLisatiedot();
            }
        }
        return lisa;
    }


    /**
     * Poistetaan tulos
     * @param poistettava poistettava tulos
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     * Tulokset tulokset = new Tulokset();
     * Tulos t1 = new Tulos(); t1.taytaTuloksella(1,1);
     * Tulos t2 = new Tulos(); t2.taytaTuloksella(2,2);
     * Tulos t3 = new Tulos(); t3.taytaTuloksella(3,3); 
     * Tulos t4 = new Tulos(); t4.taytaTuloksella(4,4); 
     * tulokset.lisaa(t1);
     * tulokset.lisaa(t2);
     * tulokset.lisaa(t3);
     * tulokset.lisaa(t4);
     * tulokset.getLkm() === 4;
     * tulokset.poista(t4);
     * tulokset.getLkm() === 3;
     * tulokset.poista(t3);
     * tulokset.getLkm() === 2;
     * </pre>
     */
    public void poista(Tulos poistettava) {
        alkiot.remove(poistettava);
    }


    /**
     * Annetaan lista tuloksista
     * @param liikeKohdalla minka liikkeen tulokset kerataan listaan
     * @return lista tietyn liikkeen kaikista tuloksista
     * @example
     * <pre name="test">
     * #import java.util.*;
     * Tulokset tulokset = new Tulokset();
     * Liike l1 = new Liike();
     * l1.parse("1|Takakyykky");
     * Liike l2 = new Liike();
     * l2.parse("2|Etukyykky");
     * Liike l3 = new Liike();
     * l3.parse("3|Penkkipunnerrus");
     * Tulos t1 = new Tulos(); t1.taytaTuloksella(1,1);
     * Tulos t2 = new Tulos(); t2.taytaTuloksella(2,2);
     * Tulos t3 = new Tulos(); t3.taytaTuloksella(3,2); 
     * tulokset.lisaa(t1);
     * tulokset.lisaa(t2);
     * tulokset.lisaa(t3);
     * 
     * List<Tulos> loytyneet;
     * loytyneet = tulokset.annaListaTuloksista(l1);
     * loytyneet.size() === 1; 
     * loytyneet = tulokset.annaListaTuloksista(l2);
     * loytyneet.size() === 2;
     * loytyneet = tulokset.annaListaTuloksista(l3);
     * loytyneet.size() === 0;  
     * </pre>
     */
    public List<Tulos> annaListaTuloksista(Liike liikeKohdalla) {
        List<Tulos> lista = new ArrayList<Tulos>();
        for (Tulos t : alkiot) {
            if (t.getLiikeId() == liikeKohdalla.getLiikeId()) lista.add(t);
        }
        return lista;
    }

    
    /**
     * Testipääohjelma
     * @param args ei käytetä
     */
    public static void main(String[] args) {
        Tulokset tulokset = new Tulokset();
        
        try {
            tulokset.lueTiedostosta("tulokset.dat");
        } catch (SailoException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        //tulokset.annaTulos(1).tulosta(System.out); tama on vanha koska aikaisemmin ei etsitty paivamaaran kanssa
        
        int apuluku = tulokset.alkiot.size();
        for (int i = 0; i < 5; i++) {
            if (tulokset.annaTulosNro(i) != null) {
                apuluku++;
                Tulos tulos1 = new Tulos();
                tulos1.rekisteroi();
                tulos1.taytaTuloksella(1, apuluku);
                tulokset.lisaa(tulos1);
            }
        }
        /*
        Tulos tulos1 = new Tulos();
        tulos1.rekisteroi();
        tulos1.taytaTuloksella(1, 1);
        Tulos tulos2 = new Tulos();
        tulos2.rekisteroi();
        tulos2.taytaTuloksella(1, 2);
        Tulos tulos3 = new Tulos();
        tulos3.rekisteroi();
        tulos3.taytaTuloksella(1, 3);
        
        tulokset.lisaa(tulos1);
        tulokset.lisaa(tulos2);
        tulokset.lisaa(tulos3);
        */
        try {
            tulokset.tallenna("tulokset.dat");
        } catch (SailoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("------------Tulokset testi------------");
        
        //List<Tulos> tulokset2 = tulokset.annaTulokset(1);
        //String tulokset2 = tulokset.annaTulokset(1);
        //String tulokset3 = tulokset.annaTulokset(2);
        
        
        //for (Tulos t : alkiot) {
        //    t.tulosta(System.out);
        //}
        //System.out.println(tulokset2);
        //System.out.println(tulokset3);
        
        for (int i = 0; i < tulokset.alkiot.size(); i++) {
            System.out.println(tulokset.annaTulosNro(i));
        }
    
    }

    
    /**
     * Iteraattori tulosten läpikäymiseen.
     */
    @Override
    public Iterator<Tulos> iterator() {
        return alkiot.iterator();
    }
}