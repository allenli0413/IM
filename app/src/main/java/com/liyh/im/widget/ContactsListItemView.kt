package com.liyh.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.liyh.im.R
import com.liyh.im.datas.ContactsItemModel
import kotlinx.android.synthetic.main.view_contact_item.view.*

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 16 日
 * @time  11 时 50 分
 * @descrip :
 */
class ContactsListItemView : RelativeLayout {
    fun setData(itemModel: ContactsItemModel) {
        if (itemModel.isShowFirstLetter) {
            firstLetter.visibility = View.VISIBLE
            firstLetter.text = itemModel.firstLetter
        } else {
            firstLetter.visibility = View.GONE
        }
        userName.text = itemModel.contactName
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.view_contact_item, this)
    }
}