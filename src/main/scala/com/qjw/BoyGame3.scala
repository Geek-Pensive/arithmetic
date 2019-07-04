package com.qjw

import scala.util.control.Breaks

/**
  *
  *
  * @author : qjw
  * @data : 2019/6/4 
  */
object BoyGame3 {

  def main(args: Array[String]): Unit = {
    val boyCircle = new BoyCircle
    boyCircle.addBoy(7)
    boyCircle.showBoy()

    boyCircle.suicide(4, 3, 7)

  }
}

class BoyCircle {
  var first: Boy = new Boy(-1)
  val loop = new Breaks

  /**
    * 批量添加
    *
    * @param nums
    */
  def addBoy(nums: Int): Unit = {
    if (nums < 1) {
      println("num < 1")
      return
    }

    var curBoy: Boy = null
    for (i <- 1 to nums) {
      val boy: Boy = new Boy(i)
      // 形成环形
      if (1 == i) {
        first = boy
        first.next = first
        curBoy = first
      } else {
        curBoy.next = boy
        boy.next = first
        curBoy = boy
      }
    }
  }

  /**
    * 遍历
    */
  def showBoy(): Unit = {
    if (first.next == null) {
      println("no node")
      return
    }

    var cur = first
    loop.breakable {
      while (true) {
        if (cur.next == first) {
          loop.break()
        }
        printf("no=%d\n", cur.no)
        cur = cur.next
      }
    }
    printf("no=%d\n", cur.no)
  }

  /**
    * 自杀游戏：从第startNo个开始，每数countNum，该人自杀
    *
    * @param startNo
    * @param countNum
    * @param nums
    */
  def suicide(startNo: Int, countNum: Int, nums: Int) {
    if (first.next == null || startNo < 1 || startNo > nums) {
      println("参数有误，请重新输入")
      return
    }

    //1. 用 hepler 保存first的前一个node
    var helper = first
    loop.breakable {
      while (true) {
        if (helper.next == first) {
          loop.break()
        }
        helper = helper.next
      }
    }

    // 2.找到开始节点
    for (i <- 0 until startNo - 1) {
      first = first.next
      helper = helper.next
    }

    // 3.计数自杀
    loop.breakable {
      while (true) {
        if (helper == first) {
          loop.break()
        }
        for (i <- 0 until countNum - 1) {
          first = first.next
          helper = helper.next
        }

        // 自杀
        printf("自杀的人：no=%d\n", first.no);
        first = first.next
        helper.next = first
      }
    }
    printf("最后的人：no=%d\n", first.no)


  }
}

class Boy(bNo: Int) {
  val no: Int = bNo
  var next: Boy = null
}