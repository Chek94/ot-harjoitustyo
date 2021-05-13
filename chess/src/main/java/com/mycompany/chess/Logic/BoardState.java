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
public class BoardState {
    
    private char[][] board;
    private boolean turn;
    private Rights rights;
    private char drawCounter;
    
    public BoardState() {
        
        turn = false;
        drawCounter = 0;
        
    }
    
    
    
}
