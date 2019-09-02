set mypath=%cd%
cd %mypath%/src
del *.class
cd  .. 
javac ./src/Main.java
java -cp "./src/mysql.jar;" src.Main