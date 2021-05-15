/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chess;

import com.mycompany.chess.UI.UI;
import com.mycompany.chess.Logic.BoardState;

/**
 *
 * @author misalope
 */
public class Main {
    
    static BoardState state;
    static UI ui;
    
    public static void main( String args[  ] ) {
        
        state = new BoardState(  );
        ui = new UI( state );
        
    }
    
}
