/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yılan;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author emrec
 */
public class GameThread extends JPanel implements ActionListener{
   Timer timer;
    PANEL pl;
    int x = 0;

    public GameThread(PANEL pl) { // Constructor'da mevcut PANEL'i alıyoruz
        this.pl = pl;
        start();
        timer = new Timer(100, this);
        timer.start();
    }

    public void start(){
        this.setBounds(600, 0, 200, 600);
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(100,600));
        this.setLayout(null);
        this.setBackground(Color.white);
        
       
        this.setVisible(true);
    }
    @Override
   public void paintComponent(Graphics g){
    super.paintComponent(g);
       draw(g);
        
    }
   public void draw(Graphics g){
       g.setColor(Color.green);
       g.setFont(new Font(null,Font.ITALIC, 20));
   g.drawString("Skor:"+pl.getYenilenElmaSayısı(),20 , 20);
    g.drawString("Bomba sayisi:"+pl.getBombasayisi(),20 , 60);
           
   
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(pl.getYenilenElmaSayısı()!=0){
      
        repaint();
    }
    
    }
            
            
  

    
}
