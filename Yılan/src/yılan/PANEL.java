/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yılan;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;


public final class PANEL extends JPanel implements ActionListener ,KeyListener{
    
     final static int SİZE_WİDTH=600;
    final static int SİZE_LENGHT=600;
    final static int UNIT=25;
  ArrayList<Integer> bombaX = new ArrayList<>();
 ArrayList<Integer> bombaY = new ArrayList<>();
    static int elmax=0;
     static int elmay=0;
    
     int bodylenght=6;
     int []bodyx=new int[1000];
     int []bodyy=new int[1000];
     int x=0; //Haraket
     int y=0; // haraket
     private int bombasayisi=1;
     int yenilenElmaSayısı=0;
    boolean ilk=true;
    boolean devam=true;
    Timer timer;
    JoystickControl joystickControl;
    boolean tek1=true;
    public PANEL(boolean tek1){
        
        this.setFocusable(true);
        elmaAdd(); 
        bombaAdd();
        System.out.println(elmax+" "+elmay);
     
     
        start();
        timer=new Timer(100, this);
        timer.start();
       
   
        joystickControl = new JoystickControl(this, "COM7");
        
    }
    public PANEL(){
    
    }
    
 
    public void start(){
      this.setBounds(0,0,SİZE_WİDTH, SİZE_LENGHT);
        this.setPreferredSize(new Dimension(SİZE_WİDTH,SİZE_LENGHT));
        this.setLayout(null);
        this.setBackground(Color.white);
        this.addKeyListener(this);
       
        this.setVisible(true);
         
    }

    /**
     *
     * @param g
     */
   public void paintComponent(Graphics g){
       super.paintComponent(g);
      
       draw(g);
      
    
       
       
     
       
   }
    public void draw(Graphics g) {
        
        for(int i=0;i<SİZE_LENGHT/UNIT;i++)
        {        
       
            g.drawLine(i*UNIT, 0, i*UNIT, SİZE_LENGHT);
            g.drawLine(0,UNIT* i, SİZE_WİDTH, i*UNIT);
            
        }  
        //elma
          
        g.setColor(Color.red);
         g.fillOval(elmax, elmay, UNIT, UNIT);
         //bomba
        for (int i = 0; i < bombaX.size(); i++) {
     g.setColor(Color.black);
    g.fillOval(bombaX.get(i), bombaY.get(i), UNIT, UNIT);
}
          //yılanın bası
          g.setColor(Color.green);
          for(int i=0;i<bodylenght;i++)
              
          g.fillRect(bodyx[i], bodyy[i], UNIT, UNIT);
    }
    
    public void elmaAdd(){
        Random random=new Random(); 
        elmax=(int)random.nextInt((SİZE_WİDTH/UNIT)) *25;
        elmay=(int)random.nextInt((SİZE_LENGHT/UNIT))*25;
        for(int i=0;i<bodylenght;i++){
            if(bodyx[i]==elmax&& bodyy[i]==elmay){
                elmaAdd();
            }
       
        }
        

    }
   public void bombaAdd() {
    Random random = new Random();
    int BombX = random.nextInt(SİZE_WİDTH / UNIT) * UNIT;
    int BombY = random.nextInt(SİZE_LENGHT / UNIT) * UNIT;
    bombaX.add(BombX);
    bombaY.add(BombY);
}
    public void checkFoodandBomba() {
    if (elmay==bodyy[0] && elmax==bodyx[0]) {
        bodylenght++;
        yenilenElmaSayısı++;
        elmaAdd();
        
        // Her 5 elma yendiğinde yeni bir bomba ekle
        if (yenilenElmaSayısı % 5 == 0) {
            bombaAdd();
            bombasayisi++;
        }
    }
    for (int i = 0; i < bombaX.size(); i++) {
        if (bombaY.get(i) == bodyy[0] && bombaX.get(i) == bodyx[0]) {
            devam = false;
          
        }
    }
}      
            
            
   public void checkTheShape(){
       // bodycheck
       //yılan kendi bedenini üstüne geçerse false döndür
      for(int i=bodylenght;i>0;i--){
          if(bodyx[0]==bodyx[i]&&bodyy[0]==bodyy[i]){
               devam=false;
               
           }
           
       }
                // 600/600 cikarsa oyunu karsi panelden cik
           if(bodyy[0]<0){
             bodyy[0]=600;}
            if(bodyy[0]>SİZE_LENGHT-25){
                bodyy[0]=-25;
            }
           if(bodyx[0]<0){
             bodyx[0]=600;}
            if(bodyx[0]>SİZE_WİDTH-25){
                bodyx[0]=-25;
            }
               
       if(!devam){
           timer.stop();
       }
       
       
       
   }
  
    public void bodymove(){
        for(int i=bodylenght;i>0;i--){//kuyruk
        bodyx[i]=bodyx[i-1];
        bodyy[i]=bodyy[i-1];
    }
     
      bodyx[0]=bodyx[0]+x*UNIT;
      bodyy[0]=bodyy[0]+y*UNIT; 
        
    }
    public void keyPressed(KeyEvent e){
        //ilk oynayış sadece sag ve asagı
     
        
        switch (e.getKeyCode()){
            
            case (KeyEvent.VK_UP):{
                if(y!=1){
                x=0;
                y=-1;
break;
                }
            break;
        }     case (KeyEvent.VK_LEFT):{
            if(x!=1){
                x=-1;
                y=0;
break;}
            break;
        }     case (KeyEvent.VK_DOWN):{
            if(y!=-1){
                x=0;
                y=1;
break;}
            break;
        } case (KeyEvent.VK_RIGHT):{
            if(x!=-1){
                x=1;
                y=0;
break;}
          
        }
    
    
    }
    }
    
    public void setDirection(int xDir, int yDir) {
    if (ilk) {
        x = xDir;
        y = yDir;
        ilk = false;
    } else if ((x == 0 && xDir != 0) || (y == 0 && yDir != 0)) {
        x = xDir;
        y = yDir;
    }
}public void GameOver(){
    JOptionPane pane=new JOptionPane();
    pane.showConfirmDialog(null, "Game Over\nSkorun: " +yenilenElmaSayısı,"Game Over" , 0);
    
    
}
 public int getYenilenElmaSayısı(){
      
       return yenilenElmaSayısı;
 }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(bodyx[6]!=0||bodyy[6]!=0){//yılan tek bloktan basladığı ıçin
       checkTheShape();}
       if(devam){
        
          
        bodymove();
        
        checkFoodandBomba();
        
       }
       else{
           
           GameOver();
           return;
       }
          repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * @return the bombasayisi
     */
    public int getBombasayisi() {
        return bombasayisi;
    }

  
       
   
   }
