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
    
    public static final MoveLambda DEFAULT;
    public static final MoveLambda DEFAULTINVERSE;
    public static final MoveLambda KINGMOVE;
    public static final MoveLambda KINGMOVEINVERSE;
    public static final MoveLambda ROOKMOVE;
    public static final MoveLambda ROOKMOVEINVERSE;
    public static final MoveLambda SHORTCASTLE;
    public static final MoveLambda SHORTCASTLEINVERSE;
    public static final MoveLambda LONGCASTLE;
    public static final MoveLambda LONGCASTLEINVERSE;
    public static final MoveLambda DOUBLEPAWNMOVE;
    public static final MoveLambda DOUBLEPAWNMOVEINVERSE;
    public static final MoveLambda ENPASSANT;
    public static final MoveLambda ENPASSANTINVERSE;
    public static final MoveLambda PROMOTION;
    public static final MoveLambda PROMOTIONINVERSE;
    
    private static HashMap inverse;
    
    private static final int X1MASK;
    private static final int X1SHIFT;
    private static final int Y1MASK;
    private static final int Y1SHIFT;
    private static final int X2MASK;
    private static final int X2SHIFT;
    private static final int Y2MASK;
    private static final int Y2SHIFT;
    private static final int P1MASK;
    private static final int P1SHIFT;
    private static final int CMASK;
    private static final int CSHIFT;
    private static final int P2MASK;
    private static final int P2SHIFT;
    private static final int SMASK;
    private static final int SSHIFT;
    private static final int LMASK;
    private static final int LSHIFT;
    
    private final MoveLambda move;
    private final int context;
    
    private Move next;
    private final Move last;
    
    static {
        
        X1MASK = 0b111;
        X1SHIFT = 0;
        Y1MASK = 0b111000;
        Y1SHIFT = 3;
        X2MASK = 0b111000000;
        X2SHIFT = 6;
        Y2MASK = 0b111000000000;
        Y2SHIFT = 9;
        P1MASK = 0b111000000000000;
        P1SHIFT = 12;
        CMASK = 0b111111000000000000000;
        CSHIFT = 15;
        P2MASK = 0b111000000000000000000000;
        P2SHIFT = 21;
        SMASK = 0b1000000000000000000000000;
        SSHIFT = 24;
        LMASK = 0b10000000000000000000000000;
        LSHIFT = 25;

        DEFAULT = ( State state, int context ) -> {
            
            basicMove( state, context );
            
        };
        
        DEFAULTINVERSE = ( State state, int context ) -> {
            
            basicUnmove( state, context );
            
        };
        
        KINGMOVE = ( State state, int context ) -> {
            
            Rights rights = state.getRights(  );
            
            if ( rights.getTurn(  ) ) {
                
                rights.setWhiteShort( false );
                rights.setWhiteLong( false );
                
            } else {
                
                rights.setBlackShort( false );
                rights.setBlackLong( false );
                
            }
            
            basicMove( state, context );
            
        };
        
        KINGMOVEINVERSE = ( State state, int context ) -> {
            
            basicUnmove( state, context );
            
            byte shortCastle = ( byte ) ( ( context & SMASK ) >> SSHIFT );
            byte longCastle = ( byte ) ( ( context & LMASK ) >> LSHIFT );
            
            Rights rights = state.getRights(  );
            
            if ( rights.getTurn(  ) ) {
                
                rights.setWhiteShort( shortCastle == 0 );
                rights.setWhiteLong( longCastle == 0 );
                
            } else {
                
                rights.setBlackShort( shortCastle == 0 );
                rights.setBlackLong( longCastle == 0 );
                
            }
            
        };
        
        ROOKMOVE = ( State state, int context ) -> {
            
            Rights rights = state.getRights(  );
            
            if ( rights.getTurn(  ) && ( context & X1MASK ) >> X1SHIFT == 0 )
                rights.setWhiteLong( false );
            if ( rights.getTurn(  ) && ( context & X1MASK ) >> X1SHIFT == 1 )
                rights.setWhiteShort( false );
            if ( !rights.getTurn(  ) && ( context & X1MASK ) >> X1SHIFT == 0 )
                rights.setBlackLong( false ); 
            else rights.setBlackShort( false );
            
            basicMove( state, context );
            
        };
        ROOKMOVEINVERSE = ;
        SHORTCASTLE = ;
        SHORTCASTLEINVERSE = ;
        LONGCASTLE = ;
        LONGCASTLEINVERSE = ;
        DOUBLEPAWNMOVE = ;
        DOUBLEPAWNMOVEINVERSE = ;
        ENPASSANT = ;
        ENPASSANTINVERSE = ;
        PROMOTION = ;
        PROMOTIONINVERSE = ;
        
        inverse = new HashMap(  );
        
        inverse.put( DEFAULT, DEFAULTINVERSE );
        inverse.put( KINGMOVE, KINGMOVEINVERSE );
        inverse.put( ROOKMOVE, ROOKMOVEINVERSE );
        inverse.put( SHORTCASTLE, SHORTCASTLEINVERSE );
        inverse.put( LONGCASTLE, LONGCASTLEINVERSE );
        inverse.put( DOUBLEPAWNMOVE, DOUBLEPAWNMOVEINVERSE );
        inverse.put( ENPASSANT, ENPASSANTINVERSE );
        inverse.put( PROMOTION, PROMOTIONINVERSE );
        
    }
    
    Move( MoveLambda move, int context, Move last ) {
        
        this.move = move;
        this.context = context;
        
    }
    
    public static MoveLambda inverse( MoveLambda move ) {
        
        return ( MoveLambda ) inverse.get( move );
        
    }
    
    public static int Context( Square square1, Square square2 ) {
        
        return ( int ) square1.getContext(  ) | ( int ) square2.getContext(  ) << 6;
        
    }
    
    public MoveLambda getLambda(  ) {
        
        return move;
        
    }
    
    public int getContext(  ) {
        
        return context;
        
    }
    
    private static void basicMove( State state, int context ) {
        
        byte x1 = ( byte ) ( context & 0b111 );
        byte y1 = ( byte ) ( ( context & 0b111000 ) >> 3 );
        byte x2 = ( byte ) ( ( context & 0b111000000 ) >> 6 );
        byte y2 = ( byte ) ( ( context & 0b111000000000 ) >> 9 );
        
        byte[  ][  ] board = state.getBoard(  );
        
        if (
                
                ( board[ x1 ][ y1 ] & 0b111 ) == State.PAWN ||
                ( board[ x2 ][ y2 ] & 0b111 ) != State.EMPTY
                
            ) state.setDrawCounter( ( byte ) 0 );
        else state.incrementDrawCounter(  );
        
        board[ x2 ][ y2 ] = board[ x1 ][ y1 ];
        board[ x1 ][ y1 ] = State.EMPTY;
        
        Rights rights = state.getRights(  );
        rights.setTurn( !rights.getTurn(  ) );
        
    }
    
    private static void basicUnmove( State state, int context ) {
        
        byte x1 = ( byte ) ( ( context & X1MASK ) >> X1SHIFT );
        byte y1 = ( byte ) ( ( context & Y1MASK ) >> Y1SHIFT );
        byte x2 = ( byte ) ( ( context & X2MASK ) >> X2SHIFT );
        byte y2 = ( byte ) ( ( context & Y2MASK ) >> Y2SHIFT );
        
        byte[  ][  ] board = state.getBoard(  );
        
        state.setDrawCounter( ( byte ) ( ( context & CMASK ) >> CSHIFT ) );
        
        board[ x1 ][ y1 ] = board[ x2 ][ y2 ];
        board[ x2 ][ y2 ] = ( byte ) ( ( context & P1MASK ) >> P1SHIFT );
        
        Rights rights = state.getRights(  );
        rights.setTurn( !rights.getTurn(  ) );
        
    }
    
}

interface MoveLambda {
    
    void lambda( State state, int context );
    
}
