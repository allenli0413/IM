package com.liyh.im.extentions

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 06 日
 * @time  17 时 59 分
 * @descrip :
 */
fun String.isValidUserName(): Boolean = this.matches(Regex("^[a-zA-Z]\\w{2,19}$"))

fun String.isValidPassword(): Boolean = this.matches(Regex("^[0-9]{3,30}$"))