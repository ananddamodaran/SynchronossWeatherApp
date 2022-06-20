package dev.anand.synchronossweatherapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dev.anand.synchronossweatherapp.util.Constants.DATABASE_NAME
import timber.log.Timber

@Database(
    entities = [
        WeatherInfoEntity::class
    ], version = 1
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherInfoDao(): WeatherInfoDao

    companion object {

        @Volatile
        private var instance: WeatherDatabase? = null

        fun getInstance(context: Context): WeatherDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }


        private fun buildDatabase(context: Context): WeatherDatabase {
            Timber.d("buildDatabase")
            return Room.databaseBuilder(context, WeatherDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {

                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Timber.d("Callback onCreate")
                          /*  val request = OneTimeWorkRequestBuilder<UpdateWeatherWorker>()
                                .setInputData(workDataOf(KEY_FILENAME to WEATHER_DATA_FILENAME))
                                .build()
                            WorkManager.getInstance(context).enqueue(request)*/
                        }
                    }
                )
                .build()
        }
    }
}