package com.qjw

import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks

/**
  *
  *
  * @author : qjw
  * @data : 2019/6/20 
  */
object BinarySearch_10 {


  def main(args: Array[String]): Unit = {
    /*val array = Array(1,2,3,4,5,6,7,8,9)
    val index = binarySearch2(array, 0, array.length - 1, 3)
    println(index)*/
    val array = Array(1, 2, 3, 5, 5, 5, 5, 5, 7, 8, 9)
    // 注意返回的不一定有序：ArrayBuffer(4, 3, 5, 6, 7)
    var arrayBuffer = binarySearch3(array, 0, array.length - 1, 5)
//    arrayBuffer = arrayBuffer.sortBy(x => x)
    println(arrayBuffer)

  }

  /**
    * 非递归版本
    *
    * @param array
    * @param left
    * @param right
    * @param key
    * @return
    */
  def binarySearch(array: Array[Int], left: Int, right: Int, key: Int): Int = {
    var l = left
    var r = right
    while (l <= r) {
      val midIndex = (l + r) / 2
      val midValue = array(midIndex)
      if (key < midValue) {
        r = midIndex - 1
      } else if (key > midValue) {
        l = midIndex + 1
      } else {
        return midIndex
      }
    }
    -1
  }

  /**
    * 递归版本
    *
    * @param array
    * @param left
    * @param right
    * @param key
    * @return
    */
  def binarySearch2(array: Array[Int], left: Int, right: Int, key: Int): Int = {
    if (left > right) {
      return -1
    }

    val midIndex = (left + right) / 2
    val midValue = array(midIndex)
    if (key < midValue) {
      binarySearch(array, left, midIndex - 1, key)
    } else if (key > midValue) {
      binarySearch(array, midIndex + 1, right, key)
    } else {
      return midIndex
    }
  }


  /**
    * 存在多个相同的值，返回索引数组
    *
    * @param array
    * @param left
    * @param right
    * @param key
    * @return
    */
  def binarySearch3(array: Array[Int], left: Int, right: Int, key: Int): ArrayBuffer[Int] = {
    var loop = new Breaks
    if (left > right) {
      return ArrayBuffer()
    }

    val midIndex = (left + right) / 2
    val midValue = array(midIndex)
    if (key < midValue) {
      binarySearch3(array, left, midIndex - 1, key)
    } else if (key > midValue) {
      binarySearch3(array, midIndex + 1, right, key)
    } else {
      // 向左查找相同的值
      var tempIndex = midIndex - 1
      var res = ArrayBuffer[Int]()
      loop.breakable {
        while (true) {
          if (tempIndex >= 0 && array(tempIndex) == key) {
            res.append(tempIndex)
            tempIndex -= 1
          } else {
            loop.break()
          }
        }
      }

      // 加入midIndex
      res.append(midIndex)

      // 向右查找相同的值
      tempIndex = midIndex + 1
      loop.breakable {
        while (true) {
          if (tempIndex <= array.length - 1 && array(tempIndex) == key) {
            res.append(tempIndex)
            tempIndex += 1
          } else {
            loop.break()
          }
        }
      }

      return res
      //      return midIndex
    }
  }
}
