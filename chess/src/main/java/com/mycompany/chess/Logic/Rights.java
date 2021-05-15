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
    
    boolean[  ] rights;
    byte enPassant;
    
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
    
}
