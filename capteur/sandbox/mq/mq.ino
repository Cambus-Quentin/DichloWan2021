#include "Arduino.h"

int pin=A0;

void setup() {
  Serial.begin(9600);
  
  pinMode(LED_BUILTIN, OUTPUT);
  //pinMode (pin,INPUT);
}

void loop() {
  int sensorValue = analogRead(pin);
  float sensorVolt=(float)sensorValue/1024*5.0;
  Serial.print("volt = ");
  Serial.println(sensorVolt);

//  if (sensorVolt > 0){
//    digitalWrite(LED_BUILTIN, HIGH);
//    delay(1000);
//    
//    digitalWrite(LED_BUILTIN, LOW);
//    delay(1000); 
//  }

  if (sensorVolt > 0){
    for(int i = 0; i < sensorVolt; i++){
      digitalWrite(LED_BUILTIN, HIGH);
      delay(500);
      
      digitalWrite(LED_BUILTIN, LOW);
      delay(500); 
    }
  }

  delay(5000);
} 
