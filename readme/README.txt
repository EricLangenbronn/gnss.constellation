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
	
	
http://127.0.1:8080/ouranos-war/ouranos/