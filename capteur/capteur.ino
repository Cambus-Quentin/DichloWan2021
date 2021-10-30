#include "LoRaWANNode.h"

#define DEV_EUI "E24F43FFFE44CE14"
#define APP_EUI "EC96EF9AA9DF9ED8"
#define APP_KEY "E24F43FFFE44CE14EC96EF9AA9DF9ED8"

//#define FPORT UNCONFIRMED
#define FPORT 1

#define FRAME_DELAY 10000  // in ms

// Serial port use to communicate with the USI shield.
// By default, use D0 (Rx) and D1(Tx).
// For Nucleo64, see "Known limitations" chapter in the README.md
HardwareSerial SerialLora(D0, D1);

#define PIN_CAPTOR A0

void setup(){
  pinMode(LED_BUILTIN, OUTPUT); //setup led
  
  joinNetwork();
}

void loop(){
  char frame[4];
  floatToByte(getCaptorValue(), frame);
  send(frame);
  delay(FRAME_DELAY);
}

void blink(int nb){
  for (int i = 0; i < nb; i++){
    digitalWrite(LED_BUILTIN, HIGH);
    delay(500);
    digitalWrite(LED_BUILTIN, LOW);
    delay(500);
  }
}

void joinNetwork(void){
  digitalWrite(LED_BUILTIN, HIGH);
  while(!loraNode.begin(&SerialLora, LORA_BAND_EU_868)) {
    delay(1000);
  }
  
  while(!loraNode.joinOTAA(APP_KEY, APP_EUI)) {
    delay(1000);
  }
  
  blink(5);
  delay(2000);
}

void send(char frame[]){  
  // Send unconfirmed data to a gateway (port 1 by default)
  int status = loraNode.sendFrame(frame, sizeof(frame), FPORT);
  if(status == LORA_SEND_ERROR) {
    blink(3);
  } else if(status == LORA_SEND_DELAYED) {
    blink(2);
  } else {
    blink(1);
  }
}

float getCaptorValue(void){
  int sensorValue = analogRead(PIN_CAPTOR);
  float sensorVolt=(float)sensorValue/1024*5.0;

  return sensorVolt;
}

void floatToByte(float value, char bytes[4]){
  //bytes[0] = (value >> 24) & 0xFF;
  //bytes[1] = (value >> 16) & 0xFF;
  //bytes[2] = (value >> 8) & 0xFF;
  //bytes[3] = value & 0xFF;
  memcpy(bytes, (unsigned char*) (&value), 4);
}
