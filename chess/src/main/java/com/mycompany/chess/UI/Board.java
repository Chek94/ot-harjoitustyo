/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chess.UI;

import com.mycompany.chess.Logic.State;
import com.mycompany.chess.Logic.PieceSquare;
import com.mycompany.chess.Logic.Square;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Iterator;

/**
 *
 * @author MSalop
 */
public class Board extends javax.swing.JFrame {
    
    private static final Color LIGHTCOLOR;
    private static final Color DARKCOLOR;
    private static final Color HIGHLIGHT;
    
    private State state;
    
    private Transform transform;
    
    private Square selectedSquare;
    
    static {
        
        LIGHTCOLOR = new Color( 100, 200, 200 );
        DARKCOLOR = new Color( 50, 100, 100 );
        HIGHLIGHT = new Color( 200, 100, 100 );
        
    }
    
    /**
     * Creates new form Board
     * @param state
     * @param transform
     */
    public Board( State state, Transform transform ) {
        
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
        
        paintHighlights( graphics, board );
        
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
        
        graphics.setColor( LIGHTCOLOR );
        graphics.fillRect( board[ 0 ], board[ 1 ], board[ 2 ], board[ 2 ] );
        
        graphics.setColor( DARKCOLOR );
        
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
        
        if ( transform == UI.IDENTITY ) i = 0;
        else i = 7;
        j = 0;
        
        while ( j < 8 ) {
            
            if ( ( i + j ) % 2 == 0 ) graphics.setColor( LIGHTCOLOR );
            else graphics.setColor( DARKCOLOR );
            
            int[  ] point = square( board, i, j );
                        
            graphics.drawString( map1[ j ], point[ 0 ] + ( board[ 3 ] >> 4 ), point[ 1 ] + ( board[ 3 ] >> 2 ) );
            
            j++;
            
        }
        
        i = 0;
        if ( transform == UI.IDENTITY ) j = 0;
        else j = 7;
        
        while ( i < 8 ) {
            
            if ( ( i + j ) % 2 == 0 ) graphics.setColor( LIGHTCOLOR );
            else graphics.setColor( DARKCOLOR );
            
            int[  ] point = square( board, i, j );
            
            graphics.drawString( map2[ i ], point[ 0 ] + board[ 3 ] - ( board[ 3 ] >> 2 ), point[ 1 ] + board[ 3 ] - ( board[ 3 ] >> 4 ) );
            
            i++;
            
        }
        
    }
    
    private void paintPieces( Graphics graphics, int[  ] board ) {
        
        String[] map = { "", "K", "P", "N", "B", "R", "Q" };
        
        Square square;
        int[] point;
        Iterator iterator;
        
        graphics.setFont( graphics.getFont(  ).deriveFont( ( float ) 72 * board[ 3 ] / Toolkit.getDefaultToolkit(  ).getScreenResolution(  ) ) );
        
        graphics.setColor(Color.WHITE);
        
        square = state.getWhiteKing(  );
        
        point  = square( board, square.getX(  ), square.getY(  ) );
        
        graphics.drawString( "K", point[ 0 ] + ( board[ 3 ] >> 2 ) , point[ 1 ] + board[ 3 ] - ( board[ 3 ] >> 2 ) );
        
        iterator = state.getWhitePieces().iterator();
        
        while ( iterator.hasNext(  ) ) {
            
            PieceSquare element = ( PieceSquare ) iterator.next(  );
            
            square = element.getSquare(  );
            
            point  = square( board, square.getX(  ), square.getY(  ) );
            
            byte piece = ( byte ) ( element.getPiece() & 0b111 );
            
            graphics.drawString( map[ piece ], point[ 0 ] + ( board[ 3 ] >> 2 ) , point[ 1 ] + board[ 3 ] - ( board[ 3 ] >> 2 ) );
            
        }
        
        graphics.setColor(Color.BLACK);
        
        square = state.getBlackKing(  );
        
        point  = square( board, square.getX(  ), square.getY(  ) );
        
        graphics.drawString( "K", point[ 0 ] + ( board[ 3 ] >> 2 ) , point[ 1 ] + board[ 3 ] - ( board[ 3 ] >> 2 ) );
        
        iterator = state.getBlackPieces().iterator();
        
        while ( iterator.hasNext(  ) ) {
            
            PieceSquare element = ( PieceSquare ) iterator.next(  );
            
            square = element.getSquare(  );
            
            point  = square( board, square.getX(  ), square.getY(  ) );
            
            byte piece = ( byte ) ( element.getPiece() & 0b111 );
            
            graphics.drawString( map[ piece ], point[ 0 ] + ( board[ 3 ] >> 2 ) , point[ 1 ] + board[ 3 ] - ( board[ 3 ] >> 2 ) );
            
        }
        
    }
    
    private void paintHighlights( Graphics graphics, int[  ] board ) {
        
        if ( selectedSquare == null ) return;
        
        graphics.setColor(HIGHLIGHT);
        
        int[  ] point = square( board, selectedSquare.getX(  ), selectedSquare.getY(  ) );
        
        graphics.fillRect( point[ 0 ], point [ 1 ], board[ 3 ], board[ 3 ] );
        
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
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

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

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        int x = evt.getX(  );
        int y = evt.getY(  );
        
        int[  ] board = board(  );
        
        int[] point1 = { ( x - board[ 0 ] ) / board[ 3 ], ( y - board[ 1 ] ) / board[ 3 ] };
        
        int[] point2 = transform( point1[ 0 ], point1[ 1 ], transform );
        
        if ( selectedSquare == null ) selectedSquare = new Square( point2[ 0 ], point2[ 1 ] );
        else selectedSquare = null;
        
        repaint();
        
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

interface Transform {

    int[  ] lambda( int i, int j );

};
