/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chess.UI;

import com.mycompany.chess.Logic.State;
import java.awt.Color;

/**
 *
 * @author MSalop
 */
public class UI {
    
    public static final Transform IDENTITY;
    public static final Transform FLIP;
    
    private State state;
    
    private final Board board;
    private final Controls controls;
    
    //
    
    static {
        
        IDENTITY = ( int i, int j ) -> { int[] point = { i, 7 - j }; return point; };
        FLIP = ( int i, int j ) -> { int[] point = { 7 - i,  j }; return point; };
        
    }
    
    public UI( State state ) {
        
        this.state = state;
        
        board = new Board( state, IDENTITY );
        controls = new Controls(  );
        
        board.setVisible( true );
        controls.setVisible( true );
        
    }
    
    public void whiteToMove(  ) {
        
        controls.labelText( "white to move" );
        controls.labelBackground( Color.white );
        controls.labelForeground( Color.black );
                
    }
    
    public void blackToMove(  ) {
        
        controls.labelText( "black to move" );
        controls.labelBackground( Color.BLACK );
        controls.labelForeground( Color.WHITE );
                
    }
    
}
