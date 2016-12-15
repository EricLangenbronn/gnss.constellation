clc # reset de la console

# chargement des librairies
addpath("./librairie/geodetic299/geodetic")

# Test du fichier fr.gnss.constellation.librairy.coordinate.TestCoordinate.java
disp("###### Test des fonctions de la librairies ######")
disp("fr.gnss.constellation.librairy.coordinate.TestCoordinate.java")
disp("1. TestCoordinate");

# Constante
[a,b,e2,finv] = refell('WGS84');
formatSortie = '%0.10f';

####### Début des tests #######

# ell2xyz - Ellipsoidal (lat,long) to Cartesian (x,y,z) coodinates
# testGeodeticToCartesianStrasbourg
lat = deg2rad(48.5839200);
lon = deg2rad(7.7455300);
h = 120.0;

[x,y,z]=ell2xyz(lat,lon,h,a,e2);

res = ['1.1 testGeodeticToCartesianStrasbourg : (x=', sprintf(formatSortie, x) , ', y=', sprintf(formatSortie,y), ', z=', sprintf(formatSortie,z), ')'];
disp(res);


# xyz2ell - Cartesian (x,y,z) to ellipsoidal (lat,long,ht) coordinates 


# testGeodeticToCartesianParis
# ct2lg - Conventional terrestrial (ECEF) to local geodetic (NEU) 
lat = deg2rad(48.8534100);
lon = deg2rad(2.3488000);
h = 120.0;

[x,y,z]=ell2xyz(lat,lon,h,a,e2);

res = ['1.2 testGeodeticToCartesianParis : (x=', sprintf(formatSortie, x) , ', y=', sprintf(formatSortie,y), ', z=', sprintf(formatSortie,z), ')'];
disp(res);


# testTransformECEFtoENUStrasbourg
# ct2lg - Conventional terrestrial (ECEF) to local geodetic (NEU) 
dX = 4201270.83938198;
dY = 172324.672938237;
dZ = 4779938.29153465;
lat = deg2rad(48.5839200);
lon = deg2rad(7.7455300);

[dx,dy,dz]=ct2lg(dX,dY,dZ,lat,lon);

res = ['1.3 testTransformECEFtoENUStrasbourg : (dx=', sprintf(formatSortie, dx) , ', dy=', sprintf(formatSortie,dy), ', dz=', sprintf(formatSortie,dz), ')'];
disp(res);