/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chess;

/**
 *
 * @author misalope
 */
public class Main {
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> {
            new Board().setVisible(true);
            new Controls().setVisible(true);
        });
        
    }
    
}