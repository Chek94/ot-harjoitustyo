/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chess.UI;

import com.mycompany.chess.Logic.BoardState;
import java.awt.Color;

/**
 *
 * @author MSalop
 */
public class UI {
    
    public static final Transform Identity;
    public static final Transform Flip;
    
    private BoardState state;
    
    private final Board board;
    private final Controls controls;
    
    //
    
    static {
        
        Identity = ( int i, int j ) -> { int[] point = { i, 7 - j }; return point; };
        Flip = ( int i, int j ) -> { int[] point = { 7 - i,  j }; return point; };
        
    }
    
    public UI( BoardState state ) {
        
        this.state = state;
        
        board = new Board( state, Flip );
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
