package com.wiktor.udemyprorazdel9servicces

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class MyService : Service() {
//    1. Создать класс  MyService
//    2. Унаследоваться от Service(android.app.Service)
//    3. Переопределить пустой конструктор Service() (добавить скобки)
//    4. переопределить методы onBind

// Жизненный цикл состоит из 3-х методов:
//    onCreate() - вызывается при создании сервиса
//    onDestroy() - вызывается при уничтожении сервиса
//    onStartCommand() - здесь выпроляется вся работа

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        log("MyService", "onCreate", "starts")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("MyService", "onStartCommand", "starts")

        coroutineScope.launch {
            for (i in 0 until 100) {
                delay(1000)
                log("MyService", "onStartCommand", "timer $i")
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        log("MyService", "onDestroy", "starts")
        super.onDestroy()
        coroutineScope.cancel()
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    // Для удобства создаем метод который будет выводитьв лог сообщения
    private fun log(className: String, methodName: String, message: String) {
        Log.i("qwertyu", "Класс $className Метод $methodName  message = $message")
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, MyService::class.java)
        }

    }
}