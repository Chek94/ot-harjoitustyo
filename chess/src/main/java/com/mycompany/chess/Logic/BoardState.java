/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chess.Logic;

import java.util.LinkedList;
/**
 *
 * @author MSalop
 */
public class BoardState {
    
    private char[][] board;
    private Rights rights;
    private char drawCounter;
    
    private Square whiteKing;
    private Square blackKing;
    
    private LinkedList whitePieces;
    private LinkedList blackPieces;
    
    public BoardState(  ) {
        
        //board
        //rights = new Rights();
        drawCounter = 0;
        
        
        
    }
    
    public void move( Move move ) {
        
        applyMove( move.getMove(  ), move.getContext(  ) );
        
    }
    
    public void unmove( Move move ) {
        
        applyMove( (MoveLambda) Move.inverse( move.getMove(  ) ), move.getContext(  ) );
        
    }
    
    private void applyMove( MoveLambda move, long context ) {
        
        move.lambda( this, context );
        
    }
    
}
