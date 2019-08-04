package com.gaurav.androidworkmanagerexample

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class TestWorker(context: Context, parameter: WorkerParameters) : Worker(context, parameter) {
    override fun doWork(): Result {
        var dataString = inputData.getString("data_to_send")
        Log.v("Test Worker :", "Worker executed with data :"+dataString)

        return Result.success()
    }
}