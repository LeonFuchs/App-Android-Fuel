# Projet Station Android

Projet réalisé par Manon Devaux et Léon Fuchs dans le cadre du cursus ISMIN.

## Description

Application Android affichant les stations essences en France et les prix des carburants à celles-ci.
Les données sont récupérées d'une [API](https://github.com/manondevaux/App-Web-Fuel) NestJS.

## Utilisation

1. Clonez le dépot
2. Ajoutez votre clé d'API Google Maps dans `/app/src/main/AndroidManifest.xml`, dans la balise, en remplaçant `{MAPS_API_KEY}`
```
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="{MAPS_API_KEY}"/>
```
3. Compilez et éxécutez
