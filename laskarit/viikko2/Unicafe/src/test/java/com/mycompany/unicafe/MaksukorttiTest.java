package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void lataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(10);
        assertEquals("saldo: 0.20", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikeinOtettaessaKunRahaaOnTarpeeksi() {
        kortti.otaRahaa(10);
        assertEquals("saldo: 0.0", kortti.toString());
    }
    
    @Test
    public void saldoEiVaheneOtettaessaKunRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(11);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void ottaminenPalauttaaTrueKunRahaaOnTarpeeksi() {
        assertEquals(true, kortti.otaRahaa(1));
    }
    
    @Test
    public void ottaminenPalauttaFalseKunRahaaEiOleTarpeeksi() {
        assertEquals(false, kortti.otaRahaa(11));
    }
    
    @Test
    public void saldoTulostuuOikein() {
        assertEquals(10, kortti.saldo());
    }
    
}
