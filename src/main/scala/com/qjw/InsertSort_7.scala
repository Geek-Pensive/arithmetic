package com.qjw

/**
  * 插入排序：5, 2, 4, 7,  3
  * 第一遍：(5), 2, 4, 7, 3
  * 第二遍：(2, 5), 4, 7, 3
  * 第三遍：(2, 4, 5), 7, 3
  * 第四遍：(2, 4, 5, 7), 3
  * 第五遍：(2, 3,  4, 5, 7)
  * ()是一个有序表
  *
  *   排序算法稳定性：假设待排序序列中有两个元素相等，而且在排序前和排序后两个相等的元素的相对位置不变，即有 a = b，排序前a在b前面，那么排序后，a还是要在b前面。排序算法的稳定性是要看具体的算法实现，
  *   比如一般情况下，直接选择排序，快速排序，希尔排序，堆排序都不是稳定排序算法，基数排序，计数排序，归并排序，插入排序，冒泡排序都是稳定排序算法。
  * @author : qjw
  * @data : 2019/6/18 
  */
object InsertSort_7 {

  def main(args: Array[String]): Unit = {
    val array = Array(5, 2, 4, 7, 3, 6, 1, 8)

    /*// 要插入的值
    val insertValue = array(1)
    // 要插入的位置，默认是【有序表】的最后一个元素的下标
    var insertIndex = 1 - 1

    // 有插入的位置， 且要插入的值 小于【有序表】的最后一个元素
    while (insertIndex >= 0 && insertValue < array(insertIndex)) {
      // 原位置的数据后移一位
      array(insertIndex + 1) = array(insertIndex)
      insertIndex -= 1  // 插入的位置-1，保证其可推出循环
    }

    // 进行插入
    array(insertIndex + 1) = insertValue*/

    insertSort(array)
    println(array.mkString("  "))
  }


  def insertSort(array: Array[Int]): Unit = {
    // 遍历无序的数据
    for (i <- 1 until array.length){
      // 要插入的值
      val insertValue = array(i)
      // 要插入的位置，默认是【有序表】的最后一个元素的下标
      var insertIndex = i - 1

      /**
        * 找到插入的位置，且将后面的元素全部后移
        */
      // 有插入的位置， 且要插入的值 小于【有序表】insertIndex的元素
      while (insertIndex >= 0 && insertValue < array(insertIndex)) {
        // 原位置的数据后移一位
        array(insertIndex + 1) = array(insertIndex)
        insertIndex -= 1  // 插入的位置-1，保证其可推出循环
      }

      // 进行插入
      array(insertIndex + 1) = insertValue
    }

  }

}
