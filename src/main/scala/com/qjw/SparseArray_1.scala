package com.qjw

import scala.collection.mutable.ArrayBuffer

/**
  * scala 中没有 static 关键字对于一个class来说，所有的方法和成员变量在实例被 new 出来之前都是无法访问的
  * 因此class文件中的main方法也就没什么用了，
  * scala object 中所有成员变量和方法默认都是 static 的所以 可以直接访问main方法。
  *
  * 稀疏数组
  *
  * @author : qjw
  * @data : 2019/6/1 
  */
object SparseArray_1 {

  def main(args: Array[String]): Unit = {

    // 1.初始化2维棋盘
    val rowSize = 11;
    val colSize = 11;
    val chessMap = Array.ofDim[Int](rowSize, colSize)
    chessMap(1)(2) = 1 // 1表示白棋
    chessMap(2)(3) = 2 // 2表示黑棋

    /*for (raw <- chessMap) {
      for (elem <- raw) {
        printf("%d\t", elem)
      }
      println()
    }*/

    // 2.保存为稀疏数组
    val sparseArray = ArrayBuffer[Node]()
    val countNode = new Node(rowSize, colSize, 0)
    sparseArray.append(countNode)
    for (i <- 0 until chessMap.length) {
      for (j <- 0 until chessMap(i).length) {
        if (0 != chessMap(i)(j)) {
          val nodeTmp = new Node(i, j, chessMap(i)(j))
          sparseArray.append(nodeTmp)
        }
      }
    }

    for (elem <- sparseArray) {
      println(elem.row, elem.col, elem.value)
    }

    // 3.恢复
    val chessMap2 = Array.ofDim[Int](sparseArray(0).row, sparseArray(0).col)

    for (i <- 1 until sparseArray.length) {
      chessMap2(sparseArray(i).row)(sparseArray(i).col) = sparseArray(i).value
    }

    for (raw <- chessMap2) {
      for (elem <- raw) {
        printf("%d\t", elem)
      }
      println()
    }
  }

}

class Node(val row: Int, val col: Int, val value: Int)
