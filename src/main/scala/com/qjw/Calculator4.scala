package com.qjw

import scala.util.control.Breaks

/**
  *
  *
  * @author : qjw
  * @data : 2019/6/5 
  */
object Calculator {

  def main(args: Array[String]): Unit = {
    val loop = new Breaks
    val expression = "300+4*10-2+22"
    val numStack = new Stack(10)
    val operStack = new Stack(10)

    var n1 = 0
    var n2 = 0
    var oper = 0
    var res = 0
    var ch = ' '
    var index = 0
    var multiBitNum = ""

    // 计算优先级高的运算
    loop.breakable {
      while (true) {
        ch = expression.substring(index, index + 1)(0)

        // 是操作符
        if (isOper(ch)) {
          /**
            * 如果当前符号的优先级，小于等于符号栈栈顶的符号的优先级，
            * 则出栈
            * 并从数栈出栈两个数据，进行运算。将结果重新push进数栈
            * 将当前符号push进符号栈
            */
          if (!operStack.isEmpty()) {
            if (getPriority(ch) <= getPriority(operStack.stackArr(operStack.top))) {
              oper = operStack.pop()
              n1 = numStack.pop()
              n2 = numStack.pop()
              res = calculate(n1, n2, oper)
              numStack.push(res)
            }
          }

          if (!operStack.isFull()) {
            operStack.push(ch)
          }
        } else { // 是数
          multiBitNum += ch

          // 已经到最后
          if (index == expression.length - 1){
            numStack.push(multiBitNum.toInt)
          }else{
            // 没到最后，探测下一位是不是数字
            val nextBitNum = expression.substring(index+1,index+2)(0)
            if (isOper(nextBitNum.toInt)){
              numStack.push(multiBitNum.toInt)
              multiBitNum = ""
            }
          }

        }

        index += 1
        if (index >= expression.length) {
          loop.break()
        }
      }

    }

    // 优先级相同的运算
    loop.breakable {
      while (true) {
        // 操作符栈为空，则返回
        if (operStack.isEmpty()) {
          loop.break()
        }
        oper = operStack.pop()
        n1 = numStack.pop()
        n2 = numStack.pop()
        res = calculate(n1, n2, oper)
        numStack.push(res)
      }
    }
    printf("表达式 %s = %d", expression, numStack.pop())
  }

  /**
    * 返回符号的优先级
    *
    * @param oper
    * @return
    */
  def getPriority(oper: Int): Int = {
    if (oper == '*' || oper == '/') {
      return 1
    } else if (oper == '+' || oper == '-') {
      return 0
    } else {
      return -1
    }
  }

  /**
    * 两个数运算
    *
    * @param n1
    * @param n2
    * @param oper
    */
  def calculate(n1: Int, n2: Int, oper: Int): Int = {
    var res = 0
    oper match {
      case '+' => {
        res = n1 + n2
      }
      case '-' => {
        res = n2 - n1
      }
      case '*' => {
        res = n1 * n2
      }
      case '/' => {
        res = n2 / n1
      }
    }
    res
  }

  def isOper(value: Int): Boolean = {
    value == '+' || value == '-' || value == '*' || value == '/'
  }

}

class Stack(size: Int) {

  val maxSize = size
  var stackArr = new Array[Int](maxSize)
  var top = -1

  def isFull(): Boolean = {
    top == maxSize - 1
  }

  def isEmpty(): Boolean = {
    top == -1
  }

  def push(value: Int): Unit = {
    if (isFull()) {
      println("栈已满")
      return
    }
    top += 1
    stackArr(top) = value
  }

  def pop(): Int = {
    if (isEmpty()) {
      print("栈为空")
      return -1
    }
    val res = stackArr(top)
    top -= 1
    return res
  }

  def list(): Unit = {
    if (isEmpty()) {
      print("栈为空")
      return
    }
    for (i <- 0 to top reverse) {
      printf("stack[%d]=%d\n", i, stackArr(i))
    }
  }


}