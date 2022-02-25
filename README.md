# Requirements to execute the application on Windows:

* JDK 1.8 or higher

* HSQL DB

* Maven

* JAVA_HOME system environment variable set

* MAVEN_HOME system environment variable set

* Add %MAVEN_HOME%\bin and %JAVA_HOME%\bin To Path system environment variable

-----------------------------
# Running the application:

##First, launch the HSQL DB:

Make sure the latest HSQL DB is extracted in a folder

Open a command prompt and type the following

cd <HSQL-HOME-FOLDER>\hsqldb\data

java -cp ../lib/hsqldb.jar org.hsqldb.server.Server --database.0 test


##Then launch the application:

Open a command prompt and type the following

cd <PROJECT-HOME-FOLDER>

mvn spring-boot:run -Dspring-boot.run.arguments=<Absolute path to logfile.txt>