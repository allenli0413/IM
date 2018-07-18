package com.liyh.im.datas.db

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 18 日
 * @time  09 时 54 分
 * @descrip :
 */
data class ContactModel(val map: MutableMap<String, Any?>) {
    var _id by map
    var name by map
}