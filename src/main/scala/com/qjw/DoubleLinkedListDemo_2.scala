package com.qjw

import scala.util.control._

/**
  *
  *
  * @author : qjw
  * @data : 2019/6/3
  */
object DoubleLinkedListDemo {
  def main(args: Array[String]): Unit = {
    val h1 = new HeroNode(1,"宋江1","及时雨1")
    val h2 = new HeroNode(1,"宋江2","及时雨2")
    val h3 = new HeroNode(1,"宋江3","及时雨3")
    val h4 = new HeroNode(1,"宋江4","及时雨4")

    val doubleLinkedList = new DoubleLinkedList
    doubleLinkedList.add(h1)
    doubleLinkedList.add(h2)
    doubleLinkedList.add(h3)
    doubleLinkedList.add(h4)
    doubleLinkedList.list()
  }
}

class DoubleLinkedList {
  // 头结点
  val head = new HeroNode(0, "", "")
  val loop = new Breaks

  // add
  def add(newHero: HeroNode): Unit = {
    // 尾结点
    var tmp = head
    loop.breakable {
      while (true){
        if (tmp.next == null){
          loop.break()
        }
        tmp = tmp.next
      }
    }
    tmp.next = newHero
    newHero.pre = tmp
  }


  //遍历
   def list(): Unit ={
     if (head.next == null){
       println("list is null!")
       return
     }
     // 尾结点
     var tmp = head
     loop.breakable {
       while (true){
         if (tmp.next != null){
           println(tmp.name,"--",tmp.nickName)
         }else{
           loop.break()
         }
         tmp = tmp.next
       }
     }

   }
}

class HeroNode(hNo: Int, hName: String, hNickName: String) {
  var no: Int = hNo
  var name: String = hName
  var nickName: String = hNickName

  var pre: HeroNode = null
  var next: HeroNode = null
}
