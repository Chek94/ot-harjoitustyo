/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chess.UI;

import com.mycompany.chess.Logic.BoardState;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;

/**
 *
 * @author MSalop
 */
public class Board extends javax.swing.JFrame {
    
    private BoardState state;
    
    private Transform transform;
    
    private static final Color LightColor;
    private static final Color DarkColor;
    
    static {
        
        LightColor = new Color( 100, 200, 200 );
        DarkColor = new Color( 50, 100, 100 );
        
    }
    
    /**
     * Creates new form Board
     * @param state
     * @param transform
     */
    public Board( BoardState state, Transform transform ) {
        
        this.state = state;
        
        this.transform = transform;
        
        initComponents(  );
        
    }
    
    @Override
    public void paint( Graphics graphics ) {
        
        super.paint(graphics);
        
        int[  ] board = board(  );
        
        graphics.clearRect( 0, 0, getWidth(  ), getHeight(  ) );
        
        paintBoard( graphics, board );
        
        paintCoordinates( graphics, board );
        
        paintPieces( graphics, board );
        
    }
    
    public void setTransform( Transform transform ) {
        
        this.transform = transform;
        
    }


    
    private int[  ] transform( int i, int j , Transform transform ) {
        
        return transform.lambda( i , j );
        
    }
    
    private int[  ] board(  ) {
        
        Insets insets = getInsets();
        
        int width = getWidth(  ) - insets.left - insets.right;
        int height = getHeight(  ) - insets.top - insets.bottom;
        
        int size = Math.min( width, height );
        size -= size % 8;
        
        int[  ] point = { 
            
            insets.left + ( width - size ) / 2,
            insets.top + ( height - size ) / 2,
            size,
            size >> 3
                
        };
        
        return point;
        
    }

    private int[  ] square( int[  ] board, int i, int j ) {
        
        int[  ] point1 = transform( i, j, transform );
        
        int[  ] point2 = {
            
            board[ 0 ] + point1[ 0 ] * board[ 3 ],
            board[ 1 ] + point1[ 1 ] * board[ 3 ]
            
        };
        
        return point2;

    }
    
    private void paintBoard( Graphics graphics, int[  ] board ) {
        
        graphics.setColor( LightColor );
        graphics.fillRect( board[ 0 ], board[ 1 ], board[ 2 ], board[ 2 ] );
        
        graphics.setColor( DarkColor );
        
        int i = 0;
        int j = 0;
        while ( j < 8 ) {
            
            if ( ( i + j ) % 2 == 0 ) {
                
                int[  ] point = square(board, i, j );
                graphics.fillRect( point[ 0 ], point [ 1 ], board[ 3 ], board[ 3 ] );
            
            }
            
            if ( 8 <= ++i ) { j++; i = 0; }
            
        }
        
    }
    
    private void paintCoordinates( Graphics graphics, int[  ] board ) {
        
        graphics.setFont( graphics.getFont(  ).deriveFont( ( float )18 * board[ 3 ] / Toolkit.getDefaultToolkit(  ).getScreenResolution(  ) ) );
        
        String[  ] map1 = { "1", "2", "3", "4", "5", "6", "7", "8" };
        String[  ] map2 = { "a", "b", "c", "d", "e", "f", "g", "h" };
        
        int i, j;
        
        if ( transform == UI.Identity ) i = 0;
        else i = 7;
        j = 0;
        
        while ( j < 8 ) {
            
            if ( ( i + j ) % 2 == 0 ) graphics.setColor( LightColor );
            else graphics.setColor( DarkColor );
            
            int[  ] point = square( board, i, j );
                        
            graphics.drawString( map1[ j ], point[ 0 ] + ( board[ 3 ] >> 4 ), point[ 1 ] + ( board[ 3 ] >> 2 ) );
            
            j++;
            
        }
        
        i = 0;
        if ( transform == UI.Identity ) j = 0;
        else j = 7;
        
        while ( i < 8 ) {
            
            if ( ( i + j ) % 2 == 0 ) graphics.setColor( LightColor );
            else graphics.setColor( DarkColor );
            
            int[  ] point = square( board, i, j );
            
            graphics.drawString( map2[ i ], point[ 0 ] + board[ 3 ] - ( board[ 3 ] >> 2 ), point[ 1 ] + board[ 3 ] - ( board[ 3 ] >> 4 ) );
            
            i++;
            
        }
        
    }
    
    private void paintPieces( Graphics graphics, int[  ] board ) {
        
        graphics.setFont( graphics.getFont(  ).deriveFont( ( float ) 72 * board[ 3 ] / Toolkit.getDefaultToolkit(  ).getScreenResolution(  ) ) );
        
        graphics.setColor(Color.WHITE);
        
        int[  ] point  = square( board, 0, 0 );
        
        graphics.drawString( "P", point[ 0 ] + ( board[ 3 ] >> 2 ) , point[ 1 ] + board[ 3 ] - ( board[ 3 ] >> 2 ) );
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

interface Transform {

    int[  ] lambda( int i, int j );

};
