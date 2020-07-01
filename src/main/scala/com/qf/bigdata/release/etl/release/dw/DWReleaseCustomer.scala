/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: DWReleaseCustomer
 * Author: yanglan88
 * Date: 2020/7/1 15:52
 * History:
 * <author> <time> <version>
 * 作者姓名 修改时间 版本号 描述
 */


/**
 * @author yanglan88
 * @create 2020/7/1
 * @since 1.0.0
 */
package com.qf.bigdata.release.etl.release.dw

import com.qf.bigdata.release.util.SparkHelper
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.slf4j.{Logger, LoggerFactory}

object DWReleaseCustomer {

    val logger :Logger = LoggerFactory.getLogger(DWReleaseCustomer.getClass)

    def handlerCustomerJob(appName:String,begin:String,end:String) = {
        var spark:SparkSession = null

        try{

            val conf = new SparkConf()
                .set("hive.exec.dynamic.partition", "true")
                .set("hive.exec.dynamic.partition.mode", "nonstrict")
                .set("spark.sql.shuffle.partitions", "32")
                .set("hive.merge.mapfiles", "true")
                .set("hive.input.format", "org.apache.hadoop.hive.ql.io.CombineHiveInputFormat")
                .set("spark.sql.autoBroadcastJoinThreshold", "50485760")
                .set("spark.sql.crossJoin.enabled", "true")
                .setAppName(appName)
                .setMaster("local[4]")

            val spark :SparkSession = SparkHelper.createSpark(conf)




        }catch{
            case e:Exception => logger.error(e.toString,e)
        }finally{
            if (spark != null){
                spark.stop()
            }
        }
    }

    def main(args: Array[String]): Unit = {
        //val Array(appName, bdp_day_begin, bdp_day_end) = args

        val appName: String = "dw_release_customer_job"
        val bdp_day_begin:String = "20191125"
        val bdp_day_end:String = "20191125"


    }
}
