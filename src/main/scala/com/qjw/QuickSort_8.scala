package com.qjw

import scala.util.control.Breaks

/**
  * 快速排序：
  *   最坏情况运行时间为θ（n2），且最坏情况发生在每次划分过程产生的两个区间分别包含n-1个元素和1个元素的时候。时间复杂度为o（n2）。
  *   由于快速排序法也是基于比较的排序法，其运行时间为Ω（nlogn)，所以如果每次划分过程产生的区间大小都为n/2，则运行时间θ（nlogn）就是最好情况运行时间。
  *   快速排序不是一种稳定的排序算法，也就是说，多个相同的值的相对位置也许会在算法结束时产生变动。
  * 参考：https://baike.baidu.com/item/%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F%E7%AE%97%E6%B3%95/369842?fromtitle=%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F&fromid=2084344&fr=aladdin
  *
  * @author : qjw
  * @data : 2019/6/18 
  */
object QuickSort_8 {

  def main(args: Array[String]): Unit = {
        val array = Array(5,2,4,7,8,3,6,1,8)
//    val array = Array(-9, 78, 0, 23, -567, 70)

    quickSort(0, array.length - 1, array)
    println(array.mkString("  "))
  }

  /**
    * 快速排序
    *
    * @param left  要排序的最左边index
    * @param right 要排序的最右边index
    * @param array 要排序的数组
    */
  def quickSort(left: Int, right: Int, array: Array[Int]): Unit = {
    val loop = new Breaks
    var l = left
    var r = right
    // 以中间的值为基准
//    val pivot = array((left + right) / 2)
    val pivot = array(left)

    loop.breakable {
      while (l < r) {
        // 从左边找一个比基准大的数，对应的下标l
        while (array(l) < pivot) {
          l += 1
        }
        // 从右边找一个比基准小的数，对应的下标r
        while (array(r) > pivot) {
          r -= 1
        }
        // 如果有一边没找到，导致下标相遇，则退出
        if (l >= r) {
          loop.break()
        }

        // 找到r、l后交换arr(l)和arr(r)
        val temp = array(l)
        array(l) = array(r)
        array(r) = temp

        /**
          * 一下三句代码可以提升效率，可以删除
          */
        // 交换后，如果这个数（较小的）和pivot相等
        if (array(l) == pivot) {
          r -= 1
        }
        if (array(r) == pivot) {
          l += 1
        }
      }
    }

    if (l == r) {
      l += 1
      r -= 1
    }

    // 左半部分递归
    if (left < r) {
      quickSort(left, r, array)
    }
    // 右半部分递归
    if (right > l) {
      quickSort(l, right, array)
    }

  }
}
