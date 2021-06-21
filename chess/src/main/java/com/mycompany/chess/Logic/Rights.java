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
public class Rights {
    
    private boolean[  ] rights;
    private byte enPassant;
    
    Rights(
            
            boolean turn,
            boolean whiteShort,
            boolean whiteLong,
            boolean blackShort,
            boolean blackLong,
            boolean enPassant,
            byte enPassantFile
            
    ) {
        
        boolean[  ] temp = { turn, whiteShort, whiteLong, blackShort, blackLong, enPassant };
        rights = temp;
        this.enPassant = enPassantFile;
        
    }
    
    public boolean getTurn(  ) {
        
        return rights[ 0 ];
        
    }
    
    public boolean getWhiteShort(  ) {
        
        return rights[ 1 ];
        
    }
    
    public boolean getWhiteLong(  ) {
        
        return rights[ 2 ];
        
    }
    
    public boolean getBlackShort(  ) {
        
        return rights[ 3 ];
        
    }
    
    public boolean getBlackLong(  ) {
        
        return rights[ 4 ];
        
    }
    
    public boolean getEnPassant(  ) {
        
        return rights[ 5 ];
        
    }
    
    public byte getEnPassantFile(  ) {
        
        return enPassant;
        
    }
    
    public void setTurn( boolean turn ) {
        
        rights[ 0 ] = turn;
        
    }
    
    public void setWhiteShort( boolean whiteShort ) {
        
        rights[ 1 ] = whiteShort;
        
    }
    
    public void setWhiteLong( boolean whiteLong ) {
        
        rights[ 2 ] = whiteLong;
        
    }
    
    public void setBlackShort( boolean blackShort ) {
        
        rights[ 3 ] = blackShort;
        
    }
    
    public void setBlackLong( boolean blackLong ) {
        
        rights[ 4 ] = blackLong;
        
    }
    
    public void setEnPassant( boolean enPassant ) {
        
        rights[ 5 ] = enPassant;
        
    }
    
    public void setEnPassantFile( byte enPassantFile ) {
        
        enPassant = enPassantFile;
        
    }
    
}
