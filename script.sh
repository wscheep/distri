clear
rm -rf bin
rm sources_list.txt

echo "Compiling java src"
find ./src -name *.java > sources_list.txt
mkdir bin
cp dockx.csv bin
cp hertz.csv bin
cp trips bin
javac -d bin -classpath "${CLASSPATH}" @sources_list.txt

echo "Starting the RMIRegistry"
cd bin
rmiregistry &


echo "Starting the Agency Company Server"
java rental/RentalAgencyServer &

echo "Starting the Rental Company Server"
java rental/RentalCompanyServer &

echo "Starting the client"
java client/Client 

echo "kill RMIRegistry"

echo "Done."

