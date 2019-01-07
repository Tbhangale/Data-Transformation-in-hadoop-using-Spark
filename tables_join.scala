
//import org.apache.spark.SparkConf
//import org.apache.spark.SparkContext
//import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.SparkSession

object tables_join {
  def main(args: Array[String]): Unit = {

    //   For Spark version < 2
    //    var conf = new SparkConf().setMaster("yarn-client").setAppName("tables_join")
    //    var sc = new SparkContext(conf)
    //    var sqlContext  = new HiveContext(sc)

    var sqlContext = SparkSession.builder().master("yarn-client").appName("tables joined by jar").enableHiveSupport().getOrCreate()

    var prod_df = sqlContext.sql("select * from tbhangale.products where load_date='2013-07-25'")

    var cat_df = sqlContext.sql("select * from tbhangale.categories where load_date='2013-07-25'")

    var dept_df = sqlContext.sql("select * from tbhangale.departments where load_date='2013-07-25'")

    var joined_df = prod_df.select("product_id","product_category_id","product_name","product_price","load_date").
      join(cat_df.select("category_id","category_department_id","category_name"), prod_df("product_category_id") === cat_df("category_id")).
      join(dept_df.select("department_id","department_name"), cat_df("category_department_id") === dept_df("department_id"))

    joined_df.select("product_id","product_category_id","product_name","product_price","category_id","category_department_id","category_name","department_id","department_name","load_date").
      coalesce(1).write.format("com.databricks.spark.csv").
      option("header", "false").
      save("/user/tbhangale/sqoop_import/retail_db/transform/joined_from_jar/load_date=2013-07-25")
  }
}
