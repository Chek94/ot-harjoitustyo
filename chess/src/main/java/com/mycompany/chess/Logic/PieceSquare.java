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
public class PieceSquare {
    
    private byte piece;
    
    private Square square;
    
    public PieceSquare( byte piece, Square square ) {
        
        this.piece = piece;
        this.square = square;
        
    }
    
    public byte getPiece(  ) {
        
        return piece;
        
    }
    
    public Square getSquare(  ) {
        
        return square;
        
    }
    
    public void setPiece( byte piece ) {
        
        this.piece = piece;
        
    }
    
    public void setSquare( Square square ) {
        
        this.square = square;
        
    }
    
}
