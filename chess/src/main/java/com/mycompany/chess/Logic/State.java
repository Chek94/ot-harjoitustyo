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
public class State {
    
    public static final byte EMPTY;
    public static final byte KING;
    public static final byte PAWN;
    public static final byte KNIGHT;
    public static final byte BISHOP;
    public static final byte ROOK;
    public static final byte QUEEN;
    
    public static final byte WHITE;
    public static final byte BLACK;
    
    private byte[  ][  ] board;
    private Rights rights;
    private byte drawCounter;
    
    private Square whiteKing;
    private Square blackKing;
    
    private LinkedList whitePieces;
    private LinkedList blackPieces;
    
    static {
        
        EMPTY = 0b000;
        KING = 0b001;
        PAWN = 0b010;
        KNIGHT = 0b011;
        BISHOP = 0b100;
        ROOK = 0b101;
        QUEEN = 0b110;
        
        WHITE = 0b0000;
        BLACK = 0b1000;
        
    }
    
    public State(  ) {
        
        board = new byte[ 8 ][ 8 ];
        
        whitePieces = new LinkedList(  );
        blackPieces = new LinkedList(  );
        
        byte[  ] map = { ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK };
        
        int i = 0;
        while ( i < 8 ) {
            
            board[ i ][ 0 ] = ( byte ) ( WHITE | map[ i ] );
            board[ i ][ 1 ] = ( byte ) ( WHITE | PAWN );
            board[ i ][ 6 ] = ( byte ) ( BLACK | PAWN );
            board[ i ][ 7 ] = ( byte ) ( BLACK | map[ i ] );
            
            if ( i != 4 ) whitePieces.add( new PieceSquare(
                    
                    ( byte ) ( WHITE | map[ i ] ),
                    new Square( ( byte ) i, ( byte ) 0 )
            
            ) );
            
            whitePieces.add( new PieceSquare( 
                    
                    ( byte ) ( WHITE | PAWN ),
                    new Square( ( byte ) i, ( byte ) 1 )
                    
            ) );
            
            blackPieces.add( new PieceSquare( 
                    
                    ( byte ) ( BLACK | PAWN ),
                    new Square( ( byte ) i, ( byte ) 6 )
                    
            ) );
            
            if ( i != 4 ) blackPieces.add( new PieceSquare(
                    
                    ( byte ) ( BLACK | map[ i ] ),
                    new Square( ( byte ) i, ( byte ) 7 )
            
            ) );
            
            i++;
            
        }
        
        whiteKing = new Square( ( byte ) 4, ( byte ) 0 );
        blackKing = new Square( ( byte ) 4, ( byte ) 7 );
        
        rights = new Rights( true, true, true, true, true, false, ( byte ) 0 );
        
        drawCounter = 0;
        
    }
    
    public byte[  ][  ] getBoard(  ) {
        
        return board;
        
    }
    
    public Rights getRights(  ) {
        
        return rights;
        
    }
    
    public Square getWhiteKing(  ) {
        
        return whiteKing;
        
    }
    
    public Square getBlackKing(  ) {
        
        return blackKing;
        
    }
    
    public LinkedList getWhitePieces(  ) {
        
        return whitePieces;
        
    }
    
    public LinkedList getBlackPieces(  ) {
        
        return blackPieces;
        
    }
    
    public void setWhiteKing( Square king ) {
        
        whiteKing = king;
        
    }
    
    public void setblackKing( Square king ) {
        
        blackKing = king;
        
    }
    
    public void incrementDrawCounter(  ) {
        
        drawCounter++;
        
    }
    
    public void setDrawCounter( byte counter  ) {
        
        drawCounter = counter;
        
    }
    
    public void move( Move move ) {
        
        applyMove( move.getLambda(  ), move.getContext(  ) );
        
    }
    
    public void unmove( Move move ) {
        
        applyMove( ( MoveLambda ) Move.inverse( move.getLambda(  ) ), move.getContext(  ) );
        
    }
    
    private void applyMove( MoveLambda move, int context ) {
        
        move.lambda( this, context );
        
    }
    
}
