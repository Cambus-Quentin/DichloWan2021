#include "Arduino.h"

#define serial Serial5

void setup() {
  serial.begin(9600);
  serial.println("setup");
  
  pinMode(LED_BUILTIN, OUTPUT);
}

// the loop function runs over and over again forever
void loop() {
  digitalWrite(LED_BUILTIN, HIGH);   // turn the LED on (HIGH is the voltage level)
  serial.println("LED on");
  delay(2000);                       // wait for a second
  
  digitalWrite(LED_BUILTIN, LOW);    // turn the LED off by making the voltage LOW
  delay(2000);                       // wait for a second
  serial.println("LED off");
}
