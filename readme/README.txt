# Eclipse :
serveur tomcat > Open launch confguration > Arguments
ajouter :
-Dlog4j.configurationFile="file:///C:/Users/eric_/Documents/workspace/ouranos.prop/log4j2.xml"
-DOURANOS_DIR_CONF="D:/Developpement/Workspace/eclipse/config/ouranos"
-DOURANOS_FILE_CONF="ouranos-rest-api.properties"

# Eclipse debug mode
-Dspring-boot.run.jvmArguments="-Xdebug
-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"


Par defaut les fichiers *.sp3 sont chargé dans C:\
Il est possible de les charger à partir d'un autre répertoire,
en lancant le programme avec un fichier en parametre : ouranos-api-rest.properties
content la ligne suivante :
	- repertoire.sp3=C:\\Users\\...\\Documents\\workspace\\ouranos.prop
	
	
http://127.0.1:8080/ouranos-livraison/


# IHM Angular
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


# URL de  test pour l'api WAR
/ouranos-war/src/test/resources/api/url/*

# URL de test de l'api
http://127.0.0.1:8080/ouranos-war/api-rest/visibleSat/v01/byPeriod?lat=38.889139&long=-77.049&alt=130.049&tsStart=1387666800&tsEnd=1387753199&elevmask=15.0
http://127.0.0.1:8080/ouranos-war/api-rest/visibleSat/v01/bySatellite?lat=38.889139&long=-77.049&alt=130.049&tsStart=1387666800&tsEnd=1387753199&elevmask=15.0



# URL de test de l'api springboot
http://127.0.0.1:8080/api/visibleSat/v01/byPeriod?lat=38.889139&longi=-77.049&alt=130.049&tsStart=1387666800&tsEnd=1387753199&elevmask=15.0
http://127.0.0.1:8080/api/visibleSat/v01/bySatellite?lat=38.889139&longi=-77.049&alt=130.049&tsStart=1387666800&tsEnd=1387753199&elevmask=15.0





# Documentation
#Fichier sp3
ftp://igs.ensg.ign.fr/pub/igs/products/1282/
#Lib java pour gps
https://www.orekit.org/static/apidocs/index.html?org/orekit/files/sp3/SP3Parser.html
#Fichier sp3
https://kb.igs.org/hc/en-us/articles/115003935351-Access-to-Products




# SpringBoot :
#Bash command :
java -Dserver.port=8080 -DOURANOS_DIR_CONF=E:\Developpement\Workspace\eclipse\config\ouranos -DOURANOS_FILE_CONF=ouranos.properties -jar .\ouranos-rest-api-0.0.0-SNAPSHOT.jar

#Powershell command :
Start-Process java -NoNewWindow -ArgumentList @('-jar ouranos-rest-api-0.0.0-SNAPSHOT.jar', '--server.port=8080', '--OURANOS_DIR_CONF=E:\Developpement\Workspace\eclipse\config\ouranos', '--OURANOS_FILE_CONF=ouranos.properties', '--logging.config=E:\Developpement\Workspace\eclipse\config\ouranos\logback.xml')
Start-Process java -ArgumentList @('-jar ouranos-rest-api-0.0.0-SNAPSHOT.jar', '--server.port=8080', '--OURANOS_DIR_CONF=E:\Developpement\Workspace\eclipse\config\ouranos', '--OURANOS_FILE_CONF=ouranos.properties', '--logging.config=E:\Developpement\Workspace\eclipse\config\ouranos\logback.xml')





# Quarkus :
cd gnss.constellation\ouranos-rest-api\target\quarkus-app
create : config/application.properties


Start-Process java -ArgumentList @('-jar quarkus-run.jar')
Start-Process java -ArgumentList @('-jar ouranos-rest-api-0.0.0-SNAPSHOT-runner.jar')


# Docker :
cd gnss.constellation
docker build --progress=plain --no-cache -f ouranos-docker/Dockerfile -t ouranos:latest . // debug mode
docker build -f ouranos-docker/Dockerfile -t ouranos:latest .
docker run --name ouranos-server -p 8080:8080 -e "JAVA_OPTS=-Ddebug -Xmx128m" ouranos:latest --server.port=8080
docker rmi --force $(docker images -q) // drop all images

# Docker compose :
cd gnss.constellation\ouranos-docker
docker-compose down && docker-compose up --build