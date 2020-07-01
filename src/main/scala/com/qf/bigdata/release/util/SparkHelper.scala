/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SparkHelper
 * Author: yanglan88
 * Date: 2020/7/1 15:37
 * History:
 * <author> <time> <version>
 * 作者姓名 修改时间 版本号 描述
 */


/**
 * @author yanglan88
 * @create 2020/7/1
 * @since 1.0.0
 */
package com.qf.bigdata.release.util

import com.qf.bigdata.release.etl.release.udf.QFUdf
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * 工具类
 */
object SparkHelper {

    def createSpark(conf :SparkConf) :SparkSession = {
        val spark :SparkSession = SparkSession.builder
            .config(conf)
            .enableHiveSupport()
            .getOrCreate();

        //加载自定义函数
        registerFun(spark)

        spark
    }

    /**
     * udf注册
     * @param spark
     */
    def registerFun(spark: SparkSession):Unit={

        //时间段
        spark.udf.register("getTimeSegment", QFUdf.getTimeSegment _)

        //年龄段
        spark.udf.register("getAgeRange", QFUdf.getAgeRange _)

        spark.udf.register("getNewSource", QFUdf.getNewSource _)
    }

}
