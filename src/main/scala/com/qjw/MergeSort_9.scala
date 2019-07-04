package com.qjw

/**
  * 归并排序
  *
  * @author : qjw
  * @data : 2019/6/20
  */
object MergeSort_9 {

  def main(args: Array[String]): Unit = {
    val array = Array(5, 2, 4, 7)
    val temp = new Array[Int](array.length)
    mergeSort(array, 0, array.length - 1, temp)
    println(array.mkString("  "))
  }

  def mergeSort(array: Array[Int], left: Int, right: Int, temp: Array[Int]): Unit = {
    if (left < right) {
      val mid = (left + right) / 2
      // 分
      mergeSort(array, left, mid, temp)
      mergeSort(array, mid + 1, right, temp)
      // 治：排序合并
      merge(array,left,mid,right,temp)
    }
  }

  def merge(array: Array[Int], left: Int,mid:Int, right: Int, temp: Array[Int]): Unit = {
    var l = left
    var midPuls1 = mid + 1
    var tempIndex = 0
    while(l <= mid && midPuls1 <= right){
      // 两个部分进行逐个比较
      // 若第一部分较小，则把第一部分放入临时数组
      if (array(l) <= array(midPuls1)){
        temp(tempIndex) = array(l)
        // 填充临时数组的下一个位置
        tempIndex += 1
        l += 1
      }else{
        temp(tempIndex) = array(midPuls1)
        // 填充临时数组的下一个位置
        tempIndex += 1
        midPuls1 += 1
      }

    }

    // 左边的有序列表还有剩余数据（剩余有序）
    while (l <= mid){
      temp(tempIndex) = array(l)
      l += 1
      tempIndex += 1
    }

    // 右边的有序列表还有剩余数据（剩余有序）
    while (midPuls1 <= right){
      temp(tempIndex) = array(midPuls1)
      tempIndex += 1
      midPuls1 +=1
    }

    // 将本次的完成归并的temp数组，
    tempIndex = 0
    var tempLeft = left
    while(tempLeft < right){
      array(tempLeft) = temp(tempIndex)
      tempIndex += 1
      tempLeft += 1
    }
  }
}
