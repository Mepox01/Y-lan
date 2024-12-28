package yılan;
import com.fazecast.jSerialComm.SerialPort;

public class JoystickControl {
    private SerialPort serialPort;
 

    public JoystickControl(PANEL panel, String portName) {
        
        serialPort = SerialPort.getCommPort(portName);
        serialPort.setBaudRate(9600);
        serialPort.openPort();
        if(  serialPort.openPort()){//sericom iletişim sağlandımı yoksa sağlanmadomı
            
            System.out.println("Acildi");
        }else{
            System.out.println("Acilmadi");
        }

       Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {
                while (serialPort.isOpen()) {
                byte[] buffer = new byte[1];
                    
                if (serialPort.readBytes(buffer, buffer.length) > 0) {//eğer 0 dan başka birşey geliyorsa işleme sokma ve sürekli okı
                    char direction = (char) buffer[0];//byte chara dönüştümre
                    switch (direction) {//yılınaı x ve y yön vektörlerini atama
                        case 'l': panel.setDirection(-1, 0); break;
                        case 'r': panel.setDirection(1, 0); break;
                        case 'u': panel.setDirection(0, -1); break;
                        case 'd': panel.setDirection(0, 1); break;} }
  }}
                
    });
       thread.start();
}
}
