clear


echo "Compiling java src"
cd src
javac *.java

echo "Starting the RMIRegistry"
cd ..
cd bin
rmiregistry
cd ..

