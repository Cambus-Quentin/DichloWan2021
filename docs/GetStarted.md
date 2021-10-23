> [Home](../README.md) > [Documentation](./README.md)

# Get Started

## Installation

---

### Arduino
Afin de mettre programmer les capteurs, il faut utiliser **Arduino IDE** qui permet de développer et flasher (installer sur la carte) les programmes.

</br>

:warning: Il faut configurer l'IDE afin qu'il prenne en compte les cartes STM32 :

- Ajouter l'URL de gestionnaire de cartes supplémentaire

Dans Fichier > Préférences : URL de gestionnaire de cartes supplémentaire
> https://github.com/stm32duino/BoardManagerFiles/raw/main/package_stmicroelectronics_index.json

- Installer le gestionnaire de cartes 
 
Dans Outils > Type de carte > Gestionnaire de carte

Ajouter le filtre *STM32* est installer ***STM32 MCU based boards*** version ***2.1.0***

- Sélectionner la carte 

Dans Outils, sélectionnez :
>Type de carte : Nucleo-64  
Board part number : Nucleo L073RZ  
Upload method : Mass Storage  
Port : /dev/ttyACM0


### Réseau de capteur

### App

## Exemple

---
Exemple de programme (sandbox) à flasher sur la carte STM32

```ino
// Récupère les informations du capteur
// La LED clignote en fonction du voltage en sortie du capteur

#include "Arduino.h"

int pin=A0;

void setup() {
  Serial.begin(9600);
  
  pinMode(LED_BUILTIN, OUTPUT);
}

void loop() {
  int sensorValue = analogRead(pin);
  float sensorVolt=(float)sensorValue/1024*5.0;
  Serial.print("volt = ");
  Serial.println(sensorVolt);

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
```