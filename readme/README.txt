Eclipse : 
serveur tomcat > Open launch confguration > Arguments
ajouter :
-Dlog4j.configuration="file:///C:/Users/eric_/Documents/workspace/ouranos.prop/log4j.xml"
-DOURANOS_REST_API_CONF="C:/Users/eric_/Documents/workspace/ouranos.prop"

Tomcat : 
modifier <tomcat>/bin/setenv.sh

Par defaut les fichiers *.sp3 sont chargé dans C:\
Il est possible de les charger à partir d'un autre répertoire,
en lancant le programme avec un fichier en parametre : ouranos-api-rest.properties
content la ligne suivante :
	- repertoire.sp3=C:\\Users\\...\\Documents\\workspace\\ouranos.prop
	
	
http://127.0.1:8080/ouranos-livraison/


IHM Angular
L'ihm angular cherche l'api à l'addresse suivante : 
http://127.0.1:8080/ouranos-livraison/

Il faut donc lancer ouranos-livraison sur le serveur Tomcat à partir d'eclipse 


/!\ ATTENTION
pour que l'IHM utilise la bonne api il faut faire une modification dans le fichier : package.json
ouranos-ihm\src\main\ngapp\package.json

à la ligne :   
"build": "ng build --env=dev",
mettre
"build": "ng build --env=prod",