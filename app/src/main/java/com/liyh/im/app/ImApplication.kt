package com.liyh.im.app

import android.app.ActivityManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.multidex.MultiDexApplication
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import cn.bmob.v3.Bmob
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMOptions
import com.hyphenate.chat.EMTextMessageBody
import com.liyh.im.BuildConfig
import com.liyh.im.R
import com.liyh.im.adapter.MessageListenerAdapter
import com.liyh.im.ui.activity.ChatActivity

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 06 日
 * @time  17 时 21 分
 * @descrip :
 */
class ImApplication : MultiDexApplication() {

    val soundPool = SoundPool(2, AudioManager.STREAM_MUSIC, 0)
    val duan by lazy { soundPool.load(instance, R.raw.duan, 0) }
    val yulu by lazy { soundPool.load(instance, R.raw.yulu, 0) }
    val messageListener = object : MessageListenerAdapter() {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            //如果在前台播放短声音， 在后台播放长声音
            if (isForeground()) {
                soundPool.play(duan, 1f, 1f, 0, 0, 1f)
            } else {
                soundPool.play(yulu, 1f, 1f, 0, 0, 1f)
                showNotication(p0)
            }
        }
    }

    /**
     * 展示通知栏
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotication(p0: MutableList<EMMessage>?) {
        val noticationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        p0?.forEach {
            var msgText = getString(R.string.no_text_message)
            if (it.type == EMMessage.Type.TXT) {
                msgText = (it.body as EMTextMessageBody).message
            }
            val intent = Intent(this, ChatActivity::class.java).putExtra("userName", it.conversationId())
            val taskStackBuilder = TaskStackBuilder.create(this).addParentStack(ChatActivity::class.java).addNextIntent(intent)
            val pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            val notificationChannel = NotificationChannel("channel_01", "lyh", NotificationManager.IMPORTANCE_DEFAULT)
            notificationChannel.apply {
                description = "liyanhui"
                enableLights(true)
                lightColor = Color.RED
                //震动
                enableVibration(true)
                vibrationPattern = longArrayOf(100L, 200L, 300L, 400L, 500L, 400L, 300L, 200L, 400L)
            }
            noticationManager.createNotificationChannel(notificationChannel)
//            val pendingIntent = PendingIntent.getActivity(this,0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            val notification = NotificationCompat.Builder(instance, "channel_01")
                    .setContentTitle(getString(R.string.receive_new_message))
                    .setContentText(msgText)
                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.avatar1))
                    .setSmallIcon(R.mipmap.ic_funchat)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build()
            noticationManager.notify(0, notification)
        }
    }

    /**
     * 判断是否在前台
     */
    private fun isForeground(): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.runningAppProcesses.forEach {
            if (it.processName == packageName) {
                return it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            }
        }
        return false
    }

    companion object {
        lateinit var instance: ImApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        EMClient.getInstance().init(applicationContext, EMOptions())
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)
        Bmob.initialize(applicationContext, "5f656277545c7f0153cc37478476771b")
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }

    override fun onTerminate() {
//        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
        super.onTerminate()
    }
}