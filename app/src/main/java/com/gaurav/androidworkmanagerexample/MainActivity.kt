package com.gaurav.androidworkmanagerexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var data = workDataOf("data_to_send" to "Input Data")

        var constraint: Constraints = Constraints.Builder().apply {
            setRequiredNetworkType(NetworkType.CONNECTED)
        }.build()

        var oneTimeWorkRequest: OneTimeWorkRequest = OneTimeWorkRequestBuilder<TestWorker>()
            .setInitialDelay(1, TimeUnit.SECONDS)
            .setInputData(data)
            .setConstraints(constraint).build()

        var periodicWorkRequest: PeriodicWorkRequest = PeriodicWorkRequestBuilder<TestWorker>(16, TimeUnit.MINUTES)
            .setInputData(data)
            .setConstraints(constraint).build()

        WorkManager.getInstance().enqueueUniqueWork("one_time_work_request", ExistingWorkPolicy.KEEP, oneTimeWorkRequest)

        WorkManager.getInstance().enqueueUniquePeriodicWork("periodic_work_request", ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequest)
    }
}
