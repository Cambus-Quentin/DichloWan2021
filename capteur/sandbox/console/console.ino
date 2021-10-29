#include "Arduino.h"

void setup() {
  Serial.begin(9600);
  Serial.println("setup");Serial.flush();
  
  pinMode(LED_BUILTIN, OUTPUT);
}

// the loop function runs over and over again forever
void loop() {
  digitalWrite(LED_BUILTIN, HIGH);   // turn the LED on (HIGH is the voltage level)
  Serial.println("LED on");Serial.flush();
  delay(2000);                       // wait for a second
  
  digitalWrite(LED_BUILTIN, LOW);    // turn the LED off by making the voltage LOW
  delay(2000);                       // wait for a second
  Serial.println("LED off");Serial.flush();
}
