package com.qjw

/**
  * 迷宫问题：最小路径，递归、回溯
  *
  * 初始化参数：
  * 0：可以走（还没走）
  * 1：墙
  * 2：可以走（已经走了）
  * 3：已经走过，但走不通
  *
  * @author : qjw
  * @data : 2019/6/10
  */
object Maze_3 {


  def main(args: Array[String]): Unit = {
    val rawNum = 8
    val colNum = 7
    var map = init(rawNum, colNum)
    prin(map)

    findWay(map, 1, 1)


    println("new map:")
    prin(map)


  }

  /**
    * 搜索优先级：下 > 右 > 上 > 左
    *
    * @param map
    * @param i
    * @param j
    * @return
    */
  def findWay(map: Array[Array[Int]], i: Int, j: Int): Boolean = {
    // 右下角已经走了
    if (map(map.length - 2)(map(0).length - 2) == 2) {
      true
    } else { // 没走到右下角
      // 当前没还走
      if (map(i)(j) == 0) {

        // 递归回溯
        map(i)(j) = 2 // 假设该点可以走通，不一定
        if (findWay(map, i + 1, j)) { // 往下找
          true
        } else if (findWay(map, i, j + 1)) { // 往右找
          true
        } else if (findWay(map, i - 1, j)) { // 往上找
          true
        } else if (findWay(map, i, j - 1)) { // 往左找
          true
        } else {
          // 走不通,回溯
          map(i)(j) = 3
          false
        }
      } else {
        // 当前为 1 、 2 、3
        false
      }
    }

  }

  def prin(map: Array[Array[Int]]): Unit = {
    for (i <- 0 until map.length) {
      for (j <- 0 until map(i).length) {
        print(map(i)(j) + "\t")
      }
      println()
    }
  }

  def init(rawNum: Int, colNum: Int): Array[Array[Int]] = {
    val map = Array.ofDim[Int](rawNum, colNum)

    // 初始化第一行和最后一行
    for (i <- 0 until colNum) {
      map(0)(i) = 1
      map(rawNum - 1)(i) = 1
    }

    // 初始化第一列和最后一列
    for (i <- 0 until rawNum) {
      map(i)(0) = 1
      map(i)(colNum - 1) = 1
    }

    // 其他挡板
    map(3)(1) = 1
    map(3)(2) = 1
    map(3)(3) = 1

    /*map(1)(4) = 1
    map(2)(4) = 1*/

    return map
  }

}
