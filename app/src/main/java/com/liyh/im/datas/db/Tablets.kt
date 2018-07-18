package com.liyh.im.datas.db

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 17 日
 * @time  18 时 19 分
 * @descrip :1.定义了一个ContactsTable类
 * 2。创建了一个ContactsTable实例，可以通过类名直接访问实例，实现单例的一种方式
 */
object ContactsTable{
    val NAME = "contact"

    //定义字段
    val ID = "_id"
    val CONTACT = "name"
}