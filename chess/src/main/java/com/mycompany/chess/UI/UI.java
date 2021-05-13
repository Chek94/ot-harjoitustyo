/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chess.UI;

import java.awt.Color;

/**
 *
 * @author MSalop
 */
public class UI {
    
    private Board board;
    private Controls controls;
    
    public UI() {
        
        board = new Board();
        controls = new Controls();
        
        java.awt.EventQueue.invokeLater(() -> {
            new Board().setVisible(true);
            new Controls().setVisible(true);
        });
        
    }
    
    public void whiteToMove() {
        
        controls.labelText("white to move");
        controls.labelBackground(Color.white);
        controls.labelForeground(Color.black);
                
    }
    
    public void blackToMove() {
        
        controls.labelText("black to move");
        controls.labelBackground(Color.black);
        controls.labelForeground(Color.white);
                
    }
    
}
