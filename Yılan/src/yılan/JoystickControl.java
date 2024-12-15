package yılan;
import com.fazecast.jSerialComm.SerialPort;

public class JoystickControl {
    private SerialPort serialPort;
 

    public JoystickControl(PANEL panel, String portName) {
        
        serialPort = SerialPort.getCommPort(portName);
        serialPort.setBaudRate(9600);
        serialPort.openPort();
        if(  serialPort.openPort()){
            
            System.out.println("Acildi");
        }else{
            System.out.println("Acilmadi");
        }

       Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {
                while (serialPort.isOpen()) {
                byte[] buffer = new byte[1];
                if (serialPort.readBytes(buffer, buffer.length) > 0) {
                    char direction = (char) buffer[0];
                    switch (direction) {
                        case 'l': panel.setDirection(-1, 0); break;
                        case 'r': panel.setDirection(1, 0); break;
                        case 'u': panel.setDirection(0, -1); break;
                        case 'd': panel.setDirection(0, 1); break;} }
 } }
                
    });
       thread.start();
}
}
