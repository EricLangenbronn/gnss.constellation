clc # reset de la console

# chargement des librairies
addpath("./librairie/geodetic299/geodetic")
formatSortie = '%0.10f';

# Test du fichier fr.gnss.constellation.librairy.coordinate.TestCoordinate.java
disp("###### Test des fonctions de la librairies ######")
disp("fr.gnss.constellation.librairy.coordinate.TestCoordinate.java")
disp("1. TestCoordinate");

# Constante
[a,b,e2,finv] = refell('WGS84');
disp(['a=', sprintf(formatSortie, a)]);
disp(['b=', sprintf(formatSortie, b)]);
disp(['e2=', sprintf(formatSortie, e2)]);
disp(['finv=', sprintf(formatSortie, finv)]);

####### Début des tests #######

# testGeodeticToCartesianStrasbourg
# ell2xyz - Ellipsoidal (lat,long) to Cartesian (x,y,z) coodinates
lat = deg2rad(48.5839200);
lon = deg2rad(7.7455300);
h = 120.0;

[x,y,z]=ell2xyz(lat,lon,h,a,e2);

disp('1.1 testGeodeticToCartesianStrasbourg :');
disp(['    (x=', sprintf(formatSortie, x) , ', y=', sprintf(formatSortie,y), ', z=', sprintf(formatSortie,z), ')']);


# testGeodeticToCartesianParis
# ct2lg - Conventional terrestrial (ECEF) to local geodetic (NEU)
lat = deg2rad(48.8534100);
lon = deg2rad(2.3488000);
h = 120.0;

[x,y,z]=ell2xyz(lat,lon,h,a,e2);

disp('1.2 testGeodeticToCartesianParis :');
disp(['    (x=', sprintf(formatSortie, x) , ', y=', sprintf(formatSortie,y), ', z=', sprintf(formatSortie,z), ')']);


# testTransformECEFtoENUStrasbourg
# ct2lg - Conventional terrestrial (ECEF) to local geodetic (NEU)
dX = 4201270.83938198;
dY = 172324.672938237;
dZ = 4779938.29153465;
lat = deg2rad(48.5839200);
lon = deg2rad(7.7455300);

[dx,dy,dz]=ct2lg(dX,dY,dZ,lat,lon);

disp('1.3 testTransformECEFtoENUStrasbourg :');
disp(['    (dx=', sprintf(formatSortie, dx) , ', dy=', sprintf(formatSortie,dy), ', dz=', sprintf(formatSortie,dz), ')']);


# testCalcElevationAzimuthSat
lat = deg2rad(48.5839200);
lon = deg2rad(7.7455300);
h = 120.0;

[x,y,z] = ell2xyz(lat,lon,h,a,e2);
disp('1.4 testCalcElevationAzimuthSat :');
disp(['    Cartesian station position : (x=', sprintf(formatSortie, x) , ', y=', sprintf(formatSortie,y), ', z=', sprintf(formatSortie,z), ')']);

satelitePositionECEF = [5441177.312, -25836148.765, -2502546.747];
stationPositionECEF = [x, y, z];

VStationSatelite = satelitePositionECEF - stationPositionECEF;
disp(['    Vecteur station/satelite : (x=', sprintf(formatSortie, VStationSatelite(1)) , ', y=', sprintf(formatSortie,VStationSatelite(2)), ', z=', sprintf(formatSortie,VStationSatelite(3)), ')']);

# /!\ ct2lg retourne : north, east, up
[vStaSatNEUX, vStaSatNEUY, vStaSatNEUZ]  = ct2lg(VStationSatelite(1),VStationSatelite(2),VStationSatelite(3),lat,lon);
disp(['    Vecteur station/satelite NEU : (x=', sprintf(formatSortie, vStaSatNEUX) , ', y=', sprintf(formatSortie,vStaSatNEUY), ', z=', sprintf(formatSortie,vStaSatNEUZ), ')']);
disp(['    Elavation : ', sprintf(formatSortie, asin(-vStaSatNEUZ/norm(vStaSatNEU))) ]);
disp(['    Azimuth : ', sprintf(formatSortie, atan2(vStaSatNEUX/norm(vStaSatNEU),vStaSatNEUY/norm(vStaSatNEU))) ]);
