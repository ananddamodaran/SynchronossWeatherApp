package dev.anand.synchronossweatherapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.anand.synchronossweatherapp.data.db.CurrentWeatherEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CurrentWeatherEntity(
    @PrimaryKey
    val dt: Long,
    val name: String,
    val description: String

) {
    companion object {
        const val TABLE_NAME = "weather"
    }
}
