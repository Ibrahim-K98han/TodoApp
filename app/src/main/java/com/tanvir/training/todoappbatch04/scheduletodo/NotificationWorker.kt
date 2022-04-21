package com.tanvir.training.todoappbatch04.scheduletodo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.tanvir.training.todoappbatch04.R

class NotificationWorker
    (val context:Context, workerParameters: WorkerParameters)
    :Worker(context,workerParameters){

    override fun doWork(): Result {
        val name = inputData.getString("name")
        setNotification(name)
        return Result.success()
    }

    private fun setNotification(name: String?) {
        val CHANNEL_ID = "my_channel"
        val builder = NotificationCompat.Builder(context,CHANNEL_ID)
            .setVibrate(listOf(1L, 2L, 3L).toLongArray())
            .setSmallIcon(R.drawable.notification)
            .setContentTitle("Todo Alert!!!")
            .setContentText("It's time for $name")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val manager:NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val nName = "test"
            val descriptionText = "this channel sends todo scheduling notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID,nName,importance).apply {
                description = descriptionText
            }
            manager.createNotificationChannel(channel)
        }
        manager.notify(1,builder.build())
    }

}