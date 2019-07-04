package com.qjw

import java.text.SimpleDateFormat
import java.util.Date

/**
  * 冒泡排序，8W数据10秒
  * 选择排序，8W数据2秒
  *
  * @author : qjw
  * @data : 2019/6/11 
  */
object BubbleSort6 {

  def main(args: Array[String]): Unit = {
    var arr = new Array[Int](80000)
    val random = new util.Random()
    for (i <- 0 until 80000) {
      arr(i) = random.nextInt(10000000)
    }
    println("排序前：")
    val now: Date = new Date()
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = dateFormat.format(now)
    println("排序前时间：" + date)
    println(arr.mkString(","))

    println("排序后：")
    selectSort(arr)
    val end: Date = new Date()
    val endDate = dateFormat.format(end)
    println("排序前时间：" + endDate)
    println(arr.mkString(","))

  }

  def selectSort(arr: Array[Int]): Unit = {
    for (i <- 0 until arr.length - 1) {
      var min = arr(i)
      var minIndex = i

      for (j <- i + 1 until arr.length) {
        if (arr(j) < min) {
          min = arr(j)
          minIndex = j
        }
      }

      if (minIndex != i){
        // 交换
        arr(minIndex) = arr(i)
        arr(i) = min
      }
    }
  }

  def bubbleSort(arr: Array[Int]): Unit = {
    for (i <- arr.indices) {
      for (j <- 0 until arr.length - 1 - i) {
        if (arr(j) > arr(j + 1)) {
          val t = arr(j)
          arr(j) = arr(j + 1)
          arr(j + 1) = t
        }
      }
    }
  }

}
