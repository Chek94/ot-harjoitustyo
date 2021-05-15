/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chess.Logic;

/**
 *
 * @author MSalop
 */
public class Square {
    
    private final byte square;
    
    public Square( byte x, byte y ) {
        
        square = ( byte ) ( x << 3 | y );
        
    }
    
    public Square( long context ) {
        
        square = ( byte ) ( context & 0b111111 );
        
    }
    
    public byte getX(  ) {
        
        return  ( byte ) ( ( square & 0b111000 ) >> 3 );
        
    }
    
    public byte getY(  ) {
        
        return ( byte ) ( square & 0b111 );
        
    }
    
    public byte getSquare(  ) {
        
        return square;
        
    }
    
}
