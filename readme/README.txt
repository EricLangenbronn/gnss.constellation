Eclipse : 
serveur tomcat > Open launch confguration > Arguments
ajouter :
-Dlog4j.configurationFile="file:///C:/Users/eric_/Documents/workspace/ouranos.prop/log4j2.xml"
-DOURANOS_REST_API_DIR_CONF="D:/Developpement/Workspace/eclipse/config/ouranos"
-DOURANOS_REST_API_FILE_CONF="ouranos-rest-api.properties"

Eclipse debug mode
-Dspring-boot.run.jvmArguments="-Xdebug
-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"


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


URL de  test pour l'api WAR
/ouranos-war/src/test/resources/api/url/*

URL de test de l'api
http://127.0.0.1:8080/ouranos-war/api-rest/visibleSat/v01/byPeriod?lat=38.889139&long=-77.049&alt=130.049&tsStart=1387666800&tsEnd=1387753199&elevmask=15.0
http://127.0.0.1:8080/ouranos-war/api-rest/visibleSat/v01/bySatellite?lat=38.889139&long=-77.049&alt=130.049&tsStart=1387666800&tsEnd=1387753199&elevmask=15.0



URL de test de l'api springboot
http://127.0.0.1:8080/api/visibleSat/v01/byPeriod?lat=38.889139&longi=-77.049&alt=130.049&tsStart=1387666800&tsEnd=1387753199&elevmask=15.0
http://127.0.0.1:8080/api/visibleSat/v01/bySatellite?lat=38.889139&longi=-77.049&alt=130.049&tsStart=1387666800&tsEnd=1387753199&elevmask=15.0





Documentation
fichier sp3
ftp://igs.ensg.ign.fr/pub/igs/products/1282/
lib java pour gps
https://www.orekit.org/static/apidocs/index.html?org/orekit/files/sp3/SP3Parser.html
Fichier sp3 
https://kb.igs.org/hc/en-us/articles/115003935351-Access-to-Products

