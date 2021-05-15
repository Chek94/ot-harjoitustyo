/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chess.Logic;

import java.util.HashMap;
/**
 *
 * @author MSalop
 */
public class Move {
    
    static HashMap inverse;
    
    static MoveLambda placeholder;
    static MoveLambda placeholderInverse;
    
    private final MoveLambda move;
    private final long context;
    
    static {
        
        inverse = new HashMap(  );
        
        //inverse.put
        //
        
    }
    
    Move( MoveLambda move, long context ) {
        
        this.move = move;
        this.context = context;
        
    }
    
    public static MoveLambda inverse( MoveLambda move ) {
        
        return (MoveLambda) inverse.get( move );
        
    }
    
    public static long Context( Square square1, Square square2 ) {
        
        return (long) square1.getSquare(  ) | (long) square2.getSquare(  ) << 6;
        
    }
    
    public MoveLambda getMove(  ) {
        
        return move;
        
    }
    
    public long getContext(  ) {
        
        return context;
        
    }
    
    private static void basicMove( BoardState state, long context ) {
        
        Square[] square = unpack( context );
        //
        
    }
    
    private static void basicUnmove( BoardState state, long context ) {
        
        Square[] square = unpack( context );
        //
        
    }
    
    private static Square[] unpack( long context ) {
        
        Square[] square = { new Square( context ), new Square( context >> 6 ) };
        
        return square;
        
    }
    
}

interface MoveLambda {
    
    void lambda( BoardState state, long context );
    
}
