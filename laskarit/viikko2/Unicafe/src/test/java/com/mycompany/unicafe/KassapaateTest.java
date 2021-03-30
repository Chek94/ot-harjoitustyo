package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }
    
    @Test
    public void rahaOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullistenLounaidenMaaraOikein() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaidenLounaidenMaaraOikein() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanKateisostoKasvattaaRahaaKunMaksuRiittaa() {
        kassa.syoEdullisesti(1000);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisenLounaanKateisostoKasvattaaLounaitaKunMaksuRiittaa() {
        kassa.syoEdullisesti(1000);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanKateisostoEiMuutaLounaitaKunMaksuEiRiita() {
        kassa.syoEdullisesti(0);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanKateisostoEiMuutaRahaaKunMaksuEiRiita() {
        kassa.syoEdullisesti(0);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisenLounaanKateisostoPalauttaaMaksunKunMaksuEiRiita() {
        assertEquals(1, kassa.syoEdullisesti(1));
    }
    
    @Test
    public void maukkaanLounaanKateisostoKasvattaaRahaaKunMaksuRiittaa() {
        kassa.syoMaukkaasti(1000);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaanLounaanKateisostoKasvattaaLounaitaKunMaksuRiittaa() {
        kassa.syoMaukkaasti(1000);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanLounaanKateisostoEiMuutaLounaitaKunMaksuEiRiita() {
        kassa.syoMaukkaasti(0);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanLounaanKateisostoEiMuutaRahaaKunMaksuEiRiita() {
        kassa.syoMaukkaasti(0);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaanLounaanKateisostoPalauttaaMaksunKunMaksuEiRiita() {
        assertEquals(1, kassa.syoMaukkaasti(1));
    }
    
    @Test
    public void edullisenLounaanKorttiostoEiMuutaRahaaKunKortillaOnRiittavastiRahaa() {
        kortti = new Maksukortti(1000);
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisenLounaanKorttiostoEiMuutaRahaaKunKortillaEiOleRiittavastiRahaa() {
        kortti = new Maksukortti(0);
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisenLounaanKorttiostoVahentaaSaldoaKunKortillaOnRiittavastiRahaa() {
        kortti = new Maksukortti(1000);
        kassa.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void edullisenLounaanKorttiostoEiMuutaSaldoaKunKortillaEiOleRiittavastiRahaa() {
        kortti = new Maksukortti(100);
        kassa.syoEdullisesti(kortti);
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void edullisenLounaanKorttiostoKasvattaaLounastaKunKortillaOnRiittavastiRahaa() {
        kortti = new Maksukortti(1000);
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanKorttiostoEiMuutaLounastaKunKortillaEiOleRiittavastiRahaa() {
        kortti = new Maksukortti(0);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanKorttiostoPalauttaaTrueKunKortillaOnRiittavastiRahaa() {
        kortti = new Maksukortti(1000);
        assertEquals(true, kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void edullisenLounaanKorttiostoPalauttaaFalseKunKortillaEiOleRiittavastiRahaa() {
        kortti = new Maksukortti(0);
        assertEquals(false, kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void maukkaanLounaanKorttiostoEiMuutaRahaaKunKortillaOnRiittavastiRahaa() {
        kortti = new Maksukortti(1000);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaanLounaanKorttiostoEiMuutaRahaaKunKortillaEiOleRiittavastiRahaa() {
        kortti = new Maksukortti(0);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaanLounaanKorttiostoVahentaaSaldoaKunKortillaOnRiittavastiRahaa() {
        kortti = new Maksukortti(1000);
        kassa.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void maukkaanLounaanKorttiostoEiMuutaSaldoaKunKortillaEiOleRiittavastiRahaa() {
        kortti = new Maksukortti(100);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void maukkaanLounaanKorttiostoKasvattaaLounastaKunKortillaOnRiittavastiRahaa() {
        kortti = new Maksukortti(1000);
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanLounaanKorttiostoEiMuutaLounastaKunKortillaEiOleRiittavastiRahaa() {
        kortti = new Maksukortti(0);
        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanLounaanKorttiostoPalauttaaTrueKunKortillaOnRiittavastiRahaa() {
        kortti = new Maksukortti(1000);
        assertEquals(true, kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void maukkaanLounaanKorttiostoPalauttaaFalseKunKortillaEiOleRiittavastiRahaa() {
        kortti = new Maksukortti(0);
        assertEquals(false, kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void lataaminenKasvattaaSaldoa() {
        kortti = new Maksukortti(1000);
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(2000, kortti.saldo());
    }
    
    @Test
    public void lataaminenKasvattaaRahaa() {
        kortti = new Maksukortti(1000);
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(101000, kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaminenNegatiivisellaMaarallaEiMuutaRahaa() {
        kortti = new Maksukortti(1000);
        kassa.lataaRahaaKortille(kortti, -1000);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
}
