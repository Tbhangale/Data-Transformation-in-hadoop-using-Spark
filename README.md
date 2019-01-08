# Execute Scala program in Spark 

### Example Scenario:  
From retail_db dataset, join hive external tables (Products, Categories, Departments) using Spark with Scala as programming language. \
Develop a scala program in IntelliJ, create a jar file and run the jar file in spark using spaark-submit. \
Uploaded scala program for joining above mentioned 3 tables and build.sbt for this project.

### Execution steps:
1. Write a program in scala using IntelliJ
Add relevant libraryDependencies in build.sbt file
To provide arguments to your program => select scala file => Run - Edit configurations - Defualts - Application - Program Arguments(space seperated)
Run your program to check if it correct

2. Make jar file in IntelliJ
File -> Project Structure -> Projecct Settings -> Artifacts -> Add Jar From modules with dependencies -> Select project in Module -> Ok

    Build -> Build Artifact -> proj_name.jar -> build

    Jar file will be in the out folder inside Project directory in your local system 

3. Transfer jar file to cluster (using scp)

4. run the jar file using spark-submit

    for example: \
    specify class name wordcount, \
		specify spark port, master, num of executors, cores, memory \
		specify jar file name \
		specify arguments(here input filename and output filename seperated by space)

    spark-submit \
    --class tables_join \
    --conf spark.ui.port=22222  \
    --master yarn \
    tables_join.jar 

5. In case of an error, java.lang.SecurityException: Invalid signature file digest for Manifest main attributes
execute below cmd and then run jar file 

    zip -d wordcount.jar META-INF/*.RSA META-INF/*.DSA META-INF/*.SF

6. You can also write application.properties file in resources folder in main folder. Write config propeties here.
