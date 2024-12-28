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
import java.util.concurrent.Delayed;

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
     int []bodyx=new int[(SİZE_LENGHT/UNIT)*(SİZE_WİDTH/UNIT)];//yılan x kordinatları
     int []bodyy=new int[(SİZE_LENGHT/UNIT)*(SİZE_WİDTH/UNIT)];//yılan y kordinatları
     int x=0; //Haraket
     int y=0; // haraket
     private int bombasayisi=1;
     int yenilenElmaSayısı=0;
   
    boolean devam=true;
    Timer timer;
    JoystickControl joystickControl;
    boolean tek1=true;
    
    public PANEL(boolean tek1){
        this.tek1=tek1;//josytick mi yada kontrol tuşlarıylamı köntrol etme
        this.setFocusable(true);
         bombaAdd();//1.ci bomba ekleme
        elmaAdd(); //1.ci elma ekleme
        System.out.println(elmax+" "+elmay);
        System.out.println(tek1);
     
        start();
        
        timer=new Timer(100, this);//zamanlayıcı
        timer.start();
       
   if(tek1)
        joystickControl = new JoystickControl(this, "COM7");//joystcik kontrolu tek1 true ise çalıştırma
        
    }
   
    
 
    public void start(){//Frame mım içindeki panel
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
       super.paintComponent(g);//@overide methodum yılanı çizmek için 
      
       draw(g);  
   }
    public void draw(Graphics g) {
        
        for(int i=0;i<SİZE_LENGHT/UNIT;i++)
        {        
           
            g.drawLine(i*UNIT, 0, i*UNIT, SİZE_LENGHT);//yukarda aşağı çizer
            g.drawLine(0,UNIT* i, SİZE_WİDTH, i*UNIT);//soldan saga dogru çizer
            //24x24 kare şeklinde alan çizer
        }  
        //elma yı ekler
          
        g.setColor(Color.red);
         g.fillOval(elmax, elmay, UNIT, UNIT);
         //bomba yı ekler
        for (int i = 0; i < bombaX.size(); i++) {
     g.setColor(Color.black);
    g.fillOval(bombaX.get(i), bombaY.get(i), UNIT, UNIT);
}
          //yılanın bası ve kuyrugu çizme
          g.setColor(Color.black);
          for(int i=0;i<bodylenght;i++){
             
          g.fillRect(bodyx[i], bodyy[i], UNIT, UNIT);
            g.setColor(Color.green);
    }}
    
    public void elmaAdd(){
        Random random=new Random(); //elmayı rastgeke kordinatları atama
        elmax=(int)random.nextInt((SİZE_WİDTH/UNIT)) *25;//x korinatları 
        elmay=(int)random.nextInt((SİZE_LENGHT/UNIT))*25;//y kordinatı
        for(int i=0;i<bodylenght;i++){//yılanın içinde olmasın
            if(bodyx[i]==elmax&& bodyy[i]==elmay ){
                elmaAdd();}
               
            else if(575<elmax||575<elmay){// sınırları aşarsa elmayı 
                //600.karde sağa doğru çizdirdiği için görömeyiz çünkü benim sınırım 600
                elmaAdd();}
            }   for(int i=0;i<bombasayisi;i++){
          if(bombaY.get(i)==elmay&& elmax==bombaX.get(i) ){// eğer elma bomba ile aynı konumda çizilmesin
            elmaAdd();
                }
            }}
            
                
                
            
       
        
        

    
   public void bombaAdd() {
    Random random = new Random();
    boolean cakisma=false;
       do {           
           
      //bomba x ve y kordinat random atama
    int BombX = random.nextInt(SİZE_WİDTH / UNIT) * UNIT;
    int BombY = random.nextInt(SİZE_LENGHT / UNIT) * UNIT;
      for(int i=0;i<bodylenght;i++){
          // Yılan bodysini cakımsa kontrolu
            if(bodyx[i]==BombX&& bodyy[i]==BombY){
                 cakisma=true;
            }else if(575<BombX || 575<BombY){// 600x600 alandan çıkmasın diye 600 olursa saga dogru çielecke o yüzden bir unit öncesine cizdimek için
          cakisma=true;
      }
            
       
        }
        // Mevcut bombalar ile çakışma kontrolü
            for (int i = 0; i < bombaX.size(); i++) {
                if (bombaX.get(i) == BombX && bombaY.get(i) == BombY) {
                    cakisma = true;
                   
                }
            }
 if(!cakisma){
 //cakımsa yoksa bomba ları ekle
   bombaX.add(BombX);
   bombaY.add(BombY);
 } 
}while (cakisma);
       
}
       
    public void checkFoodandBomba() {
    if (elmay==bodyy[0] && elmax==bodyx[0]) {//bomba yendimi
        bodylenght++;
        yenilenElmaSayısı++;
        elmaAdd();
        
        // Her 5 elma yendiğinde yeni bir bomba ekle
        if (yenilenElmaSayısı % 5 == 0) {
            bombaAdd();
            bombasayisi++;
        }
    }
    for (int i = 0; i < bombaX.size(); i++) {//yılanın kafası bombayı çarpıtığında kaybetsin
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
             bodyy[0]=600-UNIT;}
           else if (bodyy[0]>SİZE_LENGHT-25){
                bodyy[0]=-25;
            }
           if(bodyx[0]<0){
             bodyx[0]=600-UNIT;}
           else if(bodyx[0]>SİZE_WİDTH-25){
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
     
      bodyx[0]=bodyx[0]+x*UNIT;//unit sayısı kadar hareket etsin x yönünde
      bodyy[0]=bodyy[0]+y*UNIT; //unit sayısı kadar hareket etsin y yönünde
        
    }
    public void keyPressed(KeyEvent e){
 if(tek1==false){//yön tuşları
        switch (e.getKeyCode()){
            
            case (KeyEvent.VK_UP):{
                if(y!=1){//yukarı giderken aşağı gidemez
                x=0;
                y=-1;
break;
                }
            break;
        }     case (KeyEvent.VK_LEFT):{
            if(x!=1){//sola giderken sağa gidemezsin yılanı içinden geçersin
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
    }
    public void setDirection(int xDir, int yDir) {
   //josytick kontrol için yön ataması
    if ((x == 0 && xDir != 0) || (y == 0 && yDir != 0)) {
        x = xDir;
        y = yDir;
    }
}

    /**
     *
     */
    public void GameOver(){

    int result = JOptionPane.showConfirmDialog(null, "Game Over\nSkorun: " + yenilenElmaSayısı, "Game Over", JOptionPane.YES_NO_OPTION);
    //oyun bittiginde paneli kapat ve skoru göster
    if (result == JOptionPane.YES_OPTION) {
        System.exit(0); // Programı kapat
    }

}

    
    @Override
    public void actionPerformed(ActionEvent e) {
       if(bodyx[6]!=0||bodyy[6]!=0){//yılan tek bloktan basladığı ıçin
       checkTheShape();}// ilk başta ne kadar uzun olcağını ayarlama
       if(devam){
        
           
        bodymove();
        
        checkFoodandBomba();
        
       }
       else{
           
           GameOver();
          return;
       }
          repaint();//ekranı timer tetikleiğnde sürekli çizdirme
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
 public int getYenilenElmaSayısı(){
      
       return yenilenElmaSayısı;
 }
    /**
     * @return the bombasayisi
     */
    public int getBombasayisi() {
        return bombasayisi;
    }
    public void setDevam(boolean devam){
        this.devam=devam;
    }
    public int getBodyx(){
        
        return bodyx[0];
    }
       
   
   }