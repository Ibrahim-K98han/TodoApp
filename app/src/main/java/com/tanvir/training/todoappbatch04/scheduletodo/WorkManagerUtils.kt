package com.tanvir.training.todoappbatch04.scheduletodo

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class WorkManagerUtils(val context: Context, val name:String,val delay:Long) {
    fun schedule(){
//        val constant = Constraints.Builder()
//            .setRequiresCharging(true)
//            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .setRequiresDeviceIdle(false)
//            .build()

        val request = OneTimeWorkRequestBuilder<NotificationWorker>()
            //.setConstraints(constant)
            .setInputData(workDataOf("name" to name))
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .addTag(name)
            .build()

        WorkManager.getInstance(context).enqueue(request)
    }

}