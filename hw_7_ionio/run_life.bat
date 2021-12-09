call mvn clean install -Plife -DskipTests
call ..\csv_pars\run.bat
cd ..\hw_7_ionio
call mvn clean install
call java -jar .\target\hw_7_ionio.jar