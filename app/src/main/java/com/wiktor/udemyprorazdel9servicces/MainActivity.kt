package com.wiktor.udemyprorazdel9servicces

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.wiktor.udemyprorazdel9servicces.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.i("qwertyu", "onCreate")

        binding.simpleService.setOnClickListener {
            Log.i("qwertyu", "Click")
            startService(MyService2.newIntent(this, 25))
        }

        binding.foregroundService.setOnClickListener {
            showNotification()
            showNotificationAPI26()
        }
    }

    private fun showNotification() {
        //создаем уведомление. передать контекст
        //необходимо установить заголовок .setContentTitle("Title")
        //необходимо установить текст сообщения .setContentText("Текст сообщения")
        //необходимо установить картинку .setSmallIcon(R.drawable.ic_launcher_background)
        //вызвать метод .build()

        val notification = Notification.Builder(this)
            .setContentTitle("Title")
            .setContentText("Текст сообщения")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()

        // теперь нужно показать его пользователю, за это отвечает класс notificationManager
        // getSystemService возвращает тип object поэтому его нужно привести к типу NotificationManager
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        // теперь можно отображать уведомления. передать в него:
        // id  уведомления. Если бы id было разным значением, то показывалась бы куча сообщений.
        // С одинаковым id показывается только одно уведомление
        // само уведомление
        notificationManager.notify(1, notification)

    }

    private fun showNotificationAPI26() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        // Начиная с API 26 - любое уведомление должно показываться в каком-то канале. Для любого уведомления должен быть создан
        // Для того что бы создать NotificationChannel нужно мин api - 26( в приложении мин api 23). Поэтому необходимо добавить проверку

//        нужно передать :
//        id channel_id
//        имя канала channel_name
//        приоритет NotificationManager.IMPORTANCE_DEFAULT
        val notificationChannel =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationChannel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                // Создаем канал
                notificationManager.createNotificationChannel(notificationChannel)
            } else {

            }

        // id канала нужно передать в Notification.Builder  Notification.Builder(this, CHANNEL_ID)
        // здесь возникает ошибка, можно засунуть сюда проверку как и при создании notificationChannel и передавать либо только сонтекст либо контекст и CHANNEL_ID
        // или
        // Использовать класс NotificationCompat вместо Notification. в NotificationCompat уже используется эта проверка

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Title 222")
            .setContentText("Текст сообщения 222")
            .setSmallIcon(R.drawable.ic_android_black_24dp)
            .build()

        notificationManager.notify(2, notification)

    }

    companion object {
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
    }
}