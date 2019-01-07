name := "tables_join"

version := "0.1"

scalaVersion := "2.10.7"

libraryDependencies += "org.apache.spark" % "spark-core" % "2.3.0"
libraryDependencies += "com.typesafe" % "config" % "1.3.2"
libraryDependencies += "org.apache.spark" %% "spark-hivecontext-compatibility" % "2.0.0-preview"
libraryDependencies += "com.databricks" %% "spark-csv" % "1.4.0"