> [Home](../README.md)


# Technologie : 
- backend
    - Spring Boot
    - Maven
    - Docker
    - MongoDB (NonSQL)
- frontend
    - React

# Lancer l'application en développement

- step 1: Lancer la base donnée
```console
foo@bar:docker$ docker-compose up
```
L'envionnement docker comprend une base de données MongoDB et un serveur SMTP de développement.

- step 2 : Lancer l'application
```console
foo@bar:backend$ mvn spring-boot:run
```

- step 3 : Lancer l'interface
```console
foo@bar:frontend$ npm install
foo@bar:frontend$ npm start
```
- step 4 : Importer des données enregistrés (format JSON)
```console
foo@bar:docker$ docker cp fake_data_uplinkModel.json dichlowan-db:/tmp/fake_data_uplinkModel.json
foo@bar:docker$ docker exec dichlowan-db mongoimport -d dichlowan -c uplinkModel --jsonArray --file /tmp/fake_data_uplinkModel.json
```

App : http://localhost:3000

API Swagger : http://localhost:8080/api

Mail : http://localhost:1080

# Choix du réseau de capteur

Il est possible de changer de réseau de capteur sans devoir rebuild l'application.
Pour cela il faut modifier le fichier : DichloWan2021/app/backend/src/main/resources/application.properties
```yml
..
network.choice=ttn
network.ttn.api.url=https://<region>.cloud.thethings.network/api/v3/
network.ttn.app.id=<your-app-id>
network.ttn.app.auth.bearerToken=<your-app-bearer-token>
..
```
```yml
..
network.choice=campusIoT
network.campusIoT.api.url=
network.campusIoT.app.auth.bearerToken=
network.campusIoT.app.id=
..
```
*Le réseau campusIoT n'est pas encore implémenté*