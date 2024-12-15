
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
     int time=300;
    public GameThread(PANEL pl) { // Constructor'da mevcut PANEL'i alıyoruz
        this.pl = pl;
        start();
        timer = new Timer(1000, this);
        timer.start();
    }
    public void start(){
        this.setBounds(600, 0, 200, 600);
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(200,600));
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
       int minutes = time / 60;
        int seconds = time % 60;
        g.drawString(String.format("Kalan sure: %02d:%02d", minutes, seconds), 20, 100);
   g.drawString("Skor:"+pl.getYenilenElmaSayısı(),20 , 20);
    g.drawString("Bomba sayisi:"+pl.getBombasayisi(),20 , 60);

   }
   @Override
    public void actionPerformed(ActionEvent e) {
        
        if(time>0&& pl.getBodyx()!=x){
            time--;
           x=-100;
             repaint();
            }else if(time==0)
            pl.setDevam(false);
        
      
      
       
    
    
    }
            
            
  

    
}
