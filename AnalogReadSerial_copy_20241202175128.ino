const int y_ekseni=A0;
const int x_ekseni=A1;
int x_durum;
int y_durum;
void setup() {
  Serial.begin(9600);
 
  
}

// the loop routine runs over and over again forever:
void loop() {
  x_durum=analogRead(x_ekseni);
  y_durum=analogRead(y_ekseni);
  
  if(x_durum<100 ){
    Serial.println("r");//right 
  }else if(x_durum>920 ){
    Serial.println("l");//left
  }else if(y_durum<100 ){
     Serial.println("u");//up
  }else if(y_durum >920 ){
     Serial.println("d");//down
  }
  
}
