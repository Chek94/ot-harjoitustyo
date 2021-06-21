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
    
    private byte square;
    
    public Square( byte x, byte y ) {
        
        square = ( byte ) ( x | y << 3 );
        
    }
    
    public Square( int x, int y ) {
        
        square = ( byte ) ( x | y << 3 );
        
    }
    
    public Square( int context ) {
        
        square = ( byte ) ( context & 0b111111 );
        
    }
    
    public byte getX(  ) {
        
        return  ( byte ) ( square & 0b111  );
        
    }
    
    public byte getY(  ) {
        
        return ( byte ) ( ( square & 0b111000 ) >> 3 ) ;
        
    }
    
    public int getContext(  ) {
        
        return square;
        
    }
    
    public void setX( byte x ) {
        
        square = ( byte ) ( square & 0b11111000 | x );
        
    }
    
    public void setY( byte y ) {
        
        square = ( byte ) ( square & 0b11000111 | y << 3 );
        
    }
    
    public void setSquare( int context ) {
        
        square = ( byte ) ( context & 0b111111 );
        
    }
    
}
