> [Home](../README.md) > [Documentation](./README.md)

# IoT Network

# TTN (The Things Network)


## Ajouter un capteur (End Device) - Manuel

Pour cela rendez-vous dans : **votre application > End Devices**.

Ajouter un nouveau device :
- Frequency Plan : Europe 863-870 MHz (SF9 for RX2 - recommended)
- LoRaWAN version : MAC V1.0
- DevEUI : *write on the board*
- AppEUI : *write on the board*
- AppKey : *generate*
- end device ID : *choose a name*

</br>

## Stockage des messages IoT (provenant des end devices)
Il est nécessaire d'activer le stockage des messages car ils ne sont pas concervés longtemps par TTN.

Pour cela rendez-vous dans **votre application > Intégration (Menu) > Storage Integration**.

En bas de la page cliquez sur "Activate Storage Integration".

</br>

## API identifiants
Pour pouvoir utiliser l'API de TTN afin de récupérer les messages il faut posséder un **Bearer Token**.

Pour cela rendre-vous dans **votre application > API Key (Menu)**.

Vous pouvez maintenant générer un token d'accés.

:warning: Pensez à copier le Bearer Token car vous ne pourrait plus le récupérer après. Ce token est nécessaire pour le fonctionnement de l'application.
